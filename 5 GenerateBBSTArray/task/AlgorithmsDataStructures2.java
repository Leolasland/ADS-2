import java.util.*;

public class AlgorithmsDataStructures2
{
  public static int[] GenerateBBSTArray(int[] a)
  {
    Arrays.sort(a);
    int [] result = new int[a.length];
    int index = a.length / 2;
    result[0] = a[index];
    return fillResult(result, a, 0, 0, a.length - 1) ;
  }

  private static int [] fillResult(int [] result, int [] a, int tmp, int left, int right) {
    int center = (left + right) / 2;
    result[tmp] = a[center];
    if (left == right) {
      return result;
    }
    int leftChild = 2 * tmp + 1;
    if (leftChild < result.length && center != (left + right - 1) / 2 && a[(left + right - 1) / 2] != result[0]) {
      fillResult(result, a, leftChild, left, center - 1);
    }
    int rightChild = 2 * tmp + 2;
    if (rightChild < result.length && center != (center + right + 1) / 2) {
      fillResult(result, a, rightChild, center + 1, right);
    }

    return result;
  }
}
