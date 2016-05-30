package ua.ds;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SampleDataGeneratorTest {

    @Test
    public void generateZeroData() throws Exception {
        SampleDataGenerator generator = new SampleDataGenerator(0);

        assertThat(generator.next(), is(0));
        assertThat(generator.next(), is(0));
    }

    @Test
    public void generateDataBetweenZeroAndFive_inRobinWay() throws Exception {
        SampleDataGenerator generator = new SampleDataGenerator(5);

        assertThat(generator.next(), is(0));
        assertThat(generator.next(), is(1));
        assertThat(generator.next(), is(2));
        assertThat(generator.next(), is(3));
        assertThat(generator.next(), is(4));
        assertThat(generator.next(), is(0));
    }
    
    @Test
    public void generateDataInSpecifiedRange() throws Exception {
        SampleDataGenerator generator = new SampleDataGenerator(1, 5);

        assertThat(generator.next(), is(1));
        assertThat(generator.next(), is(2));
        assertThat(generator.next(), is(3));
        assertThat(generator.next(), is(4));
        assertThat(generator.next(), is(1));
    }
}
