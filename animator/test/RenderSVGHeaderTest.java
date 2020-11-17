import static org.junit.Assert.assertEquals;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.shapevisitor.RenderSVGHeader;
import cs3500.animator.view.shapevisitor.ShapeVisitor;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests and Examples for the RenderSVGHeader class.
 */
public class RenderSVGHeaderTest {

  Rectangle rect;
  Ellipse ellipse;
  Shape rect1;
  Shape ellipse1;
  ShapeVisitor<String> visiter1;
  ShapeVisitor<String> visiter2;

  @Before
  public void setUp() {
    this.rect = new Rectangle(1,2,new Color(22,1,41), new Position2D(22, 10));
    this.ellipse = new Ellipse(10,15,new Color(1,1,1), new Position2D(2, 2));
    this.rect1 = new Rectangle(1,2,new Color(22,1,41), new Position2D(22, 10));
    this.ellipse1 = new Ellipse(10,15,new Color(1,1,1), new Position2D(2, 2));
    this.visiter1 = new RenderSVGHeader("s");
    this.visiter2 = new RenderSVGHeader("p");
  }

  // test visitRectangle.
  @Test
  public void testVisitRectangle() {
    assertEquals(visiter1.visitRectangle(rect),
        "<rect id=\"s\" x=\"22\" y=\"10\" width=\"1\" height=\"2\" fill=\"rgb(22,1,41)\" "
            + "visibility=\"hidden\" >\n");
    assertEquals(visiter2.visitRectangle(rect),
        "<rect id=\"p\" x=\"22\" y=\"10\" width=\"1\" height=\"2\" fill=\"rgb(22,1,41)\" "
            + "visibility=\"hidden\" >\n");
  }

  // test visitEllipse.
  @Test
  public void testVisitEllipse() {
    assertEquals(visiter1.visitEllipse(ellipse),
        "<ellipse id=\"s\" cx=\"2\" cy=\"2\" rx=\"10\" ry=\"15\" fill=\"rgb(1,1,1)\" "
            + "visibility=\"hidden\" >\n");
    assertEquals(visiter2.visitEllipse(ellipse),
        "<ellipse id=\"p\" cx=\"2\" cy=\"2\" rx=\"10\" ry=\"15\" fill=\"rgb(1,1,1)\" "
            + "visibility=\"hidden\" >\n");
  }

  // test apply
  // test visitEllipse.
  @Test
  public void testApply() {
    assertEquals(visiter1.apply(ellipse1), "<ellipse id=\"s\" cx=\"2\" cy=\"2\" rx=\"10\" "
        + "ry=\"15\" fill=\"rgb(1,1,1)\" visibility=\"hidden\" >\n");
    assertEquals(visiter1.apply(ellipse), "<ellipse id=\"s\" cx=\"2\" cy=\"2\" rx=\"10\" "
        + "ry=\"15\" fill=\"rgb(1,1,1)\" visibility=\"hidden\" >\n");
    assertEquals(visiter1.apply(ellipse1), visiter1.apply(ellipse));

    assertEquals(visiter1.apply(rect1), "<rect id=\"s\" x=\"22\" y=\"10\" width=\"1\" "
        + "height=\"2\" fill=\"rgb(22,1,41)\" visibility=\"hidden\" >\n");
    assertEquals(visiter1.apply(rect), "<rect id=\"s\" x=\"22\" y=\"10\" width=\"1\" "
        + "height=\"2\" fill=\"rgb(22,1,41)\" visibility=\"hidden\" >\n");
    assertEquals(visiter1.apply(rect), visiter1.apply(rect1));
  }
}
