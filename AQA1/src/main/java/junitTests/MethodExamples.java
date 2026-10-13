package junitTests;

import static java.lang.Math.sqrt;
public class MethodExamples {

    public static double factorial(int n) {
        // ai-assisted-solution: добавлена обработка недопустимого отрицательного аргумента.
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определён только для неотрицательных чисел");
        }
        double result = 1.0;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int m = 3; m <= (int) sqrt(n); m += 2) {
            if (n % m == 0) return false;
        }
        return true;
    }

    public static boolean isPerfect(int n) {
        // ai-assisted-solution: числа меньше 2, включая 1, не являются совершенными.
        if (n < 2) return false;
        int sum = 1;
        for (int m = 2; m <= n / 2; m++) {
            if (n % m > 0) continue;
            sum += m;
            if (sum > n) break;
        }
        return sum == n;
    }

    public static int digitCountInNumber(int n, int m) {
        // ai-assisted-solution: преобразование в long до Math.abs корректно обрабатывает Integer.MIN_VALUE.
        long number = Math.abs((long) n);
        m = Math.abs(m);
        if (m > 9) {
            return 0; // m не является одной цифрой
        }
        if (number == 0) {
            return m == 0 ? 1 : 0;
        }
        int count = 0;
        while (number > 0) {
            // manual-solution: ранее дополнительно проверялась чётность позиции:
            // if ((number % 10 == m) && (position % 2 == 0))
            // ai-assisted-solution: теперь считаются все вхождения заданной цифры.
            if (number % 10 == m) {
                count++;
            }
            number /= 10;
        }
        return count;
    }
}
