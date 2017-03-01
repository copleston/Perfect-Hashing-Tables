import java.util.*;

public class HashTable {
    private ArrayList<HashEntry> table;
    private static int[] count = {1, 0, 3, 1, 0};
    int p = 40487, a = 13, b = 1207;

    public HashTable(int m, int[] count) {
        this.table = new ArrayList<HashEntry>(m);
        // Construct the inner hash tables, with the new lengths
        for (int i=0; i < m; i++) {
            table.add(i, new HashEntry(count[i]));
        }
    }

    public void insert(int key) {
        // find hash values
        // send hashed key to HashEntry function
    }

    public void delete(int key) {

    }

    public int locate(int key) {
        return 1;
    }

    public void printTable() {
        System.out.println();
        for (int i=0; i < table.size(); i++) {
            System.out.format("operation slot  %d:  ", i);
            table.get(i).print();
        }
    }

    public static void main(String[] args) {
        HashTable t = new HashTable(5, count);
        t.printTable();

    }
}
