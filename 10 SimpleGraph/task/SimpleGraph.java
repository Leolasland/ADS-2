import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

class Vertex
{
  public int Value;
  public boolean Hit;
  public Vertex(int val)
  {
    Value = val;
    Hit = false;
  }
}

class SimpleGraph
{
  Vertex [] vertex;
  int [][] m_adjacency;
  int max_vertex;

  public SimpleGraph(int size)
  {
    max_vertex = size;
    m_adjacency = new int [size][size];
    vertex = new Vertex[size];
  }

  public void AddVertex(int value)
  {
    // ваш код добавления новой вершины
    // с значением value
    // в незанятую позицию vertex
    for (int i = 0; i < max_vertex; i++) {
      if (vertex[i] == null) {
        vertex[i] = new Vertex(value);
        return;
      }
    }
  }

  // здесь и далее, параметры v -- индекс вершины
  // в списке  vertex
  public void RemoveVertex(int v)
  {
    // ваш код удаления вершины со всеми её рёбрами
    if (v < 0 || v > max_vertex) {
      return;
    }
    for (int i = 0; i < max_vertex; i++) {
      m_adjacency[v][i] = 0;
      m_adjacency[i][v] = 0;
    }
    vertex[v] = null;
  }

  public boolean IsEdge(int v1, int v2)
  {
    // true если есть ребро между вершинами v1 и v2
    if (v1 < 0 || v2 < 0 || v1 > max_vertex || v2 > max_vertex) {
      return false;
    }
    return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
  }

  public void AddEdge(int v1, int v2)
  {
    // добавление ребра между вершинами v1 и v2
    if (v1 < 0 || v2 < 0 || v1 > max_vertex || v2 > max_vertex) {
      return;
    }
    m_adjacency[v1][v2] = 1;
    m_adjacency[v2][v1] = 1;
  }

  public void RemoveEdge(int v1, int v2)
  {
    // удаление ребра между вершинами v1 и v2
    if (v1 < 0 || v2 < 0 || v1 > max_vertex || v2 > max_vertex) {
      return;
    }
    m_adjacency[v1][v2] = 0;
    m_adjacency[v2][v1] = 0;
  }

  public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
  {
    // Узлы задаются позициями в списке vertex.
    // Возвращается список узлов -- путь из VFrom в VTo.
    // Список пустой, если пути нету.
    ArrayList<Vertex> result = new ArrayList<>();
    if (VFrom < 0 || VTo < 0 || VFrom > max_vertex || VTo > max_vertex) {
      return result;
    }
    Stack<Integer> nodesToCheck = new Stack<>();
    clearVertex(vertex);
    nodesToCheck.push(VFrom);
    for (int currentVert = VFrom;
         nodesToCheck.size() != 0 && currentVert != VTo;
         currentVert = nodesToCheck.peek()) {
      vertex[currentVert].Hit = true;
      int nextVertex = findNextVertex(currentVert, VTo);

      if (nextVertex == -1 && nodesToCheck.size() == 1) {
        nodesToCheck.pop();
        break;
      }

      if (nextVertex == -1) {
        nodesToCheck.pop();
        continue;
      }

      nodesToCheck.push(nextVertex);
    }
    return (ArrayList<Vertex>) nodesToCheck.stream().map(index -> vertex[index]).collect(Collectors.toList());
  }

  private void clearVertex(Vertex [] v) {
    for (Vertex clear : v) {
      clear.Hit = false;
    }
  }

  private int findNextVertex(int currentVert, int target) {
    int nextVert = -1;
    for (int i = 0; i < m_adjacency.length; i++) {
      if (m_adjacency[currentVert][i] == 1 && i == target)
        return i;
      if (m_adjacency[currentVert][i] == 1 && !vertex[i].Hit && currentVert != i)
        nextVert = i;
    }
    return nextVert;
  }
}