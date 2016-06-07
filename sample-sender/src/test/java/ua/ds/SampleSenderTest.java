package ua.ds;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpStatusCode;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.HttpStatusCode.BAD_REQUEST_400;
import static org.mockserver.model.HttpStatusCode.CREATED_201;
import static org.mockserver.model.HttpStatusCode.NOT_FOUND_404;
import static org.mockserver.verify.VerificationTimes.exactly;

@Category(UnitTest.class)
public class SampleSenderTest {

    private static MockServerClient mockServer;

    @BeforeClass
    public static void startProxy() {
        mockServer = startClientAndServer(1090);
    }

    private void makeExpectation(String topology, HttpStatusCode expectedStatus) {
        mockServer
                .when(
                        request().withMethod("POST").withPath("/submit/" + topology)
                )
                .respond(
                        response().withStatusCode(expectedStatus.code())
                );
    }

    @Test
    public void sendSampleWithDefaultConfiguration() throws Exception {
        makeExpectation("topology", CREATED_201);

        SampleSender.main(new String[] {"--topology", "topology"});

        mockServer.verify(request().withMethod("POST").withPath("/submit/topology"), exactly(1));
    }

    @Test
    public void sendSampleFiveTimes() throws Exception {
        makeExpectation("topology", CREATED_201);

        SampleSender.main(new String[] {"--count", "5", "--topology", "topology"});

        mockServer.verify(request().withMethod("POST").withPath("/submit/topology"), exactly(5));
    }

    @Test
    public void sendSampleOnNotExisted() throws Exception {
        makeExpectation("not-existed", NOT_FOUND_404);

        SampleSender.main(new String[] {"--count", "2", "--topology", "not-existed"});

        mockServer.verify(request().withMethod("POST").withPath("/submit/not-existed"), exactly(1));
    }
    
    @Test
    public void sendSampleWithoutTopology() throws Exception {
        makeExpectation("", BAD_REQUEST_400);

        SampleSender.main(new String[] {"--count", "2"});

        mockServer.verify(request().withMethod("POST").withPath("/submit/"), exactly(1));
    }

    @After
    public void tearDown() throws Exception {
        mockServer.reset();
    }

    @AfterClass
    public static void stopProxy() {
        mockServer.stop();
    }
}
