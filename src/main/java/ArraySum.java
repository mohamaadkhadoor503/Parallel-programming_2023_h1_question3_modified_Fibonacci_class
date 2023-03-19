import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ArraySum extends RecursiveAction {
    public long sum;
    int arr[], lo, hi;

    public ArraySum(int[] arr, int lo, int hi) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
    }

    public long computeSeq() {
//        if(lo == hi)
//            return arr[lo];
//        else {
//            int mid = (lo + hi) / 2;
//            ArraySum left = new ArraySum(arr, lo, mid);
//            ArraySum right = new ArraySum(arr, mid + 1, hi);
//
//            return left.computeSeq()+ right.computeSeq();
//        }
        for (int i = lo; i <= hi; ++i) {
            sum += arr[i];
        }
        return sum;
    }

    @Override
    protected void compute() {
        if (hi - lo > 1_000_000) {
            int mid = (lo + hi) / 2;
            ArraySum left = new ArraySum(arr, lo, mid);
            ArraySum right = new ArraySum(arr, mid + 1, hi);
            left.fork();
            right.compute();
            left.join();
            sum = left.sum + right.sum;
        } else {
//            sum =computeSeq();
            for (int i = lo; i <= hi; ++i) {
                sum += arr[i];
            }
        }
    }

    public void computeStream() {
        sum = Arrays.stream(arr).asLongStream().parallel().sum();
//        all the intermediate and final operation run in parallel
//         Introduce to Map Reduce pattern credit to java streams
    }
}
