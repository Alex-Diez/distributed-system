package ua.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TopologyRepository {
    private final Map<String, Topology> topologies = new HashMap<>();

    public Optional<Topology> get(String topology) {
        return Optional.ofNullable(topologies.get(topology));
    }

    public void createTopology(String topology) {
        topologies.put(topology, new Topology(topology));
    }
}
