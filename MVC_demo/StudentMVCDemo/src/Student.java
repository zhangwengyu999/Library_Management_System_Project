public class Student {
    private int studentID;
    private String studentName;

    public Student() {
        studentID = 0;
        studentName = "";
    }

    // constructor
    public Student(int inID, String inName) {
        this.studentID = inID;
        this.studentName = inName;
    }

    // To get the student ID
    public int getStudentID() {
        return studentID;
    }

    // To get the student ID
    public void setStudentID(int inID) {
        this.studentID = inID;
    }

    // To get the student name
    public String getStudentName() {
        return studentName;
    }

    // To set the student name
    public void setStudentName(String inName) {
        this.studentName = inName;
    }

    public String getStudentInfo() {
        return "SID: " + studentID + ",\n SName: " + studentName;
    }

}
