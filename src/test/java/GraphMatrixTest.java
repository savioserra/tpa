import graph.representations.Matrix.UndirectedGraphMatrix;
import org.junit.Test;

import java.util.Arrays;

public class GraphMatrixTest {

    @Test
    public void Constructor() throws Exception {
        UndirectedGraphMatrix<String, String> graph = new UndirectedGraphMatrix<>(64);

        graph.insertVertex("A", null);
        graph.insertVertex("B", null);
        graph.insertVertex("C", null);

        graph.insertEdge("e1", "A", "B", null);
        graph.insertEdge("e2", "B", "A", null);
        graph.insertEdge("e3", "B", "C", null);
        graph.insertEdge("e4", "C", "B", null);

        System.out.println(graph.areAdjacent("A", "B"));
        System.out.println(graph.incomingEdges("A"));
        System.out.println(graph.outgoingEdges("A"));

        System.out.println(Arrays.toString(graph.endVertices("e1")));

        graph.removeVertex("A");
        graph.removeVertex("B");

        System.out.println(graph.vertices());
        System.out.println(graph.edges());
        System.out.println(Arrays.toString(graph.endVertices("e1")));
    }
}