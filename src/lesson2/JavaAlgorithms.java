package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Первый момент должен быть раньше второго.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    // ТРУДОЕМКОСТЬ O(N^2)
    // РЕСУРСКОЕМКОСТЬ O(N)
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        Scanner scanner = new Scanner(inputFile);
        List<Integer> list = new ArrayList<>();
        List<Pair<Integer, Integer>> mins = new ArrayList<>();
        List<Pair<Integer, Integer>> maxs = new ArrayList<>();

        while (scanner.hasNextInt()) list.add(scanner.nextInt());

        if (list.get(1) - list.get(0) > 0)
            mins.add(new Pair<>(list.get(0), 0));
        for (int i = 1; i < list.size()-1; i++){
            if (list.get(i+1) - list.get(i) > 0 && list.get(i) - list.get(i-1) < 0)
                mins.add(new Pair<>(list.get(i), i));
            else if (list.get(i+1) - list.get(i) < 0 && list.get(i) - list.get(i-1) > 0)
                maxs.add(new Pair<>(list.get(i), i));
        }

        int firstTime = 0, secondTime = 1, d = 0;

        for (Pair<Integer, Integer> min : mins){
            for (Pair<Integer, Integer> max : maxs){
                if (max.getFirst() - min.getFirst() > d && max.getSecond() > min.getSecond()){
                    d = max.getFirst() - min.getFirst();
                    firstTime = min.getSecond();
                    secondTime = max.getSecond();
                }
            }
        }

        return new Pair<>(firstTime+1, secondTime+1);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     */

    // ТРУДОЕМКОСТЬ O(N)
    // РЕСУРСОЕМКОСТЬ O(1)
    static public int josephTask(int menNumber, int choiceInterval) {

        int index = 0;
        for (int i = 1; i <= menNumber; i++) {
            index = (index + choiceInterval) % i;
        }
        return index+1;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */

    // ТРУДОЕМКОСТЬ O(M*N)
    // РЕСУРСОЕМКОСТЬ O(1)
    static public String longestCommonSubstring(String firs, String second) {

        String sub = "";
        StringBuilder sub1 = new StringBuilder();

        int i = 0;
        while (i < firs.length()){
            int j = 0;
            if (sub.length() > firs.length() - i+1 || sub.length() > second.length() - j+1)
                break;
            while (j < second.length()){
                if (sub.length() > firs.length() - i+1 || sub.length() > second.length() - j+1)
                    break;
                if (firs.charAt(i) == second.charAt(j)){
                    sub1.append(firs.charAt(i));
                    int lastI = i;
                    int lastJ = j;
                    for (i += 1, j += 1; i < firs.length() && j < second.length(); i++, j++){
                        if (firs.charAt(i) == second.charAt(j)){
                            sub1.append(firs.charAt(i));
                            if (i == firs.length()-1 || j == second.length()-1){
                                if (sub1.length() > sub.length()) sub = sub1.toString();
                                sub1 = new StringBuilder();
                                break;
                            }
                        } else {
                            if (sub1.length() > sub.length()) sub = sub1.toString();
                            sub1 = new StringBuilder();
                            break;
                        }
                    }
                    i = lastI;
                    j = lastJ;
                }
                j++;
            }
            i++;
        }
        return sub;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */

    // ТРУДОЕМКОСТЬ O(N*sqrt(N))
    // РЕСУРСОЕМКОСТЬ O(1)
    static public int calcPrimesNumber(int limit) {

        int amount = 0;
        boolean f;
        if (limit >= 2) {
            for (int num = 2; num <= limit; num++){
                f = true;
                for (int i = 2; i <= Math.sqrt(num); i++)
                    if (num % i == 0) {
                        f = false;
                        break;
                    }
                if (f) amount++;
            }
        }
        return amount;
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
