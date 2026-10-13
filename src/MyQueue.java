
public class MyQueue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    
    public MyQueue(int size) {
        this.maxSize = size;
        this.queueArray = new int[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Error: Queue Overflow! Queue .");
        } else {
            if (rear == maxSize - 1) {
                rear = -1; 
            }
            queueArray[++rear] = value;
            nItems++;
            System.out.println(value + " Queue .");
        }
    }

    
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Error: Queue Underflow! Queue .");
            return -1;
        } else {
            int temp = queueArray[front++];
            if (front == maxSize) {
                front = 0; 
            }
            nItems--;
            return temp;
        }
    }

    
    public int peekFront() {
        if (isEmpty()) {
            System.out.println("Error: Queue ");
            return -1;
        } else {
            return queueArray[front];
        }
    }

    
    public boolean isEmpty() {
        return (nItems == 0);
    }

    
    public boolean isFull() {
        return (nItems == maxSize);
    }

    
    public int getSize() {
        return nItems;
    }

    
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue ");
            return;
        }
        System.out.print("Queue (Front -> Rear): ");
        int count = 0;
        int i = front;
        while (count < nItems) {
            System.out.print(queueArray[i] + " ");
            i = (i + 1) % maxSize;
            count++;
        }
        System.out.println();
    }
}