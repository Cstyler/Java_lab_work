/**
 * Created by khan on 20.02.16.
 */

import java.util.Arrays;

public class Vector {
    private Double[] coefficients;
    public Vector(Double... args) {
        coefficients = args;
    }

    public static Double scalarProduct(Vector x, Vector y) {
        Double result = 0.0;
        for (int i = 0; i < x.coefficients.length; i++) {
            result += x.coefficients[i]*y.coefficients[i];
        }
        return result;
    }

    public String toString() {
        return Arrays.toString(coefficients);
    }
}
