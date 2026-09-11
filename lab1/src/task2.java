import java.util.ArrayList;
import java.util.Scanner;

public class task2 {
    // 18. В заданной последовательности натуральных чисел для всех трех-, четырех- и
    // пятизначных чисел определить, образуют ли цифры натурального числа убывающую
    // последовательность. Вывести соответствующее сообщение.
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
            String s = String.valueOf(num);
            int len = s.length();

            if (len >= 3 && len <= 5) {
                if (isDescending(s)) {
                    System.out.println("Число " + num + " образует убывающую последовательность.");
                } else {
                    System.out.println("Число " + num + " НЕ образует убывающую последовательность.");
                }
            }
        }

        scanner.close();
    }

    public static boolean isDescending(String s) {
        char prev = s.charAt(0);
        boolean first = true;

        for (char ch : s.toCharArray()) {
            if (first) {
                first = false;
                continue;
            }

            if (ch >= prev) {
                return false;
            }
            prev = ch;
        }
        return true;
    }

}
