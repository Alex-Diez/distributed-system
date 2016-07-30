package ua.ds;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Topology {
    private final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private final String name;

    public Topology(String name) {
        this.name = name;
    }

    public void addNode(int nodeId) {
        graph.computeIfAbsent(nodeId, HashSet::new);
    }

    public boolean addNode(int nodeId, int parentNodeId) {
        Map<Integer, Set<Integer>> copy = new HashMap<>(graph);
        copy.computeIfAbsent(parentNodeId, HashSet::new).add(nodeId);
        if (hasCycle(copy, nodeId) || hasCycle(copy, parentNodeId)) {
            return false;
        }
        graph.computeIfAbsent(parentNodeId, HashSet::new).add(nodeId);
        return true;
    }

    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int vertex) {
        return new DepthFirstSearchOfCycle().hasCycle(graph, vertex);
    }

    public Iterable<Integer> adjacentTo(int nodeId) {
        return graph.getOrDefault(nodeId, Collections.emptySet());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null
                && obj.getClass().equals(getClass())) {
            Topology topology = (Topology)obj;
            return topology.name.equals(name);
        }
        return false;
    }

    private class DepthFirstSearchOfCycle {
        private final Set<Integer> marked = new HashSet<>();
        private final Set<Integer> onStack = new HashSet<>();
        private boolean hasCycle = false;

        boolean hasCycle(Map<Integer, Set<Integer>> graph, int vertex) {
            depthFirstSearch(graph, vertex);
            return hasCycle;
        }

        private void depthFirstSearch(Map<Integer, Set<Integer>> graph, int vertex) {
            marked.add(vertex);
            onStack.add(vertex);
            for (int w : graph.getOrDefault(vertex, Collections.emptySet())) {
                if (hasCycle) return;
                if (!marked.contains(w)) {
                    depthFirstSearch(graph, w);
                }
                else if (onStack.contains(w)) {
                    hasCycle = true;
                }
            }
        }
    }
}
