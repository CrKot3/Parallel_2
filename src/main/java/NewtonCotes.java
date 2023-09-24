import java.util.concurrent.atomic.DoubleAdder;

public class NewtonCotes {
    public static double Integration(MethodType mt, Function f, double a, double b, int numSteps) {

        boolean reverse = a > b;
        if (reverse) {
            double temp = a;
            a = b;
            b = temp;
        }
        double step = (b - a) / numSteps;
        if (step == 0) return 0;
        double begin = a;
        int numThreads = Runtime.getRuntime().availableProcessors();
        int numStepsPerThread = numSteps / numThreads;
        if (numStepsPerThread == 0) {
            numThreads = numSteps;
            numStepsPerThread = 1;
        }
        DoubleAdder totalSum = new DoubleAdder();

        Function method = f;
        switch(mt) {
            case LEFT:
                method = f;
            case RIGHT:
                method = x -> f.apply(x+step);
            case MIDDLE:
                method = x -> f.apply(x + step/2);
            case TRAPEZOID:
                method = x -> (f.apply(x) + f.apply(x+step))/2;
        }
        Function me = method;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread((new Runnable() {
                double start;
                double end;

                public void run() {
                    double localSum = 0;
                    for (double value = begin + start * step; value < begin + end * step; value += step) {
                        localSum += me.apply(value) * step;
                    }
                    totalSum.add(localSum);
                }

                public Runnable pass(double start, double end) {
                    this.start = start;
                    this.end = end;
                    return this;
                }
            }).pass(i * numStepsPerThread, (i == numThreads - 1)? numSteps : (i + 1) * numStepsPerThread));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double result = totalSum.sum();
        if (reverse) result *= -1;
        return result;
    }
}
