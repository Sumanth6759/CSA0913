import java.util.*;

public class PharmacyInventory {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();

        String[] codes = {"MED103", "MED101", "MED105", "MED102", "MED104"};
        int[] stock = {25, 40, 15, 30, 20};

        for (int i = 0; i < codes.length; i++) {
            hashMap.put(codes[i], stock[i]);
            treeMap.put(codes[i], stock[i]);
            linkedMap.put(codes[i], stock[i]);
        }

        System.out.println("Q4 - Pharmacy Inventory");
        System.out.println("HashMap (no guaranteed order): " + hashMap);
        System.out.println("TreeMap (medicine-code order): " + treeMap);
        System.out.println("LinkedHashMap (insertion order): " + linkedMap);

        long start = System.nanoTime();
        int total = 0;
        for (int i = 0; i < 100000; i++) total += hashMap.get("MED103");
        long elapsed = System.nanoTime() - start;
        System.out.println("100,000 HashMap lookups: " + elapsed / 1_000_000.0 + " ms");
        System.out.println("Lookup check total: " + total);
    }
}
