import junit.framework.TestCase;


public class FibonacciTest extends TestCase {

    int n=40;

    public void testFiboPP(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n);
        int res = fib.compute();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution took %d ms\n",n,res,end);
//        Fibonacci parallel takes 144269 fon n = 50 before enhancement 1
    }

    public void testFiboSeq(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n);
        int res = fib.computeSeq();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential execution took %d ms\n",n,res,end);
        //Fibonacci Sequential takes 86495 fon n = 50 before enhancement 1
    }
}
