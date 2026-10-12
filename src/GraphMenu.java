import java.util.List;
import java.util.Scanner;

public class GraphMenu {

    private static Graph graph = null;

    public static Graph getGraph() {
        return graph;
    }

    public static void run(Scanner scanner) {

        if (graph == null) {
            graph = new Graph(askDirected(scanner));
        }

        int choice;
        do {
            System.out.println();
            System.out.println("--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addVertex(scanner);
                    break;
                case 2:
                    addEdge(scanner);
                    break;
                case 3:
                    System.out.println();
                    graph.display();
                    break;
                case 4:
                    traverse(scanner, true);
                    break;
                case 5:
                    traverse(scanner, false);
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    private static boolean askDirected(Scanner scanner) {
        while (true) {
            System.out.println("\nSelect graph type:");
            System.out.println("1. Undirected");
            System.out.println("2. Directed");
            int c = readInt(scanner, "Enter your choice: ");
            if (c == 1) {
                return false;
            }
            if (c == 2) {
                return true;
            }
            System.out.println("Please enter 1 or 2.");
        }
    }

    private static void addVertex(Scanner scanner) {
        String v = readText(scanner, "Enter vertex name: ");
        if (graph.addVertex(v)) {
            System.out.println("Vertex '" + v + "' added.");
        } else {
            System.out.println("Error: vertex '" + v + "' already exists.");
        }
    }

    private static void addEdge(Scanner scanner) {
        if (graph.vertexCount() < 2) {
            System.out.println("Error: add at least 2 vertices first.");
            return;
        }
        String from = readText(scanner, "Enter first vertex: ");
        String to = readText(scanner, "Enter second vertex: ");

        String error = graph.addEdge(from, to);
        if (error == null) {
            System.out.println("Edge added: " + from + (graph.isDirected() ? " -> " : " -- ") + to);
        } else {
            System.out.println("Error: " + error);
        }
    }

    private static void traverse(Scanner scanner, boolean isBfs) {
        if (graph.isEmpty()) {
            System.out.println("Error: the graph is empty. Add vertices first.");
            return;
        }

        String start = readText(scanner, "Enter starting vertex: ");
        List<String> order;
        if (isBfs) {
            order = graph.bfs(start);
        } else {
            order = graph.dfs(start);
        }

        if (order == null) {
            System.out.println("Error: vertex '" + start + "' does not exist.");
            return;
        }

        System.out.println("\n" + graph.getLastAlgorithm() + " from '" + start + "':");
        System.out.println("  " + String.join(" -> ", order));
        System.out.println("  Vertices visited: " + order.size() + " of " + graph.vertexCount());
        System.out.println("  Steps: " + graph.getLastSteps());
        System.out.println("  Time: " + graph.getLastTimeNanos() + " ns");
        if (order.size() < graph.vertexCount()) {
            System.out.println("  Note: some vertices cannot be reached from '" + start + "'.");
        }
    }


    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    private static String readText(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}
