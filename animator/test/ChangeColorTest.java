import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.color.Color;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.ChangeColor;
import cs3500.animator.model.animatedobjectcommand.ChangeSize;
import cs3500.animator.model.animatedobjectcommand.Move;
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
public class ChangeColorTest {

  AnimatedObjectCommand c1;
  AnimatedObjectCommand c2;
  AnimatedObjectCommand c3;
  Shape s1;
  Shape s2;
  Shape s3;

  @Before
  public void setUp() {
    this.c1 = new ChangeColor(1, 15, new Color(10, 10, 10), new Color(11, 10, 155));
    this.c2 = new ChangeColor(20, 21, new Color(11, 10, 155), new Color(200, 200, 200));
    this.c3 = new ChangeColor(70, 75, new Color(10, 20, 10), new Color(100, 20, 100));
    this.s1 = new Rectangle(1, 1, new Color(10, 10, 10), new Position2D(0, 0));
    this.s2 = new Ellipse(15, 1, new Color(11, 10, 155), new Position2D(15, 0));
    this.s3 = new Rectangle(2, 2, new Color(10, 20, 10), new Position2D(2, 2));
  }

  // test constructor

  // test constructor throws exception for negative start time.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandNegativeStartTime() {
    AnimatedObjectCommand invalid = new ChangeColor(-1, 2, new Color(10, 10, 10),
        new Color(111, 10, 10));
  }

  // test constructor throws exception for negative end time.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandNegativeEndTime() {
    AnimatedObjectCommand invalid = new ChangeColor(2, -2, new Color(10, 10, 10),
        new Color(111, 10, 10));
  }

  // test constructor throws exception for endtime before starttime.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartTimeBeforeEndTime() {
    AnimatedObjectCommand invalid = new ChangeColor(2, 1, new Color(10, 10, 10),
        new Color(111, 10, 10));
  }

  // test constructor throws exception for zero second interval.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartTimeEqualEndTime() {
    AnimatedObjectCommand invalid = new ChangeColor(2, 2, new Color(10, 10, 10),
        new Color(111, 10, 10));
  }

  // test constructor throws exception if startColor is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandStartPositionNull() {
    AnimatedObjectCommand invalid = new ChangeColor(2, 2, null, new Color(10, 10, 10));
  }

  // test constructor throws exception if endColor is null.
  @Test(expected = IllegalArgumentException.class)
  public void testCommandEndPositionNull() {
    AnimatedObjectCommand invalid = new ChangeColor(2, 2, new Color(10, 10, 10), null);
  }

  // apply

  // called apply on not applicable / time too small
  @Test(expected = IllegalArgumentException.class)
  public void testApplyTimeSmallerThanInterval() {
    c2.apply(s2, 10);
  }

  // called apply on not applicable / startColor don't line up
  @Test(expected = IllegalArgumentException.class)
  public void testApplyShapeCoordsNotAtStartColor() {
    c2.apply(s1, 20);
  }

  @Test
  public void testApply() {
    // time is equal to end time
    assertEquals(s1.getPosition(), new Position2D(0, 0));
    assertEquals(s1.getColor(), new Color(10, 10, 10));
    assertEquals(s1.getSize(), new Dimension2D(1, 1));
    Shape newS1 = c1.apply(s1, 15);
    assertEquals(s1.getPosition(), new Position2D(0, 0));
    assertEquals(s1.getColor(), new Color(10, 10, 10));
    assertEquals(s1.getSize(), new Dimension2D(1, 1));
    assertEquals(newS1.getPosition(), new Position2D(0, 0));
    assertEquals(newS1.getColor(), new Color(11, 10, 155));
    assertEquals(newS1.getSize(), new Dimension2D(1, 1));

    // time is greater than end time

    assertEquals(s2.getPosition(), new Position2D(15, 0));
    assertEquals(s2.getColor(), new Color(11, 10, 155));
    assertEquals(s2.getSize(), new Dimension2D(15, 1));
    Shape newS2 = c2.apply(s2, 25);
    assertEquals(s2.getPosition(), new Position2D(15, 0));
    assertEquals(s2.getColor(), new Color(11, 10, 155));
    assertEquals(s2.getSize(), new Dimension2D(15, 1));
    assertEquals(newS2.getPosition(), new Position2D(15, 0));
    assertEquals(newS2.getColor(), new Color(200, 200, 200));
    assertEquals(newS2.getSize(), new Dimension2D(15, 1));

    assertEquals(s1.getPosition(), new Position2D(0, 0));
    assertEquals(s1.getColor(), new Color(10, 10, 10));
    assertEquals(s1.getSize(), new Dimension2D(1, 1));
    newS1 = c1.apply(s1, 46);
    assertEquals(s1.getPosition(), new Position2D(0, 0));
    assertEquals(s1.getColor(), new Color(10, 10, 10));
    assertEquals(s1.getSize(), new Dimension2D(1, 1));
    assertEquals(newS1.getPosition(), new Position2D(0, 0));
    assertEquals(newS1.getColor(), new Color(11, 10, 155));
    assertEquals(newS1.getSize(), new Dimension2D(1, 1));

    // time is inbetween intervals
    assertEquals(s3.getColor(), new Color(10, 20, 10));
    Shape newS3 = c3.apply(s3, 71);
    assertEquals(newS3.getColor(), new Color(28, 20, 28));
    newS3 = c3.apply(s3, 72);
    assertEquals(newS3.getColor(), new Color(46, 20, 46));
    newS3 = c3.apply(s3, 73);
    assertEquals(newS3.getColor(), new Color(64, 20, 64));
    newS3 = c3.apply(s3, 74);
    assertEquals(newS3.getColor(), new Color(82, 20, 82));

    assertEquals(s1.getColor(), new Color(10, 10, 10));
    newS1 = c1.apply(s1, 10);
    assertEquals(newS1.getColor(), new Color(10, 10, 103));
    newS1 = c1.apply(s1, 12);
    assertEquals(newS1.getColor(), new Color(10, 10, 123));
    newS1 = c1.apply(s1, 6);
    assertEquals(newS1.getColor(), new Color(10, 10, 61));

    // time is equal to start

    assertEquals(s3.getColor(), new Color(10, 20, 10));
    newS3 = c3.apply(s3, 70);
    assertEquals(newS3.getColor(), new Color(10, 20, 10));

    assertEquals(s1.getColor(), new Color(10, 10, 10));
    newS1 = c1.apply(s1, 1);
    assertEquals(newS1.getColor(), new Color(10, 10, 10));

    assertEquals(s2.getColor(), new Color(11, 10, 155));
    newS2 = c2.apply(s2, 20);
    assertEquals(newS2.getColor(), new Color(11, 10, 155));

    // chaining two ChangeColor commands together together
    assertEquals(s1.getColor(), new Color(10, 10, 10));
    newS1 = c1.apply(s1, 20);
    assertEquals(newS1.getColor(), new Color(11, 10, 155));
    newS2 = c2.apply(newS1, 20);
    assertEquals(newS2.getColor(), new Color(11, 10, 155));

  }


  // test if applicable works accordingly.
  @Test
  public void testApplicable() {
    assertTrue(c1.applicable(2, s1));
    assertTrue(c1.applicable(15, s1));
    assertTrue(c1.applicable(1, s1));
    assertFalse(c1.applicable(0, s1));
    assertFalse(c1.applicable(10, s2));
    assertFalse(c2.applicable(10, s2));
    assertTrue(c2.applicable(20, s2));
  }


  // sametype
  @Test
  public void testSameType() {
    assertTrue(c1.sameType(c2));
    assertTrue(c2.sameType(c1));
    assertTrue(c2.sameType(c3));
    assertFalse(c2.sameType(new ChangeSize(1, 2, new Dimension2D(1, 1), new Dimension2D(2, 3))));
    assertFalse(c1.sameType(new Move(1, 2, new Position2D(1, 1), new Position2D(2, 3))));
  }

  // getstarttime

  // test if getstartTime works accordingly.
  @Test
  public void testGetStartTime() {
    assertEquals(this.c1.getStartTime(), 1);
    assertEquals(this.c2.getStartTime(), 20);
    assertEquals(this.c3.getStartTime(), 70);
  }

  // test if getendTime works accordingly.
  @Test
  public void testGetEndTime() {
    assertEquals(this.c1.getEndTime(), 15);
    assertEquals(this.c2.getEndTime(), 21);
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
    assertFalse(this.c1.equals(new Move(1, 2, new Position2D(1, 1), new Position2D(1, 2))));
    assertFalse(this.c1.equals(new ChangeSize(1, 2, new Dimension2D(1, 1), new Dimension2D(1, 2))));

    AnimatedObjectCommand otherC1 = new ChangeColor(1, 15, new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand otherC2 = new ChangeColor(20, 21, new Color(11, 10, 155),
        new Color(200, 200, 200));

    assertTrue(this.c1.equals(otherC1));
    assertTrue(this.c2.equals(otherC2));
  }

  //test hashcode.
  @Test
  public void testHashcode() {

    assertEquals(this.c1.hashCode(), this.c1.hashCode());
    assertEquals(this.c2.hashCode(), this.c2.hashCode());
    AnimatedObjectCommand otherC1 = new ChangeColor(1, 15, new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand otherC2 = new ChangeColor(20, 21, new Color(11, 10, 155),
        new Color(200, 200, 200));

    assertEquals(this.c1.hashCode(), otherC1.hashCode());
    assertEquals(this.c2.hashCode(), otherC2.hashCode());
  }

}
