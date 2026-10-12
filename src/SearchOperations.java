

public class SearchOperations {

    public static class SearchResult {
        public final int index;   
        public final int steps;   
        public final long nanos;  

        public SearchResult(int index, int steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    public static SearchResult linearSearch(int[] arr, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int found = -1;
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == target) {
                found = i;
                break;
            }
        }
        return new SearchResult(found, steps, System.nanoTime() - start);
    }

    public static SearchResult binarySearch(int[] arr, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int found = -1;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                found = mid;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(found, steps, System.nanoTime() - start);
    }
}