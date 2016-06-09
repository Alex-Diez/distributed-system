package ua.ds;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpStatusCode;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.verify.VerificationTimes.exactly;

class HttpServerMock {
    private final MockServerClient mockServer;

    public HttpServerMock(int port) {
        mockServer = startClientAndServer(1090);
    }

    public void setupExpectation(String uri, HttpStatusCode expectedStatus) {
        mockServer.when(
                        request().withMethod("POST").withPath("/submit/" + uri)
                )
                .respond(
                        response().withStatusCode(expectedStatus.code())
                );
    }

    public void resetServerState() {
        mockServer.reset();
    }

    public void verificationOf(String method, String uri, int count) {
        mockServer.verify(request().withMethod(method).withPath("/submit" + uri), exactly(count));
    }

    public void stopServer() {
        mockServer.stop();
    }
}
