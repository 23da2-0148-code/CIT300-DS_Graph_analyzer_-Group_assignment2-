import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerformanceAnalyzer {

    private static final int REPEATS = 1000; 
    private static final List<String> results = new ArrayList<>();

    // Called from other menus to record an operation
    public static void addResult(String operation, String algorithm, int steps, long nanos) {
        results.add(String.format("%-16s %-16s %-10d %d ns", operation, algorithm, steps, nanos));
    }

    public static void displayAllResults() {
        System.out.println("=============================================");
        System.out.println(" ALL RECORDED RESULTS");
        System.out.println("=============================================");
        if (results.isEmpty()) {
            System.out.println("No results recorded yet.");
        } else {
            System.out.printf("%-16s %-16s %-10s %s%n", "Operation", "Algorithm", "Steps", "Time");
            System.out.println("------------------------------------------------------");
            for (String line : results) {
                System.out.println(line);
            }
        }
        System.out.println("=============================================");
    }

    public static void run(Scanner scanner) {
        int n;
        do {
            n = ArrayMenu.readInt(scanner, "Enter test array size (10 - 1000000): ");
            if (n < 10 || n > 1000000) {
                System.out.println("Size must be between 10 and 1000000.");
            }
        } while (n < 10 || n > 1000000);


        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i * 2;
        }

        System.out.println();
        System.out.println("=============================================");
        System.out.println(" PERFORMANCE COMPARISON (array size = " + n + ")");
        System.out.println("=============================================");
        System.out.printf("%-14s %-16s %-14s %-8s %s%n", "Operation", "Algorithm", "Case", "Steps", "Avg Time");
        System.out.println("-----------------------------------------------------------------");

        compareSearch("First element", sorted, sorted[0]);
        compareSearch("Middle element", sorted, sorted[n / 2]);
        compareSearch("Last element", sorted, sorted[n - 1]);
        compareSearch("Not found", sorted, 1);

        compareGraph();

        System.out.println("=============================================");
        System.out.println("Why the results differ:");
        System.out.println("- Linear search is O(n): in the worst case it checks every element.");
        System.out.println("- Binary search is O(log n): each step halves the range (needs sorted data).");
        System.out.println("- BFS and DFS are both O(V + E): similar steps, different visiting order.");
        System.out.println("- Times are averaged over " + REPEATS + " runs; steps are the reliable measure.");
    }

    private static void compareSearch(String caseName, int[] sorted, int target) {
    
        for (int i = 0; i < 200; i++) {
            SearchOperations.linearSearch(sorted, target);
            SearchOperations.binarySearch(sorted, target);
        }

        SearchOperations.SearchResult lin = null;
        long linTotal = 0;
        for (int i = 0; i < REPEATS; i++) {
            lin = SearchOperations.linearSearch(sorted, target);
            linTotal += lin.nanos;
        }

        SearchOperations.SearchResult bin = null;
        long binTotal = 0;
        for (int i = 0; i < REPEATS; i++) {
            bin = SearchOperations.binarySearch(sorted, target);
            binTotal += bin.nanos;
        }

        long linAvg = linTotal / REPEATS;
        long binAvg = binTotal / REPEATS;
        System.out.printf("%-14s %-16s %-14s %-8d %d ns%n", "Search", "Linear Search", caseName, lin.steps, linAvg);
        System.out.printf("%-14s %-16s %-14s %-8d %d ns%n", "Search", "Binary Search", caseName, bin.steps, binAvg);
        addResult("Search (" + caseName + ")", "Linear Search", lin.steps, linAvg);
        addResult("Search (" + caseName + ")", "Binary Search", bin.steps, binAvg);
    }


    private static void compareGraph() {
        System.out.println("Graph Traversal: not connected yet (waiting for Graph BFS/DFS step counts).");
    }
}
