import java.util.Random;
import java.util.Scanner;

public class Utils {
    public static void print(String arg) {
        System.out.print(arg + '\n');
    }

    public static float[][] createMatrix() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        print("Введите размерность матрицы: ");
        int n = scanner.nextInt();

        if (n < 1) return null;

        float[][] a = new float[n][n];

        print("Выберите способ заполнения матрицы:");
        print("R - рандом\nG - сам");
        String choice = scanner.next();

        if (!choice.equalsIgnoreCase("G") && !choice.equalsIgnoreCase("R")) {
            print("Некорректный ввод");
            return null;
        }

        if (choice.equalsIgnoreCase("G")) {
            print("Введите " + n * n + " чисел: ");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (choice.equalsIgnoreCase("R")) {
                    a[i][j] =  (random.nextInt(201) - 100) / 10f;
                } else if (choice.equalsIgnoreCase("G")) {
                    a[i][j] = scanner.nextFloat();
                }
            }
        }

        print("\nПолученная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6.1f", a[i][j]);
            }
            print("");
        }

        return a;
    }

    public static void printResult1(java.util.List<Task1.SummedRow> rows) {
        Utils.print("\nРезультат: ");
        if (rows == null || rows.isEmpty()) {
            Utils.print("Матрица пуста");
        } else {
            for (Task1.SummedRow summedRow : rows) {
                for (float x : summedRow.row) {
                    System.out.printf("%6.1f ", x);
                }
                System.out.printf(" | сумма %6.1f\n", summedRow.sum);
            }
        }
    }

    public static void printResult2(float[][] matrix) {
        Utils.print("\nРезультат: ");
        for (float[] row : matrix) {
            for (float x : row) {
                System.out.printf("%6.0f ", x);
            }
            Utils.print("");
        }
    }
}
