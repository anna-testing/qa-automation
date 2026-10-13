package junitTests;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;


@DisplayName("TestsExampleJunit: упорядоченные тесты с @BeforeAll/@AfterAll") // manual-solution: Жизненный цикл и последовательность выполнения JUnit-тестов
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // ai-assisted-solution: включает обработку @Order
public class TestsExampleJunit {

    private static int testCounter = 0;

    @Test
    @Order(1)
    @DisplayName("is2shouldBeFirst: первый тест") // manual-solution: Первый тест: число 6 больше числа 5
    void is2shouldBeFirst() {
        System.out.println("Hi I am first test");
        System.out.println("Here is my assertion");
        assertTrue(6 > 5, "6 должно быть больше 5");
        System.out.println("Now I will perish");
        System.out.println("Bye");
        testCounter++;
    }

    @Test
    @Order(2)
    @DisplayName("is3shouldBeSecond: второй тест") // manual-solution: Второй тест: значение false остаётся ложным
    void is3shouldBeSecond() {
        System.out.println("Hi I am second test");
        System.out.println("Here is my assertion");
        assertFalse(false, "false должно быть false");
        System.out.println("Now I will perish");
        System.out.println("Bye");
        testCounter++;
    }

    @Test
    @Order(3)
    @DisplayName("is1shouldBeThird: третий тест") // manual-solution: Третий тест: число 145 больше нуля
    void is1shouldBeThird() {
        System.out.println("Hi I am third test");
        System.out.println("Here is my assertion");
        assertTrue(145 > 0, "145 должно быть больше 0");
        System.out.println("Now I will perish");
        System.out.println("Bye");
        testCounter++;
    }

    @BeforeAll
    @DisplayName("Подготовка к тестам") // manual-solution: Сброс счётчика перед выполнением всех тестов
    static void setUp() {
        // manual-solution: System.out.println("[BeforeAll] Подготовка к тестам...");
        // ai-assisted-solution: текст приведён к точной формулировке из задания.
        System.out.println("Сейчас автотесты будут запущены в верном порядке");
        testCounter = 0;
    }

    @AfterAll
    @DisplayName("Завершение тестов") // manual-solution: Вывод количества выполненных тестов после завершения запуска
    static void tearDown() {
        // manual-solution: System.out.println("[AfterAll] Тесты завершены. Выполнено тестов: " + testCounter);
        // ai-assisted-solution: текст приведён к точной формулировке из задания.
        System.out.println("Спасибо за внимание!");
    }
}
