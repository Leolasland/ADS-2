import java.util.*;

class BSTNode
{
  public int NodeKey; // ключ узла
  public BSTNode Parent; // родитель или null для корня
  public BSTNode LeftChild; // левый потомок
  public BSTNode RightChild; // правый потомок
  public int     Level; // глубина узла

  public BSTNode(int key, BSTNode parent)
  {
    NodeKey = key;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

class BalancedBST
{
  public BSTNode Root; // корень дерева

  public BalancedBST()
  {
    Root = null;
  }

  public void GenerateTree(int[] a)
  {
    // создаём дерево с нуля из неотсортированного массива a
    // ...
    Arrays.sort(a);
    int center = a.length / 2;
    Root = new BSTNode(a[center], null);
    Root.Level = 0;
    if (center - 1 >= 0) {
      generate(Root, a, 0, center - 1);
    }
    if (center + 1 < a.length) {
      generate(Root, a, center + 1, a.length - 1);
    }
  }

  private void generate(BSTNode parent, int [] a, int start, int end) {
    int center = (start + end) / 2;
    BSTNode node = new BSTNode(a[center], parent);
    node.Level = parent.Level + 1;
    if (node.NodeKey < parent.NodeKey) {
      parent.LeftChild = node;
    } else {
      parent.RightChild = node;
    }
    if (start == end) {
      return;
    }
    if (center - 1 >= 0) {
      generate(node, a, start, center - 1);
    }
    if (center + 1 < a.length) {
      generate(node, a, center + 1, end);
    }
  }

  public boolean IsBalanced(BSTNode root_node)
  {
    if (root_node.LeftChild == null && root_node.RightChild == null) {
      return true;
    }
    int rightLevel = maxLevel(root_node.RightChild);
    int leftLevel = maxLevel(root_node.LeftChild);

    return Math.abs(leftLevel - rightLevel) - root_node.Level <= 1;// сбалансировано ли дерево с корнем root_node
  }

  private int maxLevel(BSTNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(node.Level, Math.max(maxLevel(node.LeftChild), maxLevel(node.RightChild)));
  }
}  