package app.klimatic.dispatchers

import app.klimatic.FileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessResponseDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(200)
            .setBody(FileReader.readStringFromFile("success_response.json"))
    }
}
