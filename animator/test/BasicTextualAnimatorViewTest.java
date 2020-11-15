import cs3500.animator.model.color.Color;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.ActiveAnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.VisualAnimatorView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.TextualAnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * Tests and Examples for the BasicTextualAnimator class.
 */
public class BasicTextualAnimatorViewTest {


//  /**
//   * Tests that the textual cs3500.animator.view is empty when no Shapes have been created.
//   */
//  @Test
//  public void testStartToString() {
//    AnimatorModel model = new BasicAnimatorModel();
//    Appendable ap = new StringBuilder();
//    AnimatorView view = new TextualAnimatorView(model, ap);
//
//    assertEquals(view.toString(), "");
//  }
//
//  /**
//   * Tests that the textual cs3500.animator.view behaves as expected when one Shape is created.
//   */
//  @Test
//  public void testCreateOneToString() {
//    AnimatorModel model = new BasicAnimatorModel();
//    Appendable ap = new StringBuilder();
//    AnimatorView view = new TextualAnimatorView(model, ap);
//
//    assertEquals(view.toString(), "");
//
//    model.create("My Shape", new Rectangle(20, 10, Color.BLUE,
//        new Position2D(3, 5)));
//
//    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n");
//  }
//
//  /**
//   * Tests that the textual cs3500.animator.view behaves as expected when multiple Shapes are created.
//   */
//  @Test
//  public void testCreateMoreToString() {
//    AnimatorModel model = new BasicAnimatorModel();
//    Appendable ap = new StringBuilder();
//    AnimatorView view = new TextualAnimatorView(model, ap);
//
//    assertEquals(view.toString(), "");
//
//    model.create("My Shape", new Rectangle(20, 10, Color.BLUE,
//        new Position2D(3, 5)));
//
//    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n");
//
//    model.create("My Other Shape", new Ellipse(20, 10, Color.BLUE,
//        new Position2D(3, 5)));
//
//    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n"
//        + "Shape - My Other Shape - Ellipse\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n");
//  }

  /**
   * Tests that the textual cs3500.animator.view behaves as expected when multiple Shapes are created and multiple
   * commands are given.
   */
  @Test
  public void testAllToString() throws IOException { // remove throws later
    AnimatorModel model = new BasicAnimatorModel();
    model.initCanvas(new Position2D(1,1), new Dimension2D(1000,1000));
    Appendable ap = new StringBuilder();
    AnimatorView view = new TextualAnimatorView(model, ap, 2.0);
    //TEST
    AnimatorView view2 = new SVGAnimatorView(model, new FileWriter("TESTING.svg"), 2.0);

    ActiveAnimatorView view3 = new VisualAnimatorView(model.getCanvasPosition(), model.getCanvasSize());

    model.create("My Shape", new Rectangle(20, 10, new Color(1, 1, 199),
        new Position2D(3, 5)));

    model.create("My Other Shape", new Ellipse(15, 12, new Color(100, 0, 0),
        new Position2D(7, -3)));

    model.addMotion("My Shape", 1, 10, new Position2D(3, 5),
        new Position2D(10, 11), new Color(1, 1, 199), new Color(1, 1, 199)
    , new Dimension2D(20, 10), new Dimension2D(10, 40));

    model.addMotion("My Other Shape", 3, 12, new Position2D(7, -3),
        new Position2D(-10, 0), new Color(100, 0, 0), new Color(0, 255, 0),
        new Dimension2D(15, 12), new Dimension2D(15, 40));

    model.addMotion("My Shape", 10, 20, new Position2D(10, 11),
        new Position2D(10, 18), new Color(1, 1, 199), new Color(9, 1, 9)
        , new Dimension2D(10, 40), new Dimension2D(10, 90));

    model.addMotion("My Shape", 20, 27, new Position2D(10, 18), new Position2D(50, 60), new Color(9, 1, 9),
        new Color(255, 0, 0), new Dimension2D(10, 90), new Dimension2D(40, 30));
    List<Shape> shapes = new ArrayList<Shape>();
    for (String s : model.getAllShapeName()) {
      shapes.add(model.getShapeAt(s, 3));
    }

    view3.makeVisible();
    view3.setShapes(shapes);
    view3.render();

//    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n"
//        + "Motion My Shape 00 003 005 20 010 000 000 255      05 006 008 20 010 000 000 255\n"
//        + "Motion My Shape 05 006 008 20 010 000 000 255      07 007 009 20 010 039 030 216\n"
//        + "Motion My Shape 07 007 009 20 010 039 030 216      10 010 011 14 011 098 076 157\n"
//        + "Motion My Shape 10 010 011 14 011 098 076 157      16 010 011 00 015 215 169 040\n"
//        + "Motion My Shape 16 010 011 00 015 215 169 040      18 010 011 00 015 255 200 000\n"
//        + "Shape - My Other Shape - Ellipse\n"
//        + "Start: time | x | y | width| height| r | g | b |      "
//        + "End: time | x | y | width| height| r | g | b |\n"
//        + "Motion My Other Shape 03 007 -03 15 012 000 000 000      07 000 -02 15 012 000 000 000\n"
//        + "Motion My Other Shape 07 000 -02 15 012 000 000 000      07 000 -02 15 012 000 000 000\n"
//        + "Motion My Other Shape 07 000 -02 15 012 000 000 000      12 -10 000 15 014 098 098 098\n"
//        + "Motion My Other Shape 12 -10 000 15 014 098 098 098      20 -10 000 15 019 255 255 255\n"
//        + "Motion My Other Shape 20 -10 000 15 019 255 255 255      "
//        + "24 -10 000 15 022 255 255 255\n");
  }
}
