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
import java.util.Optional;

public class SampleSender {

    private final HttpClient client;
    private final ResponseHandler<Optional<String>> handler;
    private final Configuration conf;
    private final HttpHost target;
    private final HttpPost request;

    public SampleSender(HttpClient client, ResponseHandler<Optional<String>> handler, Configuration conf) {
        this.client = client;
        this.handler = handler;
        this.conf = conf;
        this.target = new HttpHost(conf.host, conf.port, "http");
        this.request = new HttpPost("/submit" + conf.topology);
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = new Parser().parse(args);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            new SampleSender(
                    client,
                    new HttpResponseHandler(conf),
                    conf
            ).start();
        }
    }

    public Optional<String> send() throws IOException {
        return client.execute(target, request, handler);
    }

    private void start() throws IOException {

        for (int i = 0; i < conf.counts; i++) {
            Optional<String> receivedMessage = send();
            receivedMessage.ifPresent(System.out::println);
            if (receivedMessage.isPresent()) {
                return;
        }
    }
}
}
