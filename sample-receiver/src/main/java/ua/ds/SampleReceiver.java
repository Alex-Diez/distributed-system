package ua.ds;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/submit")
public class SampleReceiver {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receive(String sample) {

        return Response.status(400).build();
    }
}
