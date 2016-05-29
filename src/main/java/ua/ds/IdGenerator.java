package ua.ds;

public class IdGenerator {

    private final int step;
    private int current;

    public IdGenerator(int step) {
        if (step == 0) {
            throw new IllegalArgumentException();
        }
        this.step = step;
    }

    public int next() {
        int next = current;
        current += step;
        return next;
    }
}
