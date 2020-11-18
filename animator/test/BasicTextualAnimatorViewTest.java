import cs3500.animator.model.color.Color;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.TextualAnimatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests and Examples for the BasicTextualAnimator class.
 */
public class BasicTextualAnimatorViewTest {


  /**
   * Tests that the textual cs3500.animator.view is empty when no Shapes have been created.
   */
  @Test
  public void testStartToString() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);

    assertEquals("Canvas 30 50 100 100\n", view.toString());
  }

  /**
   * Tests that the textual view behaves as expected when one Shape is created.
   */
  @Test
  public void testCreateOneToString() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);

    assertEquals(view.toString(), "Canvas 30 50 100 100\n");

    model.create("My Shape", new Rectangle(20, 10,
        new Color(0, 255, 0),
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Canvas 30 50 100 100\n"
        + "Shape - My Shape - Rectangle\n");
  }

  /**
   * Tests that the textual cs3500.animator.view behaves as expected when multiple Shapes are
   * created.
   */
  @Test
  public void testCreateMoreToString() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);

    assertEquals(view.toString(), "Canvas 30 50 100 100\n");

    model.create("My Shape", new Rectangle(20, 10,
        new Color(0, 255, 0),
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Canvas 30 50 100 100\n"
        + "Shape - My Shape - Rectangle\n");

    model.create("My Other Shape", new Ellipse(20, 10,
        new Color(255, 0, 0),
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Canvas 30 50 100 100\n"
        + "Shape - My Shape - Rectangle\n"
        + "Shape - My Other Shape - Ellipse\n");
  }

  /**
   * Tests that the textual cs3500.animator.view behaves as expected when multiple Shapes are
   * created and multiple commands are given.
   */
  @Test
  public void testAllToString() throws IOException { // remove throws later
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);

    model.create("My Shape", new Rectangle(20, 10,
        new Color(1, 1, 199),
        new Position2D(3, 5)));

    model.create("My Other Shape", new Ellipse(15, 12,
        new Color(100, 0, 0),
        new Position2D(7, -3)));

    model.addMotion("My Shape", 1, 10, new Position2D(3, 5),
        new Position2D(10, 11), new Color(1, 1, 199),
        new Color(1, 1, 199)
        , new Dimension2D(20, 10), new Dimension2D(10, 40));

    model.addMotion("My Other Shape", 3, 12, new Position2D(7, -3),
        new Position2D(-10, 0), new Color(100, 0, 0),
        new Color(0, 255, 0),
        new Dimension2D(15, 12), new Dimension2D(15, 40));

    model.addMotion("My Shape", 10, 20, new Position2D(10, 11),
        new Position2D(10, 18), new Color(1, 1, 199),
        new Color(9, 1, 9)
        , new Dimension2D(10, 40), new Dimension2D(10, 90));

    model.addMotion("My Shape", 20, 27, new Position2D(10, 18),
        new Position2D(50, 60),
        new Color(9, 1, 9),
        new Color(255, 0, 0), new Dimension2D(10, 90),
        new Dimension2D(40, 30));
    List<Shape> shapes = new ArrayList<Shape>();
    for (String s : model.getAllShapeName()) {
      shapes.add(model.getShapeAt(s, 3));
    }

    assertEquals(view.toString(), "Canvas 30 50 100 100\n"
        + "Shape - My Shape - Rectangle\n"
        + "Motion My Shape 0.50 3 5 20 10 1 1 199      5.00 10 11 10 40 1 1 199\n"
        + "Motion My Shape 5.00 10 11 10 40 1 1 199      10.00 10 18 10 90 9 1 9\n"
        + "Motion My Shape 10.00 10 18 10 90 9 1 9      13.50 50 60 40 30 255 0 0\n"
        + "Shape - My Other Shape - Ellipse\n"
        + "Motion My Other Shape 1.50 7 -3 15 12 100 0 0      6.00 -10 0 15 40 0 255 0\n");
  }

  /**
   * Tests that the constructor for the textual view properly throws an exception when it's supposed
   * to.
   */
  @Test
  public void testConstructorException() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    try {
      AnimatorView view = new TextualAnimatorView(model, ap, 0.0);
      fail();
    } catch (IllegalArgumentException e) {
      //pass!
    }

    try {
      AnimatorView view = new TextualAnimatorView(null, ap, 2.0);
      fail();
    } catch (IllegalArgumentException e) {
      //pass!
    }

    try {
      AnimatorView view = new TextualAnimatorView(model, null, 2.0);
      fail();
    } catch (IllegalArgumentException e) {
      //pass!
    }

    try {
      AnimatorView view = new TextualAnimatorView(null, ap, 0.0);
      fail();
    } catch (IllegalArgumentException e) {
      //pass!
    }
  }

  /**
   * Tests that the render method functions properly.
   */
  @Test
  public void testRender() throws IOException {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);

    model.create("My Shape", new Rectangle(20, 10,
        new Color(1, 1, 199),
        new Position2D(3, 5)));

    model.create("My Other Shape", new Ellipse(15, 12,
        new Color(100, 0, 0),
        new Position2D(7, -3)));

    model.addMotion("My Shape", 1, 10, new Position2D(3, 5),
        new Position2D(10, 11), new Color(1, 1, 199),
        new Color(1, 1, 199)
        , new Dimension2D(20, 10), new Dimension2D(10, 40));

    model.addMotion("My Other Shape", 3, 12, new Position2D(7, -3),
        new Position2D(-10, 0), new Color(100, 0, 0),
        new Color(0, 255, 0),
        new Dimension2D(15, 12), new Dimension2D(15, 40));

    model.addMotion("My Shape", 10, 20, new Position2D(10, 11),
        new Position2D(10, 18), new Color(1, 1, 199),
        new Color(9, 1, 9)
        , new Dimension2D(10, 40), new Dimension2D(10, 90));

    model.addMotion("My Shape", 20, 27, new Position2D(10, 18),
        new Position2D(50, 60),
        new Color(9, 1, 9),
        new Color(255, 0, 0), new Dimension2D(10, 90),
        new Dimension2D(40, 30));
    List<Shape> shapes = new ArrayList<Shape>();
    for (String s : model.getAllShapeName()) {
      shapes.add(model.getShapeAt(s, 3));
    }

    assertEquals("", ap.toString());

    view.render();

    assertEquals(ap.toString(), "Canvas 30 50 100 100\n"
        + "Shape - My Shape - Rectangle\n"
        + "Motion My Shape 0.50 3 5 20 10 1 1 199      5.00 10 11 10 40 1 1 199\n"
        + "Motion My Shape 5.00 10 11 10 40 1 1 199      10.00 10 18 10 90 9 1 9\n"
        + "Motion My Shape 10.00 10 18 10 90 9 1 9      13.50 50 60 40 30 255 0 0\n"
        + "Shape - My Other Shape - Ellipse\n"
        + "Motion My Other Shape 1.50 7 -3 15 12 100 0 0      6.00 -10 0 15 40 0 255 0\n");
  }

  /**
   * Tests that the translateToTime method functions properly.
   */
  @Test
  public void testTranslateToTime() {
    AnimatorModel model = BasicAnimatorModel.builder()
        .setBounds(30, 50, 100, 100).build();
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 3.0);

    assertEquals(3.0, view.translateToTime(9), .0001);
    assertEquals(1.0, view.translateToTime(3), .0001);
    assertEquals(0.33333333333333333, view.translateToTime(1), .0001);
    assertEquals(1.666666666666666666666, view.translateToTime(5), .0001);
  }
}
