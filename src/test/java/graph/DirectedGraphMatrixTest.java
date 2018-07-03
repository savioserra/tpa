package graph;

import graph.common.Graph;
import graph.common.Vertex;
import graph.representations.matrix.DirectedGraphMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedGraphMatrixTest {
    private Graph<String, String> graph;

    @Before
    public void setUp() throws Exception {
        graph = new DirectedGraphMatrix<>();

        // v2 <- v1 -> v3
        graph.insertVertex("v1", null);
        graph.insertVertex("v2", null);
        graph.insertVertex("v3", null);

        graph.insertEdge("e1", "v1", "v2", null);
        graph.insertEdge("e2", "v1", "v3", null);
    }

    @Test
    public void vertices() {
        assertEquals(3, graph.vertices().size());
        assertTrue(graph.vertices().contains("v1"));
        assertTrue(graph.vertices().contains("v2"));

        graph.vertices().forEach(graph::removeVertex);

        assertEquals(0, graph.vertices().size());
    }

    @Test
    public void edges() {
        assertEquals(2, graph.edges().size());
        assertTrue(graph.edges().contains("e1"));

        graph.edges().forEach(graph::removeEdge);

        assertEquals(0, graph.edges().size());
        assertFalse(graph.edges().contains("e1"));
    }

    @Test
    public void incomingEdges() {
        assertEquals(1, graph.incomingEdges("v2").size());
        assertEquals(0, graph.incomingEdges("v1").size());
        assertTrue(graph.incomingEdges("v2").contains("e1"));
    }

    @Test
    public void outgoingEdges() {
        assertEquals(0, graph.outgoingEdges("v2").size());
        assertEquals(2, graph.outgoingEdges("v1").size());
        assertTrue(graph.outgoingEdges("v1").contains("e1"));
    }

    @Test
    public void opposite() {
        assertNull(graph.opposite("v1", "e3"));
        assertEquals("v2", graph.opposite("v1", "e1"));
    }

    @Test
    public void endVertices() {
        assertNull(graph.endVertices("e3"));

        String[] endVertices = graph.endVertices("e1");

        assertEquals("v1", endVertices[0]);
        assertEquals("v2", endVertices[1]);
        assertEquals(2, endVertices.length);
    }

    @Test
    public void areAdjacent() {
        assertTrue(graph.areAdjacent("v1", "v2"));
        assertFalse(graph.areAdjacent("v2", "v1"));
        assertFalse(graph.areAdjacent("v1", "e1"));
    }

    @Test
    public void insertVertex() {
        Vertex<String> vertex = graph.insertVertex("v1", "test"); // v1 already exists

        assertEquals("test", vertex.getData());
        assertEquals("v1", vertex.getLabel());
        assertEquals(3, graph.vertices().size()); // checking update
        assertTrue(graph.vertices().contains("v1"));
    }

    @Test
    public void removeVertex() {
        assertEquals(3, graph.vertices().size());

        graph.removeVertex("v1");

        assertEquals(2, graph.vertices().size());
        assertEquals(0, graph.edges().size());
    }

    @Test
    public void removeEdge() {
        assertEquals(2, graph.edges().size());

        graph.removeEdge("e1");

        assertEquals(1, graph.edges().size());
    }

    @Test
    public void insertEdge() throws Exception {
        assertEquals(2, graph.edges().size());

        graph.insertEdge("e1", "v2", "v3", null);
        String[] endpoints = graph.endVertices("e1");

        assertEquals("v2", endpoints[0]);
        assertEquals("v3", endpoints[1]);
        assertEquals(2, graph.edges().size()); // should remains intact
    }

    @Test
    public void findVertexByLabel() {
    }

    @Test
    public void findEdgeByLabel() {
    }

    @Test
    public void createVertexIndex() {
    }

    @Test
    public void getVertices() {
    }

    @Test
    public void getEdges() {
    }

    @Test
    public void getMatrix() {
    }
}