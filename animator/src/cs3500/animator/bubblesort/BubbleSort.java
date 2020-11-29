package cs3500.animator.bubblesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BubbleSort {
  private final int[] arr;
  private int currIndex;
  private int roundNum;


  public BubbleSort(Random r, int length) {
    if (length < 0) {
      throw new IllegalArgumentException("Array length can not be less than 0");
    }
    arr = new int[length];
    ArrayList<Integer> arrList = new ArrayList<>();
    for(int i=0; i<length; i++) {
      arrList.add(i+1);
    }
    Collections.shuffle(arrList,r);
    for(int i = 0; i< arrList.size(); i++) {
      arr[i] = arrList.get(i);
    }

    currIndex = 0;
    roundNum = 0;

  }

  public BubbleSort(int length) throws IllegalArgumentException {
    this(new Random(), length);
  }


  public int[] getCurrentArray() {
    return Arrays.copyOf(arr, arr.length);
  }

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

  public boolean finished() {
    return roundNum == arr.length;
  }
}
