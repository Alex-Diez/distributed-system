package ua.ds;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Category(UnitTest.class)
public class TopologyCreationTest {

    private Topology topology;

    @Before
    public void setUp() throws Exception {
        topology = new Topology("topology");
    }

    @Test
    public void topologyCantBeAcyclic() throws Exception {
        assertThat(topology.addNode(0, 1), is(true));
        assertThat(topology.addNode(1, 2), is(true));
        assertThat(topology.addNode(2, 0), is(false));
    }
    
}
