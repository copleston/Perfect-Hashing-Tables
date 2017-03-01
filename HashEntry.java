import java.util.*;
import java.util.Arrays;

public class HashEntry {
    private ArrayList<Integer> entry, innerHash, keysIn;
    int slot;
    int p = 40487, a = 13, b = 1207;
    int length = 0;
    int[] count;
    int col;
    int cPairs;

    public HashEntry(int m, int slot) {
        this.length = m*m;
        this.slot = slot;
        Integer[] keyAr = new Integer[length];
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

    private void increment() {
        a = (a + 1) % p;
        if (a == 0) a++;
        b  = (b + 177) % p;
    }

    public int hash(int k) {
        return ((a*k + b) % p) % length;
    }

    private void outerHash(int m) {
        col = 0;
        count = new int[m];
        innerHash = new ArrayList<Integer>();

        for (Integer i : keysIn) {
            int h = hash(i);
            innerHash.add(h);
            count[h]++;
            if (count[h] > 1) col++;
        }
    }

    public void findHash(ArrayList<Integer> innerGrouping) {
        keysIn = innerGrouping;
        outerHash(length);
        while (col > 0) {
            increment();
            outerHash(length);
            // System.out.format("\nslot %d; NUMBER OF PAIRS OF COLLISIONS IN INNER HASH TABLE: %d", slot, col);
        }
        if (length > 1)
            System.out.format("slot %d; MODIFIED INNER HASH FUNCTION PARAMETERS: a = %d; b = %d; p %d\n\n", slot, a, b, p);

    }

    public void print() {
        for (int i = 0; i < length; i++) {
            System.out.format("%s ", entry.get(i));
        }
        System.out.println();
    }
}
