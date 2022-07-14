import java.math.BigDecimal;

public class Factorial {

    public static BigDecimal calculate(int x) {

        if (x < 0) {
            throw new ArithmeticException();
        }

        BigDecimal result = BigDecimal.ONE;

        for (int i = 2; i <= x; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }

        return result;
    }

}