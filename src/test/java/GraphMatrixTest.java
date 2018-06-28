import graph.GraphMatrix;
import graph.structures.Edge;
import graph.structures.Vertex;
import org.junit.Assert;
import org.junit.Test;

public class GraphMatrixTest {

    @Test
    public void Constructor() throws Exception {
        GraphMatrix<String, String> graph = new GraphMatrix<>(60);

        Vertex<String> a = graph.insertVertex(graph.createVertex("A", null));
        Vertex<String> b = graph.insertVertex(graph.createVertex("B", null));
        Vertex<String> c = graph.insertVertex(graph.createVertex("C", null));

        graph.insertEdge(new Edge<>("e1", a, b, null));
        graph.insertEdge(new Edge<>("e3", b, a, null));
        graph.insertEdge(new Edge<>("e2", b, c, null));

        Assert.assertTrue(graph.areAdjacent(a, b));
        graph.removeVertex(a);
        graph.removeVertex(b);

        System.out.println(graph.getVertices());
    }
}