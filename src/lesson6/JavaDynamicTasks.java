package lesson6;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */

    // ТРУДОЕМКОСТЬ O(first.length * second.length)
    // РЕСУРСОЕМКОСТЬ O(first.length * second.length)
    public static String longestCommonSubSequence(String first, String second){

        int length1 = first.length();
        int length2 = second.length();
        int[][] matrix = new int[length2 + 1][length1 + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < length2; i++){
            for (int j = 1; j < length1; j++){
                if (second.charAt(i) == first.charAt(j))
                    matrix[i+1][j+1] = matrix[i][j] + 1;
                else matrix[i+1][j+1] = Math.max(matrix[i][j+1], matrix[i+1][j]);
            }
        }

        while (length2 > 0 && length1 > 0){
            if (first.charAt(length1 - 1) == second.charAt(length2 - 1)){
                sb.append(first.charAt(length1 - 1));
                length1--;
                length2--;
            } else if (matrix[length2 - 1][length1] > matrix[length2][length1 - 1]) length2--;
            else length1--;
        }

        return sb.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */

    // ТРУДОЕМКОСТЬ O(height * width)
    // РЕСУРСОЕМКОСТЬ O(height * width)
    public static int shortestPathOnField(String inputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        Scanner scanner = new Scanner(inputFile);
        List<int[]> matrix = new ArrayList<>();

        while (scanner.hasNextLine()){
            String[] strnums = scanner.nextLine().split(" ");
            int[] nums = new int[strnums.length];
            for (int i = 0; i < nums.length; i++)
                nums[i] = Integer.parseInt(strnums[i]);
            matrix.add(nums);
        }

        int height = matrix.size();
        int width = matrix.get(0).length;

        for (int i = 1; i < height; i++)
            matrix.get(i)[0] = matrix.get(i-1)[0] + matrix.get(i)[0];
        for (int i = 1; i < width; i++)
            matrix.get(0)[i] = matrix.get(0)[i-1] + matrix.get(0)[i];
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                int h = matrix.get(i)[j-1];
                int w = matrix.get(i-1)[j];
                int d = matrix.get(i-1)[j-1];
                matrix.get(i)[j] = Math.min(Math.min(h, w), d) + matrix.get(i)[j];
            }
        }

        return matrix.get(height-1)[width-1];
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
