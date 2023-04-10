import java.util.*;

class Heap
{
  public int [] HeapArray; // хранит неотрицательные числа-ключи
  public int size = 0;

  public Heap() { HeapArray = null; }

  public void MakeHeap(int[] a, int depth)
  {
    // создаём массив кучи HeapArray из заданного
    // размер массива выбираем на основе глубины depth
    // ...
    int size = (int) Math.pow(2, depth + 1) - 1;
    HeapArray = new int[size];
    for (int key : a) {
      Add(key);
    }
  }

  public int GetMax()
  {
    // вернуть значение корня и перестроить кучу
    if (size == 0)
      return -1; // если куча пуста
    int result = HeapArray[0];
    size--;
    HeapArray[0] = HeapArray[size];
    HeapArray[size] = 0;
    dropToCorrectPlace(0);
    return result;
  }

  private void dropToCorrectPlace(int index) {
    int indexOfMaxChild = checkChildToDown(index);
    if (indexOfMaxChild == -1)
      return;
    int swap = HeapArray[indexOfMaxChild];
    HeapArray[indexOfMaxChild] = HeapArray[index];
    HeapArray[index] = swap;
    dropToCorrectPlace(indexOfMaxChild);
  }

  private int checkChildToDown(int index) {
    int leftChild = index * 2 + 1;
    int rightChild = index * 2 + 2;
    boolean isLeftChildCorrect = leftChild < size && HeapArray[index] < HeapArray[leftChild];
    boolean isRightChildCorrect = rightChild < size && HeapArray[index] < HeapArray[rightChild];

    if (isLeftChildCorrect && isRightChildCorrect && HeapArray[leftChild] > HeapArray[rightChild])
      return leftChild;

    if (isLeftChildCorrect && isRightChildCorrect && HeapArray[rightChild] > HeapArray[leftChild])
      return rightChild;

    if (isLeftChildCorrect && HeapArray[leftChild] > HeapArray[index])
      return leftChild;

    if (isRightChildCorrect && HeapArray[rightChild] > HeapArray[index])
      return rightChild;
    return -1;
  }

  public boolean Add(int key)
  {
    // добавляем новый элемент key в кучу и перестраиваем её
    if (size == HeapArray.length) {
      return false; // если куча вся заполнена
    }
    HeapArray[size] = key;
    addToCorrectPlace(size);
    size++;
    return true;
  }

  private void addToCorrectPlace(int index) {
    int parent = (index - 1) / 2;
    int leftChild = index * 2 + 1;
    int rightChild = index * 2 + 2;
    boolean isLeftChildCorrect = leftChild > size || HeapArray[index] > HeapArray[leftChild];
    boolean isRightChildCorrect = rightChild > size || HeapArray[index] > HeapArray[rightChild];

    if (index == 0 && isLeftChildCorrect && isRightChildCorrect)
      return;

    if (HeapArray[parent] > HeapArray[index] && isLeftChildCorrect && isRightChildCorrect)
      return;

    int swap = HeapArray[parent];
    HeapArray[parent] = HeapArray[index];
    HeapArray[index] = swap;
    addToCorrectPlace(parent);
  }
}
