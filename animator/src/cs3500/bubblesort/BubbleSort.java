package cs3500.bubblesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BubbleSort {
  private int[] arr;
  private int currIndex;
  private int roundNum;

  BubbleSort(int length) {
    arr = new int[length];
    Random r = new Random();
    ArrayList<Integer> arrList = new ArrayList<>();
    for(int i=0; i<length; i++) {
      arrList.add(i+1);
    }
    Collections.shuffle(arrList);
    for(int i = 0; i< arrList.size(); i++) {
      arr[i] = arrList.get(i);
    }

    currIndex = 0;
    roundNum = 0;
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
