import java.util.ArrayList;
import java.util.Comparator;

public class Task1 {

    //Для каждой строки матрицы найти сумму элементов матрицы,
    //расположенных между первым и вторым положительными элементами
    //каждой строки. Отсортировать строки матрицы по этой сумме. Строки, в
    //которых все элементы отрицательные — удалить. Строки у которых только
    //один положительный элемент — удалить

    public static class SummedRow {
        float[] row;
        float sum;

        SummedRow(float[] row, float sum) {
            this.row = row;
            this.sum = sum;
        }
    }

    public static void main(String[] argv) {
        float[][] matrix = Utils.createMatrix();
        if (matrix == null) return;

        ArrayList<SummedRow> validRows = new ArrayList<>();

        for (float[] row : matrix) {
            Float sum = getSumBetweenFirstAndSecondPositives(row);

            if (sum != null) validRows.add(new SummedRow(row, sum));
        }

        validRows.sort(Comparator.comparingDouble(r -> r.sum));

        Utils.printResult1(validRows);
    }

    public static Float getSumBetweenFirstAndSecondPositives(float[] row) {
        int left = -1;
        int right = -1;

        for (int j = 0; j < row.length; j++) {
            if (row[j] > 0) {
                if (left == -1) {
                    left = j;
                } else if (right == -1) {
                    right = j;
                } else {
                    break;
                }
            }
        }

        if (left == -1 || right == -1) {
            return null;
        }

        float sum = 0;

        for (int j = left + 1; j < right; j++) {
            sum += row[j];
        }
        return sum;
    }
}