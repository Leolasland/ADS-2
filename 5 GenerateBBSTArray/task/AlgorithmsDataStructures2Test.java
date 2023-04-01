import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsDataStructures2Test {

  @Test
  void generateBBSTArray() {
    int [] a = {50, 25, 75, 23, 37, 62, 84};
    int[] array = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    System.out.println(array.toString());
  }
}
