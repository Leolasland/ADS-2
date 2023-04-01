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
    int centr = (left + right) / 2;
    result[tmp] = a[centr];
    if (left == right) {
      return result;
    }
    left = 2 * tmp + 1;

    right = 2 * tmp + 2;

    return fillResult(result, a, tmp, left, right);
  }
}
