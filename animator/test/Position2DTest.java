import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.position2d.Position2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic class for testing Position2D class.
 */
public class Position2DTest {

  Position2D a;
  Position2D b;
  Position2D c;
  Position2D a2;
  Position2D b2;

  @Before
  public void setup() {
    this.a = new Position2D(1, 2);
    this.b = new Position2D(2, 17);
    this.c = new Position2D(4, 110000);
    this.a2 = new Position2D(a);
    this.b2 = new Position2D(b);
  }

  // test getXDir works appropriately.
  @Test
  public void testGetXDir() {
    assertEquals(a.getX(), 1);
    assertEquals(b.getX(), 2);
    assertEquals(c.getX(), 4);
  }

  // test getYDir works appropriately.
  @Test
  public void testGetYDir() {
    assertEquals(a.getY(), 2);
    assertEquals(b.getY(), 17);
    assertEquals(c.getY(), 110000);
  }

  // test if copy constructor copies given Position2D.
  @Test
  public void testCopyConstructor() {
    assertEquals(a.getX(), a2.getX());
    assertEquals(a2.getX(), a.getX());
    assertEquals(a2.getY(), a.getY());
    assertEquals(a2.getY(), a.getY());

    assertEquals(b.getX(), b2.getX());
    assertEquals(b2.getX(), b.getX());
    assertEquals(b2.getY(), b.getY());
    assertEquals(b2.getY(), b.getY());
  }

  // test toString method in Position2D works appropriately.
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

    Position2D swapA = new Position2D(2, 1);
    assertFalse(a.equals(swapA));
    String h = "hello";
    assertFalse(a.equals(h));
  }

  // tests that if two Positon2Ds are equal, they have the same hashCode
  @Test
  public void testHashCode() {
    assertEquals(a.hashCode(), a2.hashCode());
    assertEquals(b.hashCode(), b2.hashCode());
  }


}