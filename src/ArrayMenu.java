import java.util.Scanner;

public class ArrayMenu {

    private static ArrayOperations array = null;

    public static ArrayOperations getArray() {
        return array;
    }

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static void run(Scanner scanner) {
    
        if (array == null) {
            int capacity;
            do {
                capacity = readInt(scanner, "Enter array capacity (1 - 1000000): ");
                if (capacity < 1 || capacity > 1000000) {
                    System.out.println("Capacity must be between 1 and 1000000.");
                }
            } while (capacity < 1 || capacity > 1000000);
            array = new ArrayOperations(capacity);
        }

        int choice;
        do {
            System.out.println();
            System.out.println("--- ARRAY OPERATIONS ---");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Index");
            System.out.println("3. Delete by Index");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for a Value");
            System.out.println("6. Display Array");
            System.out.println("7. Sort Array");
            System.out.println("8. Fill with Random Numbers");
            System.out.println("9. Return to Main Menu");
            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    insertAtEnd(scanner);
                    break;
                case 2:
                    insertAtIndex(scanner);
                    break;
                case 3:
                    deleteByIndex(scanner);
                    break;
                case 4:
                    deleteByValue(scanner);
                    break;
                case 5:
                    search(scanner);
                    break;
                case 6:
                    array.display();
                    break;
                case 7:
                    array.sort();
                    System.out.println("Array sorted in ascending order.");
                    break;
                case 8:
                    fillRandom(scanner);
                    break;
                case 9:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-9.");
            }
        } while (choice != 9);
    }

    private static void insertAtEnd(Scanner scanner) {
        if (array.isFull()) {
            System.out.println("Array is full. Cannot insert.");
            return;
        }
        int value = readInt(scanner, "Enter value to insert: ");
        array.insertAtEnd(value);
        System.out.println(value + " inserted at the end.");
    }

    private static void insertAtIndex(Scanner scanner) {
        if (array.isFull()) {
            System.out.println("Array is full. Cannot insert.");
            return;
        }
        int index = readInt(scanner, "Enter index (0 to " + array.getSize() + "): ");
        int value = readInt(scanner, "Enter value to insert: ");
        if (array.insertAt(index, value)) {
            System.out.println(value + " inserted at index " + index + ".");
        } else {
            System.out.println("Invalid index. Must be between 0 and " + array.getSize() + ".");
        }
    }

    private static void deleteByIndex(Scanner scanner) {
        if (array.isEmpty()) {
            System.out.println("Array is empty. Nothing to delete.");
            return;
        }
        int index = readInt(scanner, "Enter index to delete (0 to " + (array.getSize() - 1) + "): ");
        if (array.deleteAt(index)) {
            System.out.println("Element at index " + index + " deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void deleteByValue(Scanner scanner) {
        if (array.isEmpty()) {
            System.out.println("Array is empty. Nothing to delete.");
            return;
        }
        int value = readInt(scanner, "Enter value to delete: ");
        if (array.deleteByValue(value)) {
            System.out.println("Value " + value + " deleted.");
        } else {
            System.out.println("Value " + value + " not found.");
        }
    }

    private static void search(Scanner scanner) {
        if (array.isEmpty()) {
            System.out.println("Array is empty. Nothing to search.");
            return;
        }
        int value = readInt(scanner, "Enter value to search: ");
        SearchOperations.SearchResult result = SearchOperations.linearSearch(array.toArray(), value);
        if (result.index >= 0) {
            System.out.println("Found " + value + " at index " + result.index
                    + " (" + result.steps + " comparisons).");
        } else {
            System.out.println(value + " not found (" + result.steps + " comparisons).");
        }
    }

    private static void fillRandom(Scanner scanner) {
        int count = readInt(scanner, "How many random numbers (1 to " + array.getCapacity() + ")? ");
        if (count < 1 || count > array.getCapacity()) {
            System.out.println("Invalid count.");
            return;
        }
        array.fillRandom(count, 100000);
        System.out.println("Array filled with " + count + " random numbers (existing data replaced).");
    }
}
