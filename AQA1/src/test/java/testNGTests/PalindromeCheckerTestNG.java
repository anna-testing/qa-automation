package testNGTests;

import junitTests.PalindromeChecker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// ai-assisted-solution: TestNG-аналог параметризованного теста палиндрома.
public class PalindromeCheckerTestNG {

    // ai-assisted-solution: DataProvider передаёт в тест пары «строка — ожидаемый результат».
    @DataProvider(name = "palindromes")
    public Object[][] palindromeData() {
        return new Object[][]{
                {"radar", true},
                {"Madam, I'm Adam", true},
                {"А роза упала на лапу Азора", true},
                {"Привет", false},
                {"hello", false},
                {"", true},
                {"12321", true},
                {"12345", false}
        };
    }

    // ai-assisted-solution: один метод запускается отдельно для каждой пары из DataProvider.
    @Test(dataProvider = "palindromes", description = "Параметризованная проверка палиндромов")
    public void isPalindrome(String input, boolean expected) {
        Assert.assertEquals(PalindromeChecker.isPalindrome(input), expected,
                "Неверный результат для строки: " + input);
    }
}
