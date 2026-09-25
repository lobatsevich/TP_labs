import java.util.*;
import java.util.stream.Stream;

public class Task1StreamAPI {

    //Для каждой строки матрицы найти сумму элементов матрицы,
    //расположенных между первым и вторым положительными элементами
    //каждой строки. Отсортировать строки матрицы по этой сумме. Строки, в
    //которых все элементы отрицательные — удалить. Строки у которых только
    //один положительный элемент — удалить

    public static void main(String[] argv) {
        float[][] matrix = Utils.createMatrix();
        if (matrix == null) return;

        List<Task1.SummedRow> validRows = Stream.of(matrix)
                .map(row -> {
                    Float sum = Task1.getSumBetweenFirstAndSecondPositives(row);
                    return sum != null ? new Task1.SummedRow(row, sum) : null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(r -> r.sum))
                .toList();

        Utils.printResult1(validRows);
    }
}
