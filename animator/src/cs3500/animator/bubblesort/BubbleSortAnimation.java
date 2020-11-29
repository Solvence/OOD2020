package cs3500.animator.bubblesort;

import java.io.FileWriter;
import java.io.IOException;

public class BubbleSortAnimation {
  static int TICKS_PER_SWAP = 15;

  public static void main(String[] args) throws IOException {
    int listLength = initializeLength(args);
    Appendable out = findOut(args);
    int heightPerUnit;
    int widthPerRectangle = 1000 / listLength;


    BubbleSort bbsort = new BubbleSort(listLength);

    out.append("canvas 0 0 1000 1000\n");

    heightPerUnit = 1000 / initializeRectangles(bbsort.getCurrentArray(), out);

    int currentTick = 1;

    while(!bbsort.finished()) {
      int[] previousArray = bbsort.getCurrentArray();
      bbsort.nextStep();
      int[] currentArray = bbsort.getCurrentArray();

      for (int i=0; i < previousArray.length; i++) {
        int currElement = previousArray[i];
        int nextIndexOfElement = findIndex(currentArray, currElement);

        out.append(String.format("motion R%d %d %d %d %d %d %d %d %d ",
            currElement, currentTick, i*widthPerRectangle, 1000-currElement*heightPerUnit,
            widthPerRectangle, currElement*heightPerUnit, 0, 0, 0));

        out.append(String.format("%d %d %d %d %d %d %d %d\n",
            currentTick + TICKS_PER_SWAP, nextIndexOfElement*widthPerRectangle, 1000-currElement*heightPerUnit,
            widthPerRectangle, currElement*heightPerUnit, 0, 0, 0));
      }
      currentTick += TICKS_PER_SWAP;
    }

    if (out instanceof FileWriter) {
      FileWriter file = (FileWriter) out;
      file.close();
    }
  }

  public static int findIndex(int[] arr, int t)
  {

    // if array is Null
    if (arr == null) {
      return -1;
    }

    // find length of array
    int len = arr.length;
    int i = 0;

    // traverse in the array
    while (i < len) {

      // if the i-th element is t
      // then return the index
      if (arr[i] == t) {
        return i;
      }
      else {
        i = i + 1;
      }
    }
    return -1;
  }

  private static int initializeRectangles(int[] currArray, Appendable out) throws IOException {
    int maxValue = -1;
    for (int i = 0; i < currArray.length; i++) {
      maxValue = Math.max(maxValue, currArray[i]);
      out.append("shape R" + currArray[i] + " rectangle\n");
    }
    return maxValue;
  }

  private static int initializeLength(String[] args) {
    int lengthIndex = -1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-length")) {
        lengthIndex = i + 1;
      }
    }

    if (lengthIndex == -1 || lengthIndex > args.length - 1
        || Integer.parseInt(args[lengthIndex]) <= 1) {
      return 10;
    }
    return Integer.parseInt(args[lengthIndex]) <= 30 ? Integer.parseInt(args[lengthIndex]) : 30;
  }

  /**
   * searches the command line arguments for the output file specification.
   *
   * @param args - the command line arguments
   * @return - the output file that the animation will write to (default=System.out), if applicable
   * @throws IOException - if a process cannot be completed when reading the output file
   */
  private static Appendable findOut(String[] args) throws IOException {
    int outIndex = -1;
    Appendable out = System.out;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-out")) {
        outIndex = i + 1;
      }
    }
    if (outIndex == -1 || outIndex > args.length - 1 || args[outIndex].equals("out")) {
      return out;
    }
    return new FileWriter(args[outIndex]);
  }
}
