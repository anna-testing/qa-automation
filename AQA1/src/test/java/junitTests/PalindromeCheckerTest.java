package junitTests;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PalindromeCheckerTest {

    static Stream<Object[]> palindromeData() {
        return Stream.of(
                new Object[]{"radar", true},
                new Object[]{"Madam, I'm Adam", true},
                // manual-solution: new Object[]{"Аргентина манит и разграбится", true},
                // ai-assisted-solution: добавлены корректный кириллический палиндром и отрицательный кириллический сценарий.
                new Object[]{"А роза упала на лапу Азора", true},
                new Object[]{"Привет", false},
                new Object[]{"hello", false},
                new Object[]{"OpenAI", false},
                new Object[]{"", true},
                new Object[]{"a", true},
                new Object[]{"abacaba", true},
                new Object[]{"12321", true},
                new Object[]{"12345", false}
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("palindromeData")
    @DisplayName("PalindromeChecker: параметризованный тест с @MethodSource — строка и ожидаемый результат") // manual-solution: Параметризованная проверка палиндромов и обычных строк
    void isPalindromeTest(String input, boolean expected) {
        assertEquals(expected, PalindromeChecker.isPalindrome(input),
                () -> "isPalindrome(\"" + input + "\") должен быть " + expected);
    }

    @Test
    @DisplayName("PalindromeChecker: null возвращает true (пустая строка)") // manual-solution: Передача null не вызывает исключение и возвращает true
    void isPalindromeNull() {
        assertTrue(PalindromeChecker.isPalindrome(null), "null должен считаться палиндромом");
    }

    @Test
    @DisplayName("PalindromeChecker: регистр не учитывается") // manual-solution: Регистр символов не влияет на определение палиндрома
    void isPalindromeCaseInsensitive() {
        assertTrue(PalindromeChecker.isPalindrome("RaDaR"));
        assertTrue(PalindromeChecker.isPalindrome("MaDaM"));
    }

    @Test
    @DisplayName("PalindromeChecker: игнорирование пробелов и знаков препинания") // manual-solution: Пробелы и знаки препинания не влияют на проверку палиндрома
    void isPalindromeIgnoreSpacesAndPunctuation() {
        assertTrue(PalindromeChecker.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(PalindromeChecker.isPalindrome("Was it a car or a cat I saw?"));
    }

    @Test
    @DisplayName("PalindromeChecker: не-палиндромы") // manual-solution: Метод возвращает false для непалиндромов
    void isPalindromeNotPalindrome() {
        assertFalse(PalindromeChecker.isPalindrome("hello"));
        assertFalse(PalindromeChecker.isPalindrome("world"));
        assertFalse(PalindromeChecker.isPalindrome("OpenAI"));
    }
}
