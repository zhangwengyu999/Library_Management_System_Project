import java.util.Scanner;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController() {
        this.model = new Student();
        this.view = new StudentView();
    }

    public void addStudentByAdmin(int inID, String inName) {
        model.setStudentID(inID);
        model.setStudentName(inName);
    }

    public void setStudentName(String name) {
        model.setStudentName(name);
    }

    public String getStudentName() {
        return model.getStudentName();
    }

    public void setStudentID(int id) {
        model.setStudentID(id);
    }

    public int getStudentID() {
        return model.getStudentID();
    }

    public void updateView() {
        view.printStudentInfo(model.getStudentInfo());
    }

    public void updateViewTable() {
        view.printStudentInfoTable(model.getStudentInfo());
    }

    public void askForStudentName() {
        view.printDialogForStudentName();
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        setStudentName(name);
    }

    public void askForStudentID() {
        view.printDialogForStudentID();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        setStudentID(id);
    }

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        // Done by admin
        controller.addStudentByAdmin(12345678, "Mike");

        // Render the view
        System.out.println("(Normal view)");
        controller.updateView();

        System.out.println("**************************");


        // Select the view
        System.out.println("(Table view)");
        controller.updateViewTable();

        while (true) {
            // Handle user events
            controller.askForStudentID();
            controller.askForStudentName();

            System.out.println("**************************");

            // Render the view again
            controller.updateView();
            controller.updateViewTable();
        }

    }
}
