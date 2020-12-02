import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import cs3500.bubblesort.BubbleSort;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the BubbleSort class.
 */
public class BubbleSortTest {

  BubbleSort s1;
  BubbleSort s2;
  BubbleSort s3;
  BubbleSort s4;

  @Before
  public void setup() {
    s1 = new BubbleSort(new Random(1), 4);
    s2 = new BubbleSort(3);
    s3 = new BubbleSort(0);
    s4 = new BubbleSort(1);
  }

  // test constructor: given length is negative.
  @Test(expected = IllegalArgumentException.class)
  public void testBubbleSortConstructor() {
    BubbleSort error = new BubbleSort(-1);
  }

  // test if constructor shuffle works.
  @Test
  public void testConstructorShuffle() {
    int[] base = {1, 2, 3, 4};
    int[] real = {4, 1, 2, 3};
    assertNotSame(base, s1.getCurrentArray());
    assertArrayEquals(real, s1.getCurrentArray());

  }

  // test if constructed Bubblesorts have duplicates
  @Test
  public void testNoDuplicatesConstructor() {
    for (int i = 0; i < this.s2.getCurrentArray().length; i++) {
      assertEquals(Collections
          .frequency(Arrays.asList(this.s2.getCurrentArray()), this.s2.getCurrentArray()[i]),
          0);
    }
    for (int i = 0; i < this.s1.getCurrentArray().length; i++) {
      assertEquals(Collections
          .frequency(Arrays.asList(this.s1.getCurrentArray()), this.s1.getCurrentArray()[i]),
          0);
    }
  }

  // testGetCurrentArray().
  @Test
  public void testGetCurrentArray() {
    int[] real = {4, 1, 2, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{1, 4, 2, 3};
    assertArrayEquals(real, s1.getCurrentArray());

    assertArrayEquals(new int[0], s3.getCurrentArray());
    assertArrayEquals(new int[]{1}, s4.getCurrentArray());
  }

  // testNextStep().
  @Test
  public void testNextStep() {
    int[] real = {4, 1, 2, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{1, 4, 2, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{1, 2, 4, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{1, 2, 3, 4};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    assertArrayEquals(real, s1.getCurrentArray());

    s1 = new BubbleSort(new Random(2), 4);
    real = new int[]{4, 2, 1, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{2, 4, 1, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{2, 1, 4, 3};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{2, 1, 3, 4};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    real = new int[]{1, 2, 3, 4};
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
    assertArrayEquals(real, s1.getCurrentArray());
    s1.nextStep();
  }

  // testFinished()
  @Test
  public void testFinished() {
    assertTrue(s3.finished());
    assertFalse(s4.finished());
    s4.nextStep();
    assertTrue(s4.finished());

    assertFalse(s1.finished());
    s1.nextStep();
    assertFalse(s1.finished());
    s1.nextStep();
    assertFalse(s1.finished());
    s1.nextStep();
    assertFalse(s1.finished());
    s1.nextStep();
    assertTrue(s1.finished());
  }


}
