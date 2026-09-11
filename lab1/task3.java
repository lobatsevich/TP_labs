import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    // 30. Найти все числа-палиндромы из заданной последовательности чисел при
    // возведении которых в квадрат получают также числа-палиндромы. Число называется
    // палиндромом, если его запись читается одинаково слева направо и справа налево,
    // например, 12321.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = Utils.readNumbers(scanner);

        System.out.print("--- Результаты проверки ---\n");

        for (int num : numbers) {
            if (Utils.isQuadraticPalindrome(num)) {
                System.out.print("Число " + num + " подходит\n");
            } else {
                System.out.print("Число " + num + " не подходит\n");
            }
        }
    }

}
