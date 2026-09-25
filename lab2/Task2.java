public class Task2 {
    //Округлить все элементы матрицы до целого числа, в строках с
    //четным номером — с недостатком, в строках с нечетным номером — с
    //избытком)

    public static void main(String[] argv) {
        float[][] matrix = Utils.createMatrix();
        if (matrix == null) return;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 == 0) {
                    matrix[i][j] = (float) Math.floor(matrix[i][j]);
                } else {
                    matrix[i][j] = (float) Math.ceil(matrix[i][j]);
                }
            }
        }

        Utils.printResult2(matrix);
    }
}
