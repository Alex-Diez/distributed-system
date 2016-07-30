package ua.ds;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Category(IntegrationTest.class)
public class SampleReceiverRestApiTest {

    @Test
    public void createTopology() throws Exception {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpHost target = new HttpHost("localhost", 1090, "http");
            HttpPost createTopologyRequest = new HttpPost("/topology");
            HttpResponse createTopologyResponse = client.execute(target, createTopologyRequest);

            assertThat(createTopologyResponse.getStatusLine().getStatusCode(), is(201));
        }
    }
}
