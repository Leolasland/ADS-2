import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BalancedBSTTest {

  @Test
  void generateTree() {
    int [] a = {50, 25, 75, 23, 37, 62, 84};
    BalancedBST tree = new BalancedBST();
    tree.GenerateTree(a);
    assertEquals(50, tree.Root.NodeKey);
    assertNull(tree.Root.Parent);
    assertEquals(0, tree.Root.Level);
    assertEquals(25, tree.Root.LeftChild.NodeKey);
    assertEquals(75, tree.Root.RightChild.NodeKey);
    assertEquals(23, tree.Root.LeftChild.LeftChild.NodeKey);
    assertEquals(37, tree.Root.LeftChild.RightChild.NodeKey);
    assertEquals(62, tree.Root.RightChild.LeftChild.NodeKey);
    assertEquals(84, tree.Root.RightChild.RightChild.NodeKey);
    assertEquals(2, tree.Root.RightChild.RightChild.Level);
  }

  @Test
  void isBalanced() {
    int [] a = {50, 25, 75, 23, 37, 62, 84};
    BalancedBST tree = new BalancedBST();
    tree.GenerateTree(a);
    assertTrue(tree.IsBalanced(tree.Root));

    int [] a1 = {50, 75};
    tree = new BalancedBST();
    tree.GenerateTree(a1);
    tree.Root.LeftChild.LeftChild = new BSTNode(10, tree.Root.LeftChild);
    tree.Root.LeftChild.LeftChild.Level = 2;
    assertFalse(tree.IsBalanced(tree.Root));
  }
}
