import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

  @Test
  void BreadthFirstSearch() {
    SimpleGraph graph = new SimpleGraph(8);
    ArrayList<Vertex> route;
    for (int i = 0; i < 8; i++) {
      graph.AddVertex(i);
    }
    route = graph.BreadthFirstSearch(0, 5);
    assertEquals(route.size(), 0);
    graph.AddEdge(0, 1);
    graph.AddEdge(0, 2);
    graph.AddEdge(1, 3);
    graph.AddEdge(1, 4);
    graph.AddEdge(2, 5);
    graph.AddEdge(2, 6);
    graph.AddEdge(3, 7);
    route = graph.BreadthFirstSearch(0, 5);
    assertEquals(route.get(0).Value, 0);
    assertEquals(route.get(1).Value, 2);
    assertEquals(route.get(2).Value, 5);
    assertEquals(route.size(), 3);

    route = graph.BreadthFirstSearch(0, 7);
    assertEquals(route.get(0).Value, 0);
    assertEquals(route.get(1).Value, 1);
    assertEquals(route.get(2).Value, 3);
    assertEquals(route.get(3).Value, 7);
    assertEquals(route.size(), 4);

    graph.AddEdge(0, 7);
    route = graph.BreadthFirstSearch(0, 7);
    assertEquals(route.get(0).Value, 0);
    assertEquals(route.get(1).Value, 7);
    assertEquals(route.size(), 2);

    route = graph.BreadthFirstSearch(0, 100);
    assertEquals(route.size(), 0);

    graph = new SimpleGraph(7);
    IntStream.range(0, 8).forEach(graph::AddVertex);
    graph.AddEdge(0, 1);
    graph.AddEdge(1, 3);
    graph.AddEdge(3, 4);
    graph.AddEdge(4, 5);

    graph.AddEdge(0, 2);
    graph.AddEdge(2, 6);
    graph.AddEdge(6, 5);
    route = graph.BreadthFirstSearch(0, 5);
    assertEquals(route.get(0).Value, 0);
    assertEquals(route.get(1).Value, 2);
    assertEquals(route.get(2).Value, 6);
    assertEquals(route.get(3).Value, 5);
    assertEquals(route.size(), 4);
  }
}