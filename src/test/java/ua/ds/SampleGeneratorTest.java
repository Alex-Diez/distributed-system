package ua.ds;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SampleGeneratorTest {

    private SampleGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new SampleGenerator(new IdGenerator(1));
    }

    @Test
    public void idempotentSampleGenerator() throws Exception {
        SampleGenerator sampleGenerator = new SampleGenerator(new IdGenerator(0));

        assertThat(sampleGenerator.sample(), is(new Sample(0, 0)));
        assertThat(sampleGenerator.sample(), is(new Sample(0, 0)));
    }

    @Test
    public void generateSampleWithNewId() throws Exception {
        assertThat(generator.sample(), is(new Sample(0, 0)));
        assertThat(generator.sample(), is(new Sample(1, 0)));
        assertThat(generator.sample(), is(new Sample(2, 0)));
    }

    @Test
    public void sampleDataShouldBeInRangeBetweenZeroAndFour() throws Exception {
        generator = new SampleGenerator(new IdGenerator(1), new SampleDataGenerator(5));

        assertThat(generator.sample(), is(new Sample(0, 0)));
        assertThat(generator.sample(), is(new Sample(1, 1)));
        assertThat(generator.sample(), is(new Sample(2, 2)));
        assertThat(generator.sample(), is(new Sample(3, 3)));
        assertThat(generator.sample(), is(new Sample(4, 4)));
        assertThat(generator.sample(), is(new Sample(5, 0)));
    }
}
