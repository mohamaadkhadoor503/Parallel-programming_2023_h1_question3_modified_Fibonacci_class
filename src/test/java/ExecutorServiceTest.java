/*
Ref:
https://www.youtube.com/watch?v=9DvDheKRJ9Y&t=656s
https://jenkov.com/tutorials/java-util-concurrent/executorservice.html

Threads disadvantages and limitation
1- Thread Creation and teardown comes with a cost, both in terms of compute and time, it adds latency into request processing
2- Number of threads that can be created is limited
3- When there are more threads than the number of processors or cores, then the thread sit idle (memory effect).

ExecutorService automatically provides a pool of threads and an API for assigning tasks to it, to fix this issue.

it's based on the producer-consumer pattern
All thread in the pool are managed by a queue
some Thread pool type:

newFixedThreadPool
newCachedThreadPool
newSingleThreadExecutor
newScheduledThreadPool

* */

import Resource.Student;
import Service.StudentService;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTest extends TestCase {


    public void testFixedThreadPool() {
        StudentService studentService = new StudentService();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 30; ++i) {
            Student std = new Student("student " + i);

            Future<String> future = executorService.submit(() -> studentService.saveStudent(std));
            futures.add(future);
        }

        futures.forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        });
//        try {
//            executorService.awaitTermination(5,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        while (!executorService.isTerminated()){
//            try {
//                System.out.println("Terminating..");
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public void testScheduledThreadPool() {
        StudentService studentService = new StudentService();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Student std = new Student("student");

//        executorService.scheduleAtFixedRate(() -> System.out.println("hello"), 1, 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> studentService.saveStudent(std), 1, 3, TimeUnit.SECONDS);

        try {
            executorService.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

    }
}
