import static org.junit.Assert.assertEquals;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.shapevisitor.RenderSVGExitTag;
import cs3500.animator.view.shapevisitor.ShapeVisitor;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests and Examples for the RenderSVGExistTag class.
 */
public class RenderSVGExistTagTest {

  Rectangle rect;
  Ellipse ellipse;
  Shape rect1;
  Shape ellipse1;
  ShapeVisitor<String> visiter1;
  ShapeVisitor<String> visiter2;

  @Before
  public void setUp() {
    this.rect = new Rectangle();
    this.ellipse = new Ellipse();
    this.rect1 = new Rectangle();
    this.ellipse1 = new Ellipse();
    this.visiter1 = new RenderSVGExitTag();
    this.visiter2 = new RenderSVGExitTag();
  }

  // test visitRectangle.
  @Test
  public void testVisitRectangle() {
    assertEquals(visiter1.visitRectangle(rect),
        "</rect>\n");
    assertEquals(visiter2.visitRectangle(rect),
        "</rect>\n");
  }

  // test visitEllipse.
  @Test
  public void testVisitEllipse() {
    assertEquals(visiter1.visitEllipse(ellipse),
        "</ellipse>\n");
    assertEquals(visiter2.visitEllipse(ellipse),
        "</ellipse>\n");
  }

  // test apply
  // test visitEllipse.
  @Test
  public void testApply() {
    assertEquals(visiter1.apply(ellipse1), "</ellipse>\n");
    assertEquals(visiter1.apply(ellipse), "</ellipse>\n");
    assertEquals(visiter1.apply(ellipse1), visiter1.apply(ellipse));

    assertEquals(visiter1.apply(rect1), "</rect>\n");
    assertEquals(visiter1.apply(rect), "</rect>\n");
    assertEquals(visiter1.apply(rect), visiter1.apply(rect1));
  }
}
