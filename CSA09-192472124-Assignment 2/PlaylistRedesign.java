import java.util.*;

public class PlaylistRedesign {
    public static void main(String[] args) {
        int n = 100000;

        // Original design: ArrayList is suitable for indexed access.
        List<Integer> original = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) original.add(i);
        long originalBuild = System.nanoTime() - start;

        // Redesigned Up Next queue: ArrayDeque is suitable for both ends.
        Deque<Integer> redesigned = new ArrayDeque<>();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) redesigned.addLast(i);
        for (int i = 0; i < n / 2; i++) redesigned.removeFirst();
        long redesignedTime = System.nanoTime() - start;

        System.out.println("Q7 - Diagnose, Redesign and Validate");
        System.out.println("Design problem: an ArrayList is not ideal when the app repeatedly removes from the front.");
        System.out.println("Redesign: use ArrayDeque for the Up Next queue.");
        System.out.println("ArrayList build time: " + originalBuild / 1_000_000.0 + " ms");
        System.out.println("ArrayDeque add-last/remove-first time: " + redesignedTime / 1_000_000.0 + " ms");
        System.out.println("Original list size: " + original.size());
        System.out.println("Redesigned queue remaining size: " + redesigned.size());
        System.out.println("Conclusion: ArrayDeque supports insertion/removal at both ends in amortized O(1).");
        System.out.println("Note: timings depend on computer and JVM; run several times for report evidence.");
    }
}
