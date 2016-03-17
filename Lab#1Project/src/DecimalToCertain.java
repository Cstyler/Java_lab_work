/**
 * Created by khan on 20.02.16.
 */

import java.util.ArrayList;
import java.util.Collections;

public class DecimalToCertain {
    private ArrayList<Integer> digits;
    private int number, base;

    public DecimalToCertain(int number, int base) {
        this.number = number;
        if (base > 1 && base <= 36) {
            this.base = base;
        } else {
            System.out.println("BASE ERROR");
        }
        digits = new ArrayList<>();
        for (int i = Math.abs(number); i > 0; i /= base) {
            digits.add(i % base);
        }
        Collections.reverse(digits);
    }

    public String sign() {
        return number>0 ? "Positive" : "Negative";
    }

    public int get(int index) {
        return digits.get(index);
    }

    public String toString() {
        return number + "->" + digits + ", base = " + base;
    }
}
