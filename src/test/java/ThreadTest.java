import Resource.Student;
import Service.StudentService;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends TestCase {

    public void testStudent() {
        long start = System.currentTimeMillis();

        StudentService studentService = new StudentService();

        for (int i = 1; i <= 10; ++i) {
            Student std = new Student("student " + i);
            studentService.saveStudent(std);
        }
        long execTime = System.currentTimeMillis() - start;
        System.out.println("Execution Time: " + execTime + " ms");

    }

    public void testStudentThread() {
        long start = System.currentTimeMillis();

        System.out.println("Name of current Thread: " + Thread.currentThread().getName());

        StudentService studentService = new StudentService();
        Student std = new Student("student");

        StudentThread studentThread = new StudentThread(std, studentService);
        studentThread.setName("myThread");
        studentThread.start(); // start thread
//        studentThread.run(); // treat as a simple object not as a thread
        try {
            studentThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long execTime = System.currentTimeMillis() - start;
        System.out.println("Execution Time: " + execTime + " ms");
    }

    public void testStudentsThreads() {
        long start = System.currentTimeMillis();

        StudentService studentService = new StudentService();

        for (int i = 1; i <= 10; ++i) {

            Student std = new Student("student " + i);
            StudentThread studentThread = new StudentThread(std, studentService);
            studentThread.setName("myThread-" + i);
            studentThread.start(); // start thread
            //        studentThread.run(); // treat as a simple object not as a thread
            try {
                studentThread.join(); //wait thread to complete
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long execTime = System.currentTimeMillis() - start;
        System.out.println("Execution Time: " + execTime + " ms");
    }

    public void testStudentThreadsPP() {
        long start = System.currentTimeMillis();

        StudentService studentService = new StudentService();
        List<StudentThread> studentThreads = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {

            Student std = new Student("student " + i);
            StudentThread studentThread = new StudentThread(std, studentService);
            studentThread.setName("myThread-" + i);
            studentThread.start(); // start thread
            studentThreads.add(studentThread);
            //        studentThread.run(); // treat as a simple object not as a thread
        }

        studentThreads.forEach(e -> {
            try {
                e.join(); //wait thread to complete
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        long execTime = System.currentTimeMillis() - start;
        System.out.println("Execution Time: " + execTime + " ms");
    }

    public void testStudentRunnable() {
        StudentService studentService = new StudentService();
        Student std = new Student("student");
        StudentRunnable studentRunnable = new StudentRunnable(std, studentService);

        Thread thread = new Thread(studentRunnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Completed Runnable");
    }

    public void testStudentRunnableAnonymousClass() {
        StudentService studentService = new StudentService();
        Student std = new Student("student");
//        StudentRunnable studentRunnable = new StudentRunnable(std, studentService);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                studentService.saveStudent(std);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Completed Runnable");
    }

    public void testStudentRunnableLambda() {
        StudentService studentService = new StudentService();
        Student std = new Student("student");

        Thread thread = new Thread(() -> {
            studentService.saveStudent(std);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed Runnable");
    }
}
