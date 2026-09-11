import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private Utils() {}

    public static ArrayList<Integer> readNumbers(Scanner scanner) {
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.print("Вводите числа. Для завершения введите 'q': ");

        while (true) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                int num = Integer.parseInt(input);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.print("Некорректный ввод");
            }
        }
        return numbers;
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