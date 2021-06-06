package app.klimatic.ui

import android.os.Bundle
import android.widget.Toast
import app.klimatic.R
import app.klimatic.data.remote.CurrentWeatherService
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private var compositeDisposable: CompositeDisposable? = null

    @Inject
    lateinit var currentWeatherService: CurrentWeatherService

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(
               currentWeatherService.fetchCurrentWeather("Delhi")
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe({
                           Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT)
                                   .show()
                       }, {
                           println(it)
                       })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable?.isDisposed == false) {
            compositeDisposable?.dispose()
        }
    }
}