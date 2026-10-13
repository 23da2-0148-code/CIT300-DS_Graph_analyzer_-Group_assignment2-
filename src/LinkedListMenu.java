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
                case 1:
                    list.insertAtFront(InputHelper.readInt(scanner, "Enter value to insert: "));
                    System.out.println("Value inserted at the front.");
                    break;
                case 2:
                    list.insertAtEnd(InputHelper.readInt(scanner, "Enter value to insert: "));
                    System.out.println("Value inserted at the end.");
                    break;
                case 3:
                    int toDelete = InputHelper.readInt(scanner, "Enter value to delete: ");
                    if (list.deleteValue(toDelete)) {
                        System.out.println("Value " + toDelete + " deleted.");
                    } else {
                        System.out.println("Value " + toDelete + " was not found.");
                    }
                    break;
                case 4:
                    int toFind = InputHelper.readInt(scanner, "Enter value to search: ");
                    int index = list.search(toFind);
                    if (index == -1) {
                        System.out.println("Value " + toFind + " was not found.");
                    } else {
                        System.out.println("Value " + toFind + " found at position " + index + ".");
                    }
                    break;
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