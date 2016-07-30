package ua.ds;

public class SampleGenerator {
    private final IdGenerator sampleIdGenerator;
    private final SampleDataGenerator dataGenerator;

    public SampleGenerator(IdGenerator sampleIdGenerator) {
        this(sampleIdGenerator, new SampleDataGenerator(1));
    }

    public SampleGenerator(IdGenerator sampleIdGenerator, SampleDataGenerator dataGenerator) {
        this.sampleIdGenerator = sampleIdGenerator;
        this.dataGenerator = dataGenerator;
    }

    public Sample sample() {
        int id = sampleIdGenerator.next();
        int data = dataGenerator.next();
        return new Sample(id, data);
    }
}
