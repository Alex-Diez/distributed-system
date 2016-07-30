package ua.ds;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SampleReceiverTest {

    private SampleReceiver receiver;

    @Before
    public void setUp() throws Exception {
        TopologyRepository repository = new TopologyRepository();
        repository.createTopology("topology");
        receiver = new SampleReceiver(repository);
    }

    @Test
    public void putSampleIntoTheBus() throws Exception {
        receiver.putIntoBus("topology", new Sample(0, 0));
    }

    @Test(expected = TopologyNotFoundException.class)
    public void putSampleIntoTheBus_nonExistedTopology_shouldThrowException() throws Exception {
        receiver.putIntoBus("not-existed", new Sample(0, 0));
    }
}
