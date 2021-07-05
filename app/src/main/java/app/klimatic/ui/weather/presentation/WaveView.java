package app.klimatic.ui.weather.presentation;

/**
 * This is library implementation of Wave View
 * Wave view is structured on the Sine Wave formula
 * The sine wave formula is :
 * <p>
 * y(t) = A sin(2πft + ρ) = A sin(ωt + ρ)
 * <p>
 * The above formula can be explained in sound terms as follows:
 * <p>
 * y = amplitude X sin ( 2π ( velocity of rotation in cycles per second))
 * <p>
 * Increasing the amplitude of the sine wave, how high the tops and bottoms of the wave go, increases the volume.
 * Increasing or decreasing the cycle rate, how many cycles over distance/time, increases and decreases the pitch of the sound – how high or low the tone sounds.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import app.klimatic.R;

public class WaveView extends View {

    /**
     * To run animation in a infinite loop handler and runnable is used
     *  In every 16ms difference the wave is updated
     */
    private Handler handler;

    private Context mContext = null;

    /**
     * @width - Device screen width
     */
    private int width = 0;

    /**
     * x, y1, y2 are used to plot the path for wave
     */
    float x;
    float y1, y2;

    private Paint firstWaveColor;
    private Paint secondWaveColor;

    /**
     * @frequency - Then less the frequency more will be the number of
     *  waves
     */
    int frequency1 = 240;
    int frequency2 = 300;

    /**
     * @amplitude - Amplitude gives the height of wave
     */

    int amplitude = 10;
    private float shift1 = 0;
    private float shift2 = 0;

    private int quadrant;

    Path firstWavePath = new Path();
    Path secondWavePath = new Path();

    /**
     * @speed - Its the velocity of wave in x-axis
     */
    private float speed1 = (float) 0.1;
    private float speed2 = (float) 0.15;

    public WaveView(Context context) {
        super(context);
        init(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.WaveView, 0, 0);
            int height = attributes.getInt(R.styleable.WaveView_wave_height, 8);
//            if (height > 10) {
//                amplitude = 200;
//            } else {
//                amplitude = height * 10;
//            }
            float s = attributes.getFloat(R.styleable.WaveView_wave_speed, 0.5f);
//            if (s > 10) {
//                speed = (float) 0.25;
//            } else {
//                speed = s/8;
//            }
        }

        mContext = context;
        firstWaveColor = new Paint();
        firstWaveColor.setAntiAlias(true);
        firstWaveColor.setStrokeWidth(2);
        firstWaveColor.setColor(context.getResources().getColor(R.color.wave1));

        secondWaveColor = new Paint();
        secondWaveColor.setAntiAlias(true);
        secondWaveColor.setStrokeWidth(2);
        secondWaveColor.setColor(context.getResources().getColor(R.color.wave2));

        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(getContext().getResources().getColor(R.color.blue));
        quadrant = getHeight() / 3;
        width = canvas.getWidth();

        firstWavePath.moveTo(0, getHeight());
        secondWavePath.moveTo(0, getHeight());
        firstWavePath.lineTo(0, quadrant * 1.9f);
        secondWavePath.lineTo(0, quadrant * 2.0f);

        for (int i = 0; i < width + 10; i = i + 10) {
            x = (float) i;

            y1 = quadrant * 1.9f + amplitude * (float) Math.sin(((i + 10) * Math.PI / frequency1) + shift1);
            y2 = quadrant * 2.0f + amplitude * (float) Math.sin(((i + 10) * Math.PI / frequency2) + shift2);

            firstWavePath.lineTo(x, y1);
            secondWavePath.lineTo(x, y2);
        }
        firstWavePath.lineTo(getWidth(), getHeight());
        secondWavePath.lineTo(getWidth(), getHeight());
        canvas.drawPath(firstWavePath, firstWaveColor);
        canvas.drawPath(secondWavePath, secondWaveColor);
    }


    private class WaveRunnable implements Runnable {

        /**
         * This runnable helps to run animation in an infinite loop
         */
        @Override
        public void run() {
            firstWavePath.reset();
            secondWavePath.reset();
            shift1 = shift1 + speed1 / 2;
            shift2 = shift2 + speed2 / 2;
            invalidate();
            handler.postDelayed(runnable, 16);
        }
    }

    WaveRunnable runnable = new WaveRunnable();

    public void setLifecycle(Lifecycle lifecycle) {
        WaveViewLifeCycleObserver observer = new WaveViewLifeCycleObserver();
        observer.setCallback(new PlayPauseCallback() {
            @Override
            public void play() {
                handler.postDelayed(runnable, 16);
            }

            @Override
            public void pause() {
                handler.removeCallbacks(runnable);
            }
        });
        observer.registerLifecycle(lifecycle);
    }

    public static class WaveViewLifeCycleObserver implements LifecycleObserver {

        private PlayPauseCallback callback;

        public void setCallback(PlayPauseCallback callback) {
            this.callback = callback;
        }

        public void registerLifecycle(Lifecycle lifecycle) {
            lifecycle.addObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        private void onResume() {
            callback.play();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        private void onPause() {
            callback.pause();
        }
    }

    interface PlayPauseCallback {

        void play();

        void pause();
    }
}