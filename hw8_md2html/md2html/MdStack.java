package md2html;

import java.util.Arrays;

public class MdStack {
    private int[] startInx;
    private String[] mdElem;
    private int size;

    public MdStack() {
        this.startInx = new int[100];
        this.mdElem = new String[100];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getLastInx() {
        return startInx[size - 1];
    }

    public String getLastMd() {
        return mdElem[size - 1];
    }

    public String getFirstMd() {
        return mdElem[0];
    }

    private void expand() {
        this.startInx = Arrays.copyOf(startInx, startInx.length * 2);
        this.startInx = Arrays.copyOf(startInx, mdElem.length * 2);
    }

    public void insert(int start, String mdElem) {
        if (size == startInx.length) {
            expand();
        }
        this.startInx[size] = start;
        this.mdElem[size] = mdElem;
        size++;
    }

    public void pop() {
        size--;
    }
}
