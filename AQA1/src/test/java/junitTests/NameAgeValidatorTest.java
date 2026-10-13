package junitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameAgeValidatorTest {

    // manual-solution: первоначальный вариант. Это обычный @Test с циклом,
    // а не настоящий параметризованный тест с @ValueSource.
    // @Test
    // @DisplayName("NameAgeValidator: проверка happy path через массив и цикл")
    // void validateHappyPath() {
    //     String[] validInputs = {
    //             "Алексей 25 лет",
    //             "Мария 30 лет",
    //             "John 42 years",
    //             "Анна 19 лет",
    //             "Владимир 65 лет"
    //     };
    //
    //     for (String input : validInputs) {
    //         assertTrue(NameAgeValidator.validate(input),
    //                 () -> "Ожидалось true для валидного ввода: '" + input + "'");
    //     }
    // }

    // ai-assisted-solution: исправленный параметризованный happy path.
    @ParameterizedTest(name = "Валидация корректной строки: {0}")
    @ValueSource(strings = {
            "Алексей 25 лет",
            "Мария 30 лет",
            "John 42 years",
            "Анна 19 лет",
            "Владимир 65 лет"
    })
    @DisplayName("Валидные имя и возраст проходят проверку")
    void validateHappyPath(String input) {
        assertTrue(
                NameAgeValidator.validate(input),
                () -> "Ожидалось true для валидного ввода: '" + input + "'"
        );
    }

    @Test
    @DisplayName("NameAgeValidator: @ValueSource — невалидные имена (<= 3 букв)") // manual-solution: Имя длиной не более трёх символов не проходит проверку
    void validateShortName() {
        String[] invalidInputs = {
                "Ива 25 лет",
                "Аня 30 лет",
                "Tom 42 years",
                "Мак 19 лет"
        };

        for (String input : invalidInputs) {
            assertFalse(NameAgeValidator.validate(input),
                    () -> "Ожидалось false для короткого имени: '" + input + "'");
        }
    }

    @Test
    @DisplayName("NameAgeValidator: @ValueSource — невалидный возраст (<= 18)") // manual-solution: Возраст 18 лет и меньше не проходит проверку
    void validateInvalidAge() {
        String[] invalidInputs = {
                "Алексей 18 лет",
                "Мария 15 лет",
                "John 1 years",
                "Анна 0 лет"
        };

        for (String input : invalidInputs) {
            assertFalse(NameAgeValidator.validate(input),
                    () -> "Ожидалось false для возраста <= 18: '" + input + "'");
        }
    }

    @Test
    @DisplayName("NameAgeValidator: null ввод бросает исключение") // manual-solution: Передача null вызывает IllegalArgumentException
    void validateNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> NameAgeValidator.validate(null));
    }

    @Test
    @DisplayName("NameAgeValidator: пустая строка бросает исключение") // manual-solution: Пустая строка вызывает IllegalArgumentException
    void validateEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> NameAgeValidator.validate(""));
    }

    @ParameterizedTest(name = "Невалидный ввод: {0}")
    @ValueSource(strings = {
            "Алексей",
            "25 лет",
            "Алексей 25",
            "Алексей двадцать пять лет"
    })
    @DisplayName("NameAgeValidator: параметризованный тест с @ValueSource — ожидаем исключения") // manual-solution: Некорректный формат строки вызывает IllegalArgumentException
    void validateThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> NameAgeValidator.validate(input));
    }

    // ai-assisted-solution: добавлена проверка кастомного исключения, его сообщения и сохранённой причины.

    @ParameterizedTest(name = "Кастомное исключение для: {0}")
    @ValueSource(strings = {"Аня 18 лет"})
    @DisplayName("Ошибка assertion оборачивается в InvalidDataException")
    void invalidAssertionIsWrappedInCustomException(String input) {
        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> {
                    try {
                        assertTrue(NameAgeValidator.validate(input),
                                "Данные должны пройти валидацию: " + input);
                    } catch (AssertionError cause) {
                        throw new InvalidDataException(
                                "Проверка не пройдена для тестовых данных: " + input,
                                cause
                        );
                    }
                }
        );

        assertTrue(exception.getMessage().contains(input),
                "Сообщение должно содержать проблемные тестовые данные");
        assertInstanceOf(AssertionError.class, exception.getCause(),
                "Исходный AssertionError должен сохраниться как cause");
    }
}
