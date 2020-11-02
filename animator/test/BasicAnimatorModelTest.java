import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.HashMap;
import model.AnimatorModel;
import model.BasicAnimatorModel;
import model.animatedObject.BasicAnimatedObject;
import model.animatedObjectCommand.ChangeColor;
import model.animatedObjectCommand.ChangeSize;
import model.animatedObjectCommand.Move;
import model.dimension2D.Dimension2D;
import model.position2d.Position2D;
import model.shape.Ellipse;
import model.shape.Rectangle;
import org.junit.Test;

/**
 * Examples and tests for the BasicAnimatorModel class
 */
public class BasicAnimatorModelTest {

  /**
   * Tests that the create method throws an exception if null inputs are
   * given or the given name already is assigned to a Shape
   */
  @Test
  public void testCreateException() {
    AnimatorModel model = new BasicAnimatorModel();

    try {
      model.create(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.create("Hi", null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.create(null, new Ellipse(20, 15, Color.BLUE,
          new Position2D(6, 4)));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    try {
      model.create("Pog", new Rectangle(22, 17, Color.ORANGE,
          new Position2D(2, -4)));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that the create method works as intended when proper inputs are given.
   * Also tests getAnimatedObjects works as intended (since this method must be called if
   * we wish to test the the expected changes from create occurred).
   */
  @Test
  public void testCreate() {
    AnimatorModel model = new BasicAnimatorModel();

    assertEquals(new HashMap<>(), model.getAnimatedObjects());

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    assertEquals(new BasicAnimatedObject(new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4))),
        model.getAnimatedObjects().get("Pog"));

    assertEquals(null, model.getAnimatedObjects().get("Wow"));

    model.create("Wow", new Rectangle(22, 0, Color.ORANGE,
        new Position2D(-6, -4)));

    assertEquals(new BasicAnimatedObject(new Rectangle(22, 0, Color.ORANGE,
            new Position2D(-6, -4))),
        model.getAnimatedObjects().get("Wow"));

    model.move("Pog", 0, 10,
        new Position2D(6, 4),
        new Position2D(0, -10));

    assertEquals(new Move(0, 10,
            new Position2D(6, 4),
            new Position2D(0, -10)),
        model.getAnimatedObjects().get("Pog").getCommands().get(0));

    model.changeColor("Wow", 3, 14,
        Color.ORANGE,
        Color.PINK);

    assertEquals(new ChangeColor(3, 14,
        Color.ORANGE,
        Color.PINK),
        model.getAnimatedObjects().get("Wow").getCommands().get(0));


  }

  /**
   * Tests that the move method functions properly given proper inputs
   */
  @Test
  public void testMove() {
    AnimatorModel model = new BasicAnimatorModel();

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    model.create("Wow", new Rectangle(22, 0, Color.ORANGE,
        new Position2D(-6, -4)));

    model.move("Pog", 0, 10,
        new Position2D(6, 4),
        new Position2D(0, -10));

    assertEquals(new Move(0, 10,
            new Position2D(6, 4),
            new Position2D(0, -10)),
        model.getAnimatedObjects().get("Pog").getCommands().get(0));

    model.move("Pog", 11, 20,
        new Position2D(0, -10),
        new Position2D(1, 1));

    assertEquals(new Move(11, 20,
            new Position2D(0, -10),
            new Position2D(1, 1)),
        model.getAnimatedObjects().get("Pog").getCommands().get(1));

    model.move("Wow", 3, 14,
        new Position2D(-6, -4),
        new Position2D(0, -10));

    assertEquals(new Move(3, 14,
        new Position2D(-6, -4),
        new Position2D(0, -10)),
        model.getAnimatedObjects().get("Wow").getCommands().get(0));
  }

  /**
   * Tests that the move method functions properly throws an exception if an overlapping move
   * command is attempted
   */
  @Test
  public void testMoveException() {
    AnimatorModel model = new BasicAnimatorModel();

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    model.move("Pog", 0, 10,
        new Position2D(6, 4),
        new Position2D(0, -10));

    assertEquals(new Move(0, 10,
            new Position2D(6, 4),
            new Position2D(0, -10)),
        model.getAnimatedObjects().get("Pog").getCommands().get(0));

    try {
      model.move("Pog", 9, 20,
          new Position2D(0, -10),
          new Position2D(1, 1));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that the changeColor method functions properly given proper inputs
   */
  @Test
  public void testChangeColor() {
    AnimatorModel model = new BasicAnimatorModel();

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    model.create("Wow", new Rectangle(22, 0, Color.ORANGE,
        new Position2D(-6, -4)));

    model.changeColor("Pog", 0, 10,
        Color.BLUE,
        Color.RED);

    assertEquals(new ChangeColor(0, 10,
            Color.BLUE,
            Color.RED),
        model.getAnimatedObjects().get("Pog").getCommands().get(0));

    model.changeColor("Pog", 10, 14,
        Color.RED,
        Color.GREEN);

    assertEquals(new ChangeColor(10, 14,
            Color.RED,
            Color.GREEN),
        model.getAnimatedObjects().get("Pog").getCommands().get(1));

    model.changeColor("Wow", 3, 14,
        Color.ORANGE,
        Color.PINK);

    assertEquals(new ChangeColor(3, 14,
            Color.ORANGE,
            Color.PINK),
        model.getAnimatedObjects().get("Wow").getCommands().get(0));


  }

  /**
   * Tests that the changeSize method functions properly given proper inputs
   */
  @Test
  public void testChangeSize() {
    AnimatorModel model = new BasicAnimatorModel();

    model.create("Pog", new Ellipse(20, 15, Color.BLUE,
        new Position2D(6, 4)));

    model.create("Wow", new Rectangle(22, 0, Color.ORANGE,
        new Position2D(-6, -4)));

    model.changeSize("Pog", 0, 10,
        new Dimension2D(20, 15),
        new Dimension2D(2, 5));

    assertEquals(new ChangeSize(0, 10,
            new Dimension2D(20, 15),
            new Dimension2D(2, 5)),
        model.getAnimatedObjects().get("Pog").getCommands().get(0));

    model.changeSize("Pog", 10, 14,
        new Dimension2D(2, 5),
        new Dimension2D(0, 40));

    assertEquals(new ChangeSize(10, 14,
            new Dimension2D(2, 5),
            new Dimension2D(0, 40)),
        model.getAnimatedObjects().get("Pog").getCommands().get(1));

    model.changeSize("Wow", 3, 14,
        new Dimension2D(22, 0),
        new Dimension2D(6, 22));

    assertEquals(new ChangeSize(3, 14,
            new Dimension2D(22, 0),
            new Dimension2D(6, 22)),
        model.getAnimatedObjects().get("Wow").getCommands().get(0));
  }
}
