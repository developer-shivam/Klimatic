package app.klimatic.ui.currentweather.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import app.klimatic.R
import app.klimatic.dispatchers.EmptyJsonResponseDispatcher
import app.klimatic.dispatchers.EmptyResponseDispatcher
import app.klimatic.dispatchers.SuccessResponseDispatcher
import app.klimatic.ui.home.HomeActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private lateinit var scenario: ActivityScenario<HomeActivity>

    var mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun giveSuccessResponse_whenFetchWeather_shouldShowView() {

        // Set success response dispatcher
        mockWebServer.dispatcher = SuccessResponseDispatcher()

        // Launch the activity
        scenario = ActivityScenario.launch(HomeActivity::class.java)

        // launch activity creates fragment which fetches current weather
        scenario.onActivity { activity ->
            assertTrue(activity.supportFragmentManager.fragments[0].isResumed)
            assertTrue(activity.supportFragmentManager.fragments[0].isVisible)
        }

        onView(withId(R.id.currentWeather))
            .check(matches(isDisplayed()))

        scenario.close()
    }

    @Test
    fun giveEmptyResponse_whenFetchWeather_shouldNotShowView() {

        // Set empty response dispatcher
        mockWebServer.dispatcher = EmptyResponseDispatcher()

        // Launch the activity
        scenario = ActivityScenario.launch(HomeActivity::class.java)

        // launch activity creates fragment which fetches current weather
        scenario.onActivity { activity ->
            assertTrue(activity.supportFragmentManager.fragments[0].isResumed)
            assertTrue(activity.supportFragmentManager.fragments[0].isVisible)
        }

        onView(withId(R.id.currentWeather))
            .check(matches(not(isDisplayed())))

        scenario.close()
    }

    @Test
    fun giveEmptyJsonResponse_whenFetchWeather_shouldNotShowView() {

        // Set empty json response dispatcher
        mockWebServer.dispatcher = EmptyJsonResponseDispatcher()

        // Launch the activity
        scenario = ActivityScenario.launch(HomeActivity::class.java)

        // launch activity creates fragment which fetches current weather
        scenario.onActivity { activity ->
            assertTrue(activity.supportFragmentManager.fragments[0].isResumed)
            assertTrue(activity.supportFragmentManager.fragments[0].isVisible)
        }

        onView(withId(R.id.currentWeather))
            .check(matches(not(isDisplayed())))

        scenario.close()
    }
}
