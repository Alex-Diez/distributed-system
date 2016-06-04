package ua.ds;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SampleSender {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 1090;
        int counts = 1;
        String topology = "/";
        for (int i = 0; i < args.length; i += 2) {
            if ("--host".equals(args[i])) {
                host = args[i + 1];
            }
            else if ("--port".equals(args[i])) {
                port = Integer.parseInt(args[i + 1]);
            }
            else if ("--count".equals(args[i])) {
                counts = Integer.parseInt(args[i + 1]);
            }
            else if ("--topology".equals(args[i])) {
                topology = "/" + args[i + 1];
            }
        }

        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpHost target = new HttpHost(host, port, "http");
            HttpRequest request = new HttpPost("/submit" + topology);

            for(int i = 0; i < counts; i++) {
                HttpResponse response = client.execute(target, request);
                if (response.getStatusLine().getStatusCode() != 201) {
                    break;
                }
            }
        }
    }
}
