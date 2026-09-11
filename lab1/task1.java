import java.util.ArrayList;
import java.util.Scanner;

public class task1 {
    // 6. Найти все p-значные числа из заданной последовательности натуральных чисел, в
    // записи которых цифры упорядочены, и подсчитать их количество.

    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);

        int counter = 0;

        System.out.print("В ведите длину числа: ");
        int p = scanner.nextInt();

        ArrayList<Integer> numbers = Utils.readNumbers(scanner);
        ArrayList<Integer> orderedNumbers = new ArrayList<>();

        for (int num : numbers) {
            String s = String.valueOf(num);

            if (Utils.isOrdered(s) && s.length() == p) {
                orderedNumbers.add(num);
                counter += 1;
            }
        }

        System.out.print("Количество чисел: " + counter);
        System.out.print("\nУпорядоченные числа: " + orderedNumbers);

        scanner.close();
    }

}