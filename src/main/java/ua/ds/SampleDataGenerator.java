package ua.ds;

public class SampleDataGenerator {
    private final int limit;
    private int current;

    public SampleDataGenerator(int limit) {
        this.limit = limit;
    }

    public int next() {
        int data = current;
        current += 1;
        if (current >= limit) {
            current = 0;
        }
        return data;
    }
}
