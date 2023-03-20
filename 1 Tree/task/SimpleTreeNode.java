import java.util.*;

public class SimpleTreeNode<T>
{
  public T NodeValue; // значение в узле
  public SimpleTreeNode<T> Parent; // родитель или null для корня
  public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

  public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
  {
    NodeValue = val;
    Parent = parent;
    Children = new LinkedList<>();
  }
}

class SimpleTree<T>
{
  public SimpleTreeNode<T> Root; // корень, может быть null

  public SimpleTree(SimpleTreeNode<T> root)
  {
    Root = root;
  }

  public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
  {
    // ваш код добавления нового дочернего узла существующему ParentNode
    if (NewChild != null) {
      NewChild.Parent = ParentNode;
      ParentNode.Children.add(NewChild);
    }
  }

  public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
  {
    // ваш код удаления существующего узла NodeToDelete
    if (NodeToDelete == null || NodeToDelete == Root) {
      return;
    }
    NodeToDelete.Parent.Children.remove(NodeToDelete);
    if (!NodeToDelete.Children.isEmpty()) {
      for (SimpleTreeNode<T> childNode : NodeToDelete.Children) {
        childNode.Parent = NodeToDelete.Parent;
        NodeToDelete.Parent.Children.add(childNode);
      }
    }
    NodeToDelete.NodeValue = null;
    NodeToDelete.Parent = null;
    NodeToDelete.Children.clear();
  }

  public List<SimpleTreeNode<T>> GetAllNodes()
  {
    // ваш код выдачи всех узлов дерева в определённом порядке
    List<SimpleTreeNode<T>> list = new ArrayList<>();
    ArrayDeque<SimpleTreeNode<T>> deque = new ArrayDeque<>();
    deque.add(Root);
    while (!deque.isEmpty()) {
      SimpleTreeNode<T> node = deque.poll();
      list.add(node);
      if (!node.Children.isEmpty()) {
        deque.addAll(node.Children);
      }
    }
    return list;
  }

  public List<SimpleTreeNode<T>> FindNodesByValue(T val)
  {
    // ваш код поиска узлов по значению
    return null;
  }

  public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
  {
    // ваш код перемещения узла вместе с его поддеревом --
    // в качестве дочернего для узла NewParent
  }

  public int Count()
  {
    // количество всех узлов в дереве
    return 0;
  }

  public int LeafCount()
  {
    // количество листьев в дереве
    return 0;
  }
}