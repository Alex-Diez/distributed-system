package ua.ds;

public class SampleDataGenerator {
    private final int from;
    private final int till;
    private int current;

    public SampleDataGenerator(int from, int till) {
        this.from = from;
        this.till = till;
        this.current = from;
    }

    public SampleDataGenerator(int limit) {
        this(0, limit);
    }

    public int next() {
        int data = current;
        current += 1;
        if (current >= till) {
            current = from;
        }
        return data;
    }
}
