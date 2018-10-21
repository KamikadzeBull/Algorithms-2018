package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;
import java.util.regex.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    // ТРУДОЕМКОСТЬ O(Nlog(N)) ИЗ-ЗА СОРТИРОВКИ СЛИЯНИЯМИ
    // РЕСУРСОЕМКОСТЬ O(N)
    static public void sortTimes(String inputName, String outputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        File outputFile = new File(outputName);
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) lines.add(scanner.nextLine());

        Collections.sort(lines);

        for (String line: lines) printWriter.println(line);

        scanner.close();
        printWriter.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    // ТРУДОЕМКОСТЬ O(Nlog(N)) ИЗ-ЗА TREESET, TREEMAP
    // РЕСУРСОЕМКОСТЬ O(N)
    static public void sortAddresses(String inputName, String outputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        File outputFile = new File(outputName);
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        Pattern pattern = Pattern.compile(
                "(([A-Za-zА-Яа-яЁё]+\\s?)+)\\s-\\s(([A-Za-zА-Яа-яЁё]+\\s?)+\\d+)");
        Matcher matcher;

        Map<String, TreeSet<String>> base = new TreeMap<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            matcher = pattern.matcher(line);
            String name = "", address = "";
            if (matcher.matches()) {
                name = matcher.group(1);
                address = matcher.group(3);
            }

            if (!base.containsKey(address)){
                TreeSet<String> names = new TreeSet<>();
                names.add(name);
                base.put(address, names);
            } else base.get(address).add(name);
        }

        Object[] addresses = base.keySet().toArray();

        for (int i=0; i < base.size(); i++){
            printWriter.print(addresses[i].toString() + " - ");

            Object[] names = base.get(addresses[i].toString()).toArray();
            for (int j=0; j < names.length-1; j++){
                printWriter.print(names[j].toString() + ", ");
            }
            printWriter.println(names[names.length-1]);
        }

        scanner.close();
        printWriter.close();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */

    // ТРУДОЕМКОСТЬ O(Nlog(N)) ИЗ-ЗА СОРТИРОВКИ СЛИЯНИЯМИ
    // РЕСУРСОЕМКОСТЬ O(N)
    static public void sortTemperatures(String inputName, String outputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        File outputFile = new File(outputName);
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        List<Double> temperatures = new ArrayList<>();
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            temperatures.add(Double.parseDouble(str));
        }

        Collections.sort(temperatures);

        for (Double temperature: temperatures) printWriter.println(temperature);

        scanner.close();
        printWriter.close();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */

    // ТРУДОЕМКОСТЬ O(N), ЛИНЕЙНОЕ ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ
    // РЕСУРСОЕМКОСТЬ O(N)
    static public void sortSequence(String inputName, String outputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        File outputFile = new File(outputName);
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();

        while (scanner.hasNextInt()){
            int a = scanner.nextInt();
            if (!map.containsKey(a)){
                map.put(a,1);
            } else {
                int i = map.get(a) + 1;
                map.put(a,i);
            }
        }
        scanner.close();

        int max = 0;    // max frequency
        int min = -1;   // min value with max frequency
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            if (entry.getValue() > max){
                min = entry.getKey();
                max = entry.getValue();
            }
        }

        scanner = new Scanner(inputFile);
        while (scanner.hasNextInt()){
            int a = scanner.nextInt();
            if (a != min) list.add(a);
        }
        for (int i = 0; i < max; i++) {
            list.add(min);
        }
        for (int a: list) {
            printWriter.println(a);
        }

        scanner.close();
        printWriter.close();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
