import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.util.*;

class PerfectHash {
    // Replace with ArrayList
    // private int[] keys;
    // private int[] values;
    private Vector<Integer> keys;
    private Vector values;

    private PerfectHash(int length) {
        if (length < 0)
          throw new IllegalArgumentException
            ("The initialSize is negative: " + length);

        System.out.println("SETTING HASH TABLE SIZE: " + length);

        // keys = new Vector(length);
        // values = new Vector(length);

        int[] keyAr = new int[length];
        Arrays.fill(keyAr, -1);
        System.out.println("Size: " + keyAr.length);
        // Arrays.fill(values, -1);

        // VECTOR:
        // create arrays of -1
        // copy values of arrays into the vectors
        keys = new Vector<Integer>(Arrays.asList(keyAr));
    }

    private static void printArray(Enumeration<Integer> inArray) {
        for (Integer i: inArray) System.out.format("%s ", i.toString());;
        System.out.println();
    }

    private void insert(int key) {
        // insert the element at index key
    }

    private void delete(int key) {
        // delete the element with key
    }

    private int locate(int key) {
        // locate the element with key
        return 1;
    }

    private static int hash(int k) {
        // ((a*k + b) % p) % m
        // p is prime
        // k < p
        // a >> {1 ... p-1}
        // b >> {0 ... p-1}
        return 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Initialise the file and scanner objects
        File file = new File("data.txt");
        Scanner sc = new Scanner(file);

        // set size of hash table, then advance the scanner onto next line
        int hashSize = sc.nextInt();
        PerfectHash p = new PerfectHash(hashSize);

        printArray(p.keys.elements());
        // p.keys.elements()
    }
}
