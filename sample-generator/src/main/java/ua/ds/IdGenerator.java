package ua.ds;

public class IdGenerator {

    private final int step;
    private int current;

    public IdGenerator(int step) {
        this(0, step);
    }

    public IdGenerator(int startWith, int step) {
        this.current = startWith;
        this.step = step;
    }

    public int next() {
        int next = current;
        current += step;
        return next;
    }
}
