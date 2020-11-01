import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.dimension2D.Dimension2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic class for testing Dimension2D class.
 */
public class Dimension2DTest {
  Dimension2D a;
  Dimension2D b;
  Dimension2D c;
  Dimension2D a2;
  Dimension2D b2;

  @Before
  public void setup() {
    this.a = new Dimension2D(1,2);
    this.b = new Dimension2D(2,17);
    this.c = new Dimension2D(4,110000);
    this.a2 = new Dimension2D(a);
    this.b2 = new Dimension2D(b);
  }

  // test if constructor throws exception when xdir is negative.
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeXDirDimensionConstructor() {
    Dimension2D invalid = new Dimension2D(-2,1);
  }

  // test if constructor throws exception when ydir is negative.
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeYDirDimensionConstructor() {
    Dimension2D invalid = new Dimension2D(2,-1);
  }

  // test if constructor throws exception when both dimensions are negative.
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBothDimensionConstructor() {
    Dimension2D invalid = new Dimension2D(-2,-1);
  }

  // test getXDir works appropriately.
  @Test
  public void testGetXDir() {
    assertEquals(a.getXDir(), 1);
    assertEquals(b.getXDir(), 2);
    assertEquals(c.getXDir(), 4);
  }

  // test getYDir works appropriately.
  @Test
  public void testGetYDir() {
    assertEquals(a.getYDir(), 2);
    assertEquals(b.getYDir(), 17);
    assertEquals(c.getYDir(), 110000);
  }

  // test if copy constructor copies given Dimension2D.
  @Test
  public void testCopyConstructor() {
    assertEquals(a.getXDir(), a2.getXDir());
    assertEquals(a2.getXDir(), a.getXDir());
    assertEquals(a2.getYDir(), a.getYDir());
    assertEquals(a2.getYDir(), a.getYDir());

    assertEquals(b.getXDir(), b2.getXDir());
    assertEquals(b2.getXDir(), b.getXDir());
    assertEquals(b2.getYDir(), b.getYDir());
    assertEquals(b2.getYDir(), b.getYDir());
  }

  // test toString method in Dimension2D works appropriately.
  @Test
  public void testToString() {
    assertEquals(a.toString(), "(1, 2)");
    assertEquals(b.toString(), "(2, 17)");
    assertEquals(c.toString(), "(4, 110000)");
  }

  // test equals method can adequately identify equality.
  @Test
  public void testEquals() {
    assertTrue(a.equals(a2));
    assertTrue(b.equals(b2));
    assertFalse(a.equals(b2));
    assertTrue(a.equals(a));
    assertTrue(b.equals(b));

    Dimension2D swapA = new Dimension2D(2,1);
    assertFalse(a.equals(swapA));
    String h = "hello";
    assertFalse(a.equals(h));
  }

  // test hashCode method in Dimension2D.
  @Test
  public void testHashCode() {
    assertEquals(a.hashCode(), 994);
    assertEquals(a.hashCode(), a2.hashCode());
    assertEquals(b.hashCode(), b2.hashCode());
    assertEquals(b.hashCode(), 1040);
    assertEquals(c.hashCode(), 111085);
  }


}
