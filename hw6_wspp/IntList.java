import java.util.Arrays;

public class IntList {
    private int size;
    private int[] values;
    private int[] inx;

    public IntList() {
        this.values = new int[100];
        this.inx = new int[100];
        this.size = 0;
    }
    public int getSize() {
        return size;
    }

    public void expand() {
        this.values = Arrays.copyOf(values, values.length * 2);
        this.inx = Arrays.copyOf(inx, inx.length * 2);
    }

    public void insert(int value) {
        if (size == values.length) {
            expand();
        }
        values[size] = value;
        size++;
    }

    public void insert(int valueFir, int valueSec) {
        if (size == values.length) {
            expand();
        }
        values[size] = valueFir;
        inx[size] = valueSec;
        size++;
    }

    public int[] getArr() {
        return values;
    }

    public int[] getInx() {
        return inx;
    }
}

