import static org.junit.Assert.assertEquals;

import cs3500.animator.bubblesort.BubbleSort;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
  BubbleSort s1;
  BubbleSort s2;
  BubbleSort s3;
  BubbleSort s4;

  @Before
  public void setup() {
    s1 = new BubbleSort(5);
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
    
  }

  // test if constructed Bubblesorts have duplicates
  @Test
  public void testNoDuplicatesConstructor() {
    for (int i = 0; i < this.s2.getCurrentArray().length; i++) {
      assertEquals(Collections.frequency(Arrays.asList(this.s2.getCurrentArray()), this.s2.getCurrentArray()[i]), 1);
    }
    for (int i = 0; i < this.s1.getCurrentArray().length; i++) {
      assertEquals(Collections.frequency(Arrays.asList(this.s1.getCurrentArray()), this.s1.getCurrentArray()[i]), 1);
    }
  }






}
