package ua.ds;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Category(IntegrationTest.class)
public class SampleReceiverTest {

    @Test
    @Ignore
    public void okStatusIfReceiveValidSample() throws Exception {
//        assertThat(receiver.receive(new Sample("topology", 0, 0)), is(200));
    }

    @Test
    @Ignore
    public void notFoundStatusIfSampleTopologyNotFound() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpHost target = new HttpHost("localhost", 1090, "http");
            HttpPost request = new HttpPost("/submit");
            JSONObject json = new JSONObject();
            json.put("topology", "not-existed");
            json.put("node-id", 0);
            json.put("data", 0);
            request.setEntity(new StringEntity(json.toString()));
            request.addHeader("content-type", "application/json");

            HttpResponse response = client.execute(target, request);
            assertThat(response.getStatusLine().getStatusCode(), is(404));
        }
    }

    @Test
    public void badDataStatusIfSampleDoesNotHaveTopologyName() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpHost target = new HttpHost("localhost", 1090, "http");
            HttpPost request = new HttpPost("/submit");
            JSONObject json = new JSONObject();
            json.put("node-id", 0);
            json.put("data", 0);
            request.setEntity(new StringEntity(json.toString()));
            request.addHeader("content-type", "application/json");

            HttpResponse response = client.execute(target, request);
            assertThat(response.getStatusLine().getStatusCode(), is(400));
        }
    }
}
