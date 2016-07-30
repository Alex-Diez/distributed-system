package ua.ds;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Category(UnitTest.class)
public class TopologyCalculationTest {

    private Topology topology;
    private TopologyCalculator calculator;

    @Before
    public void setUp() throws Exception {
        topology = new Topology("topology");
        calculator = new TopologyCalculator();
    }

    @Test
    public void calculateAppliedSampleValues() throws Exception {
        topology.addNode(0);

        SampleGenerator sampleGenerator = new SampleGenerator(new IdGenerator(0), new SampleDataGenerator(1, 1));

        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 1);

        assertThat(calculator.calculate(topology, Collections.singletonList(sampleGenerator.sample())), is(result));
    }

    @Test
    public void sampleDataShouldAccumulateOnNode() throws Exception {
        topology.addNode(0);

        SampleGenerator sampleGenerator = new SampleGenerator(new IdGenerator(0), new SampleDataGenerator(1, 1));

        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 3);

        assertThat(calculator.calculate(topology, Arrays.asList(sampleGenerator.sample(), sampleGenerator.sample(), sampleGenerator.sample())), is(result));
    }

    @Test
    public void eachNodeAccumulateItsData() throws Exception {
        topology.addNode(0);
        topology.addNode(1);

        SampleGenerator sampleGenerator0 = new SampleGenerator(new IdGenerator(0), new SampleDataGenerator(1, 1));
        SampleGenerator sampleGenerator1 = new SampleGenerator(new IdGenerator(1, 0), new SampleDataGenerator(2, 2));

        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 1);
        result.put(1, 2);

        assertThat(calculator.calculate(topology, Arrays.asList(sampleGenerator1.sample(), sampleGenerator0.sample())), is(result));
    }

    @Test
    public void sampleDataShouldPropagateToParentNode() throws Exception {
        topology.addNode(0);
        topology.addNode(1, 0);

        SampleGenerator sampleGenerator = new SampleGenerator(new IdGenerator(0), new SampleDataGenerator(1, 1));

        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 3);
        result.put(0, 3);

        assertThat(calculator.calculate(topology, Arrays.asList(sampleGenerator.sample(),sampleGenerator.sample(), sampleGenerator.sample())), is(result));
    }
}
