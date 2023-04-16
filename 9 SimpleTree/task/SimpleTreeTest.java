import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTreeTest {
  
  @Test
  void evenTrees() {
    SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(root);
    SimpleTreeNode<Integer> val2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> val3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> val6 = new SimpleTreeNode<>(6, null);
    SimpleTreeNode<Integer> val5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> val7 = new SimpleTreeNode<>(7, null);
    SimpleTreeNode<Integer> val4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> val8 = new SimpleTreeNode<>(8, null);
    SimpleTreeNode<Integer> val9 = new SimpleTreeNode<>(9, null);
    SimpleTreeNode<Integer> val10 = new SimpleTreeNode<>(10, null);
    tree.AddChild(root, val2);
    tree.AddChild(root, val3);
    tree.AddChild(root, val6);
    tree.AddChild(val2, val5);
    tree.AddChild(val2, val7);
    tree.AddChild(val3, val4);
    tree.AddChild(val6, val8);
    tree.AddChild(val8, val9);
    tree.AddChild(val8, val10);

    ArrayList<Integer> evenTrees = tree.EvenTrees();
    assertEquals(1, evenTrees.get(0));
    assertEquals(3, evenTrees.get(1));
    assertEquals(1, evenTrees.get(2));
    assertEquals(6, evenTrees.get(3));
  }
}
