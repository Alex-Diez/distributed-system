package ua.ds;

import java.util.Optional;

public class SampleReceiver {

    private final TopologyRepository repository;

    public SampleReceiver(TopologyRepository repository) {
        this.repository = repository;
    }

    public void putIntoBus(String topology, Sample sample) {
        Optional<Topology> t = repository.get(topology);
        if (!t.isPresent()) {
            throw new TopologyNotFoundException();
        }
    }
}
