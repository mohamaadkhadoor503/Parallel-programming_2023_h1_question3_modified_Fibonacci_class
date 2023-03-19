import Resource.Student;
import Service.StudentService;

public class StudentThread extends Thread {

    private Student student;
    private StudentService studentService;

    public StudentThread(Student student, StudentService studentService) {
        this.student = student;
        this.studentService = studentService;
    }

    @Override
    public void run() {
        System.out.println("Name of current Thread: "+Thread.currentThread().getName());
        studentService.saveStudent(student);
    }
}
