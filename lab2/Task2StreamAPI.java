import java.util.stream.IntStream;

public class Task2StreamAPI {
    //Округлить все элементы матрицы до целого числа, в строках с
    //четным номером — с недостатком, в строках с нечетным номером — с
    //избытком)

    public static void main(String[] argv) {
        float[][] matrix = Utils.createMatrix();
        if (matrix == null) return;

        IntStream.range(0, matrix.length)
                .forEach(i -> {
                    IntStream.range(0, matrix[i].length)
                            .forEach(j -> {
                                if (i % 2 == 0) {
                                    matrix[i][j] = (float) Math.floor(matrix[i][j]);
                                } else {
                                    matrix[i][j] = (float) Math.ceil(matrix[i][j]);
                                }
                            });
                });

        Utils.printResult2(matrix);
    }
}
