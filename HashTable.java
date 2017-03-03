import java.util.*;

public class HashTable {
    private ArrayList<HashEntry> table;
    private static int[] count = {1, 0, 3, 1, 0};
    int p = 40487, a = 13, b = 1207;
    int hashSize, outerIndex;

    public HashTable(ArrayList<ArrayList<Integer>> outerTable, int m, int[] count, int a, int b) {
        this.table = new ArrayList<HashEntry>(m);
        this.hashSize = m;
        this.a = a;
        this.b = b;
        // Construct the inner hash tables, with the new lengths
        for (int i=0; i < m; i++) {
            HashEntry temp = new HashEntry(count[i], i);
            temp.findHash(outerTable.get(i));
            table.add(i, temp);
            // for each hash entry, call hash
        }
    }

    public void operations(ArrayList<Command> commands) {
        for (Command i : commands) {
            i.execute();
            if ( i.getCom().equals("insert") ) {
                insert(i.getVar());
            } else if ( i.getCom().equals("locate") ) {
                locate(i.getVar());
            } else if ( i.getCom().equals("delete") ) {
                delete(i.getVar());
            } else {
                System.out.println("Unrecognised command");
            }
        }
    }

    private int hash(Integer k) {
        return ((a*k + b) % p) % hashSize;
    }

    public void insert(Integer key) {
        int h = hash(key);
        System.out.format("HASH KEY TO OUTER SLOT: %d, ", h);
        table.get(h).insert(key);
        printTable();
        System.out.println();
    }

    public void locate(Integer key) {
        int h = hash(key);
        int index = table.get(h).locate(key);
        if (index != -1) {
            System.out.format("LOCATED KEY = %d at: %d, %d\n", key, h, index);
        }
        System.out.println();
    }

    public void delete(Integer key) {
        int h = hash(key);
        int index = table.get(h).locate(key);
        if(index != -1) {
            System.out.format("LOCATED KEY = %d at: %d, %d\n", key, h, index);
            table.get(h).delete(key);
            printTable();
        } else {
            System.out.println("ERROR: invalid key");
        }
        System.out.println();
    }

    public void printTable() {
        for (int i=0; i < table.size(); i++) {
            System.out.format("operation slot  %d:  ", i);
            table.get(i).print();
        }
    }
}
