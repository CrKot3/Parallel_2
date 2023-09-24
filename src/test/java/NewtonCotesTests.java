import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewtonCotesTests {
    private static final double delta = 0.00001;

    @Test
    public void basicIntegrals() {
        Function f1 = x -> x;
        Function f2 = x -> x * x * x;
        Function f3 = x -> Math.sqrt(Math.sin(x));
        assertEquals(24, NewtonCotes.Integration(MethodType.LEFT,f1,-1,7, 8000000), delta);
        assertEquals(0, NewtonCotes.Integration(MethodType.LEFT,f1,-1,1, 8000000), delta);
        assertEquals(0, NewtonCotes.Integration(MethodType.LEFT,f2,-4,4, 8000000), delta);
        assertEquals(0.1, NewtonCotes.Integration(MethodType.LEFT,x->f2.apply(f2.apply(x)),0,1, 8000000), delta);
        assertEquals(0.946710208752, NewtonCotes.Integration(MethodType.LEFT,f3,7,8, 8000000), delta);
        assertEquals(0.547449433025, NewtonCotes.Integration(MethodType.LEFT,x->f3.apply(f2.apply(x)),0,1.162, 8000000), delta);
    }

    @Test
    public void differentMethods() {
        Function f = x -> x * Math.exp(x);
        assertEquals(0.735758882343,NewtonCotes.Integration(MethodType.LEFT, f,-1,1, 8000000),delta);
        assertEquals(0.735758882343,NewtonCotes.Integration(MethodType.RIGHT, f,-1,1, 8000000),delta);
        assertEquals(0.735758882343,NewtonCotes.Integration(MethodType.MIDDLE, f,-1,1, 8000000),delta);
        assertEquals(0.735758882343,NewtonCotes.Integration(MethodType.TRAPEZOID, f,-1,1, 8000000),delta);
    }

    @Test
    public void additionRule() {
        Function f = x -> x * x - x;
        assertEquals(NewtonCotes.Integration(MethodType.LEFT, f,0,4, 8000000),NewtonCotes.Integration(MethodType.LEFT, f,0,2, 4000000) + NewtonCotes.Integration(MethodType.LEFT, f,2,4, 4000000), delta);
    }

    @Test
    public void oddities() {
        Function f = x -> Math.sin(x) + x;
        assertEquals(0,NewtonCotes.Integration(MethodType.LEFT, f,5,5, 8000000));
        assertEquals(NewtonCotes.Integration(MethodType.LEFT, f,0,6, 8000000),-NewtonCotes.Integration(MethodType.LEFT, f,6,0, 8000000), delta);
    }
}
