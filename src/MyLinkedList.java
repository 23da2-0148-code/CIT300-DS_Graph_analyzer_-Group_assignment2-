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

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public void insertAtFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Deletes the first node holding this value. Returns true if one was removed.
    public boolean deleteValue(int value) {
        if (head == null) {
            return false;
        }
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Returns the 0-based position of the value, or -1 if not found.
    public int search(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            current = current.next;
            index++;
        }
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