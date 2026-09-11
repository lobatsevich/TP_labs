import java.util.ArrayList;
import java.util.Scanner;

public class task2 {
    // 18. В заданной последовательности натуральных чисел для всех трех-, четырех- и
    // пятизначных чисел определить, образуют ли цифры натурального числа убывающую
    // последовательность. Вывести соответствующее сообщение.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = Utils.readNumbers(scanner);

        System.out.print("\n--- Результаты проверки ---\n");

        for (int num : numbers) {
            String s = String.valueOf(num);
            int len = s.length();

            if (len >= 3 && len <= 5) {
                if (Utils.isDescending(s)) {
                    System.out.print("Число " + num + " образует убывающую последовательность.\n");
                } else {
                    System.out.print("Число " + num + " НЕ образует убывающую последовательность.\n");
                }
            }
        }

        scanner.close();
    }

}
