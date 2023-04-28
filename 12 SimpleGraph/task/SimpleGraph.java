import java.util.*;
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

  public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
  {
    // Узлы задаются позициями в списке vertex.
    // Возвращается список узлов -- путь из VFrom в VTo.
    // Список пустой, если пути нету.
    if (VFrom < 0 || VTo < 0 || VFrom > max_vertex || VTo > max_vertex) {
      return new ArrayList<>();
    }

    clearVertex(vertex);
    ArrayList<Vertex> path = new ArrayList<>();
    int [] parents = new int[max_vertex];

    vertex[VFrom].Hit = true;
    parents[VFrom] = VFrom;

    return bfs(VFrom, VFrom, VTo, new LinkedList<>(), parents, path);
  }

  public ArrayList<Vertex> WeakVertices()
  {
    // возвращает список узлов вне треугольников
    ArrayList<Vertex> weakVertex = new ArrayList<>();
    clearVertex(vertex);
    for (int i = 0; i < max_vertex; i++) {
      if (vertex[i].Hit) {
        continue;
      }
      if (!inTriangle(i, getNearVertex(i))) {
        weakVertex.add(vertex[i]);
      }
    }
    return weakVertex;
  }

  private boolean inTriangle(int v, ArrayList<Integer> nearVertex) {
    for (int i = 0; i < nearVertex.size(); i++) {
      for (int j = 0; j < nearVertex.size(); j++){
        if (IsEdge(nearVertex.get(i), nearVertex.get(j))) {
          vertex[v].Hit = true;
          vertex[nearVertex.get(i)].Hit = true;
          vertex[nearVertex.get(j)].Hit = true;
          return true;
        }
      }
    }
    return false;
  }

  private ArrayList<Integer> getNearVertex(int v) {
    ArrayList<Integer> nearVertex = new ArrayList<>();
    for (int i = 0; i < max_vertex; i++) {
      if (m_adjacency[v][i] == 1) {
        nearVertex.add(i);
      }
    }
    return nearVertex;
  }

  private ArrayList<Vertex> bfs(int start, int vFrom, int vTo, Queue<Vertex> vertexList, int[] parents, ArrayList<Vertex> path){
    int nearVertexIndex = getNewFromIndex(vFrom);
    if (nearVertexIndex == vTo) {
      parents[vTo] = vFrom;
      return getPath(start, vTo, parents, path);
    }
    if (nearVertexIndex >= 0) {
      vertex[nearVertexIndex].Hit = true;
      parents[nearVertexIndex] = vFrom;
      vertexList.add(vertex[nearVertexIndex]);
      return bfs(start, vFrom, vTo, vertexList, parents, path);
    }
    if (vertexList.isEmpty()) {
      return new ArrayList<>();
    }
    return bfs(start, getVertexIndex(vertexList.remove()), vTo, vertexList, parents, path);
  }

  private ArrayList<Vertex> getPath(int start, int vTo, int[] parents, ArrayList<Vertex> path){
    Vertex v = vertex[vTo];
    while (v != vertex[start]){
      path.add(v);
      v = vertex[parents[vTo]];
      vTo = parents[vTo];
    }
    path.add(vertex[start]);
    Collections.reverse(path);
    return path;
  }

  private int getNewFromIndex(int vFrom) {
    for (int i = 0; i < vertex.length; i++){
      if (vertex[i] != null && IsEdge(vFrom, i) && !vertex[i].Hit) {
        return i;
      }
    }
    return -1;
  }

  private int getVertexIndex(Vertex v){
    for (int i = 0; i < vertex.length; i++){
      if (vertex[i] != null && vertex[i].Value == v.Value) {
        return i;
      }
    }
    return -1;
  }
}