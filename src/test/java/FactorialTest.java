import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @ParameterizedTest
    @MethodSource("simpleValues")
    void factorialCalculateTest_SimpleValues(int x, BigDecimal expected) {

        //when:
        BigDecimal result = Factorial.calculate(x);

        //then:
        assertEquals(expected, result);
    }

    // Проверка работы на простых параметрах
    static Stream<Arguments> simpleValues() {
        return Stream.of(
                // Проверяем простые значение
                Arguments.of(5, new BigDecimal(120)),
                Arguments.of(10, new BigDecimal(3628800)),
                Arguments.of(1, BigDecimal.ONE),
                // Факториал 0 равен 1
                Arguments.of(0, BigDecimal.ONE),
                // Максимальное значение, которое можно получить в пределах Long
                Arguments.of(20, new BigDecimal(2432902008176640000L))
        );
    }

    // Выбрасывается исключение при отрицательном параметре
    @Test
    void factorialCalculateTest_ExpectedException() {

        //given:
        int x = -1;
        var expected = ArithmeticException.class;

        //when:
        Executable action = () -> Factorial.calculate(x);

        //then:
        assertThrows(expected, action);
    }

    // Не выбрасываются исключения при больших параметрах факториала
    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 10000, 100000})
    void factorialCalculateTest_DoesNotThrow(int x) {
        //when:
        Executable action = () -> Factorial.calculate(x);

        //then:
        assertDoesNotThrow(action);
    }

    // Проверяем, что результат не null
    @Test
    void factorialCalculateTest_NotNull() {

        //given:
        int x = 100;

        //when:
        BigDecimal result = Factorial.calculate(x);

        //then:
        assertNotNull(result);
    }

    // Проверяем, что вычисление результата не слишком затянуто
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void factorialCalculateTest_TimeOut100ms() {

        //given:
        int x = 10000;

        //when:
        BigDecimal result = Factorial.calculate(x);

    }

    // Проверяем, что вычисление результата не слишком затянуто вариант 2
    @Test
    void factorialCalculateTest_TimeOut3s() {

        //given:
        int x = 100000;

        //assert
        assertTimeout(Duration.ofSeconds(3), () -> Factorial.calculate(x));
    }

}