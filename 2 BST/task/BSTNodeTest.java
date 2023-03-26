import org.junit.jupiter.api.Test;

import java.util.Collections;
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
  void count() {
    BST<Integer> bst = new BST<>(null);
    assertEquals(0, bst.Count());
    assertTrue(bst.AddKeyValue(8, 8));
    assertEquals(1, bst.Count());
    assertTrue(bst.AddKeyValue(4, 4));
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
}
