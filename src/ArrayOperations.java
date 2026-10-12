import java.util.Arrays;
import java.util.Random;


public class ArrayOperations {

    private final int[] data;
    private int size;

    public ArrayOperations(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        data = new int[capacity];
        size = 0;
    }

    public int getSize() { return size; }
    public int getCapacity() { return data.length; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }

    public boolean insertAtEnd(int value) {
        if (isFull()) return false;
        data[size++] = value;
        return true;
    }

    public boolean insertAt(int index, int value) {
        if (isFull() || index < 0 || index > size) return false;
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
        return true;
    }

    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) return false;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    public boolean deleteByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return deleteAt(i);
            }
        }
        return false;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println("Array (size " + size + "/" + data.length + "):");
        int limit = Math.min(size, 50); // avoid flooding the console
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < limit; i++) {
            sb.append(data[i]);
            if (i < limit - 1) sb.append(", ");
        }
        if (size > limit) sb.append(", ... (").append(size - limit).append(" more)");
        sb.append("]");
        System.out.println(sb);
    }

    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void sort() {
        Arrays.sort(data, 0, size);
    }

    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (data[i - 1] > data[i]) return false;
        }
        return true;
    }

    public void fillRandom(int count, int maxValue) {
        Random random = new Random();
        size = 0;
        for (int i = 0; i < count && i < data.length; i++) {
            data[size++] = random.nextInt(maxValue + 1);
        }
    }
}
