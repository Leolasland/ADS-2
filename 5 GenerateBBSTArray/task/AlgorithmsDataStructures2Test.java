import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsDataStructures2Test {

  @Test
  void generateBBSTArray() {
    int [] a = {50, 25, 75, 23, 37, 62, 84};
    int[] array = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    assertEquals(50, array[0]);
    assertEquals(25, array[1]);
    assertEquals(75, array[2]);
    assertEquals(23, array[3]);
    assertEquals(37, array[4]);
    assertEquals(62, array[5]);
    assertEquals(84, array[6]);
    assertEquals(a.length, array.length);
  }
}
