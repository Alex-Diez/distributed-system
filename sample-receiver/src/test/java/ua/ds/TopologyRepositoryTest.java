package ua.ds;

import co.unruly.matchers.OptionalMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TopologyRepositoryTest {

    private TopologyRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new TopologyRepository();
    }

    @Test
    public void doesNotContain_whenTopologyWasNotCreated() throws Exception {
        assertThat(repository.get("topology"), OptionalMatchers.empty());
    }

    @Test
    public void contains_whenTopologyWasCreated() throws Exception {
        repository.createTopology("topology");

        assertThat(repository.get("topology"), OptionalMatchers.contains(new Topology("topology")));
    }
}
