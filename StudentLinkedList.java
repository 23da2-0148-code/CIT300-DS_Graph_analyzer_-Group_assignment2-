public class StudentLinkedList {
    private Student head;

    public StudentLinkedList() {
        this.head = null;
    }

    public boolean addStudent(String studentID, String name, String programme, double marks) {
        
        if (searchStudent(studentID) != null) {
            System.out.println("Error: Student ID " + studentID + " already exists!");
            return false;
        }

        Student newStudent = new Student(studentID, name, programme, marks);

        if (head == null) {
            head = newStudent;
        } else {
            Student temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newStudent;
        }
        System.out.println("Student added successfully!");
        return true;
    }


    public void displayAllStudents() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student Records ---");
        Student temp = head;
        while (temp != null) {
            temp.displayStudent();
            temp = temp.next;
        }
    }


    public Student searchStudent(String studentID) {
        Student temp = head;
        while (temp != null) {
            if (temp.getStudentID().equalsIgnoreCase(studentID)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

      public boolean updateStudent(String studentID, String newName, String newProgramme, double newMarks) {
        Student student = searchStudent(studentID);
        if (student != null) {
            student.setName(newName);
            student.setProgramme(newProgramme);
            student.setMarks(newMarks);
            System.out.println("Student record updated successfully!");
            return true;
        }
        System.out.println("Error: Student ID not found.");
        return false;
    }


    public Student deleteStudent(String studentID) {
        if (head == null) {
            System.out.println("Error: List is empty.");
            return null;
        }


        if (head.getStudentID().equalsIgnoreCase(studentID)) {
            Student deletedStudent = head;
            head = head.next;
            System.out.println("Student record deleted successfully.");
            return deletedStudent;
        }

        Student current = head;
        Student previous = null;

        while (current != null && !current.getStudentID().equalsIgnoreCase(studentID)) {
            previous = current;
            current = current.next;
        }

        if (current != null) {
            previous.next = current.next;
            System.out.println("Student record deleted successfully.");
            return current; // Stack/Undo සඳහා deleted object එක return කරයි
        }

        System.out.println("Error: Student ID not found.");
        return null;
    }
}