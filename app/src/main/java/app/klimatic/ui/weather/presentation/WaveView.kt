package app.klimatic.ui.weather.presentation

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Handler
import app.klimatic.R
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * This is library implementation of Wave View
 * Wave view is structured on the Sine Wave formula
 * The sine wave formula is :
 *
 *
 * y(t) = A sin(2πft + ρ) = A sin(ωt + ρ)
 *
 *
 * The above formula can be explained in sound terms as follows:
 *
 *
 * y = amplitude X sin ( 2π ( velocity of rotation in cycles per second))
 *
 *
 * Increasing the amplitude of the sine wave, how high the tops and bottoms of the wave go, increases the volume.
 * Increasing or decreasing the cycle rate, how many cycles over distance/time, increases and decreases the pitch of the sound – how high or low the tone sounds.
 */
class WaveView : View {

    /**
     * To run animation in a infinite loop handler and runnable is used
     * In every 16ms difference the wave is updated
     */
    private var animationHandler: Handler? = null
    private var mContext: Context? = null

    /**
     * @width - Device screen width
     */
    private var viewWidth = 0

    /**
     * x, y1, y2 are used to plot the path for wave
     */
    var x1 = 0f
    var y1 = 0f
    var y2 = 0f
    private var firstWaveColor: Paint? = null
    private var secondWaveColor: Paint? = null

    /**
     * @frequency - Then less the frequency more will be the number of
     * waves
     */
    var frequency1 = 240
    var frequency2 = 300

    /**
     * @amplitude - Amplitude gives the height of wave
     */
    var amplitude = 10
    private var shift1 = 0f
    private var shift2 = 0f
    private var quadrant = 0
    var firstWavePath = Path()
    var secondWavePath = Path()

    /**
     * @speed - Its the velocity of wave in x-axis
     */
    private val speed1 = 0.1.toFloat()
    private val speed2 = 0.15.toFloat()

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            var attributes: TypedArray? = null
            try {
                attributes = getContext().obtainStyledAttributes(
                    attrs,
                    R.styleable.WaveView,
                    0,
                    0
                )
                val colorOne = attributes.getInt(
                    R.styleable.WaveView_wave_color_one,
                    context.resources.getColor(R.color.blue_500)
                )
                val colorTwo = attributes.getInt(
                    R.styleable.WaveView_wave_color_two,
                    context.resources.getColor(R.color.blue_700)
                )
                mContext = context
                firstWaveColor = Paint()
                firstWaveColor!!.isAntiAlias = true
                firstWaveColor!!.strokeWidth = 2f
                firstWaveColor!!.color = colorTwo
                secondWaveColor = Paint()
                secondWaveColor!!.isAntiAlias = true
                secondWaveColor!!.strokeWidth = 2f
                secondWaveColor!!.color = colorOne
            } finally {
                attributes!!.recycle()
            }
        }
        animationHandler = Handler(Looper.getMainLooper())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        quadrant = height / 3
        viewWidth = width
        firstWavePath.moveTo(0f, height.toFloat())
        secondWavePath.moveTo(0f, height.toFloat())
        firstWavePath.lineTo(0f, quadrant * 1.9f)
        secondWavePath.lineTo(0f, quadrant * 2.0f)
        var i = 0
        while (i < viewWidth + 10) {
            x1 = i.toFloat()
            y1 = quadrant * 1.9f + amplitude * Math.sin((i + 10) * Math.PI / frequency1 + shift1)
                .toFloat()
            y2 = quadrant * 2.0f + amplitude * Math.sin((i + 10) * Math.PI / frequency2 + shift2)
                .toFloat()
            firstWavePath.lineTo(x1, y1)
            secondWavePath.lineTo(x1, y2)
            i += 10
        }
        firstWavePath.lineTo(width.toFloat(), height.toFloat())
        secondWavePath.lineTo(width.toFloat(), height.toFloat())
        canvas.drawPath(firstWavePath, firstWaveColor!!)
        canvas.drawPath(secondWavePath, secondWaveColor!!)
    }

    inner class WaveRunnable : Runnable {
        /**
         * This runnable helps to run animation in an infinite loop
         */
        override fun run() {
            firstWavePath.reset()
            secondWavePath.reset()
            shift1 += speed1 / 2
            shift2 += speed2 / 2
            invalidate()
            animationHandler!!.postDelayed(runnable, 16)
        }
    }

    var runnable = WaveRunnable()

    fun setLifecycle(lifecycle: Lifecycle) {
        val observer = WaveViewLifeCycleObserver()
        observer.setCallback(object : PlayPauseCallback {
            override fun play() {
                animationHandler!!.postDelayed(runnable, 16)
            }

            override fun pause() {
                animationHandler!!.removeCallbacks(runnable)
            }
        })
        observer.registerLifecycle(lifecycle)
    }

    class WaveViewLifeCycleObserver : LifecycleObserver {

        private var callback: PlayPauseCallback? = null

        fun setCallback(callback: PlayPauseCallback?) {
            this.callback = callback
        }

        fun registerLifecycle(lifecycle: Lifecycle) {
            lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        private fun onResume() {
            callback!!.play()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        private fun onPause() {
            callback!!.pause()
        }
    }

    interface PlayPauseCallback {
        fun play()
        fun pause()
    }
}