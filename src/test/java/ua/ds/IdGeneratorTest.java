package ua.ds;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IdGeneratorTest {

    @Test
    public void nextIdIncreasedOnOne() throws Exception {
        IdGenerator generator = new IdGenerator(1);

        assertThat(generator.next(), is(0));
        assertThat(generator.next(), is(1));
    }

    @Test
    public void nextIdIncreasedOnTen() throws Exception {
        IdGenerator generator = new IdGenerator(10);

        assertThat(generator.next(), is(0));
        assertThat(generator.next(), is(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stepCanNotBeZero_throwsException() throws Exception {
        IdGenerator generator = new IdGenerator(0);
    }
}
