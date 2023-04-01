import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class aBSTTest {

  @Test
  void tree() {
    aBST tree = new aBST(0);
    assertEquals(1, tree.Tree.length);
    tree = new aBST(1);
    assertEquals(3, tree.Tree.length);
    tree = new aBST(2);
    assertEquals(7, tree.Tree.length);
    tree = new aBST(3);
    assertEquals(15, tree.Tree.length);
  }

  @Test
  void FindKeyIndex() {
    aBST tree = new aBST(3);
    assertEquals(0, tree.FindKeyIndex(50));
    tree.Tree[0] = 50;
    assertEquals(-1, tree.FindKeyIndex(25));
    assertEquals(-2, tree.FindKeyIndex(75));
    tree.Tree[1] = 25;
    tree.Tree[2] = 75;
    assertEquals(-4, tree.FindKeyIndex(37));
    assertEquals(-5, tree.FindKeyIndex(62));
    assertEquals(-6, tree.FindKeyIndex(84));
    tree.Tree[4] = 37;
    tree.Tree[5] = 62;
    tree.Tree[6] = 84;
    assertEquals(-9, tree.FindKeyIndex(31));
    assertEquals(-10, tree.FindKeyIndex(43));
    assertEquals(-11, tree.FindKeyIndex(55));
    assertEquals(-14, tree.FindKeyIndex(92));
    tree.Tree[9] = 31;
    tree.Tree[10] = 43;
    tree.Tree[11] = 55;
    tree.Tree[14] = 92;
    assertNull(tree.FindKeyIndex(34));
    assertNull(tree.FindKeyIndex(30));
    assertNull(tree.FindKeyIndex(40));
    assertNull(tree.FindKeyIndex(44));
    assertNull(tree.FindKeyIndex(54));
    assertNull(tree.FindKeyIndex(56));
    assertNull(tree.FindKeyIndex(91));
    assertNull(tree.FindKeyIndex(100));

    assertEquals(0, tree.FindKeyIndex(50));
    assertEquals(1, tree.FindKeyIndex(25));
    assertEquals(2, tree.FindKeyIndex(75));
    assertEquals(4, tree.FindKeyIndex(37));
    assertEquals(5, tree.FindKeyIndex(62));
    assertEquals(6, tree.FindKeyIndex(84));
    assertEquals(9, tree.FindKeyIndex(31));
    assertEquals(10, tree.FindKeyIndex(43));
    assertEquals(11, tree.FindKeyIndex(55));
    assertEquals(14, tree.FindKeyIndex(92));
  }

  @Test
  void AddKey() {
    aBST tree = new aBST(3);
    tree.AddKey(50);
    assertEquals(50, tree.Tree[0]);
    tree.AddKey(25);
    tree.AddKey(75);
    assertEquals(25, tree.Tree[1]);
    assertEquals(75, tree.Tree[2]);
    tree.AddKey(37);
    tree.AddKey(62);
    tree.AddKey(84);
    assertNull(tree.Tree[3]);
    assertEquals(37, tree.Tree[4]);
    assertEquals(62, tree.Tree[5]);
    assertEquals(84, tree.Tree[6]);
    tree.AddKey(31);
    tree.AddKey(43);
    tree.AddKey(55);
    tree.AddKey(92);
    assertNull(tree.Tree[7]);
    assertNull(tree.Tree[8]);
    assertEquals(31, tree.Tree[9]);
    assertEquals(43, tree.Tree[10]);
    assertEquals(55, tree.Tree[11]);
    assertNull(tree.Tree[12]);
    assertNull(tree.Tree[13]);
    assertEquals(92, tree.Tree[14]);
  }
}