import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Represent various tests for the Ellipse class.
 */
public class EllipseTest {

  private Ellipse e1 = new Ellipse(7, 4, Color.BLUE, new Position2D(50, 75));
  private Ellipse e2 = new Ellipse(0, 4, Color.WHITE, new Position2D(-50, 75));
  private Ellipse e3 = new Ellipse(7, 0, Color.ORANGE, new Position2D(50, -75));
  private Ellipse e4 = new Ellipse(0, 0, new Color(200, 150, 133),
      new Position2D(-50, -75));

  /**
   * Tests that the Ellipse constructor throws an exception when negative dimensions are given or
   * when null values are given.
   */
  @Test
  public void testConstructorInvalidInputs() {
    try {
      Ellipse e5 = new Ellipse(-7, 4, Color.BLUE, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Ellipse e6 = new Ellipse(7, -4, Color.BLUE, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Ellipse e7 = new Ellipse(7, 4, null, new Position2D(50, 75));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Ellipse e8 = new Ellipse(7, 4, Color.BLUE, null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    try {
      Ellipse e9 = new Ellipse(-7, 4, null, null);
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
    assertEquals(e1.build(new Position2D(21, 42), Color.CYAN,
        new Dimension2D(100, 20)), new Ellipse(100, 20, Color.CYAN,
        new Position2D(21, 42)));

    assertEquals(e1.build(new Position2D(-28, 0), Color.WHITE,
        new Dimension2D(4, 0)), new Ellipse(4, 0, Color.WHITE,
        new Position2D(-28, 0)));
  }

  /**
   * Tests that the getPosition method properly computes the position of the Ellipse.
   */
  @Test
  public void testGetPosition() {
    assertEquals(e1.getPosition(), new Position2D(50, 75));
    assertEquals(e2.getPosition(), new Position2D(-50, 75));
    assertEquals(e3.getPosition(), new Position2D(50, -75));
    assertEquals(e4.getPosition(), new Position2D(-50, -75));
  }

  /**
   * Tests that the getColor method properly computes the color of the Ellipse.
   */
  @Test
  public void testGetColor() {
    assertEquals(e1.getColor(), Color.BLUE);
    assertEquals(e2.getColor(), Color.WHITE);
    assertEquals(e3.getColor(), Color.ORANGE);
    assertEquals(e4.getColor(), new Color(200, 150, 133));
  }

  /**
   * Tests that the getSize method properly computes the dimensions of the Ellipse.
   */
  @Test
  public void testGetSize() {
    assertEquals(e1.getSize(), new Dimension2D(7, 4));
    assertEquals(e2.getSize(), new Dimension2D(0, 4));
    assertEquals(e3.getSize(), new Dimension2D(7, 0));
    assertEquals(e4.getSize(), new Dimension2D(0, 0));
  }

  /**
   * Tests that the equals method properly returns true when we want it to return true.
   */
  @Test
  public void testEqualsTrue() {
    Ellipse e5 = new Ellipse(7, 4, Color.BLUE, new Position2D(50, 75));
    Ellipse e6 = new Ellipse(0, 4, new Color(255, 255, 255),
        new Position2D(-50, 75));
    Ellipse e7 = new Ellipse(7, 0, Color.ORANGE, new Position2D(50, -75));
    Ellipse e8 = new Ellipse(0, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));

    assertEquals(e1, e5);
    assertEquals(e2, e6);
    assertEquals(e3, e7);
    assertEquals(e4, e8);
  }

  /**
   * Tests that the equals method properly returns false when we want it to return false.
   */
  @Test
  public void testEqualsFalse() {
    Ellipse e5 = new Ellipse(7, 5, Color.BLUE, new Position2D(50, 75));
    Ellipse e6 = new Ellipse(0, 4, new Color(255, 255, 254),
        new Position2D(-50, 75));
    Ellipse e7 = new Ellipse(0, 4, new Color(255, 155, 255),
        new Position2D(-50, 75));
    Ellipse e8 = new Ellipse(0, 4, new Color(0, 255, 255),
        new Position2D(-50, 75));
    Ellipse e9 = new Ellipse(7, 0, Color.ORANGE, new Position2D(50, 75));
    Ellipse e10 = new Ellipse(7, 0, Color.ORANGE, new Position2D(51, -75));
    Ellipse e11 = new Ellipse(1, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));
    Ellipse e12 = new Ellipse(1, 100, new Color(199, 155, 13),
        new Position2D(50, 0));

    assertNotEquals(e1, e5);
    assertNotEquals(e2, e6);
    assertNotEquals(e2, e7);
    assertNotEquals(e2, e8);
    assertNotEquals(e3, e9);
    assertNotEquals(e3, e10);
    assertNotEquals(e4, e11);
    assertNotEquals(e4, e12);
  }

  /**
   * Tests that if two Ellipses are equal, they have the same hashcode.
   */
  @Test
  public void testHashCode() {
    Ellipse e5 = new Ellipse(7, 4, Color.BLUE, new Position2D(50, 75));
    Ellipse e6 = new Ellipse(0, 4, new Color(255, 255, 255),
        new Position2D(-50, 75));
    Ellipse e7 = new Ellipse(7, 0, Color.ORANGE, new Position2D(50, -75));
    Ellipse e8 = new Ellipse(0, 0, new Color(200, 150, 133),
        new Position2D(-50, -75));

    assertEquals(e1, e5);
    assertEquals(e2, e6);
    assertEquals(e3, e7);
    assertEquals(e4, e8);

    assertEquals(e1.hashCode(), e5.hashCode());
    assertEquals(e2.hashCode(), e6.hashCode());
    assertEquals(e3.hashCode(), e7.hashCode());
    assertEquals(e4.hashCode(), e8.hashCode());
  }

  /**
   * Tests that the toString method functions correctly.
   */
  @Test
  public void testToString() {
    assertEquals(e1.toString(), "Ellipse");
    assertEquals(e2.toString(), "Ellipse");
  }
}
