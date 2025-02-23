package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList();
        AList<Integer> opCounts = new AList();
        AList<Double> times = new AList();
        SLList<Integer> test = new SLList();
        int n=1;
        for(int i=1; i<=8; i++){
            Ns.addLast(n*1000);
            opCounts.addLast(10000);
            for (int j=test.size(); j<n*1000; j++){
                test.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j=0; j<10000; j++){
                test.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            n*=2;
        }
        printTimingTable(Ns, times, opCounts);
    }

}
