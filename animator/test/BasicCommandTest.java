import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ChangeColor Command class object.
 */
public class BasicCommandTest {

  AnimatedObjectCommand c1;
  AnimatedObjectCommand c2;
  AnimatedObjectCommand c3;
  AnimatedObjectCommand c4;
  Shape s1;
  Shape s2;

  @Before
  public void setUp() {
    this.c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    this.c2 = new BasicCommand(15, 30, new Position2D(10, 10), new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    this.c3 = new BasicCommand(70, 75, new Position2D(25, 10), new Position2D(200, 10),
        new Dimension2D(10, 40), new Dimension2D(80, 40), new Color(11, 11, 11),
        new Color(1, 1, 1));
    this.c4 = new BasicCommand(70, 70, new Position2D(25, 10), new Position2D(25, 10),
        new Dimension2D(10, 40), new Dimension2D(10, 40), new Color(1, 1, 1),
        new Color(1, 1, 1));
    this.s1 = new Rectangle();
    this.s2 = new Ellipse();
  }

  // test constructor

  // test constructor throws exception for negative start time.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandNegativeStartTime() {
    AnimatedObjectCommand c1 = new BasicCommand(-1, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception for negative end time.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandNegativeEndTime() {
    AnimatedObjectCommand c1 = new BasicCommand(1, -15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception for endtime before starttime.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartTimeBeforeEndTime() {
    AnimatedObjectCommand c1 = new BasicCommand(16, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception for keyframe that changes shape field position.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandConstructorDoesntAllowTeleportPosition() {
    AnimatedObjectCommand c1 = new BasicCommand(15, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(1, 1), new Color(10, 10, 10),
        new Color(10, 10, 10));
  }

  // test constructor throws exception for keyframe that changes shape field color.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandConstructorDoesntAllowTeleportColor() {
    AnimatedObjectCommand c1 = new BasicCommand(15, 15, new Position2D(0, 0),
        new Position2D(0, 0),
        new Dimension2D(1, 1), new Dimension2D(1, 1),
        new Color(10, 10, 10),
        new Color(10, 11, 10));
  }

  // test constructor throws exception for keyframe that changes shape field size.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandConstructorDoesntAllowTeleportSize() {
    AnimatedObjectCommand c1 = new BasicCommand(15, 15, new Position2D(0, 0),
        new Position2D(0, 0),
        new Dimension2D(1, 1), new Dimension2D(2, 1),
        new Color(10, 10, 10),
        new Color(10, 10, 10));
  }

  // test constructor throws exception if startPosition is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartPositionNull() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, null,
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception if endPosition is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandEndPositionNull() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15,
        new Position2D(10, 10), null,
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception if startSize is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartSizeNull() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        null, new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception if endSize is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandEndSizeNull() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), null, new Color(10, 10, 10),
        new Color(11, 10, 155));
  }

  // test constructor throws exception if startColor is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartColorNull() {
    AnimatedObjectCommand c1 = new BasicCommand(16, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), null,
        new Color(11, 10, 155));
  }

  // test constructor throws exception if endColor is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandEndColorNull() {
    AnimatedObjectCommand c1 = new BasicCommand(16, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        null);
  }

  // test constructor throws exception for teleporting keyFrame.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandConstructorTeleportingKeyFrame() {
    AnimatedObjectCommand c1 = new BasicCommand(15, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        null);
  }

  // apply

  // called apply on time too small
  @Test(expected = IllegalArgumentException.class)
  public void testApplyTimeSmallerThanInterval() {
    c2.apply(s2, 10);
  }

  // called apply on time negative
  @Test(expected = IllegalArgumentException.class)
  public void testApplyTimeNegative() {
    c2.apply(s2, -1);
  }

  // called apply on time too large
  @Test(expected = IllegalArgumentException.class)
  public void testApplyTimeLargerThanInterval() {
    c1.apply(s2, 16);
  }

  @Test
  public void testApply() {
    // time is equal to end time
    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getColor(), null);
    assertEquals(s1.getSize(), null);
    Shape newS1 = c1.apply(s1, 15);
    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getColor(), null);
    assertEquals(s1.getSize(), null);
    assertEquals(newS1.getPosition(), new Position2D(10, 10));
    assertEquals(newS1.getColor(), new Color(11, 10, 155));
    assertEquals(newS1.getSize(), new Dimension2D(10, 10));

    // keyframe

    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getColor(), null);
    assertEquals(s1.getSize(), null);
    Shape keyFrame = c4.apply(s1, 70);
    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getColor(), null);
    assertEquals(s1.getSize(), null);
    assertEquals(keyFrame.getPosition(), new Position2D(25, 10));
    assertEquals(keyFrame.getColor(), new Color(1, 1, 1));
    assertEquals(keyFrame.getSize(), new Dimension2D(10, 40));

    // apply next command

    Shape newS12 = c2.apply(newS1, 25);

    assertEquals(newS1.getPosition(), new Position2D(10, 10));
    assertEquals(newS1.getColor(), new Color(11, 10, 155));
    assertEquals(newS1.getSize(), new Dimension2D(10, 10));
    assertEquals(newS12.getPosition(), new Position2D(20, 10));
    assertEquals(newS12.getColor(), new Color(11, 10, 59));
    assertEquals(newS12.getSize(), new Dimension2D(10, 30));

    assertEquals(s2.getPosition(), null);
    assertEquals(s2.getColor(), null);
    assertEquals(s2.getSize(), null);
    Shape newS2 = c3.apply(s2, 75);
    assertEquals(s2.getPosition(), null);
    assertEquals(s2.getColor(), null);
    assertEquals(s2.getSize(), null);
    assertEquals(newS2.getPosition(), new Position2D(200, 10));
    assertEquals(newS2.getColor(), new Color(1, 1, 1));
    assertEquals(newS2.getSize(), new Dimension2D(80, 40));

    // time is inbetween intervals
    assertEquals(s2.getColor(), null);
    assertEquals(s2.getPosition(), null);
    assertEquals(s2.getSize(), null);
    Shape newS22 = c3.apply(s2, 71);
    assertEquals(newS22.getColor(), new Color(9, 9, 9));
    assertEquals(newS22.getPosition(), new Position2D(60, 10));
    assertEquals(newS22.getSize(), new Dimension2D(24, 40));
    newS22 = c3.apply(newS22, 72);
    assertEquals(newS22.getColor(), new Color(7, 7, 7));
    assertEquals(newS22.getPosition(), new Position2D(95, 10));
    assertEquals(newS22.getSize(), new Dimension2D(38, 40));
    newS22 = c3.apply(newS22, 73);
    assertEquals(newS22.getColor(), new Color(5, 5, 5));
    assertEquals(newS22.getPosition(), new Position2D(130, 10));
    assertEquals(newS22.getSize(), new Dimension2D(52, 40));
    newS22 = c3.apply(newS22, 74);
    assertEquals(newS22.getColor(), new Color(3, 3, 3));
    assertEquals(newS22.getPosition(), new Position2D(165, 10));
    assertEquals(newS22.getSize(), new Dimension2D(66, 40));

    assertEquals(s1.getColor(), null);
    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getSize(), null);
    newS1 = c1.apply(s1, 10);
    assertEquals(newS1.getColor(), new Color(10, 10, 103));
    assertEquals(newS1.getPosition(), new Position2D(6, 6));
    assertEquals(newS1.getSize(), new Dimension2D(6, 6));
    newS1 = c1.apply(s1, 12);
    assertEquals(newS1.getColor(), new Color(10, 10, 123));
    assertEquals(newS1.getPosition(), new Position2D(7, 7));
    assertEquals(newS1.getSize(), new Dimension2D(8, 8));
    newS1 = c1.apply(s1, 6);
    assertEquals(newS1.getColor(), new Color(10, 10, 61));
    assertEquals(newS1.getPosition(), new Position2D(3, 3));
    assertEquals(newS1.getSize(), new Dimension2D(4, 4));

    // time is equal to start

    assertEquals(s2.getColor(), null);
    assertEquals(s2.getPosition(), null);
    assertEquals(s2.getSize(), null);
    newS2 = c3.apply(s2, 70);
    assertEquals(newS2.getColor(), new Color(11, 11, 11));
    assertEquals(newS2.getPosition(), new Position2D(25, 10));
    assertEquals(newS2.getSize(), new Dimension2D(10, 40));

    assertEquals(s1.getColor(), null);
    assertEquals(s1.getPosition(), null);
    assertEquals(s1.getSize(), null);
    newS1 = c1.apply(s1, 1);
    assertEquals(newS1.getColor(), new Color(10, 10, 10));
    assertEquals(newS1.getPosition(), new Position2D(0, 0));
    assertEquals(newS1.getSize(), new Dimension2D(1, 1));

  }

  // getstarttime

  // test if getstartTime works accordingly.
  @Test
  public void testGetStartTime() {
    assertEquals(this.c1.getStartTime(), 1);
    assertEquals(this.c2.getStartTime(), 15);
    assertEquals(this.c3.getStartTime(), 70);
  }

  // test if getendTime works accordingly.
  @Test
  public void testGetEndTime() {
    assertEquals(this.c1.getEndTime(), 15);
    assertEquals(this.c2.getEndTime(), 30);
    assertEquals(this.c3.getEndTime(), 75);
  }

  // test equals method.
  @Test
  public void testEquals() {
    assertTrue(this.c1.equals(this.c1));
    assertTrue(this.c2.equals(this.c2));
    assertTrue(this.c3.equals(this.c3));
    assertFalse(this.c1.equals(this.c2));
    assertFalse(this.c2.equals(this.c1));
    assertFalse(this.c1.equals(this.c3));
    assertFalse(this.c1.equals("hello"));

    AnimatedObjectCommand otherC1 = new BasicCommand(1, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));

    assertTrue(this.c1.equals(otherC1));
  }

  //test hashcode.
  @Test
  public void testHashcode() {

    assertEquals(this.c1.hashCode(), this.c1.hashCode());
    assertEquals(this.c2.hashCode(), this.c2.hashCode());
    AnimatedObjectCommand otherC1 = new BasicCommand(1, 15, new Position2D(0, 0),
        new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand otherC2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));

    assertEquals(this.c1.hashCode(), otherC1.hashCode());
    assertEquals(this.c2.hashCode(), otherC2.hashCode());
  }

}
