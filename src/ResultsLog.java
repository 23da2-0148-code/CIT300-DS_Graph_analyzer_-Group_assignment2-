// Thin wrapper so every menu records into the same table as PerformanceAnalyzer.
public class ResultsLog {

    // e.g. ResultsLog.record("Linked List", "Search", steps, nanos);
    public static void record(String structure, String operation, int steps, long nanos) {
        PerformanceAnalyzer.addResult(structure, operation, steps, nanos);
    }

    // Shows the combined table of all recorded results.
    public static void display() {
        PerformanceAnalyzer.displayAllResults();
    }
}