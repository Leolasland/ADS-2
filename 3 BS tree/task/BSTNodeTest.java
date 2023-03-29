import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTNodeTest {

  @Test
  void findNodeByKey() {
    BST<Integer> bst = new BST<>(new BSTNode<Integer>(8, 8, null));
    bst.AddKeyValue(4, 4);
    //find present
    BSTFind<Integer> find = bst.FindNodeByKey(4);
    assertTrue(find.NodeHasKey);
    assertEquals(4, find.Node.NodeKey);
    //find left absent
    find = bst.FindNodeByKey(2);
    assertFalse(find.NodeHasKey);
    assertEquals(bst.Root.LeftChild, find.Node);
    assertTrue(find.ToLeft);
    //find right absent
    find = bst.FindNodeByKey(5);
    assertFalse(find.NodeHasKey);
    assertEquals(bst.Root.LeftChild, find.Node);
    assertFalse(find.ToLeft);
  }

  @Test
  void addKeyValue() {
    BST<Integer> bst = new BST<>(null);
    assertFalse(bst.FindNodeByKey(8).NodeHasKey);
    assertNull(bst.Root);
    assertTrue(bst.AddKeyValue(8, 8));
    assertEquals(8, bst.Root.NodeKey);
    //add left child
    assertFalse(bst.FindNodeByKey(4).NodeHasKey);
    assertTrue(bst.AddKeyValue(4, 4));
    assertEquals(4, bst.Root.LeftChild.NodeKey);
    assertNull(bst.Root.RightChild);
    assertNull(bst.Root.Parent);
    //add right child
    assertFalse(bst.FindNodeByKey(12).NodeHasKey);
    assertTrue(bst.AddKeyValue(12, 12));
    assertEquals(12, bst.Root.RightChild.NodeKey);
    //try to add present
    assertTrue(bst.FindNodeByKey(4).NodeHasKey);
    assertFalse(bst.AddKeyValue(4, 4));
  }

  @Test
  void finMinMax() {
    BST<Integer> bst = new BST<>(null);
    assertTrue(bst.AddKeyValue(8, 8));
    assertTrue(bst.AddKeyValue(4, 4));
    assertTrue(bst.AddKeyValue(12, 12));
    assertTrue(bst.AddKeyValue(2, 2));
    assertTrue(bst.AddKeyValue(6, 6));
    assertTrue(bst.AddKeyValue(1, 1));
    assertTrue(bst.AddKeyValue(3, 3));
    assertTrue(bst.AddKeyValue(5, 5));
    assertTrue(bst.AddKeyValue(7, 7));

    assertEquals(12, bst.FinMinMax(bst.Root, true).NodeKey);
    assertEquals(7, bst.FinMinMax(bst.FindNodeByKey(4).Node, true).NodeKey);
    assertEquals(3, bst.FinMinMax(bst.FindNodeByKey(2).Node, true).NodeKey);

    assertEquals(1, bst.FinMinMax(bst.Root, false).NodeKey);
    assertEquals(1, bst.FinMinMax(bst.FindNodeByKey(4).Node, false).NodeKey);
    assertEquals(5, bst.FinMinMax(bst.FindNodeByKey(6).Node, false).NodeKey);
  }

  @Test
  void deleteNodeByKey() {
    BST<Integer> bst = new BST<>(null);
    assertTrue(bst.AddKeyValue(8, 8));
    assertTrue(bst.AddKeyValue(4, 4));
    assertTrue(bst.AddKeyValue(12, 12));
    assertTrue(bst.AddKeyValue(2, 2));
    assertTrue(bst.AddKeyValue(6, 6));
    assertTrue(bst.AddKeyValue(1, 1));
    assertTrue(bst.AddKeyValue(3, 3));
    assertTrue(bst.AddKeyValue(5, 5));
    assertTrue(bst.AddKeyValue(7, 7));

    assertTrue(bst.FindNodeByKey(2).NodeHasKey);
    assertTrue(bst.DeleteNodeByKey(2));
    assertFalse(bst.FindNodeByKey(2).NodeHasKey);
    assertEquals(3, bst.FindNodeByKey(4).Node.LeftChild.NodeKey);
    assertEquals(6, bst.FindNodeByKey(4).Node.RightChild.NodeKey);
    assertNull(bst.FindNodeByKey(3).Node.RightChild);
    assertEquals(1, bst.FindNodeByKey(3).Node.LeftChild.NodeKey);
    assertEquals(4, bst.FindNodeByKey(3).Node.Parent.NodeKey);
    assertEquals(3, bst.FindNodeByKey(1).Node.Parent.NodeKey);
  }

  @Test
  void countTest() {
    BST<Integer> bst = new BST<>(null);
    assertEquals(0, bst.Count());
    assertTrue(bst.AddKeyValue(8, 8));
    assertEquals(1, bst.Count());
    assertTrue(bst.AddKeyValue(4, 4));
    assertEquals(2, bst.Count());
    assertTrue(bst.AddKeyValue(12, 12));
    assertTrue(bst.AddKeyValue(2, 2));
    assertEquals(4, bst.Count());
    assertTrue(bst.AddKeyValue(6, 6));
    assertTrue(bst.AddKeyValue(1, 1));
    assertTrue(bst.AddKeyValue(3, 3));
    assertEquals(7, bst.Count());
    assertTrue(bst.AddKeyValue(5, 5));
    assertTrue(bst.AddKeyValue(7, 7));
    assertEquals(9, bst.Count());
    assertTrue(bst.DeleteNodeByKey(4));
    assertEquals(8, bst.Count());
    assertTrue(bst.DeleteNodeByKey(2));
    assertTrue(bst.DeleteNodeByKey(12));
    assertTrue(bst.DeleteNodeByKey(3));
    assertEquals(5, bst.Count());
  }

  @Test
  void count() {
    BST<Integer> bst = new BST<>(new BSTNode<>(8, 8, null));
    bst.DeleteNodeByKey(0);
    assertEquals(1, bst.Count());
    bst.DeleteNodeByKey(8);
    assertEquals(0, bst.Count());

    bst.Root = new BSTNode<>(8, 8, null);
    bst.AddKeyValue(4, 4);
    assertEquals(2, bst.Count());
    bst.DeleteNodeByKey(8);
    assertEquals(1, bst.Count());

    bst.Root = new BSTNode<>(8, 8, null);
    int[] init = new int[]{4, 12};
    for (int i : init) {
      bst.AddKeyValue(i, i);
    }
    assertEquals(3, bst.Count());
  }

  @Test
  void WideAllNodes() {
    BST<Integer> bst = new BST<>(null);
    int[] init = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
    for (int i : init) {
      bst.AddKeyValue(i, i);
    }
    ArrayList<BSTNode> bstNodes = bst.WideAllNodes();
    for (int i = 0; i < init.length; i++) {
      assertEquals(init[i], bstNodes.get(i).NodeKey);
    }
  }

  @Test
  void DeepAllNodes() {
    BST<Integer> bst = new BST<>(null);
    int[] init = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
    for (int i : init) {
      bst.AddKeyValue(i, i);
    }
    ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(0);
    for (int i = 1; i <= 15; i++) {
      assertEquals(bstNodes.get(i - 1).NodeKey, i);
    }

    bstNodes = bst.DeepAllNodes(1);
    int[] tests = new int[]{1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 15, 14, 12, 8};
    for (int i = 0; i < tests.length; i++) {
      assertEquals(bstNodes.get(i).NodeKey, tests[i]);
    }

    bstNodes = bst.DeepAllNodes(2);
    tests = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
    for (int i = 0; i < tests.length; i++) {
      assertEquals(bstNodes.get(i).NodeKey, tests[i]);
    }
  }
}
