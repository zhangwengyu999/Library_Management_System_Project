public class StudentView {
    public void printStudentInfo(String info) {
        System.out.println("Student Info: ");
        System.out.println(info);
    }

    public void printStudentInfoTable(String info) {
        System.out.println("==========================");
        System.out.println("         Student          ");
        System.out.println("--------------------------");
        System.out.println(info);
        System.out.println("==========================");
    }

    public void printDialogForStudentName() {
        System.out.print("Enter student name: ");
    }

    public void printDialogForStudentID() {
        System.out.print("Enter student ID: ");
    }
}
