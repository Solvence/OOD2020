import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;

import cs3500.animator.model.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Represent various tests for the Rectangle class.
 */
public class RectangleTest {

  private Rectangle r1 = new Rectangle(7, 4, Color.BLUE,
      new Position2D(50, 75));
  private Rectangle r2 = new Rectangle(0, 4, Color.WHITE,
      new Position2D(-50, 75));
  private Rectangle r3 = new Rectangle(7, 0, Color.ORANGE,
      new Position2D(50, -75));
  private Rectangle r4 = new Rectangle(0, 0, new Color(200, 150, 133),
      new Position2D(-50, -75));

  /**
   * Tests that the Rectangle constructor throws an exception when negative dimensions are given or
   * when null values are given.
   */
  @Test
  public void testConstructorInvalidInputs() {
    try {
      Rectangle r5 = new Rectangle(-7, 4, Color.BLUE, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Rectangle r6 = new Rectangle(7, -4, Color.BLUE, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Rectangle r7 = new Rectangle(7, 4, null, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Rectangle r8 = new Rectangle(7, 4, Color.BLUE, null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Rectangle r9 = new Rectangle(-7, 4, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that the build method functions correctly given proper inputs.
   */
  @Test
  public void testBuild() {
    assertEquals(r1.build(new Position2D(21, 42), Color.CYAN,
        new Dimension2D(100, 20)), new Rectangle(100, 20, Color.CYAN,
        new Position2D(21, 42)));

    assertEquals(r1.build(new Position2D(-28, 0), Color.WHITE,
        new Dimension2D(4, 0)), new Rectangle(4, 0, Color.WHITE,
        new Position2D(-28, 0)));
  }

  /**
   * Tests that the getPosition method properly computes the position of the Rectangle.
   */
  @Test
  public void testGetPosition() {
    assertEquals(r1.getPosition(), new Position2D(50, 75));
    assertEquals(r2.getPosition(), new Position2D(-50, 75));
    assertEquals(r3.getPosition(), new Position2D(50, -75));
    assertEquals(r4.getPosition(), new Position2D(-50, -75));
  }

  /**
   * Tests that the getColor method properly computes the color of the Rectangle.
   */
  @Test
  public void testGetColor() {
    assertEquals(r1.getColor(), Color.BLUE);
    assertEquals(r2.getColor(), Color.WHITE);
    assertEquals(r3.getColor(), Color.ORANGE);
    assertEquals(r4.getColor(), new Color(200, 150, 133));
  }

  /**
   * Tests that the getSize method properly computes the dimensions of the Rectangle.
   */
  @Test
  public void testGetSize() {
    assertEquals(r1.getSize(), new Dimension2D(7, 4));
    assertEquals(r2.getSize(), new Dimension2D(0, 4));
    assertEquals(r3.getSize(), new Dimension2D(7, 0));
    assertEquals(r4.getSize(), new Dimension2D(0, 0));
  }

  /**
   * Tests that the equals method properly returns true when we want it to return true.
   */
  @Test
  public void testEqualsTrue() {
    Rectangle r5 = new Rectangle(7, 4, Color.BLUE, new Position2D(50, 75));
    Rectangle r6 = new Rectangle(0, 4, new Color(255, 255, 255),
        new Position2D(-50, 75));
    Rectangle r7 = new Rectangle(7, 0, Color.ORANGE, new Position2D(50, -75));
    Rectangle r8 = new Rectangle(0, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));

    assertEquals(r1, r5);
    assertEquals(r2, r6);
    assertEquals(r3, r7);
    assertEquals(r4, r8);
  }

  /**
   * Tests that the equals method properly returns false when we want it to return false.
   */
  @Test
  public void testEqualsFalse() {
    Rectangle r5 = new Rectangle(7, 5, Color.BLUE, new Position2D(50, 75));
    Rectangle r6 = new Rectangle(0, 4, new Color(255, 255, 254),
        new Position2D(-50, 75));
    Rectangle r7 = new Rectangle(0, 4, new Color(255, 155, 255),
        new Position2D(-50, 75));
    Rectangle r8 = new Rectangle(0, 4, new Color(0, 255, 255),
        new Position2D(-50, 75));
    Rectangle r9 = new Rectangle(7, 0, Color.ORANGE, new Position2D(50, 75));
    Rectangle r10 = new Rectangle(7, 0, Color.ORANGE, new Position2D(51, -75));
    Rectangle r11 = new Rectangle(1, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));
    Rectangle r12 = new Rectangle(1, 100, new Color(199, 155, 13),
        new Position2D(50, 0));

    assertNotEquals(r1, r5);
    assertNotEquals(r2, r6);
    assertNotEquals(r2, r7);
    assertNotEquals(r2, r8);
    assertNotEquals(r3, r9);
    assertNotEquals(r3, r10);
    assertNotEquals(r4, r11);
    assertNotEquals(r4, r12);
  }

  /**
   * Tests that if two Ellipses are equal, they have the same hashcode.
   */
  @Test
  public void testHashCode() {
    Rectangle r5 = new Rectangle(7, 4, Color.BLUE, new Position2D(50, 75));
    Rectangle r6 = new Rectangle(0, 4, new Color(255, 255, 255),
        new Position2D(-50, 75));
    Rectangle r7 = new Rectangle(7, 0, Color.ORANGE, new Position2D(50, -75));
    Rectangle r8 = new Rectangle(0, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));

    assertEquals(r1, r5);
    assertEquals(r2, r6);
    assertEquals(r3, r7);
    assertEquals(r4, r8);

    assertEquals(r1.hashCode(), r5.hashCode());
    assertEquals(r2.hashCode(), r6.hashCode());
    assertEquals(r3.hashCode(), r7.hashCode());
    assertEquals(r4.hashCode(), r8.hashCode());
  }

  /**
   * Tests that the toString method functions correctly.
   */
  @Test
  public void testToString() {
    assertEquals(r1.toString(), "Rectangle");
    assertEquals(r2.toString(), "Rectangle");
  }
}
