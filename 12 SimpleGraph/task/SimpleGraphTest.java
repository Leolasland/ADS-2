import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

  @Test
  void WeakVertices() {
    SimpleGraph graph = new SimpleGraph(9);
    ArrayList<Vertex> weak;
    for (int i = 0; i < 9; i++) {
      graph.AddVertex(i);
    }
    weak = graph.WeakVertices();
    assertEquals(9, weak.size());

    graph.AddEdge(0, 1);
    graph.AddEdge(0, 2);
    graph.AddEdge(0, 4);
    graph.AddEdge(1, 2);
    graph.AddEdge(1, 3);
    graph.AddEdge(2, 3);
    graph.AddEdge(2, 5);
    graph.AddEdge(4, 5);
    graph.AddEdge(5, 6);
    graph.AddEdge(5, 7);
    graph.AddEdge(6, 7);
    graph.AddEdge(7, 8);
    weak = graph.WeakVertices();

    List<Integer> comparedList = new ArrayList<>();
    comparedList.add(4);
    comparedList.add(8);

    List<Integer> result = new ArrayList<>();
    for (Vertex v : graph.WeakVertices()){
      result.add(v.Value);
    }

    assertEquals(comparedList, result);
  }

  @Test
  void WeakVerticesEmpty() {
    SimpleGraph graph = new SimpleGraph(7);
    ArrayList<Vertex> weak;
    for (int i = 0; i < 7; i++) {
      graph.AddVertex(i);
    }
    graph.AddEdge(0, 1);
    graph.AddEdge(0, 2);
    graph.AddEdge(1, 2);
    graph.AddEdge(1, 3);
    graph.AddEdge(2, 3);
    graph.AddEdge(4, 5);
    graph.AddEdge(4, 6);
    graph.AddEdge(5, 6);

    weak = graph.WeakVertices();

    assertTrue(weak.isEmpty());
  }
}