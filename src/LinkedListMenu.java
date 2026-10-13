import java.util.Scanner;

public class LinkedListMenu {

    private static MyLinkedList list = new MyLinkedList();

    public static MyLinkedList getList() {
        return list;
    }

    public static void run(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Linked List Menu ===");
            System.out.println("1. Insert at front");
            System.out.println("2. Insert at end");
            System.out.println("3. Delete a value");
            System.out.println("4. Search for a value");
            System.out.println("5. Display list");
            System.out.println("6. Show size");
            System.out.println("7. Clear list");
            System.out.println("0. Back to main menu");
            choice = InputHelper.readIntInRange(scanner, "Enter your choice: ", 0, 7);

            switch (choice) {
                case 1: {
                    int value = InputHelper.readInt(scanner, "Enter value to insert: ");
                    long start = System.nanoTime();
                    list.insertAtFront(value);
                    long nanos = System.nanoTime() - start;
                    ResultsLog.record("Linked List", "Insert at front", list.getLastSteps(), nanos);
                    System.out.println("Value inserted at the front.");
                    break;
                }
                case 2: {
                    int value = InputHelper.readInt(scanner, "Enter value to insert: ");
                    long start = System.nanoTime();
                    list.insertAtEnd(value);
                    long nanos = System.nanoTime() - start;
                    ResultsLog.record("Linked List", "Insert at end", list.getLastSteps(), nanos);
                    System.out.println("Value inserted at the end.");
                    break;
                }
                case 3: {
                    int toDelete = InputHelper.readInt(scanner, "Enter value to delete: ");
                    long start = System.nanoTime();
                    boolean deleted = list.deleteValue(toDelete);
                    long nanos = System.nanoTime() - start;
                    ResultsLog.record("Linked List", "Delete value", list.getLastSteps(), nanos);
                    if (deleted) {
                        System.out.println("Value " + toDelete + " deleted.");
                    } else {
                        System.out.println("Value " + toDelete + " was not found.");
                    }
                    break;
                }
                case 4: {
                    int toFind = InputHelper.readInt(scanner, "Enter value to search: ");
                    long start = System.nanoTime();
                    int index = list.search(toFind);
                    long nanos = System.nanoTime() - start;
                    ResultsLog.record("Linked List", "Search", list.getLastSteps(), nanos);
                    if (index == -1) {
                        System.out.println("Value " + toFind + " was not found (" + list.getLastSteps() + " steps).");
                    } else {
                        System.out.println("Value " + toFind + " found at position " + index
                                + " (" + list.getLastSteps() + " steps).");
                    }
                    break;
                }
                case 5:
                    list.display();
                    break;
                case 6:
                    System.out.println("List size: " + list.size());
                    break;
                case 7:
                    list.clear();
                    System.out.println("List cleared.");
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }
}