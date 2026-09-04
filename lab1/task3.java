import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    // 30. Найти все числа-палиндромы из заданной последовательности чисел при
    // возведении которых в квадрат получают также числа-палиндромы. Число называется
    // палиндромом, если его запись читается одинаково слева направо и справа налево,
    // например, 12321.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Вводите числа. Для завершения введите 'q':");

        while (true) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            int num = Integer.parseInt(input);
            numbers.add(num);
        }

        System.out.println("\n--- Результаты проверки ---");

        for (int num : numbers) {
            if (isQuadraticPalindrome(num)) {
                System.out.println("Число " + num + " подходит");
            } else {
                System.out.println("Число " + num + " не подходит");
            }
        }
    }

    public static boolean isQuadraticPalindrome(int num) {
        Integer numSquare = num * num;

        return isPalindrome(String.valueOf(num)) && isPalindrome(String.valueOf(numSquare));
    }

    public static boolean isPalindrome(String num) {
        int r = num.length() - 1;
        int l = 0;
        while (l < r) {
            if (num.charAt(r) != num.charAt(l)) {
                return false;
            }
            r -= 1;
            l += 1;
        }
        return true;
    }
}
