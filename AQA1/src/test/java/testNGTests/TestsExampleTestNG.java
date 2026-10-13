package testNGTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestsExampleTestNG {

    // ai-assisted-solution: priority задаёт третью позицию теста в порядке TestNG.
    @Test(priority = 3, description = "Третий тест: 145 больше 0")
    public void is1shouldBeThird() {
        //Метод должен быть вызван третьим
        System.out.println("Hi I am third test");
        System.out.println("Here is my assertion");
        // manual-solution: assertion был закомментирован.
        Assert.assertTrue(145 > 0, "145 должно быть больше 0");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    // ai-assisted-solution: priority задаёт первую позицию теста в порядке TestNG.
    @Test(priority = 1, description = "Первый тест: 6 больше 5")
    public void is2shouldBeFirst() {
        //Метод должен быть вызван первым
        System.out.println("Hi I am first test");
        System.out.println("Here is my assertion");
        Assert.assertTrue(6 > 5, "6 должно быть больше 5");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    // ai-assisted-solution: priority задаёт вторую позицию теста в порядке TestNG.
    @Test(priority = 2, description = "Второй тест: true остаётся true")
    public void is3shouldBeSecond() {
        //Метод должен быть вызван вторым
        System.out.println("Hi I am second test");
        System.out.println("Here is my assertion");
        Assert.assertTrue(true, "true должно оставаться true");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    // ai-assisted-solution: методы жизненного цикла выполняются до и после всех тестов класса.
    @BeforeClass
    public void setUp() {
        System.out.println("Сейчас TestNG-тесты будут запущены в верном порядке");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Спасибо за внимание!");
    }
}
