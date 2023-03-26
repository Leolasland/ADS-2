import java.io.*;
import java.util.*;


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

  int count;

  public BST(BSTNode<T> node)
  {
    Root = node;
    count = 1;
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
    count++;
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

  public boolean DeleteNodeByKey(int key)
  {
    // удаляем узел по ключу
    BSTFind<T> bstFind = FindNodeByKey(key);
    if (!bstFind.NodeHasKey) {
      return false; // если узел не найден
    }
    BSTNode<T> node = bstFind.Node;
    if (node.LeftChild != null && node.RightChild != null) {
      BSTNode<T> replace = FinMinMax(node.RightChild, false);
      if (node.Parent != null && node.Parent.LeftChild == node) {
        node.Parent.LeftChild = replace;
      } else if (node.Parent != null && node.Parent.RightChild == node) {
        node.Parent.RightChild = replace;
      }
      replace.Parent = node.Parent;
      if (node.RightChild != replace) {
        replace.RightChild = node.RightChild;
        node.RightChild.Parent = replace;
      }
      replace.LeftChild = node.LeftChild;
      replace.LeftChild.Parent = replace;
      if (node == Root) {
        Root = replace;
      }
    }
    else if (node.LeftChild == null && node.RightChild == null) {
      if (node.Parent != null && node.Parent.LeftChild == node) {
        node.Parent.LeftChild = null;
      } else if (node.Parent != null && node.Parent.RightChild == node) {
        node.Parent.RightChild = null;
      }
      if (node == Root) {
        Root = null;
      }
    } else {
      if (node.RightChild != null) {
        if (node.Parent != null && node.Parent.LeftChild == node) {
          node.Parent.LeftChild = node.RightChild;
        } else if (node.Parent != null && node.Parent.RightChild == node) {
          node.Parent.RightChild = node.RightChild;
        }
        node.RightChild.Parent = node.Parent;
        if (node == Root) {
          Root = node.RightChild;
        }
      } else {
        if (node.Parent != null && node.Parent.LeftChild == node) {
          node.Parent.LeftChild = node.LeftChild;
        } else if (node.Parent != null && node.Parent.RightChild == node) {
          node.Parent.RightChild = node.LeftChild;
        }
        node.LeftChild.Parent = node.Parent;
        if (node == Root) {
          Root = node.LeftChild;
        }
      }
    }
    count--;
    return true;
  }

  public int Count()
  {
    if (Root == null) {
      return 0;
    }
    return count; // количество узлов в дереве
  }

}