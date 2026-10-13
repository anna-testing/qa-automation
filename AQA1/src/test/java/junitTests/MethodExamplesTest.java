package junitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class MethodExamplesTest {

    @Test
    @DisplayName("factorial: 0! = 1, 1! = 1, 5! = 120")
        // manual-solution: Вычисление факториала для 0, 1 и 5
    // Факториал возвращает ожидаемый результат
    // Проверка базовых значений факториала
    // Корректное вычисление факториала целого числа
    // Факториал: граничные и положительные значения
    void factorialTests() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(1.0, MethodExamples.factorial(0), "0!"), //я бы написала "0! должен быть равен 1"
                () -> Assertions.assertEquals(1.0, MethodExamples.factorial(1), "1!"), //я бы написала "1! должен быть равен 1"
                () -> Assertions.assertEquals(120.0, MethodExamples.factorial(5), "5!"), //я бы написала "5! должен быть равен 120"
                // ai-assisted-solution: добавлена проверка исключения для отрицательного факториала.
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> MethodExamples.factorial(-1),
                        "Для отрицательного числа должно возникать исключение")
        );
    }

    @Test
    @DisplayName("isPrime: проверка простых и составных чисел, граничные случаи")
        // manual-solution: Распознавание простых чисел и граничных значений
        // Определение простых и составных чисел
        // Проверка числа на простоту
        // Метод отличает простые числа от составных
        // Проверка простоты отрицательных, граничных и положительных чисел
    void isPrimeTests() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(MethodExamples.isPrime(-5), "отрицательное"),
                () -> Assertions.assertFalse(MethodExamples.isPrime(0), "0"),
                () -> Assertions.assertFalse(MethodExamples.isPrime(1), "1"),
                () -> Assertions.assertTrue(MethodExamples.isPrime(2), "2"),
                () -> Assertions.assertTrue(MethodExamples.isPrime(3), "3"),
                () -> Assertions.assertFalse(MethodExamples.isPrime(4), "4"),
                () -> Assertions.assertTrue(MethodExamples.isPrime(13), "13"),
                () -> Assertions.assertFalse(MethodExamples.isPrime(15), "15")
        );
    }

    @Test
    @DisplayName("isPerfect: проверка совершенных чисел 6, 28 и не‑совершенных")
        // manual-solution: Определение совершенных и несовершенных чисел
        // Проверка числа на совершенность
        // Метод распознаёт совершенные числа 6 и 28
        // Проверка положительных и отрицательных случаев для совершенных чисел
        // Совершенные числа возвращают true, остальные — false
    void isPerfectTests() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(MethodExamples.isPerfect(6), "6 – совершенное"),
                () -> Assertions.assertTrue(MethodExamples.isPerfect(28), "28 – совершенное"),
                // ai-assisted-solution: добавлена граничная проверка числа 1.
                () -> Assertions.assertFalse(MethodExamples.isPerfect(1), "1 – не совершенное"),
                () -> Assertions.assertFalse(MethodExamples.isPerfect(12), "12 – не совершенное"),
                () -> Assertions.assertFalse(MethodExamples.isPerfect(30), "30 – не совершенное")
        );
    }

    @Test
    @DisplayName("digitCountInNumber: подсчёт цифр в числе") // manual-solution: Подсчёт заданной цифры на чётных позициях справа
    void digitCountInNumberTests() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, MethodExamples.digitCountInNumber(5, 5), "одна цифра"),
                () -> Assertions.assertEquals(0, MethodExamples.digitCountInNumber(123, 4), "отсутствие"),
                () -> Assertions.assertEquals(3, MethodExamples.digitCountInNumber(10101, 1), "повторяющиеся"),
                // manual-solution: ранее ожидалось 3 из-за ошибочного подсчёта только на чётных позициях.
                // () -> Assertions.assertEquals(3, MethodExamples.digitCountInNumber(777777, 7), "все цифры одинаковы")
                () -> Assertions.assertEquals(6, MethodExamples.digitCountInNumber(777777, 7),
                        "Все шесть цифр 7 должны быть подсчитаны"),
                // ai-assisted-solution: добавлены проверки числа 0 и некорректной искомой цифры 12.
                () -> Assertions.assertEquals(1, MethodExamples.digitCountInNumber(0, 0),
                        "Число 0 содержит одну цифру 0"),
                () -> Assertions.assertEquals(0, MethodExamples.digitCountInNumber(123, 12),
                        "Искомое значение должно быть одной цифрой")
        );
    }
}
