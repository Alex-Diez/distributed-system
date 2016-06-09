package ua.ds;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InputParameterParserTest {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @Test
    public void createDefaultConfiguration() throws Exception {
        Configuration conf = parser.parse(new String[] {});
        assertThat(conf.host, is("localhost"));
        assertThat(conf.port, is(1090));
        assertThat(conf.topology, is("/"));
        assertThat(conf.counts, is(1));
        assertThat(conf.frequency, is(0));
        assertThat(conf.frequencyPeriod, is('s'));
    }

    @Test
    public void hostDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--host", "host-A"});
        assertThat(conf.host, is("host-A"));
    }

    @Test
    public void portDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--port", "1080"});
        assertThat(conf.port, is(1080));
    }

    @Test
    public void topologyDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--topology", "topology-A"});
        assertThat(conf.topology, is("/topology-A"));
    }

    @Test
    public void countsDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--count", "5"});
        assertThat(conf.counts, is(5));
    }

    @Test
    public void frequencyDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--frequency", "1", "--frequency-period", "m"});
        assertThat(conf.frequency, is(1));
    }

    @Test
    public void frequencyPeriodDefinedInParams() throws Exception {
        Configuration conf = parser.parse(new String[] {"--frequency", "1", "--frequency-period", "m"});
        assertThat(conf.frequencyPeriod, is('m'));
    }
}
