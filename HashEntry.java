import java.util.*;
import java.util.Arrays;

public class HashEntry {
    private ArrayList<Integer> entry;
    int p = 40487, a = 13, b = 1207;
    int length = 0;

    public HashEntry(int m) {
        this.length = m*m;
        Integer[] keyAr = new Integer[m*m];
        Arrays.fill(keyAr, -1);
        entry = new ArrayList<Integer>(Arrays.asList(keyAr));
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

    public void print() {
        for (int i = 0; i < length; i++) {
            System.out.format("%s ", entry.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] temp = {659, 24, 704};

        HashEntry h = new HashEntry(temp.length);

        h.print();
        h.getParameters();
    }
}
