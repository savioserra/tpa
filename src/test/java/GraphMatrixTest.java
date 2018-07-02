import graph.representations.list.DirectedListAdjMatrix;
import graph.representations.matrix.DirectedGraphMatrix;
import graph.representations.matrix.UndirectedGraphMatrix;
import graph.shared.Graph;
import org.junit.Test;

public class GraphMatrixTest {

    @Test
    public void Constructor() throws Exception {
        Graph<String, String> graph = new DirectedListAdjMatrix<>();

        graph.insertVertex("A", null);
        graph.insertVertex("B", null);
        graph.insertVertex("C", null);

        graph.insertEdge("e1", "A", "B", null);
        graph.insertEdge("e2", "B", "A", null);
        graph.insertEdge("e3", "B", "C", null);
        graph.insertEdge("e4", "C", "B", null);

        graph.insertVertex("D", null);
        graph.insertEdge("a1", "D", "A", null);
        graph.removeVertex("B");
        graph.removeVertex("B");
        graph.insertVertex("K", null);
        graph.insertEdge("k1", "K", "A", null);

        System.out.println(graph);

        System.out.println(graph.areAdjacent("K", "D"));;
        System.out.println(graph.areAdjacent("K", "A"));
        System.out.println(graph.areAdjacent("A", "D"));
        System.out.println(graph.areAdjacent("D", "A"));
    }
}