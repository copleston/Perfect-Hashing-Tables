import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.util.*;

class PerfectHash {
    int p = 40487, a = 13, b = 1207;
    int hashSize, col, cPairs, sumPairs;
    int[] count;
    private ArrayList<Integer> keysIn, outerHash;
    private ArrayList<ArrayList<Integer>> outerTable, innerTable;
    private ArrayList<Command> commands;
    private Scanner sc;

    private PerfectHash(String keys, String commands) {
        readData(keys);
        // readCommands(commands);
        System.out.format("INITIAL OUTER HASH FUNCTION PARAMETERS: a = %d; b = %d; p = %d\n", a, b, p);

        findOuterHash();
        findGroupings();

        // printCount();

        HashTable ht = new HashTable(outerTable, hashSize, count);
        ht.printTable();
        // createInnerTables();
    }

    private void readData(String filename) {
        // Initialise the file and scanner objects
        File data = new File(filename);

        try {
            sc = new Scanner(data);
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

    private void readCommands(String filename) {
        // Instanciate the command container
        commands = new ArrayList<Command>();
        // Open and read the command file
        File data = new File(filename);
        try {
            sc = new Scanner(data);
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e);
        }
        // Read in commands and add to container
        while (sc.hasNext()) {
            // System.out.format("Command: %s \t Operator: %d\n", sc.next(), sc.nextInt());
            Command com = new Command(sc.next(), sc.nextInt());
            commands.add(com);
        }
        sc.close();

        for (int i=0; i < commands.size(); i++) {
            System.out.format("Command: %s \t Operator: %d\n", commands.get(i).com, commands.get(i).var);
        }
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
        a = (a + 1) % (p);
        if (a == 0) a++;
        b  = (b + 177) % (p);
    }

    private void outerHash(int m) {
        col = 0;
        count = new int[m];
        outerHash = new ArrayList<Integer>(m);
        cPairs = 0;

        for (Integer i : keysIn) {
            int h = hash(i);
            outerHash.add(h);
            count[h]++;
            if (count[h] > 1) col++;
        }

        // for each collision element. find the cPair of that element
        for (int j : count) {
            cPairs = ((col + 1) * col)/2;
        }

        cPairs = ((col + 1) * col)/2;
    }

    private void findOuterHash() {
        int count = 0;

        System.out.print("HASHED TO OUTER HASH TABLE AT: ");
        outerHash(hashSize);
        printArray(outerHash);
        printCount();
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
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PerfectHash p = new PerfectHash("data3.txt", "commands3.txt"); // allows to read file
    }
}
