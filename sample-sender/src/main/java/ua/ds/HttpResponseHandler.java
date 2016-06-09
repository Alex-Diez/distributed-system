package ua.ds;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.util.Optional;

class HttpResponseHandler
        implements ResponseHandler<Optional<String>> {

    private final String topology;

    public HttpResponseHandler(Configuration conf) {
        this.topology = conf.topology.substring(1);
    }

    @Override
    public Optional<String> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        int responseStatus = response.getStatusLine().getStatusCode();
        if (responseStatus == 400) {
            return Optional.of("Topology name was not set, please set topology name by '--topology' flag");

        }
        if (responseStatus == 404) {
            return Optional.of(String.format("Topology '%s' was not found in sample receiver", topology));
        }
        return Optional.empty();
    }
}
