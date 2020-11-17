import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import cs3500.animator.model.animatedobjectcommand.BasicCommand;
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
    this.s1 = new Rectangle();
    this.s2 = new Ellipse();
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
        new Rectangle(), null);
  }

  // test base constructor.
  @Test
  public void testBaseConstructor() {
    AnimatedObject ao = new BasicAnimatedObject(
        new Rectangle());
    assertEquals(ao.getCommands(), new ArrayList<>());
    assertEquals(ao.getShape(0), new Rectangle());
  }

  // test initial constructor.
  @Test
  public void testCopyConstructor() {
    AnimatedObject ao = new BasicAnimatedObject(
        new Rectangle());
    AnimatedObject aoCopy = new BasicAnimatedObject(new Rectangle());
    assertEquals(ao.getCommands(), aoCopy.getCommands());
    assertEquals(ao.getShape(0), aoCopy.getShape(0));
  }

  // test add command throws exception if command intervals overlap.
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorScreamsIfListCommandsHaveInvalidIntervalsForAnimatedObject() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(14, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    List<AnimatedObjectCommand> commands = new ArrayList<>();
    commands.add(c1);
    commands.add(c2);
    AnimatedObject invalid = new BasicAnimatedObject(new Rectangle(), commands);
  }


  // test add command throws exception if command intervals overlap.
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandInvalidSameCommandTypeOverlap() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(14, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    ao1.addCommand(c1);
    ao1.addCommand(c2);
  }

  // test add command throws exception if command intervals aren't butt to butt.
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandInvalidSameCommandTypeNotButtToButt() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(16, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    ao1.addCommand(c1);
    ao1.addCommand(c2);
  }


  // test addCommand works correctly.
  @Test
  public void testAddCommand() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    AnimatedObjectCommand c3 = new BasicCommand(30, 45, new Position2D(25, 10),
        new Position2D(200, 10),
        new Dimension2D(10, 40), new Dimension2D(80, 40), new Color(11, 11, 11),
        new Color(1, 1, 1));

    assertEquals(ao1.getCommands(), new ArrayList<>());
    ao1.addCommand(c1);
    assertEquals(ao1.getCommands().get(0), c1);
    assertEquals(ao1.getCommands().size(), 1);
    ao1.addCommand(c2);
    assertEquals(ao1.getCommands().get(1), c2);
    assertEquals(ao1.getCommands().size(), 2);

    ao1.addCommand(c3);
    assertEquals(ao1.getCommands().get(2), c3);
    assertEquals(ao1.getCommands().get(0), c1);
    assertEquals(ao1.getCommands().get(1), c2);
    assertEquals(ao1.getCommands().size(), 3);
  }

  // test get shape at time negative.
  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtNegativeTime() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    ao1.addCommand(c1);
    Shape invalid = ao1.getShape(-1);
  }

  // test getShapeAt().
  @Test
  public void testGetShapeAt() {
    AnimatedObjectCommand c1 = new BasicCommand(2, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    AnimatedObjectCommand c3 = new BasicCommand(30, 45, new Position2D(25, 10),
        new Position2D(200, 10),
        new Dimension2D(10, 40), new Dimension2D(80, 40), new Color(11, 11, 11),
        new Color(1, 1, 1));
    ao1.addCommand(c1);
    ao1.addCommand(c2);
    ao1.addCommand(c3);

    assertEquals(ao1.getShape(0), s1);
    assertEquals(ao1.getShape(1), s1);
    assertEquals(ao1.getShape(5),
        new Rectangle(3, 3, new Color(10, 10, 43), new Position2D(2, 2)));
    assertEquals(ao1.getShape(10),
        new Rectangle(6, 6, new Color(10, 10, 99), new Position2D(6, 6)));
    assertEquals(ao1.getShape(11),
        new Rectangle(7, 7, new Color(10, 10, 110), new Position2D(6, 6)));
    assertEquals(ao1.getShape(15),
        new Rectangle(10, 10, new Color(11, 10, 155), new Position2D(10, 10)));
    assertEquals(ao1.getShape(16),
        new Rectangle(10, 12, new Color(11, 10, 146), new Position2D(11, 10)));
    assertEquals(ao1.getShape(20),
        new Rectangle(10, 20, new Color(11, 10, 107), new Position2D(15, 10)));
    assertEquals(ao1.getShape(41),
        new Rectangle(61, 40, new Color(4, 4, 4), new Position2D(153, 10)));
    assertEquals(ao1.getShape(45),
        new Rectangle(80, 40, new Color(1, 1, 1), new Position2D(200, 10)));

    AnimatedObjectCommand c4 = new BasicCommand(45, 50, new Position2D(25, 10),
        new Position2D(200, 10),
        new Dimension2D(10, 40), new Dimension2D(80, 40), new Color(22, 11, 11),
        new Color(1, 1, 1));
    ao2.addCommand(c4);
    assertEquals(ao2.getShape(0), s2);
    assertEquals(ao2.getShape(5), s2);
    assertEquals(ao2.getShape(10), s2);
    assertEquals(ao2.getShape(47), new Ellipse(38, 40, new Color(14, 7, 7), new Position2D(95, 10)));
    assertEquals(ao2.getShape(50), new Ellipse(80, 40, new Color(1, 1, 1), new Position2D(200, 10)));
    assertEquals(ao2.getShape(45),
        new Ellipse(10, 40, new Color(22, 11, 11), new Position2D(25, 10)));
    assertEquals(ao2.getShape(5), s2);
    assertEquals(ao2.getShape(0), s2);


  }

  // test get commands.
  @Test
  public void testGetCommands() {
    AnimatedObjectCommand c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    AnimatedObjectCommand c2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    AnimatedObjectCommand c3 = new BasicCommand(30, 45, new Position2D(25, 10),
        new Position2D(200, 10),
        new Dimension2D(10, 40), new Dimension2D(80, 40), new Color(11, 11, 11),
        new Color(1, 1, 1));
    AnimatedObjectCommand c4 = new BasicCommand(45, 45, new Position2D(200, 10),
        new Position2D(200, 10),
        new Dimension2D(80, 40), new Dimension2D(80, 40), new Color(1, 1, 1),
        new Color(1, 1, 1));
    List<AnimatedObjectCommand> testList = new ArrayList<>();
    assertEquals(ao1.getCommands(), testList);
    ao1.addCommand(c1);
    testList.add(c1);
    assertEquals(ao1.getCommands(), testList);
    ao1.addCommand(c2);
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c2);
    assertEquals(ao1.getCommands(), testList);
    ao1.addCommand(c3);
    ao1.addCommand(c4);
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c3);
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c4);
    assertEquals(ao1.getCommands(), testList);
  }

  /**
   * Tests that the equals method functions properly and returns true/false when expected to.
   */
  @Test
  public void testEquals() {
    AnimatedObjectCommand c2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
    assertEquals(ao1, new BasicAnimatedObject(
        new Rectangle()));
    assertEquals(ao2, new BasicAnimatedObject(
        new Ellipse()));

    BasicAnimatedObject ao3 = new BasicAnimatedObject(
        new Rectangle());

    ao3.addCommand(c2);

    assertNotEquals(ao1, ao3);
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 7, new Color(14, 100, 22),
            new Position2D(15, 1))));
    assertNotEquals(ao2, new BasicAnimatedObject(
        new Ellipse(2, 6, new Color(14, 110, 22),
            new Position2D(15, 1))));
  }

  /**
   * tests that if two BasicAnimatedObjects are equal, they have the same hashcode.
   */
  @Test
  public void testHashCode() {
    BasicAnimatedObject ao3 = new BasicAnimatedObject(
        new Rectangle());

    BasicAnimatedObject ao4 = new BasicAnimatedObject(
        new Ellipse());

    assertEquals(ao1, ao3);
    assertEquals(ao2, ao4);

    assertEquals(ao1.hashCode(), ao3.hashCode());
    assertEquals(ao2.hashCode(), ao4.hashCode());
  }


}
