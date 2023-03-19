import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    final int n;
    private static final HashMap<Integer, Integer> precomputedValues = new HashMap<>();

    public Fibonacci(int n) {
        this.n = n;
    }

    public Integer compute() {
        if(n <= 1) {
            return n;
        }

        // Check if the value is precomputed
        if (precomputedValues.containsKey(n)) {
            return precomputedValues.get(n);
        }

        if(n > 20) {
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            int result = f2.compute() + f1.join();
            // Store the computed value
            precomputedValues.put(n, result);
            return result;
        } else {
            int result = computeSeq();
            // Store the computed value
            precomputedValues.put(n, result);
            return result;
        }
    }

    public Integer computeSeq() {
        if (n <= 1) {
            return n;
        }
        return new Fibonacci(n - 1).computeSeq() + new Fibonacci(n - 2).computeSeq();
    }
}
/*In this modified version of the Fibonacci class,
we added a static HashMap called precomputedValues to store the precomputed values. In the compute method,
we first check if the value of n is already in the precomputedValues map, and if so, we return the precomputed value.
Otherwise, we calculate the value as before,
and store it in the map. We also modified the condition for the serial computation to n <= 20,
which means that only values up to 20 will be computed sequentially.
By using this approach,
we can avoid repeating expensive calculations and improve the performance of the Fibonacci counting functions.
 */