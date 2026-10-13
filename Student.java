public class Student {
    private String studentID;
    private String name;
    private String programme;
    private double marks;
    

    Student next;


    public Student(String studentID, String name, String programme, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.programme = programme;
        this.marks = marks;
        this.next = null;
    }


    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }
    
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }


    public void displayStudent() {
        System.out.printf("ID: %-10s | Name: %-20s | Programme: %-15s | Marks: %.2f\n", 
                          studentID, name, programme, marks);
    }
}