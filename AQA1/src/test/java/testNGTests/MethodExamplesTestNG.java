package testNGTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junitTests.MethodExamples;

public class MethodExamplesTestNG {

    @BeforeClass
    public void setUp() {
        System.out.println("[TestNG BeforeClass] Подготовка к тестам...");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("[TestNG AfterClass] Тесты завершены.");
    }

    @Test(description = "factorial: 0! = 1, 1! = 1, 5! = 120")
    public void factorialTests() {
        // manual-solution: ранее использовались три жёстких Assert, первое падение остановило бы тест.
        // ai-assisted-solution: SoftAssert выполняет все проверки, а assertAll в конце собирает их результаты.
        SoftAssert softly = new SoftAssert();
        softly.assertEquals(MethodExamples.factorial(0), 1.0, "0! должен быть равен 1");
        softly.assertEquals(MethodExamples.factorial(1), 1.0, "1! должен быть равен 1");
        softly.assertEquals(MethodExamples.factorial(5), 120.0, "5! должен быть равен 120");
        softly.assertAll();
    }

    @Test(description = "isPrime: проверка простых и составных чисел, граничные случаи")
    public void isPrimeTests() {
        Assert.assertFalse(MethodExamples.isPrime(-5), "отрицательное");
        Assert.assertFalse(MethodExamples.isPrime(0), "0");
        Assert.assertFalse(MethodExamples.isPrime(1), "1");
        Assert.assertTrue(MethodExamples.isPrime(2), "2");
        Assert.assertTrue(MethodExamples.isPrime(3), "3");
        Assert.assertFalse(MethodExamples.isPrime(4), "4");
        Assert.assertTrue(MethodExamples.isPrime(13), "13");
        Assert.assertFalse(MethodExamples.isPrime(15), "15");
    }

    @Test(description = "isPerfect: проверка совершенных чисел 6, 28 и не-совершенных")
    public void isPerfectTests() {
        Assert.assertTrue(MethodExamples.isPerfect(6), "6 – совершенное");
        Assert.assertTrue(MethodExamples.isPerfect(28), "28 – совершенное");
        Assert.assertFalse(MethodExamples.isPerfect(12), "12 – не совершенное");
        Assert.assertFalse(MethodExamples.isPerfect(30), "30 – не совершенное");
    }

    @Test(description = "digitCountInNumber: подсчёт цифр в числе")
    public void digitCountInNumberTests() {
        Assert.assertEquals(1, MethodExamples.digitCountInNumber(5, 5), "одна цифра");
        Assert.assertEquals(0, MethodExamples.digitCountInNumber(123, 4), "отсутствие");
        Assert.assertEquals(3, MethodExamples.digitCountInNumber(10101, 1), "повторяющиеся");
        // manual-solution: Assert.assertEquals(3, MethodExamples.digitCountInNumber(777777, 7));
        // ai-assisted-solution: ожидается 6, потому что теперь считаются все вхождения цифры.
        Assert.assertEquals(MethodExamples.digitCountInNumber(777777, 7), 6, "все цифры одинаковы");
    }
}
