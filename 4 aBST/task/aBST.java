import java.util.*;

class aBST
{
  public Integer Tree []; // массив ключей

  public aBST(int depth)
  {
    // правильно рассчитайте размер массива для дерева глубины depth:
    int tree_size = (int) Math.pow(2, (depth + 1)) - 1 ;
    Tree = new Integer[ tree_size ];
    for(int i=0; i<tree_size; i++) Tree[i] = null;
  }

  public Integer FindKeyIndex(int key)
  {
    // ищем в массиве индекс ключа
    return find(key, 0);
  }

  private Integer find(int key, int index) {
    if (index >= Tree.length) {
      return null; // не найден
    }
    if (Tree[index] == null) {
      return -index;
    }
    if (Tree[index] == key) {
      return index;
    }
    if (Tree[index] > key) {
      index = 2 * index + 1;
    } else if (Tree[index] < key) {
      index = 2 * index + 2;
    }
    return find(key, index);
  }

  public int AddKey(int key)
  {
    // добавляем ключ в массив
    Integer index = FindKeyIndex(key);
    if (index == null) {
      return -1;
    }
    if (index == 0 && Tree[0] == null) {
      Tree[0] = key;
    }
    if (index < 0) {
      index = -index;
      Tree[index] = key;
    }
    return index;
    // индекс добавленного/существующего ключа или -1 если не удалось
  }

}