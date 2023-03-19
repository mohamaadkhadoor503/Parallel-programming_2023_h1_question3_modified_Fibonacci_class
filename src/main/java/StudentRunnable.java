import Resource.Student;
import Service.StudentService;

public class StudentRunnable implements Runnable {

    private Student student;
    private StudentService studentService;

    public StudentRunnable(Student student, StudentService studentService) {
        this.student = student;
        this.studentService = studentService;
    }

    @Override
    public void run() {
        System.out.println("Name of current Thread: " + Thread.currentThread().getName());
        studentService.saveStudent(student);
    }
}
