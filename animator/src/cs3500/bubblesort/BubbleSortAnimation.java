package cs3500.bubblesort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Represents a programatically generated animation that displays the motions of Bubble Sort on an
 * array.
 */
public class BubbleSortAnimation {
  static int TICKS_PER_SWAP = 15;
  static int SCREEN_SIZE = 800;

  /**
   * main method. Generates the full animation in a text file.
   * @param args - "-out" specifies the destination file to which the animation text will be
   *             outputted.
   *             "-length" is optional and specifies the number of elements to be sorted
   *             (1<length<31)
   * @throws IOException - if a problem occurs with the Appendable.
   */
  public static void main(String[] args) throws IOException {
    int listLength = initializeLength(args);
    Appendable out = findOut(args);
    Random seed = findSeed(args);
    int heightPerUnit;
    int widthPerRectangle = SCREEN_SIZE / listLength;


    BubbleSort bbsort = new BubbleSort(seed, listLength);

    out.append("canvas 0 0 ").append(String.valueOf(SCREEN_SIZE)).append(" ")
        .append(String.valueOf(SCREEN_SIZE)).append("\n");

    heightPerUnit = SCREEN_SIZE / initializeRectangles(bbsort.getCurrentArray(), out);

    int currentTick = 1;

    while(!bbsort.finished()) {
      int[] previousArray = bbsort.getCurrentArray();
      bbsort.nextStep();
      int[] currentArray = bbsort.getCurrentArray();

      for (int i=0; i < previousArray.length; i++) {
        int currElement = previousArray[i];
        int nextIndexOfElement = findIndex(currentArray, currElement);

        out.append(String.format("motion R%d %d %d %d %d %d %d %d %d ",
            currElement, currentTick, i*widthPerRectangle, SCREEN_SIZE-currElement*heightPerUnit,
            widthPerRectangle, currElement*heightPerUnit, 0, 0, 0));

        out.append(String.format("%d %d %d %d %d %d %d %d\n",
            currentTick + TICKS_PER_SWAP, nextIndexOfElement*widthPerRectangle, SCREEN_SIZE-currElement*heightPerUnit,
            widthPerRectangle, currElement*heightPerUnit, 0, 0, 0));
      }
      currentTick += TICKS_PER_SWAP;
    }

    if (out instanceof FileWriter) {
      FileWriter file = (FileWriter) out;
      file.close();
    }
  }

  /**
   * finds the index of an integer in an array of integers.
   * @param arr - the array to be searched
   * @param t - the element to be found
   * @return - the index of the specifies integer, or -1 if the array does not contain it.
   */
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

  /**
   * appends statements to the output signifying initialization of the necessary shapes for this
   * animation. Returns the max element in the array.
   * @param currArray - the array of integers that is being sorted
   * @param out - the output
   * @return - the maximum integer in the array to be sorted
   * @throws IOException - if Appendable fails
   */
  private static int initializeRectangles(int[] currArray, Appendable out) throws IOException {
    int maxValue = -1;
    for (int i = 0; i < currArray.length; i++) {
      maxValue = Math.max(maxValue, currArray[i]);
      out.append("shape R" + currArray[i] + " rectangle\n");
    }
    return maxValue;
  }

  /**
   * searches the command line arguments for the array length specification.
   *
   * @param args - the command line arguments
   * @return - the length of the array to be sorted
   */
  private static int initializeLength(String[] args) {
    int lengthIndex = -1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-length")) {
        lengthIndex = i + 1;
      }
    }

    if (lengthIndex == -1 || lengthIndex > args.length - 1
        || Integer.parseInt(args[lengthIndex]) <= 0) {
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

  /**
   * searches command line arguments for a random seed specification.
   * @param args - the command line arguments
   * @return - the seeded random if input has been found, a unseeded otherwise
   * @throws NumberFormatException if -seed isn't followed by an integer
   */
  private static Random findSeed(String[] args) throws NumberFormatException {
    int outIndex = -1;
    Random out = new Random();

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-seed")) {
        outIndex = i + 1;
      }
    }
    if (outIndex == -1 || outIndex > args.length - 1 || args[outIndex].equals("out")) {
      return out;
    }
    return new Random(Integer.parseInt(args[outIndex]));
  }
}
