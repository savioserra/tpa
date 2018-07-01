import graph.representations.Matrix.UndirectedGraphMatrix;
import graph.shared.Graph;
import org.junit.Test;

public class GraphMatrixTest {

    @Test
    public void Constructor() throws Exception {
        Graph<String, String> graph = new UndirectedGraphMatrix<>();

        graph.insertVertex("A", null);
        graph.insertVertex("B", null);
        graph.insertVertex("C", null);

        graph.insertEdge("e1", "A", "B", null);
        graph.insertEdge("e2", "B", "A", null);
        graph.insertEdge("e3", "B", "C", null);
        graph.insertEdge("e4", "C", "B", null);

        graph.insertVertex("D", null);
        graph.insertEdge("a1", "D", "A", null);

        System.out.println(graph);
    }
}