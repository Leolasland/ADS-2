import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

  @Test
  void addVertex(){
    SimpleGraph simpleGraph = new SimpleGraph(3);
    simpleGraph.AddVertex(100);
    simpleGraph.AddVertex(50);
    simpleGraph.AddVertex(20);
    assertEquals(100, simpleGraph.vertex[0].Value);
    assertEquals(50, simpleGraph.vertex[1].Value);
    assertEquals(20, simpleGraph.vertex[2].Value);
    for (int i = 0; i < simpleGraph.m_adjacency.length; i++) {
      for (int j = 0; j < simpleGraph.m_adjacency.length; j++) {
        assertEquals(0, simpleGraph.m_adjacency[i][j]);
      }
    }
  }

  @Test
  void addEdge() {
    SimpleGraph simpleGraph = new SimpleGraph(3);
    simpleGraph.AddVertex(100);
    simpleGraph.AddVertex(50);
    simpleGraph.AddVertex(20);
    simpleGraph.AddEdge(0, 1000);
    simpleGraph.AddEdge(0, 2);
    assertEquals(1, simpleGraph.m_adjacency[0][2]);
    assertEquals(1, simpleGraph.m_adjacency[2][0]);
    assertEquals(0, simpleGraph.m_adjacency[0][1]);
  }

  @Test
  void isEdge() {
    SimpleGraph simpleGraph = new SimpleGraph(3);
    simpleGraph.AddVertex(100);
    simpleGraph.AddVertex(50);
    simpleGraph.AddVertex(20);
    simpleGraph.AddEdge(0, 2);
    assertTrue(simpleGraph.IsEdge(0, 2));
    assertTrue(simpleGraph.IsEdge(2, 0));
    assertFalse(simpleGraph.IsEdge(0, 1000));
    assertFalse(simpleGraph.IsEdge(0, 1));
  }

  @Test
  void removeEdge() {
    SimpleGraph simpleGraph = new SimpleGraph(3);
    simpleGraph.AddVertex(100);
    simpleGraph.AddVertex(50);
    simpleGraph.AddVertex(20);
    simpleGraph.AddEdge(0, 2);
    assertEquals(1, simpleGraph.m_adjacency[0][2]);
    assertEquals(1, simpleGraph.m_adjacency[2][0]);

    simpleGraph.RemoveEdge(0, 1000);
    simpleGraph.RemoveEdge(0, 2);
    assertEquals(0, simpleGraph.m_adjacency[0][2]);
    assertEquals(0, simpleGraph.m_adjacency[2][0]);
  }

  @Test
  void removeVertex() {
    SimpleGraph simpleGraph = new SimpleGraph(3);
    simpleGraph.AddVertex(100);
    simpleGraph.AddVertex(50);
    simpleGraph.AddVertex(20);
    simpleGraph.AddEdge(0, 2);
    assertEquals(100, simpleGraph.vertex[0].Value);
    assertEquals(20, simpleGraph.vertex[2].Value);
    assertEquals(1, simpleGraph.m_adjacency[0][2]);
    assertEquals(1, simpleGraph.m_adjacency[2][0]);

    simpleGraph.RemoveVertex(100);
    simpleGraph.RemoveVertex(0);
    assertEquals(0, simpleGraph.m_adjacency[0][2]);
    assertEquals(0, simpleGraph.m_adjacency[2][0]);
    assertNull(simpleGraph.vertex[0]);
  }
}
