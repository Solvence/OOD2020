package incompletetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import cs3500.animator.model.color.Color;
import java.util.ArrayList;
import java.util.List;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobject.BasicAnimatedObject;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests and examples for the BasicAnimatedObject class.
 */
public class BasicAnimatedObjectTest {

  AnimatedObject ao1;
  AnimatedObject ao2;
  Shape s1;
  Shape s2;


  @Before
  public void setUp() {
    this.s1 = new Rectangle(10, 10, new Color(10, 10, 10),
        new Position2D(0, 0));
    this.s2 = new Ellipse(2, 6, new Color(14, 100, 22),
        new Position2D(15, 1));
    this.ao1 = new BasicAnimatedObject(s1);
    this.ao2 = new BasicAnimatedObject(s2);
  }

  // test constructor

  // test if baseShape is null.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorShapeNull() {
    AnimatedObject invalid = new BasicAnimatedObject(null, new ArrayList<>());
  }

  // test if Command List is null.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorCommandsNull() {
    AnimatedObject invalid = new BasicAnimatedObject(
        new Rectangle(1, 1, new Color(1, 1, 1), new Position2D(1, 1)), null);
  }

  // test base constructor.
  @Test
  public void testBaseConstructor() {
    AnimatedObject ao = new BasicAnimatedObject(
        new Rectangle(1, 1, new Color(1, 1, 1), new Position2D(1, 1)));
    assertEquals(ao.getCommands(), new ArrayList<>());
    assertEquals(ao.getShape(0), new Rectangle(1, 1, new Color(1, 1, 1), new Position2D(1, 1)));
  }

  // test copy constructor.
  @Test
  public void testCopyConstructor() {
    AnimatedObject ao = new BasicAnimatedObject(
        new Rectangle(1, 1, new Color(1, 1, 1), new Position2D(1, 1)));
    AnimatedObject aoCopy = new BasicAnimatedObject(ao);
    assertEquals(ao.getCommands(), aoCopy.getCommands());
    assertEquals(ao.getShape(0), aoCopy.getShape(0));
  }

  // test add command throws exception if two same commands overlap completely.
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandInvalidSameCommandTypeOverlap() {
    AnimatedObjectCommand c1 = new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12));
    AnimatedObjectCommand c4 = new Move(1, 10, new Position2D(11, 11), new Position2D(40, 12));
    ao1.addCommand(c1);
    ao1.addCommand(c4);
  }

  // test add command throws exception if two same commands overlap there ends and start times.
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandInvalidSameCommandTypeEndAndStartOverlap() {
    AnimatedObjectCommand c1 = new ChangeSize(10, 20, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeSize(1, 11, new Dimension2D(40, 15),
        new Dimension2D(67, 40));
    ao1.addCommand(c1);
    ao1.addCommand(c4);
  }

  // test add command throws exception if two same commands overlap there ends and start times but
  // reversed.
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandInvalidSameCommandTypeEndAndStartOverlapReversed() {
    AnimatedObjectCommand c1 = new ChangeSize(10, 20, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeSize(1, 11, new Dimension2D(40, 15),
        new Dimension2D(67, 40));
    ao1.addCommand(c4);
    ao1.addCommand(c1);
  }

  // test addCommand works correctly.
  @Test
  public void testAddCommand() {
    AnimatedObjectCommand c1 = new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12));
    AnimatedObjectCommand c2 = new Move(10, 15, new Position2D(11, 15), new Position2D(22, 222));
    AnimatedObjectCommand c3 = new ChangeSize(1, 10, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeColor(10, 15, new Color(11, 15, 10),
        new Color(100, 22, 222));

    assertEquals(ao1.getCommands(), new ArrayList<>());
    ao1.addCommand(c1);
    assertEquals(ao1.getCommands().get(0), c1);
    assertEquals(ao1.getCommands().size(), 1);
    ao1.addCommand(c2);
    assertEquals(ao1.getCommands().get(1), c2);
    assertEquals(ao1.getCommands().size(), 2);

    // inserting a command which begins earlier than the last in command list.
    ao1.addCommand(c3);
    assertEquals(ao1.getCommands().get(2), c2);
    assertEquals(ao1.getCommands().get(0), c1);
    assertEquals(ao1.getCommands().get(1), c3);
    assertEquals(ao1.getCommands().size(), 3);

    ao1.addCommand(c4);
    assertEquals(ao1.getCommands().get(2), c2);
    assertEquals(ao1.getCommands().get(0), c1);
    assertEquals(ao1.getCommands().get(1), c3);
    assertEquals(ao1.getCommands().get(3), c4);
    assertEquals(ao1.getCommands().size(), 4);
  }

  // test get shape at time negative.
  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtNegativeTime() {
    AnimatedObjectCommand c1 = new ChangeSize(10, 20, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    ao1.addCommand(c1);
    Shape invalid = ao1.getShape(-1);
  }

  // test getShapeAt().
  @Test
  public void testGetShapeAt() {
    AnimatedObjectCommand c1 = new Move(1, 10, new Position2D(0, 0), new Position2D(10, 10));
    AnimatedObjectCommand c2 = new Move(10, 15, new Position2D(10, 10), new Position2D(20, 25));
    AnimatedObjectCommand c3 = new ChangeSize(1, 10, new Dimension2D(10, 10),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeColor(16, 20, new Color(10, 10, 10),
        new Color(100, 22, 222));
    ao1.addCommand(c1);
    ao1.addCommand(c2);
    ao1.addCommand(c3);
    ao1.addCommand(c4);

    assertEquals(ao1.getShape(0), s1);
    assertEquals(ao1.getShape(1), s1);
    assertEquals(ao1.getShape(5),
        new Rectangle(15, 104, new Color(10, 10, 10), new Position2D(4, 4)));
    assertEquals(ao1.getShape(10),
        new Rectangle(22, 222, new Color(10, 10, 10), new Position2D(10, 10)));
    assertEquals(ao1.getShape(11),
        new Rectangle(22, 222, new Color(10, 10, 10), new Position2D(12, 13)));
    assertEquals(ao1.getShape(15),
        new Rectangle(22, 222, new Color(10, 10, 10), new Position2D(20, 25)));
    assertEquals(ao1.getShape(16),
        new Rectangle(22, 222, new Color(10, 10, 10), new Position2D(20, 25)));
    assertEquals(ao1.getShape(20),
        new Rectangle(22, 222, new Color(100, 22, 222), new Position2D(20, 25)));
    assertEquals(ao1.getShape(56),
        new Rectangle(22, 222, new Color(100, 22, 222), new Position2D(20, 25)));

    c1 = new Move(1, 10, new Position2D(15, 1), new Position2D(0, 0));
    c4 = new ChangeColor(16, 20, new Color(14, 100, 22),
        new Color(100, 22, 222));
    ao2.addCommand(c1);
    ao2.addCommand(c4);
    assertEquals(ao2.getShape(0), s2);
    assertEquals(ao2.getShape(5), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(9, 1)));
    assertEquals(ao2.getShape(10), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(0, 0)));
    assertEquals(ao2.getShape(11), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(0, 0)));
    assertEquals(ao2.getShape(12), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(0, 0)));
    assertEquals(ao2.getShape(16), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(0, 0)));
    assertEquals(ao2.getShape(20),
        new Ellipse(2, 6, new Color(100, 22, 222), new Position2D(0, 0)));
    assertEquals(ao2.getShape(25),
        new Ellipse(2, 6, new Color(100, 22, 222), new Position2D(0, 0)));
    assertEquals(ao2.getShape(5), new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(9, 1)));
    assertEquals(ao2.getShape(0), s2);


  }

  // test get commands.
  @Test
  public void testGetCommands() {
    List<AnimatedObjectCommand> testList = new ArrayList<>();
    assertEquals(ao1.getCommands(), testList);
    ao1.addCommand(new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12)));
    AnimatedObjectCommand c1 = new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12));
    testList.add(c1);
    assertEquals(ao1.getCommands(), testList);
    AnimatedObjectCommand c2 = new Move(10, 15, new Position2D(11, 15), new Position2D(22, 222));
    ao1.addCommand(new Move(10, 15, new Position2D(11, 15), new Position2D(22, 222)));
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c2);
    assertEquals(ao1.getCommands(), testList);
    AnimatedObjectCommand c3 = new ChangeSize(1, 10, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeColor(10, 15, new Color(11, 15, 10),
        new Color(100, 22, 222));
    ao1.addCommand(new ChangeSize(1, 10, new Dimension2D(11, 15), new Dimension2D(22, 222)));
    ao1.addCommand(new ChangeColor(10, 15, new Color(11, 15, 10), new Color(100, 22, 222)));
    assertNotSame(ao1.getCommands(), testList);
    testList.add(1, c3);
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c4);
    assertEquals(ao1.getCommands(), testList);
  }

  /**
   * Tests that the equals method functions properly and returns true/false when expected to.
   */
  @Test
  public void testEquals() {
    assertEquals(ao1, new BasicAnimatedObject(
        new Rectangle(10, 10, new Color(10, 10, 10),
            new Position2D(0, 0))));
    assertEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 6, new Color(14, 100, 22),
            new Position2D(15, 1))));

    BasicAnimatedObject ao3 = new BasicAnimatedObject(
        new Rectangle(10, 10, new Color(10, 10, 10),
            new Position2D(0, 0)));

    ao3.addCommand(new Move(0, 10, new Position2D(0, 0), new Position2D(10, -7)));

    assertNotEquals(ao1, ao3);
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(3, 6, new Color(14, 100, 22),
            new Position2D(15, 1))));
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 7, new Color(14, 100, 22),
            new Position2D(15, 1))));
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 6, new Color(14, 110, 22),
            new Position2D(15, 1))));
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 6, new Color(14, 100, 22),
            new Position2D(-15, 1))));
  }

  /**
   * tests that if two BasicAnimatedObjects are equal, they have the same hashcode.
   */
  @Test
  public void testHashCode() {
    BasicAnimatedObject ao3 = new BasicAnimatedObject(
        new Rectangle(10, 10, new Color(10, 10, 10),
            new Position2D(0, 0)));

    BasicAnimatedObject ao4 = new BasicAnimatedObject(
        new Ellipse(2, 6, new Color(14, 100, 22),
            new Position2D(15, 1)));

    assertEquals(ao1, ao3);
    assertEquals(ao2, ao4);

    assertEquals(ao1.hashCode(), ao3.hashCode());
    assertEquals(ao2.hashCode(), ao4.hashCode());
  }


}
