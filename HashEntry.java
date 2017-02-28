import java.util.*;

public class HashEntry {
    private Vector<Integer> outerTable, innerTable;
    int p = 40487, a = 13, b = 1207;
    int length = 0;

    public HashEntry(int m) {
        this.length = m;
    }

    public void getParameters() {
        System.out.format("MODIFIED INNER LEVEL HASH FUNCTION PARAMETERS: a = %d; b = %d; p = %d\n", a, b, p);
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int innerHash(int k) {
        return ((a*k + b) % p) % length;
    }

}
