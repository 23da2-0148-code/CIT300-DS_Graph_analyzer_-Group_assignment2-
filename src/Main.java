import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println();
            System.out.println("========== GRAPH ANALYZER - MAIN MENU ==========");
            System.out.println("1. Array Operations");
            System.out.println("2. Searching Operations");
            System.out.println("3. Linked List Operations");
            System.out.println("4. Graph Operations");
            System.out.println("5. Performance Comparison");
            System.out.println("6. View All Recorded Results");
            
            System.out.println("0. Exit");
            choice = InputHelper.readIntInRange(scanner, "Enter your choice: ", 0, 6);

            switch (choice) {
                case 1:
                    ArrayMenu.run(scanner);
                    break;
                case 2:
                    SearchMenu.run(scanner);
                    break;
                case 3:
                    LinkedListMenu.run(scanner);
                    break;
                case 4:
                    GraphMenu.run(scanner);
                    break;
                case 5:
                    PerformanceAnalyzer.run(scanner);
                    break;
                case 6:
                    ResultsLog.display();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
}