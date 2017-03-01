import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.util.*;

class PerfectHash {
    int p = 40487, a = 13, b = 1207;
    int hashSize, col, cPairs;
    int[] count;
    private ArrayList<Integer> keysIn, outerHash;
    private ArrayList<ArrayList<Integer>> outerTable, innerTable;
    private Scanner sc;

    private PerfectHash(String fileName) {
        readFile(fileName);
        System.out.format("INITIAL OUTER HASH FUNCTION PARAMETERS: a = %d; b = %d; p = %d\n", a, b, p);

        findOuterHash();
        findGroupings();

        // printCount();

        HashTable ht = new HashTable(hashSize, count);
        ht.printTable();
        // createInnerTables();
    }

    private void readFile(String name) {
        // Initialise the file and scanner objects
        File file = new File(name);

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e);
        }
        // set size of hash table, then advance the scanner onto next line
        hashSize = sc.nextInt();

        if (hashSize < 0)
          throw new IllegalArgumentException
            ("The initialSize is negative: " + hashSize);

        System.out.println("SETTING HASH TABLE SIZE: " + hashSize);

        keysIn = new ArrayList<Integer>(); // Must specify Integer type to avoid compilaiton warnings
        while (sc.hasNext()) {
            keysIn.add(sc.nextInt());
        }
        sc.close();

        System.out.print("READ SET OF KEYS: ");
        printArray(keysIn);
    }

    private static void printArray(ArrayList inArray) {
        // for (Integer i: inArray.iterator()) System.out.format("%s ", i.toString());
        for (int i = 0; i < inArray.size(); i++) {
            System.out.format("%s ", inArray.get(i));
        }
        System.out.println();
    }

    private int hash(int k) {
        return ((a*k + b) % p) % hashSize;
    }

    private void increment() {
        a = (a + 1) % p;
        if (a == 0) a++;
        b  = (b + 177) % p;
    }

    private void outerHash(int m) {
        col = 1;
        count = new int[m];
        outerHash = new ArrayList<Integer>();

        for (Integer i : keysIn) {
            int h = hash(i);
            outerHash.add(h);
            count[h]++;
            if (count[h] > 1) col++;
        }
        cPairs = ((col*col)-col)/2;
    }

    private void findOuterHash() {
        int count = 0;

        System.out.print("HASHED TO OUTER HASH TABLE AT: ");
        outerHash(hashSize);
        printArray(outerHash);

        do {
            if (count > 0) increment();
            outerHash(hashSize);
            System.out.format("\nNUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: %d", cPairs);
            count++;
        } while (cPairs > hashSize);

        System.out.format("\n%d OUTER HASH FUNCTIONS TESTED\n", count);

        if (count > 1) {
            System.out.format("MODIFIED OUTER HASH FUNCTION PARAMETERS: a = %d; b = %d; p = %d\n", a, b, p);
            System.out.print("MODIFIED HASHING TO OUTER HASH TABLE AT: ");
            printArray(outerHash);
        }
    }

    private void printCount() {
        System.out.print("\nCollisions at: ");
        for (int i=0; i < hashSize; i++) {
            System.out.format("%d ", count[i]);
        }
        System.out.print("\n\n");
    }

    private void findGroupings() {
        // Initialise the outer hashtable: A collection of Vector objects
        outerTable = new ArrayList<ArrayList<Integer>>(hashSize);
        for (int i=0; i < hashSize; i++) {
            outerTable.add(i, new ArrayList<Integer>());
        }

        int index = 0;
        for (Integer i : keysIn) {                          // For each item in the input...
            outerTable.get( outerHash.get(index) ).add(i);  // Add to it's respective index, given in hte outerHash Vector
            index++;
        }
        printGroupings(); // Print the groupings of the outer table
    }

    private void printGroupings() {
        System.out.println("\nKEYS GROUPED ONTO SLOTS:");
        for (int i=0; i < hashSize; i++) {
            System.out.format("grouping slot  %d:  ", i);
            printArray(outerTable.get(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PerfectHash p = new PerfectHash("data2.txt"); // allows to read file
    }
}
