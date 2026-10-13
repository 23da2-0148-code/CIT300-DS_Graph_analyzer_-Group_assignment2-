
public class MyStack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    
    public MyStack(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1; 
    }

    
    public void push(int value) {
        if (isFull()) {
            System.out.println("Error: Stack Overflow! Stack.");
        } else {
            stackArray[++top] = value;
            System.out.println(value + " Stack.");
        }
    }

    
    public int pop() {
        if (isEmpty()) {
            System.out.println("Error: Stack Underflow! Stack .");
            return -1;
        } else {
            int poppedValue = stackArray[top--];
            return poppedValue;
        }
    }

    
    public int peek() {
        if (isEmpty()) {
            System.out.println("Error: Stack !");
            return -1;
        } else {
            return stackArray[top];
        }
    }

    
    public boolean isEmpty() {
        return (top == -1);
    }

    
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    
    public int getSize() {
        return top + 1;
    }

    
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack");
            return;
        }
        System.out.print("Stack (Top -> Bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }
}

