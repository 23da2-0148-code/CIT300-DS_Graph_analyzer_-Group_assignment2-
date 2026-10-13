public class MyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    private int lastSteps; // nodes touched by the most recent operation

    public MyLinkedList() {
        head = null;
        size = 0;
        lastSteps = 0;
    }

    public int getLastSteps() {
        return lastSteps;
    }

    public void insertAtFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        lastSteps = 1;
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        int steps = 1;
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
                steps++;
            }
            current.next = newNode;
        }
        size++;
        lastSteps = steps;
    }

    
    public boolean deleteValue(int value) {
        if (head == null) {
            lastSteps = 0;
            return false;
        }
        int steps = 1;
        if (head.data == value) {
            head = head.next;
            size--;
            lastSteps = steps;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            steps++;
            if (current.next.data == value) {
                current.next = current.next.next;
                size--;
                lastSteps = steps;
                return true;
            }
            current = current.next;
        }
        lastSteps = steps;
        return false;
    }

    
    public int search(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                lastSteps = index + 1;
                return index;
            }
            current = current.next;
            index++;
        }
        lastSteps = index;
        return -1;
    }

    public void display() {
        if (head == null) {
            System.out.println("The linked list is empty.");
            return;
        }
        Node current = head;
        System.out.print("Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }
}