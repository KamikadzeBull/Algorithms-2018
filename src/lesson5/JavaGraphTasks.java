package lesson5;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */

    // ТРУДОЕМКОСТЬ O(V+E)
    // РЕСУРСОЕМКОСТЬ O(V+E)
    public static List<Graph.Edge> findEulerLoop(Graph graph) {

        for (Graph.Vertex vertex : graph.getVertices()) {
            int amount = graph.getNeighbors(vertex).size();
            if (amount % 2 == 1 || amount == 0) return new ArrayList<>();
        }

        List<Graph.Vertex> v = new ArrayList<>(graph.getVertices());
        Graph.Vertex start = v.get(0);
        v = new ArrayList<>();
        Stack<Graph.Vertex> stack = new Stack<>();

        List<Graph.Edge> e = new ArrayList<>(graph.getEdges());
        stack.add(start);

        while (!stack.isEmpty()){
            Graph.Vertex vertex1 = stack.peek();
            for (Graph.Vertex vertex2 : graph.getNeighbors(vertex1)){
                Graph.Edge edge = graph.getConnection(vertex1, vertex2);
                if (e.contains(edge)){
                    stack.push(vertex2);
                    e.remove(edge);
                    break;
                }
            }
            if (vertex1 == stack.peek())
                v.add(stack.pop());
        }

        e = new ArrayList<>();
        for (int i = 1; i < v.size(); i++)
            e.add(graph.getConnection(v.get(i-1), v.get(i)));

        return e;

    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */

    // ТРУДОЕМКОСТЬ O(V^2)
    // РЕСУРСОЕМКОСТЬ O(V)
    public static Path longestSimplePath(Graph graph) {

        LinkedList<Graph.Vertex> v = new LinkedList<>();
        for (Graph.Vertex vertex : graph.getVertices())
            if (search(graph, vertex, v)) break;

        return new Path(v);
    }

    private static boolean search(Graph graph, Graph.Vertex vertex, LinkedList<Graph.Vertex> v){

        if (v.size() == graph.getVertices().size())
            return true;
        for (Graph.Vertex vertex1 : graph.getNeighbors(vertex)){
            if (v.contains(vertex1))
                continue;
            v.add(vertex1);
            if (search(graph, vertex1, v))
                return true;
        }
        if (!v.isEmpty()) v.remove();
        return false;
    }
}
