package cs3500.bubblesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a Bubble sorting algorithm in action. Object allows for stepping through each
 * individual steps of swapping elements of an array, until the array is finally sorted.
 */
public class BubbleSort {

  private final int[] arr;
  private int currIndex;
  private int roundNum;

  /**
   * Constructs a bubble sorting algorithm with elements in random order.
   *
   * @param r      - random for seeding collection
   * @param length - the length of the list to be sorted. Elements in the list will be between 1 and
   *               length.
   * @throws IllegalArgumentException if given length is less than 0 or if Random is null
   */
  public BubbleSort(Random r, int length) throws IllegalArgumentException {
    if (length < 0 || r == null) {
      throw new IllegalArgumentException("Random can't be null and length must be larger than 0.");
    }
    arr = new int[length];
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      arrList.add(i + 1);
    }
    Collections.shuffle(arrList, r);
    for (int i = 0; i < arrList.size(); i++) {
      arr[i] = arrList.get(i);
    }

    currIndex = 0;
    roundNum = 0;
  }

  /**
   * Default constructor.
   *
   * @param length length of base array to be sorted.
   * @throws IllegalArgumentException if length is less than 0.
   */
  public BubbleSort(int length) throws IllegalArgumentException {
    this(new Random(), length);
  }

  /**
   * Returns a copy of the current array being sorted.
   *
   * @return - a copy of the current array being sorted
   */
  public int[] getCurrentArray() {
    return Arrays.copyOf(arr, arr.length);
  }

  /**
   * Executes the next step/swap in the bubble sorting algorithm. If the list is already sorted, do
   * nothing.
   */
  public void nextStep() {
    while (roundNum != arr.length) {
      if (currIndex >= arr.length - roundNum - 1) {
        currIndex = 0;
        roundNum++;
      } else {
        if (arr[currIndex] > arr[currIndex + 1]) {
          int temp = arr[currIndex];
          arr[currIndex] = arr[currIndex + 1];
          arr[currIndex + 1] = temp;
          currIndex++;
          break;
        } else {
          currIndex++;
        }
      }
    }
  }

  /**
   * Is the sorting algorithm finished. Is the array properly sorted.
   *
   * @return - true if the array is sorted, false if not.
   */
  public boolean finished() {
    return roundNum == arr.length;
  }
}
