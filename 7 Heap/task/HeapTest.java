import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

  private int[] test;
  private Heap heap;

  @BeforeEach
  void setUp() {
    test = new int[]{11, 9, 4, 7, 8, 3, 1, 2, 5, 6, 0, 0, 0, 0, 0};
    heap = new Heap();
    heap.HeapArray = test;
    heap.size = 10;
  }

  @Test
  void makeHeap() {
    int [] a = {11, 9, 4, 7, 8, 3, 1, 2, 5, 6};
    Heap heap = new Heap();
    heap.MakeHeap(a, 3);
    assertEquals(heap.HeapArray[0], 11);
    assertEquals(heap.HeapArray[1], 9);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 7);
    assertEquals(heap.HeapArray[4], 8);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 1);
    assertEquals(heap.HeapArray[7], 2);
    assertEquals(heap.HeapArray[8], 5);
    assertEquals(heap.HeapArray[9], 6);
    assertEquals(heap.HeapArray[10], 0);
    assertEquals(heap.HeapArray[11], 0);
    assertEquals(heap.HeapArray[12], 0);
    assertEquals(heap.HeapArray[13], 0);
    assertEquals(heap.HeapArray[14], 0);
    assertEquals(heap.HeapArray.length, 15);
    assertEquals(heap.size, 10);
  }

  @Test
  void getMax() {
    assertEquals(heap.GetMax(), 11);
    assertEquals(heap.HeapArray[0], 9);
    assertEquals(heap.HeapArray[1], 8);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 7);
    assertEquals(heap.HeapArray[4], 6);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 1);
    assertEquals(heap.HeapArray[7], 2);
    assertEquals(heap.HeapArray[8], 5);
    assertEquals(heap.HeapArray[9], 0);

    assertEquals(heap.GetMax(), 9);
    assertEquals(heap.HeapArray[0], 8);
    assertEquals(heap.HeapArray[1], 7);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 5);
    assertEquals(heap.HeapArray[4], 6);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 1);
    assertEquals(heap.HeapArray[7], 2);
    assertEquals(heap.HeapArray[8], 0);
    assertEquals(heap.HeapArray[9], 0);

    assertEquals(heap.GetMax(), 8);
    assertEquals(heap.HeapArray[0], 7);
    assertEquals(heap.HeapArray[1], 6);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 5);
    assertEquals(heap.HeapArray[4], 2);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 1);
    assertEquals(heap.HeapArray[7], 0);
    assertEquals(heap.HeapArray[8], 0);
    assertEquals(heap.HeapArray[9], 0);

    assertEquals(heap.GetMax(), 7);
    assertEquals(heap.HeapArray[0], 6);
    assertEquals(heap.HeapArray[1], 5);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 1);
    assertEquals(heap.HeapArray[4], 2);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 0);

    assertEquals(heap.GetMax(), 6);
    assertEquals(heap.HeapArray[0], 5);
    assertEquals(heap.HeapArray[1], 3);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 1);
    assertEquals(heap.HeapArray[4], 2);
    assertEquals(heap.HeapArray[5], 0);

    assertEquals(heap.GetMax(), 5);
    assertEquals(heap.HeapArray[0], 4);
    assertEquals(heap.HeapArray[1], 3);
    assertEquals(heap.HeapArray[2], 2);
    assertEquals(heap.HeapArray[3], 1);
    assertEquals(heap.HeapArray[4], 0);

    assertEquals(heap.GetMax(), 4);
    assertEquals(heap.HeapArray[0], 3);
    assertEquals(heap.HeapArray[1], 1);
    assertEquals(heap.HeapArray[2], 2);
    assertEquals(heap.HeapArray[3], 0);

    assertEquals(heap.GetMax(), 3);
    assertEquals(heap.HeapArray[0], 2);
    assertEquals(heap.HeapArray[1], 1);
    assertEquals(heap.HeapArray[2], 0);

    assertEquals(heap.GetMax(), 2);
    assertEquals(heap.HeapArray[0], 1);
    assertEquals(heap.HeapArray[1], 0);
    assertEquals(heap.HeapArray[2], 0);

    assertEquals(heap.GetMax(), 1);
    assertEquals(heap.HeapArray[0], 0);
    assertEquals(heap.HeapArray[1], 0);
    assertEquals(heap.HeapArray[2], 0);
  }

  @Test
  void add() {
    assertEquals(heap.Add(20), true);
    assertEquals(heap.HeapArray[0], 20);
    assertEquals(heap.HeapArray[1], 11);
    assertEquals(heap.HeapArray[2], 4);
    assertEquals(heap.HeapArray[3], 7);
    assertEquals(heap.HeapArray[4], 9);
    assertEquals(heap.HeapArray[5], 3);
    assertEquals(heap.HeapArray[6], 1);
    assertEquals(heap.HeapArray[7], 2);
    assertEquals(heap.HeapArray[8], 5);
    assertEquals(heap.HeapArray[9], 6);
    assertEquals(heap.HeapArray[10], 8);
    assertEquals(heap.HeapArray[11], 0);
    assertEquals(heap.HeapArray[12], 0);
    assertEquals(heap.HeapArray[13], 0);
    assertEquals(heap.HeapArray[14], 0);
    assertEquals(heap.size, 11);
    assertEquals(heap.Add(19), true);
    assertEquals(heap.Add(18), true);
    assertEquals(heap.Add(17), true);
    assertEquals(heap.Add(16), true);
    assertEquals(heap.Add(25), false);

    heap.HeapArray = new int[7];
    heap.size = 0;
    heap.Add(20);
    heap.Add(7);
    heap.Add(19);
    heap.Add(9);
    heap.Add(11);
    heap.Add(18);
    heap.Add(17);
    assertEquals(heap.HeapArray[0], 20);
    assertEquals(heap.HeapArray[1], 11);
    assertEquals(heap.HeapArray[2], 19);
    assertEquals(heap.HeapArray[3], 7);
    assertEquals(heap.HeapArray[4], 9);
    assertEquals(heap.HeapArray[5], 18);
    assertEquals(heap.HeapArray[6], 17);
  }
}
