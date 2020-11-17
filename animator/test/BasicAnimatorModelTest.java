import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import java.util.ArrayList;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import org.junit.Test;

/**
 * Examples and tests for the BasicAnimatorModel class.
 */
public class BasicAnimatorModelTest {

  /**
   * Tests that the create method throws an exception if null inputs are given or the given name
   * already is assigned to a Shape.
   */
  @Test
  public void testCreateException() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(10, 20, 50, 50).build();

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
      model.create(null, new Ellipse(20, 15,
          new Color(255, 0, 0),
          new Position2D(6, 4)));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    model.create("Pog", new Ellipse(20, 15, new Color(255, 0, 0),
        new Position2D(6, 4)));

    try {
      model.create("Pog", new Rectangle(22, 17, new Color(255, 255, 0),
          new Position2D(2, -4)));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that the create method works as intended when proper inputs are given. Also tests
   * getAnimatedObjects works as intended (since this method must be called if we wish to test the
   * the expected changes from create occurred).
   */
  @Test
  public void testCreate() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(10, 20, 50, 50).build();

    assertEquals(new ArrayList<String>(), model.getAllShapeName());

    model.create("Pog", new Ellipse(20, 15, new Color(255, 0, 0),
        new Position2D(6, 4)));

    assertEquals("Pog",
        model.getAllShapeName().get(0));

    model.create("Wow", new Rectangle(22, 0, new Color(255, 0, 0),
        new Position2D(-6, -4)));

    assertEquals("Wow",
        model.getAllShapeName().get(1));


  }

  /**
   * Tests that the move method functions properly given proper inputs.
   */
  @Test
  public void testAddMotion() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(10, 20, 50, 50).build();

    model.create("Pog", new Ellipse(20, 15, new Color(255, 0, 0),
        new Position2D(6, 4)));

    model.create("Wow", new Rectangle(22, 0, new Color(255, 255, 0),
        new Position2D(-6, -4)));

    model.addMotion("Pog", 1, 10,
        new Position2D(6, 4),
        new Position2D(0, -10), new Color(255, 0, 0),
        new Color(0, 0, 255), new Dimension2D(20, 15),
        new Dimension2D(15, 20));

    assertEquals(new BasicCommand(1, 10,
            new Position2D(6, 4),
            new Position2D(0, -10), new Dimension2D(20, 15),
            new Dimension2D(15, 20), new Color(255, 0, 0),
            new Color(0, 0, 255)),
        model.getCommandsForShape("Pog").get(0));
  }

  /**
   * Tests that the move method functions properly throws an exception if an overlapping move
   * command is attempted, if the start time/state of one command does not equal the end time/state
   * of the previous command, if a command is inputted before the canvas has been initialized, or if
   * a command with invalid fields is inputted.
   */
  @Test
  public void testMotionException() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(10, 20, 50, 50).build();

    AnimatorModel model2 = BasicAnimatorModel.builder().build();

    model.create("Pog", new Ellipse(20, 15, new Color(255, 0, 0),
        new Position2D(6, 4)));

    try {
      model.addMotion("Pog", 0, 10,
          new Position2D(6, 4),
          new Position2D(0, -10), new Color(255, 0, 0),
          new Color(0, 0, 255), new Dimension2D(20, 15),
          new Dimension2D(15, 20));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    model.addMotion("Pog", 1, 10,
        new Position2D(6, 4),
        new Position2D(0, -10), new Color(255, 0, 0),
        new Color(0, 0, 255), new Dimension2D(20, 15),
        new Dimension2D(15, 20));

    assertEquals(new BasicCommand(1, 10,
            new Position2D(6, 4),
            new Position2D(0, -10), new Dimension2D(20, 15),
            new Dimension2D(15, 20), new Color(255, 0, 0),
            new Color(0, 0, 255)),
        model.getCommandsForShape("Pog").get(0));

    try {
      model.addMotion("Pog", 9, 12,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.addMotion("Pog", 11, 14,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.addMotion("Pog", 10, 15,
          new Position2D(0, -9),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.addMotion("Pog", 10, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 2, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.addMotion("Pog", 10, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(16, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model.addMotion("Pogg", 10, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      model2.addMotion("Pog", 10, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
    model2.create("Wow", new Rectangle());
    try {

      model2.addMotion("Wow", 10, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalStateException e) {
      // pass!
    }

    model2.initCanvas(new Position2D(1, 1), new Dimension2D(40, 40));

    try {

      model2.addMotion("Wow", 0, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {

      model2.addMotion("Wow", -1, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {

      model2.addMotion("Wow", 8, 7,
          new Position2D(0, -10),
          new Position2D(-40, 30), new Color(0, 0, 255),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {

      model2.addMotion("Wow", 1, 15,
          new Position2D(0, -10),
          new Position2D(-40, 30), null,
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {

      model2.addMotion("Wow", 1, 15,
          new Position2D(0, -10),
          null, new Color(255, 0, 0),
          new Color(0, 255, 255), new Dimension2D(15, 20),
          new Dimension2D(5, 50));
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }
}
