package junitTests;

public class NameAgeValidator {

    public static boolean validate(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        // Ожидаемый формат: "Имя Возраст лет"
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format. Expected: 'Name Age years'");
        }

        String name = parts[0];
        String agePart = parts[1];
        String yearsWord = parts[2];

        // Проверяем, что последняя часть — "лет"/"years"
        if (!yearsWord.toLowerCase().matches("лет|years")) {
            throw new IllegalArgumentException("Expected 'лет' or 'years' as the last word");
        }

        // Проверяем, что возраст — число
        int age;
        try {
            age = Integer.parseInt(agePart);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Age must be a number");
        }

        // Имя должно содержать более 3 букв
        if (name.length() <= 3) {
            return false;
        }

        // Возраст должен быть больше 18
        if (age <= 18) {
            return false;
        }

        return true;
    }

    public static boolean validateWithCustomException(String input) throws InvalidDataException {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Input cannot be null or empty");
        }

        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3) {
            throw new InvalidDataException("Invalid format. Expected: 'Name Age years', got: '" + input + "'");
        }

        String name = parts[0];
        String agePart = parts[1];
        String yearsWord = parts[2];

        if (!yearsWord.toLowerCase().matches("лет|years")) {
            throw new InvalidDataException("Expected 'лет' or 'years' as the last word, got: '" + yearsWord + "'");
        }

        int age;
        try {
            age = Integer.parseInt(agePart);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Age must be a number, got: '" + agePart + "'");
        }

        if (name.length() <= 3) {
            throw new InvalidDataException("Name must be more than 3 characters, got: '" + name + "' (" + name.length() + ")");
        }

        if (age <= 18) {
            throw new InvalidDataException("Age must be greater than 18, got: " + age);
        }

        return true;
    }
}
