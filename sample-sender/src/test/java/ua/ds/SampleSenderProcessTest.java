package ua.ds;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
public class SampleSenderProcessTest {

    private static HttpServerMock mockServer;

    @BeforeClass
    public static void startProxy() {
        mockServer = new HttpServerMock(1090);
    }

    @Test
    public void sendSampleWithDefaultConfiguration() throws Exception {
        mockServer.setupExpectation("topology", CREATED_201);

        SampleSender.main(new String[] {"--topology", "topology"});

        mockServer.verificationOf("POST", "/topology", 1);
    }

    @Test
    public void sendSampleFiveTimes() throws Exception {
        mockServer.setupExpectation("topology", CREATED_201);

        SampleSender.main(new String[] {"--count", "5", "--topology", "topology"});

        mockServer.verificationOf("POST", "/topology", 5);
    }

    @Test
    public void sendSampleOnNotExisted() throws Exception {
        mockServer.setupExpectation("not-existed", NOT_FOUND_404);

        SampleSender.main(new String[] {"--count", "2", "--topology", "not-existed"});

        mockServer.verificationOf("POST", "/not-existed", 1);
    }
    
    @Test
    public void sendSampleWithoutTopology() throws Exception {
        mockServer.setupExpectation("", BAD_REQUEST_400);

        SampleSender.main(new String[] {"--count", "2"});

        mockServer.verificationOf("POST", "/", 1);
    }

    @After
    public void tearDown() throws Exception {
        mockServer.resetServerState();
    }

    @AfterClass
    public static void stopProxy() {
        mockServer.stopServer();
    }
}
