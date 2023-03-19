package Service;

import Resource.Student;

import java.util.UUID;

public class StudentService {
    public String saveStudent(Student std){
        System.out.println("Saving Student: " + std.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Student "+ std.getName()+ " is saved.");
        return UUID.randomUUID().toString();
    }
}
