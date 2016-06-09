package ua.ds;

import co.unruly.matchers.OptionalMatchers;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.ds.Configuration.Builder;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockserver.model.HttpStatusCode.BAD_REQUEST_400;
import static org.mockserver.model.HttpStatusCode.NOT_FOUND_404;
import static org.mockserver.model.HttpStatusCode.OK_200;

@Category(UnitTest.class)
public class SampleSenderTest {

    private static HttpServerMock mockServer;
    private CloseableHttpClient client;
    private Builder configurationBuilder;

    @BeforeClass
    public static void setUpEnv() throws Exception {
        mockServer = new HttpServerMock(1090);
    }

    @Before
    public void setUp() throws Exception {
        client = HttpClients.createDefault();
        configurationBuilder = new Builder();
    }

    @After
    public void tearDown() throws Exception {
        mockServer.resetServerState();
        client.close();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        mockServer.stopServer();
    }

    @Test
    public void responseHandlerShouldBeInvokedOnResponse() throws Exception {
        mockServer.setupExpectation("", OK_200);

        SpyResponseHandler handler = new SpyResponseHandler();
        SampleSender sampleSender = new SampleSender(client, handler, new Configuration.Builder().build());

        sampleSender.send();
        assertThat(handler.wasInvoked(), is(true));
    }

    @Test
    public void showsThatTopologyNameWasNotSetMessage() throws Exception {
        mockServer.setupExpectation("", BAD_REQUEST_400);

        Configuration conf = configurationBuilder.build();
        HttpResponseHandler handler = new HttpResponseHandler(conf);
        SampleSender sampleSender = new SampleSender(client, handler, conf);

        Optional<String> message = sampleSender.send();

        assertThat(message, OptionalMatchers.contains("Topology name was not set, please set topology name by '--topology' flag"));
    }

    @Test
    public void showsThatTopologyNameWasNotFoundMessage() throws Exception {
        mockServer.setupExpectation("/not-found-topology", NOT_FOUND_404);

        Configuration conf = configurationBuilder.withTopology("/not-found-topology").build();
        ResponseHandler<Optional<String>> handler = new HttpResponseHandler(conf);
        SampleSender sampleSender = new SampleSender(client, handler, conf);

        Optional<String> message = sampleSender.send();

        assertThat(message, OptionalMatchers.contains("Topology 'not-found-topology' was not found in sample receiver"));
    }

    private class SpyResponseHandler
            implements ResponseHandler<Optional<String>> {
        private boolean invoked;

        @Override
        public Optional<String> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            invoked = true;
            return null;
        }

        boolean wasInvoked() {
            return invoked;
        }
    }

}
