package ua.ds;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Category(UnitTest.class)
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

    @Test
    public void startWithNonZeroId() throws Exception {
        IdGenerator generator = new IdGenerator(5, 1);

        assertThat(generator.next(), is(5));
        assertThat(generator.next(), is(6));
    }
}
