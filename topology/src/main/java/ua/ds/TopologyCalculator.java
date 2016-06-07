package ua.ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologyCalculator {

    public Map<Integer, Integer> calculate(Topology topology, List<Sample> samples) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Sample sample : samples) {
            visit(sample.id, topology, sample.data, result);
        }
        return result;
    }


    private void visit(int node, Topology topology, int data, Map<Integer, Integer> result) {
        result.compute(node, (key, value) -> value != null ? value + data : data);
        for (int vertex : topology.adjacentTo(node)) {
            visit(vertex, topology, data, result);
        }
    }
}
