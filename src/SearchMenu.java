import java.util.Arrays;
import java.util.Scanner;

public class SearchMenu {

    public static void run(Scanner scanner) {
        int choice;
        do {
            System.out.println();
            System.out.println("--------------- SEARCHING OPERATIONS ---------------");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Compare Linear vs Binary");
            System.out.println("4. Return to Main Menu");
            choice = ArrayMenu.readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    if (hasData()) {
                        handleSearch(scanner, choice);
                    }
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        } while (choice != 4);
    }

    private static boolean hasData() {
        ArrayOperations array = ArrayMenu.getArray();
        if (array == null || array.isEmpty()) {
            System.out.println("The array is empty. Use 'Array Operations' to add data first "
                    + "(option 8 there fills it with random numbers).");
            return false;
        }
        return true;
    }

    private static void handleSearch(Scanner scanner, int choice) {
        int target = ArrayMenu.readInt(scanner, "Enter value to search: ");
        int[] data = ArrayMenu.getArray().toArray();

        if (choice == 1) {
            SearchOperations.SearchResult r = SearchOperations.linearSearch(data, target);
            printResult("Linear Search", r, target);
            PerformanceAnalyzer.addResult("Search", "Linear Search", r.steps, r.nanos);
            return;
        }

        int[] sorted = data.clone();
        Arrays.sort(sorted);
        if (!ArrayMenu.getArray().isSorted()) {
            System.out.println("Note: array is not sorted, so a sorted copy is used for binary search.");
        }

        if (choice == 2) {
            SearchOperations.SearchResult r = SearchOperations.binarySearch(sorted, target);
            printResult("Binary Search", r, target);
            PerformanceAnalyzer.addResult("Search", "Binary Search", r.steps, r.nanos);
        } else {
            SearchOperations.SearchResult lin = SearchOperations.linearSearch(sorted, target);
            SearchOperations.SearchResult bin = SearchOperations.binarySearch(sorted, target);
            printResult("Linear Search", lin, target);
            printResult("Binary Search", bin, target);
            System.out.println();
            System.out.printf("Array size: %d | Linear steps: %d | Binary steps: %d%n",
                    sorted.length, lin.steps, bin.steps);
            PerformanceAnalyzer.addResult("Search", "Linear Search", lin.steps, lin.nanos);
            PerformanceAnalyzer.addResult("Search", "Binary Search", bin.steps, bin.nanos);
        }
    }

    private static void printResult(String name, SearchOperations.SearchResult r, int target) {
        if (r.index >= 0) {
            System.out.printf("%-14s: found %d at index %d | steps: %d | time: %d ns%n",
                    name, target, r.index, r.steps, r.nanos);
        } else {
            System.out.printf("%-14s: %d not found | steps: %d | time: %d ns%n",
                    name, target, r.steps, r.nanos);
        }
    }
}
