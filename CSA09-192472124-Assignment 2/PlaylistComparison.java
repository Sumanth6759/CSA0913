import java.util.*;

public class PlaylistComparison {
    static long timeAddEnd(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(i);
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};
        System.out.println("Q2 - ArrayList, LinkedList and ArrayDeque comparison");
        for (int n : sizes) {
            List<Integer> array = new ArrayList<>();
            List<Integer> linked = new LinkedList<>();
            long a = timeAddEnd(array, n);
            long l = timeAddEnd(linked, n);

            long start = System.nanoTime();
            long sum = 0;
            for (int i = 0; i < n; i++) sum += array.get(i);
            long access = System.nanoTime() - start;

            ArrayDeque<Integer> deque = new ArrayDeque<>();
            start = System.nanoTime();
            for (int i = 0; i < n; i++) deque.addLast(i);
            for (int i = 0; i < n / 2; i++) deque.removeFirst();
            long dequeTime = System.nanoTime() - start;

            System.out.println("\nSize: " + n);
            System.out.println("ArrayList add-end: " + a / 1_000_000.0 + " ms");
            System.out.println("LinkedList add-end: " + l / 1_000_000.0 + " ms");
            System.out.println("ArrayList indexed access: " + access / 1_000_000.0 + " ms");
            System.out.println("ArrayDeque add-last/remove-first: " + dequeTime / 1_000_000.0 + " ms");
            System.out.println("Check sum: " + sum);
        }
        System.out.println("\nConclusion: ArrayList suits frequent indexed access; ArrayDeque suits operations at both ends.");
    }
}
