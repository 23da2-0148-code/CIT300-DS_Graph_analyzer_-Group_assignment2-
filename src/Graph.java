import java.util.*;

// Graph using an adjacency list.
// Each vertex has a list of the vertices it is connected to.
public class Graph {

    private Map<String, List<String>> adjList = new LinkedHashMap<>();
    private boolean directed;

    // info about the last traversal (used for performance comparison)
    private String lastAlgorithm = "None";
    private long lastSteps = 0;
    private long lastTimeNanos = 0;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isEmpty() {
        return adjList.isEmpty();
    }

    public int vertexCount() {
        return adjList.size();
    }

    public boolean hasVertex(String v) {
        return adjList.containsKey(v);
    }

    // add a new vertex, returns false if it is already there
    public boolean addVertex(String v) {
        if (adjList.containsKey(v)) {
            return false;
        }
        adjList.put(v, new ArrayList<>());
        return true;
    }

    // add an edge, returns null if ok, or an error message if not
    public String addEdge(String from, String to) {
        if (!hasVertex(from)) {
            return "Vertex '" + from + "' does not exist.";
        }
        if (!hasVertex(to)) {
            return "Vertex '" + to + "' does not exist.";
        }
        if (from.equals(to)) {
            return "A vertex cannot connect to itself.";
        }
        if (adjList.get(from).contains(to)) {
            return "That edge already exists.";
        }

        adjList.get(from).add(to);
        if (!directed) {
            adjList.get(to).add(from);   // undirected, so add both ways
        }
        return null;
    }

    // print every vertex with its neighbours
    public void display() {
        if (isEmpty()) {
            System.out.println("The graph is empty.");
            return;
        }
        System.out.println("Graph type: " + (directed ? "Directed" : "Undirected"));
        String arrow = directed ? " -> " : " -- ";
        for (String v : adjList.keySet()) {
            List<String> neighbours = adjList.get(v);
            if (neighbours.isEmpty()) {
                System.out.println("  " + v + " (no edges)");
            } else {
                System.out.println("  " + v + arrow + String.join(", ", neighbours));
            }
        }
    }

    // Breadth-First Search: visit level by level using a queue
    // Time O(V + E)
    public List<String> bfs(String start) {
        if (!hasVertex(start)) {
            return null;
        }

        long startTime = System.nanoTime();
        long steps = 0;

        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            steps++;                        // vertex processed

            for (String next : adjList.get(current)) {
                steps++;                    // edge checked
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        saveResult("BFS", steps, System.nanoTime() - startTime);
        return order;
    }

    // Depth-First Search: go as deep as possible, then backtrack
    // Time O(V + E)
    private long dfsSteps;

    public List<String> dfs(String start) {
        if (!hasVertex(start)) {
            return null;
        }

        long startTime = System.nanoTime();
        dfsSteps = 0;

        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited, order);

        saveResult("DFS", dfsSteps, System.nanoTime() - startTime);
        return order;
    }

    private void dfsHelper(String current, Set<String> visited, List<String> order) {
        visited.add(current);
        order.add(current);
        dfsSteps++;                         // vertex processed

        for (String next : adjList.get(current)) {
            dfsSteps++;                     // edge checked
            if (!visited.contains(next)) {
                dfsHelper(next, visited, order);
            }
        }
    }

    private void saveResult(String algorithm, long steps, long nanos) {
        lastAlgorithm = algorithm;
        lastSteps = steps;
        lastTimeNanos = nanos;
    }

    public String getLastAlgorithm() {
        return lastAlgorithm;
    }

    public long getLastSteps() {
        return lastSteps;
    }

    public long getLastTimeNanos() {
        return lastTimeNanos;
    }
}
