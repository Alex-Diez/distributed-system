package ua.ds;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class SampleSender {

    private final HttpClient client;
    private final ResponseHandler<Optional<String>> handler;
    private final HttpHost target;
    private final HttpPost request;
    private final int counts;

    public SampleSender(HttpClient client, Configuration conf) {
        this.client = client;
        this.counts = conf.counts;
        this.handler = new HttpResponseHandler(conf.topology);
        this.target = new HttpHost(conf.host, conf.port, "http");
        this.request = new HttpPost("/submit" + "/" + conf.topology);
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = new Parser().parse(args);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            new SampleSender(client, conf).start();
        }
    }

    public Optional<String> send() throws IOException {
        return client.execute(target, request, handler);
    }

    private void start() throws IOException {

        for (int i = 0; i < counts; i++) {
            Optional<String> errorMessage = send();
            errorMessage.ifPresent(System.out::println);
            if (errorMessage.isPresent()) {
                return;
            }
        }
    }
}
