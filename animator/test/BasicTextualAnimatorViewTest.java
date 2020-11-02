import java.awt.Color;
import model.AnimatorModel;
import model.BasicAnimatorModel;
import model.dimension2D.Dimension2D;
import model.position2d.Position2D;
import model.shape.Ellipse;
import model.shape.Rectangle;
import org.junit.Test;
import view.AnimatorView;
import view.BasicTextualAnimatorView;

import static org.junit.Assert.assertEquals;

public class BasicTextualAnimatorViewTest {


  /**
   * Tests that the textual view is empty when no Shapes have been created
   */
  @Test
  public void testStartToString() {
    AnimatorModel model = new BasicAnimatorModel();
    Appendable ap = new StringBuilder();
    AnimatorView view = new BasicTextualAnimatorView(model, ap);

    assertEquals(view.toString(), "");
  }

  /**
   * Tests that the textual view behaves as expected when one Shape is created
   */
  @Test
  public void testCreateOneToString() {
    AnimatorModel model = new BasicAnimatorModel();
    Appendable ap = new StringBuilder();
    AnimatorView view = new BasicTextualAnimatorView(model, ap);

    assertEquals(view.toString(), "");

    model.create("My Shape", new Rectangle(20, 10, Color.BLUE,
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n");
  }

  /**
   * Tests that the textual view behaves as expected when multiple Shapes are created
   */
  @Test
  public void testCreateMoreToString() {
    AnimatorModel model = new BasicAnimatorModel();
    Appendable ap = new StringBuilder();
    AnimatorView view = new BasicTextualAnimatorView(model, ap);

    assertEquals(view.toString(), "");

    model.create("My Shape", new Rectangle(20, 10, Color.BLUE,
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n");

    model.create("My Other Shape", new Ellipse(20, 10, Color.BLUE,
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
        + "Shape - My Other Shape - Ellipse\n");
  }

  /**
   * Tests that the textual view behaves as expected when multiple Shapes are created and multiple
   * commands are given
   */
  @Test
  public void testAllToString() {
    AnimatorModel model = new BasicAnimatorModel();
    Appendable ap = new StringBuilder();
    AnimatorView view = new BasicTextualAnimatorView(model, ap);

    assertEquals(view.toString(), "");

    model.create("My Shape", new Rectangle(20, 10, Color.BLUE,
        new Position2D(3, 5)));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
        + "Start: time | x | y | width| height| r | g | b |      "
        + "End: time | x | y | width| height| r | g | b |\n");

    model.create("My Other Shape", new Ellipse(15, 12, Color.BLACK,
        new Position2D(7, -3)));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
        + "Start: time | x | y | width| height| r | g | b |      "
        + "End: time | x | y | width| height| r | g | b |\n"
        + "Shape - My Other Shape - Ellipse\n"
        + "Start: time | x | y | width| height| r | g | b |      "
        + "End: time | x | y | width| height| r | g | b |\n");

    model.move("My Shape", 0, 10, new Position2D(3, 5),
        new Position2D(10, 11));

    model.move("My Other Shape", 3, 12, new Position2D(7, -3),
        new Position2D(-10, 0));

    model.changeColor("My Shape", 5, 18, Color.BLUE, Color.ORANGE);

    model.changeColor("My Other Shape", 7, 20, Color.BLACK, Color.WHITE);

    model.changeSize("My Shape", 7, 16, new Dimension2D(20, 10),
        new Dimension2D(0, 15));
    model.changeSize("My Other Shape", 7, 24,
        new Dimension2D(15, 12),
        new Dimension2D(15, 22));

    assertEquals(view.toString(), "Shape - My Shape - Rectangle\n"
        + "Start: time | x | y | width| height| r | g | b |      "
        + "End: time | x | y | width| height| r | g | b |\n"
        + "Motion My Shape 00 003 005 20 010 000 000 255      05 006 008 20 010 000 000 255\n"
        + "Motion My Shape 05 006 008 20 010 000 000 255      07 007 009 20 010 039 030 216\n"
        + "Motion My Shape 07 007 009 20 010 039 030 216      10 010 011 14 011 098 076 157\n"
        + "Motion My Shape 10 010 011 14 011 098 076 157      16 010 011 00 015 215 169 040\n"
        + "Motion My Shape 16 010 011 00 015 215 169 040      18 010 011 00 015 255 200 000\n"
        + "Shape - My Other Shape - Ellipse\n"
        + "Start: time | x | y | width| height| r | g | b |      "
        + "End: time | x | y | width| height| r | g | b |\n"
        + "Motion My Other Shape 03 007 -03 15 012 000 000 000      07 000 -02 15 012 000 000 000\n"
        + "Motion My Other Shape 07 000 -02 15 012 000 000 000      07 000 -02 15 012 000 000 000\n"
        + "Motion My Other Shape 07 000 -02 15 012 000 000 000      12 -10 000 15 014 098 098 098\n"
        + "Motion My Other Shape 12 -10 000 15 014 098 098 098      20 -10 000 15 019 255 255 255\n"
        + "Motion My Other Shape 20 -10 000 15 019 255 255 255      "
        + "24 -10 000 15 022 255 255 255\n");
  }
}
