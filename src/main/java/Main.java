public class Main {
    public static void main(String[] args) {
        timeCompare(x -> x*x, 5, 10, 500);
        timeCompare(x -> x*x, 5, 10, 50000);
        timeCompare(x -> x*x, 5, 10, 5000000);
        timeCompare(x -> x*x, 5, 10, 500000000);

        System.out.println("Integral of x^3 on [0, 10] with LEFT point method: " + NewtonCotes.Integration(MethodType.LEFT, x -> x*x*x, 0, 10, 95955959));
        System.out.println("Integral of x^3 on [0, 10] with RIGHT point method: " + NewtonCotes.Integration(MethodType.RIGHT, x -> x*x*x, 0, 10, 95955959));
        System.out.println("Integral of x^3 on [0, 10] with MIDDLE point method: " + NewtonCotes.Integration(MethodType.MIDDLE, x -> x*x*x, 0, 10, 95955959));
        System.out.println("Integral of x^3 on [0, 10] with TRAPEZOID method: " + NewtonCotes.Integration(MethodType.TRAPEZOID, x -> x*x*x, 0, 10, 95955959));
    }

    private static void timeCompare(Function func, double a, double b, int num) {
        System.out.println("Number of divisions: " + num + ".");
        long startTime = System.nanoTime();
        NewtonCotes.Integration(MethodType.LEFT, func, a, b, num);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Parallel time: " + estimatedTime / 1000000f + " ms.");
        startTime = System.nanoTime();
        NonParallelIntegral.Integration(func, a, b, num);
        long estimatedTime2 = System.nanoTime() - startTime;
        System.out.println("Non-parallel time: " + estimatedTime2 / 1000000f + " ms.");
        if (estimatedTime > estimatedTime2) {
            System.out.println("NON-PARALLEL WINS... :-(");
        }
        else {
            System.out.println("PARALLEL WINS!!! :-D");
        }
        System.out.println("");
    }
}