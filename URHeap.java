// Julien Diamond
// lab partner: Norman Sackett

import java.lang.Comparable;
import java.util.Arrays;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

public class URHeap<T extends Comparable<T>> implements UR_Heap<T> {
  private T[] heap;
  private int size;
  private int cap;

  // --- constructors ---

  @SuppressWarnings({"rawtypes", "unchecked"})
  public URHeap() {
    cap = 10;
    size = 0;
    heap = (T[]) new Comparable[cap];
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public URHeap(int initCap) {
    if (initCap < 1) throw new IllegalArgumentException();
    cap = initCap;
    size = 0;
    heap = (T[]) new Comparable[cap];
  }

  public URHeap(T[] arr) {
    cap = arr.length;
    size = arr.length;
    heap = arr.clone();
    heapify();
  }

  // --- helper methods ---

  void heapify() { for (int i = size-1; i >= 0; i--) bubbleDown(i); }

  public int size() { return size; }

  public boolean isEmpty() { return size == 0; }

  public void printHeap() {
    System.out.print("[");
    if (size == 0) System.out.println("]");
    for (int i = 0; i < size; i++) System.out.print(heap[i] + (i < size - 1 ? ", " : "]\n"));
  }

  private void swap(int a, int b) {
    T temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
  }

  // --- methods for insertion ---

  private void bubbleUp(int index) {
    while (index > 0 && heap[index].compareTo(heap[(index-1) / 2]) < 0) swap(index, index = (index-1)/2);
  }

  public void insert(T item) {
    if (item == null) throw new IllegalArgumentException();
    if (size == cap) expandHeap();
    heap[size] = item;
    bubbleUp(size);
    size++;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private void expandHeap() {
    T[] newHeap = (T[]) new Comparable[cap*=2];
    for (int i = 0; i < size; i++) newHeap[i] = heap[i];
    heap = newHeap;
  }

  // --- methods for deletion

  private void bubbleDown(int index) {
      while (index < size) {
        int leftChildIndex = index*2+1;
        int rightChildIndex = index*2+2;
        // no children
        if (leftChildIndex >= size) return;
        // one child
        if (rightChildIndex == size && heap[index].compareTo(heap[leftChildIndex]) > 0) {
          swap(index, leftChildIndex);
          return;
        } else if (rightChildIndex == size) return;
        // two children
        if (heap[index].compareTo(heap[rightChildIndex]) < 0 && heap[index].compareTo(heap[leftChildIndex]) < 0) return;
        int swappedChildIndex = heap[leftChildIndex].compareTo(heap[rightChildIndex]) < 0 ? leftChildIndex : rightChildIndex;
        swap(index, swappedChildIndex);
        index = swappedChildIndex;
      }
  }

  public T deleteMin() {
    if (size == 0) throw new NoSuchElementException();
    size--;
    swap(0, size);
    bubbleDown(0);
    return heap[size];
  }

}
