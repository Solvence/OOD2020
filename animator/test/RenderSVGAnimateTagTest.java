import static org.junit.Assert.assertEquals;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.shapevisitor.RenderSVGAnimateTag;
import cs3500.animator.view.shapevisitor.ShapeVisitor;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RenderSVGAnimateTagTest {

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
    this.visiter1 = new RenderSVGAnimateTag(
        new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
            new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
            new Color(11, 10, 155)), 1, 15);
    this.visiter2 = new RenderSVGAnimateTag(
        new BasicCommand(15, 30, new Position2D(10, 10), new Position2D(25, 10),
            new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
            new Color(11, 11, 11)), 15, 30);
  }

  // test visitRectangle.
  @Test
  public void testVisitRectangle() {
    assertEquals(visiter1.visitRectangle(rect),
        "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
            + "begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
            + "/>\n");
    assertEquals(visiter2.visitRectangle(rect), "<set attributeName=\"visibility\" "
        + "from=\"hidden\"    to=\"visible\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "attributeName=\"x\" from=\"10\" to=\"25\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "attributeName=\"y\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "attributeName=\"width\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "attributeName=\"height\" from=\"10\" to=\"40\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(11,10,155)\" to=\"rgb(11,11,11)\" fill=\"freeze\" "
        + "/>\n");
  }

  // test visitEllipse.
  @Test
  public void testVisitEllipse() {
    assertEquals(visiter1.visitEllipse(ellipse),
        "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
            + "begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"cx\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\""
            + " attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"rx\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"ry\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
            + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
            + "visibility=\"visible\" />\n");
    assertEquals(visiter2.visitEllipse(ellipse),
        "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
            + "begin=\"15.000000ms\" dur=\"15.000000ms\" fill=\"remove\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
            + "attributeName=\"cx\" from=\"10\" to=\"25\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
            + "attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
            + "attributeName=\"rx\" from=\"10\" to=\"10\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"40\" fill=\"freeze\" visibility=\"visible\" "
            + "/>\n"
            + "<animate attributeType=\"xml\" begin=\"15.000000ms\" dur=\"15.000000ms\" "
            + "attributeName=\"fill\" from=\"rgb(11,10,155)\" to=\"rgb(11,11,11)\" fill=\"freeze\" "
            + "visibility=\"visible\" />\n");
  }

  // test apply
  // test visitEllipse.
  @Test
  public void testApply() {
    assertEquals(visiter1.apply(ellipse1), "<set attributeName=\"visibility\" from=\"hidden\""
        + "    to=\"visible\" begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"cx\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"rx\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"ry\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
        + "visibility=\"visible\" />\n");
    assertEquals(visiter1.apply(ellipse), "<set attributeName=\"visibility\" from=\"hidden\""
        + "    to=\"visible\" begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"cx\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"rx\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"ry\" from=\"1\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
        + "visibility=\"visible\" />\n");
    assertEquals(visiter1.apply(ellipse1), visiter1.apply(ellipse));

    assertEquals(visiter1.apply(rect1), "<set attributeName=\"visibility\" from=\"hidden\"   "
        + " to=\"visible\" begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
        + "/>\n");
    assertEquals(visiter1.apply(rect), "<set attributeName=\"visibility\" from=\"hidden\"   "
        + " to=\"visible\" begin=\"1.000000ms\" dur=\"14.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1.000000ms\" dur=\"14.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" "
        + "/>\n");
    assertEquals(visiter1.apply(rect), visiter1.apply(rect1));
  }


}
