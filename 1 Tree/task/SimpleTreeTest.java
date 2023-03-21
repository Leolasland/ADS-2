import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

  @Test
  void addChildTest() {
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
  void deleteNodeTest() {
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

  @Test
  void getAllNodesTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);

    List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

    for (SimpleTreeNode<Integer> s : list) {
      System.out.println(s.NodeValue);
    }
  }

  @Test
  void findNodesByValueTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(111, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(111, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(111, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);

    List<SimpleTreeNode<Integer>> list= tree.FindNodesByValue(111);

    for (SimpleTreeNode<Integer> s : list) {
      System.out.println(s.NodeValue);
    }
  }

  @Test
  void moveNodeTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);
    tree.MoveNode(child4, child17);

    List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

    for (SimpleTreeNode<Integer> s : list) {
      System.out.println(s.NodeValue);
    }
  }

  @Test
  void countTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    assertEquals(4,tree.Count() );
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    assertEquals(6,tree.Count() );
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);

    assertEquals(9,tree.Count() );
    tree.DeleteNode(child3);
    assertEquals(8,tree.Count());
  }

  @Test
  void leafCountTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);
    assertEquals(4,tree.LeafCount());
    tree.DeleteNode(child7);
    assertEquals(3,tree.LeafCount());
  }

  @Test
  void levelsCountTest() {
    SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
    SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
    SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
    SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
    SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
    SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
    SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
    SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
    SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
    tree.AddChild(tree.Root, child4);
    tree.AddChild(tree.Root, child17);
    tree.AddChild(child17, child22);
    tree.AddChild(child4, child3);
    tree.AddChild(child4, child6);
    tree.AddChild(child6, child5);
    tree.AddChild(child6, child7);
    tree.AddChild(child22, child20);
    List<Integer> list= tree.LevelsCount();

    for (Integer i : list) {
      System.out.println(i);
    }
  }
}