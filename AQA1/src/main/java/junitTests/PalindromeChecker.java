package junitTests;
public class PalindromeChecker {

    public static boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return true; // пустая строка считается палиндромом
        }

        // manual-solution: ранее любая строка с кириллицей ошибочно считалась палиндромом:
        // if (input.matches(".*\\p{IsCyrillic}.*")) return true;
        // ai-assisted-solution: кириллица теперь проходит обычную посимвольную проверку.
        // Убираем все символы, не являющиеся буквами или цифрами, и приводим к нижнему регистру.
        // \p{L} (буквы) и \p{N} (цифры) поддерживают любые алфавиты, включая кириллицу.
        String cleaned = input.replaceAll("[^\\p{L}\\p{N}]", "").toLowerCase();

        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
