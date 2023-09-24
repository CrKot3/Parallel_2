public class NonParallelIntegral {
    public static double Integration(Function f, double a, double b, int numSteps) {

        boolean reverse = a > b;
        if (reverse) {
            double temp = a;
            a = b;
            b = temp;
        }
        double step = (b - a) / numSteps;
        double result = 0;
        for (int i = 0; i < numSteps; i++) {
            result += f.apply(a + i * step) * step;
        }
        return result;
    }
}
