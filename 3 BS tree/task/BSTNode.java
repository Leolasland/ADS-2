import java.util.ArrayDeque;
import java.util.ArrayList;

class BSTNode<T>
{
  public int NodeKey; // ключ узла
  public T NodeValue; // значение в узле
  public BSTNode<T> Parent; // родитель или null для корня
  public BSTNode<T> LeftChild; // левый потомок
  public BSTNode<T> RightChild; // правый потомок

  public BSTNode(int key, T val, BSTNode<T> parent)
  {
    NodeKey = key;
    NodeValue = val;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

// промежуточный результат поиска
class BSTFind<T>
{
  // null если в дереве вообще нету узлов
  public BSTNode<T> Node;

  // true если узел найден
  public boolean NodeHasKey;

  // true, если родительскому узлу надо добавить новый левым
  public boolean ToLeft;

  public BSTFind() { Node = null; }
}

class BST<T>
{
  BSTNode<T> Root; // корень дерева, или null

  public BST(BSTNode<T> node)
  {
    Root = node;
  }

  public BSTFind<T> FindNodeByKey(int key)
  {
    // ищем в дереве узел и сопутствующую информацию по ключу
    BSTFind<T> bstFind = new BSTFind<>();
    if (Root == null) {
      return bstFind;
    }
    BSTNode<T> node = searchNode(Root, key);
    bstFind.Node = node;
    bstFind.NodeHasKey = node.NodeKey == key;
    if (!bstFind.NodeHasKey) {
      bstFind.ToLeft = (key < node.NodeKey);
    }
    return bstFind;
  }

  private BSTNode<T> searchNode(BSTNode<T> node, int key) {
    if (key < node.NodeKey && node.LeftChild != null) {
      return searchNode(node.LeftChild, key);
    }
    if (key > node.NodeKey && node.RightChild != null) {
      return searchNode(node.RightChild, key);
    }
    return node;
  }

  public boolean AddKeyValue(int key, T val)
  {
    // добавляем ключ-значение в дерево
    if (Root == null) {
      Root = new BSTNode<>(key, val, null);
      return true;
    }
    BSTFind<T> bstFind = FindNodeByKey(key);
    if (bstFind.NodeHasKey) {
      return false; // если ключ уже есть
    }
    BSTNode<T> node = new BSTNode<>(key, val, bstFind.Node);
    if (bstFind.ToLeft) {
      bstFind.Node.LeftChild = node;
    } else {
      bstFind.Node.RightChild = node;
    }
    return true;
  }

  public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
  {
    // ищем максимальный/минимальный ключ в поддереве
    if (!FindMax && FromNode.LeftChild != null) {
      return FinMinMax(FromNode.LeftChild, false);
    }
    if (FindMax && FromNode.RightChild != null) {
      return FinMinMax(FromNode.RightChild, true);
    }
    return FromNode;
  }

  public boolean DeleteNodeByKey(int key) {
    // удаляем узел по ключу
    BSTFind<T> findToDelete = FindNodeByKey(key);
    if (!findToDelete.NodeHasKey) {
      return false; // если узел не найден
    }
    BSTNode<T> node = findToDelete.Node;
    if (node.LeftChild == null && node.RightChild == null) {
      if (Root == node) {
        Root = null;
        return true;
      }
      if (node.Parent.LeftChild == node) {
        node.Parent.LeftChild = null;
        return true;
      }
      node.Parent.RightChild = null;
      return true;
    }

    if (node.RightChild == null) {
      deleteIfHasOneChild(node, node.LeftChild);
      return true;
    }
    if (node.LeftChild == null) {
      deleteIfHasOneChild(node, node.RightChild);
      return true;
    }

    BSTNode<T> replace = FinMinMax(node.RightChild, false);
    if (node == Root) {
      Root = replace;
    }
    if (node != Root && node.Parent.RightChild == node) {
      node.Parent.RightChild = replace;
    }
    if (node != Root && node.Parent.LeftChild == node) {
      node.Parent.LeftChild = replace;
    }
    replace.LeftChild = node.LeftChild;
    if (replace.LeftChild != null) {
      replace.LeftChild.Parent = replace;
    }
    replace.Parent.LeftChild = replace.RightChild;
    if (replace.RightChild != null) {
      replace.RightChild.Parent = replace.Parent;
    }
    replace.Parent = node.Parent;

    if (node.RightChild != replace) {
      replace.RightChild = node.RightChild;
      if (replace.RightChild != null) {
        replace.RightChild.Parent = replace;
      }
    }
    return true;
  }

  public void deleteIfHasOneChild(BSTNode<T> node, BSTNode<T> replace) {
    replace.Parent = node.Parent;
    if (node == Root) {
      Root = replace;
      return;
    }
    if (replace.Parent.LeftChild == node) {
      replace.Parent.LeftChild = replace;
      return;
    }
    replace.Parent.RightChild = replace;

  }

  public int Count()
  {
    if (Root == null) {
      return 0;
    }
    return countNodes(Root); // количество узлов в дереве
  }

  private int countNodes(BSTNode<T> node) {
    if (node == null) {
      return 0;
    }
    return 1 + countNodes(node.LeftChild) + countNodes(node.RightChild);
  }

  public ArrayList<BSTNode> WideAllNodes() {
    ArrayList<BSTNode> result = new ArrayList<>();
    if (Root == null) {
      return result;
    }
    ArrayDeque<BSTNode<T>> dequeNodes = new ArrayDeque<>();
    result.add(Root);
    dequeNodes.add(Root);
    return getAllNodes(result, dequeNodes);
  }

  private ArrayList<BSTNode> getAllNodes(ArrayList<BSTNode> list, ArrayDeque<BSTNode<T>> dequeNodes) {
    if (dequeNodes == null || dequeNodes.isEmpty()) {
      return list;
    }
    BSTNode<T> node = dequeNodes.pollFirst();
    if (node.LeftChild != null) {
      list.add(node.LeftChild);
      dequeNodes.add(node.LeftChild);
    }
    if (node.RightChild != null) {
      list.add(node.RightChild);
      dequeNodes.add(node.RightChild);
    }
    return getAllNodes(list, dequeNodes);
  }

  public ArrayList<BSTNode> DeepAllNodes(int order) {
    ArrayList<BSTNode> result = new ArrayList<>();
    if (Root == null) {
      return result;
    }
    if (order == 0) {
      return inOrderNodes(result, Root);
    }
    if (order == 1) {
      return postOrderNodes(result, Root);
    }
    if (order == 2) {
      return preOrderNodes(result, Root);
    }
    return result;
  }

  private ArrayList<BSTNode> inOrderNodes(ArrayList<BSTNode> list, BSTNode<T> node) {
    if (node.LeftChild != null) {
      inOrderNodes(list, node.LeftChild);
    }
    list.add(node);
    if (node.RightChild != null) {
      inOrderNodes(list, node.RightChild);
    }
    return list;
  }

  private ArrayList<BSTNode> postOrderNodes(ArrayList<BSTNode> list, BSTNode<T> node) {
    if (node.LeftChild != null) {
      postOrderNodes(list, node.LeftChild);
    }
    if (node.RightChild != null) {
      postOrderNodes(list, node.RightChild);
    }
    list.add(node);
    return list;
  }

  private ArrayList<BSTNode> preOrderNodes(ArrayList<BSTNode> list, BSTNode<T> node) {
    list.add(node);
    if (node.LeftChild != null) {
      preOrderNodes(list, node.LeftChild);
    }
    if (node.RightChild != null) {
      preOrderNodes(list, node.RightChild);
    }
    return list;
  }
}