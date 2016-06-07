package ua.ds;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@Path("/")
public class TopologyManager {
    private final Set<String> topologies = new HashSet<>();

    @POST
    @Path("/{topology}")
    public Response createTopology(@PathParam("topology") String topology) {
        if (topologies.contains(topology)) return Response.status(304).build();
        else {
            topologies.add(topology);
            return Response.status(201).build();
        }
    }

    @DELETE
    @Path("/{topology}")
    public Response removeTopology(@PathParam("topology") String topology) {
        topologies.remove(topology);
        return Response.ok().build();
    }
}
