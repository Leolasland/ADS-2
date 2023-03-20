import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

  @Test
  void addChild() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child1 = new SimpleTreeNode<>(3, child);
    SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(6, child);

    tree.AddChild(tree.Root, child);
    tree.AddChild(child, child1);
    tree.AddChild(child, child2);

    assertEquals(9, tree.Root.NodeValue);
    assertEquals(1, tree.Root.Children.size());
    assertEquals(2, child.Children.size());
    assertEquals(3, child.Children.get(0).NodeValue);
    assertEquals(6, child.Children.get(1).NodeValue);
  }

  @Test
  void deleteNode() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child1 = new SimpleTreeNode<>(3, child);
    SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(6, child);

    tree.AddChild(tree.Root, child);
    tree.AddChild(child, child1);
    tree.AddChild(child, child2);
    tree.DeleteNode(child);

    assertEquals(9, tree.Root.NodeValue);
    assertEquals(2, tree.Root.Children.size());
    assertEquals(0, child.Children.size());
    assertNull(child.Parent);
    assertNull(child.NodeValue);
    assertEquals(Collections.EMPTY_LIST, child.Children);
  }
}