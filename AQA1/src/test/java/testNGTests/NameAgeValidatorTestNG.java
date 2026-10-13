package testNGTests;

import junitTests.NameAgeValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// ai-assisted-solution: TestNG-аналог параметризованных тестов валидатора.
public class NameAgeValidatorTestNG {

    // ai-assisted-solution: DataProvider передаёт в happy path несколько корректных строк как отдельные запуски.
    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return new Object[][]{
                {"Алексей 25 лет"},
                {"Мария 30 лет"},
                {"John 42 years"},
                {"Анна 19 лет"}
        };
    }

    @Test(dataProvider = "validUsers", description = "Валидные имя и возраст проходят проверку")
    public void validateHappyPath(String input) {
        Assert.assertTrue(NameAgeValidator.validate(input),
                "Ожидалось true для валидного ввода: " + input);
    }

    // ai-assisted-solution: отдельный DataProvider содержит некорректные форматы для проверки исключений.
    @DataProvider(name = "invalidFormats")
    public Object[][] invalidFormats() {
        return new Object[][]{{"Алексей"}, {"25 лет"}, {"Алексей 25"}, {"Алексей abc лет"}};
    }

    // ai-assisted-solution: expectedExceptions проверяет тип ожидаемого исключения средствами TestNG.
    @Test(dataProvider = "invalidFormats", expectedExceptions = IllegalArgumentException.class,
            description = "Некорректный формат вызывает исключение")
    public void invalidFormatThrowsException(String input) {
        NameAgeValidator.validate(input);
    }
}
