import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

  @Test
  void DepthFirstSearch() {
    SimpleGraph graph = new SimpleGraph(4);
    graph.AddVertex(10);
    graph.AddVertex(15);
    graph.AddVertex(20);
    graph.AddVertex(30);
    assertEquals(10, graph.DepthFirstSearch(0, 0).get(0).Value);
    graph.AddEdge(0, 2);
    assertEquals(0, graph.DepthFirstSearch(0, 3).size());
    assertEquals(0, graph.DepthFirstSearch(0, 1).size());
    assertEquals(0, graph.DepthFirstSearch(2, 1).size());
    assertEquals(0, graph.DepthFirstSearch(2, 3).size());

    assertEquals(10, graph.DepthFirstSearch(0, 2).get(0).Value);
    assertEquals(20, graph.DepthFirstSearch(0, 2).get(1).Value);
    assertEquals(20, graph.DepthFirstSearch(2, 0).get(0).Value);
    assertEquals(10, graph.DepthFirstSearch(2, 0).get(1).Value);
    graph.AddEdge(1, 2);
    assertEquals(10, graph.DepthFirstSearch(0, 1).get(0).Value);
    assertEquals(20, graph.DepthFirstSearch(0, 1).get(1).Value);
    assertEquals(15, graph.DepthFirstSearch(0, 1).get(2).Value);
    graph.AddEdge(1, 3);
    assertEquals(10, graph.DepthFirstSearch(0, 3).get(0).Value);
    assertEquals(20, graph.DepthFirstSearch(0, 3).get(1).Value);
    assertEquals(15, graph.DepthFirstSearch(0, 3).get(2).Value);
    assertEquals(30, graph.DepthFirstSearch(0, 3).get(3).Value);
  }
}