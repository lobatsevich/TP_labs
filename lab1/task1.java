import java.util.ArrayList;
import java.util.Scanner;

public class task1 {
    // 6. Найти все p-значные числа из заданной последовательности натуральных чисел, в
    // записи которых цифры упорядочены, и подсчитать их количество.

    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> orderedNumbers = new ArrayList<>();

        int counter = 0;

        System.out.print("В ведите длину числа");
        int p = scanner.nextInt();


        System.out.println("Введите натуральные числа, для завершения ввода введите 0:");

        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            numbers.add(input);
        }

        for (int num : numbers) {
            String s = String.valueOf(num);

            if (isOrdered(s) && s.length() == p) {
                orderedNumbers.add(num);
                counter += 1;
            }
        }

        System.out.println("Количество чисел: " + counter);
        System.out.println("Упорядоченные числа: " + orderedNumbers);

        scanner.close();
    }

    public static boolean isOrdered(String s) {
        boolean st = true;
        boolean re = true;

        char prev = s.charAt(0);

        for (char ch : s.toCharArray()) {
            if (ch > prev) {
                st = false;
            }
            if (ch < prev) {
                re = false;
            }

            prev = ch;
        }

        return re || st;
    }
}