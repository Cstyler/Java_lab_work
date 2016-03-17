/**
 * Created by khan on 19.02.16.
 */

import java.util.Arrays;

public class Polynomial {
    private Double[] coefficients;

    public Polynomial(Double... args)  {
        coefficients = args;
    }

    public Double calculate(Double x) {
        return Arrays.stream(coefficients).reduce(0.0, (r,i)->r*x+i);
    }

    public String toString() {
        return Arrays.toString(coefficients);
    }
}
