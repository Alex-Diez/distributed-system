package ua.ds;

public class Sample {
    final int id;
    final int data;

    public Sample(int id, int data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id) + Integer.hashCode(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && obj.getClass().equals(getClass())) {
            Sample sample = (Sample)obj;
            return sample.id == id
                    && sample.data == data;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[ Sample id - " + id + " data - " + data + " ]";
    }
}
