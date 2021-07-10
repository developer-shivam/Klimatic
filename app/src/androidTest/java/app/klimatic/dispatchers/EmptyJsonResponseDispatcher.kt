package app.klimatic.dispatchers

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class EmptyJsonResponseDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(200)
            .setBody("{}")
    }
}
