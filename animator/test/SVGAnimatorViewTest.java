import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests and Examples for the SVGAnimatorView class.
 */
public class SVGAnimatorViewTest {
  AnimatorModel model;
  AnimatorView view1;
  AnimatedObjectCommand c1;
  AnimatedObjectCommand c2;
  Appendable log;
  double tickRate;

  @Before
  public void setUp() {
    this.model = BasicAnimatorModel.builder().setBounds(0,0, 100, 100).build();
    this.log = new StringBuilder();
    tickRate = 1;
    this.view1 = new SVGAnimatorView(model, log, tickRate);
    this.c1 = new BasicCommand(1, 15, new Position2D(0, 0), new Position2D(10, 10),
        new Dimension2D(1, 1), new Dimension2D(10, 10), new Color(10, 10, 10),
        new Color(11, 10, 155));
    this.c2 = new BasicCommand(15, 30, new Position2D(10, 10),
        new Position2D(25, 10),
        new Dimension2D(10, 10), new Dimension2D(10, 40), new Color(11, 10, 155),
        new Color(11, 11, 11));
  }

  @Test
  public void testRender() {
    try {
      view1.render();
    } catch (IOException e) {
      // do nothing
    }
    assertEquals(this.log.toString(), "<svg viewBox = \"0 0 100 100\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "</svg>\n");

    setUp();
    model.create("s", new Rectangle());
    model.addMotion("s", 1, 15, new Position2D(0, 0), new Position2D(10, 10)
        , new Color(10, 10, 10), new Color(11, 10, 155),
        new Dimension2D(1, 1), new Dimension2D(10, 10));

    try {
      view1.render();
    } catch (IOException e) {
      // do nothing
    }
    assertEquals(this.log.toString(), "<svg viewBox = \"0 0 100 100\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"s\" x=\"0\" y=\"0\" width=\"1\" height=\"1\" fill=\"rgb(10,10,10)\" "
        + "visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"1000.000000ms\" dur=\"14000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>\n");

    setUp();
    model.create("s", new Rectangle());
    model.addMotion("s", 1, 15, new Position2D(0, 0), new Position2D(10, 10)
        , new Color(10, 10, 10), new Color(11, 10, 155),
        new Dimension2D(1, 1), new Dimension2D(10, 10));

    model.addMotion("s", 15, 30, new Position2D(10, 10),
        new Position2D(25, 10), new Color(11, 10, 155),
        new Color(11, 11, 11),  new Dimension2D(10, 10),
        new Dimension2D(10, 40));

    try {
      view1.render();
    } catch (IOException e) {
      // do nothing
    }
    assertEquals(this.log.toString(), "<svg viewBox = \"0 0 100 100\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"s\" x=\"0\" y=\"0\" width=\"1\" height=\"1\" fill=\"rgb(10,10,10)\" "
        + "visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"1000.000000ms\" dur=\"14000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" />\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"15000.000000ms\" dur=\"15000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"x\" from=\"10\" to=\"25\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"y\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"width\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"height\" from=\"10\" to=\"40\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(11,10,155)\" to=\"rgb(11,11,11)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "</svg>\n");

    setUp();
    model.create("s", new Rectangle());
    model.addMotion("s", 1, 15, new Position2D(0, 0), new Position2D(10, 10)
        , new Color(10, 10, 10), new Color(11, 10, 155),
        new Dimension2D(1, 1), new Dimension2D(10, 10));

    model.addMotion("s", 15, 30, new Position2D(10, 10),
        new Position2D(25, 10), new Color(11, 10, 155),
        new Color(11, 11, 11),  new Dimension2D(10, 10),
        new Dimension2D(10, 40));

    model.create("e", new Ellipse());
    model.addMotion("e", 15, 30, new Position2D(10, 10),
        new Position2D(25, 10), new Color(11, 10, 155),
        new Color(11, 11, 11),  new Dimension2D(10, 10),
        new Dimension2D(10, 40));

    try {
      view1.render();
    } catch (IOException e) {
      // do nothing
    }
    assertEquals(this.log.toString(), "<svg viewBox = \"0 0 100 100\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"s\" x=\"0\" y=\"0\" width=\"1\" height=\"1\" fill=\"rgb(10,10,10)\" "
        + "visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"1000.000000ms\" dur=\"14000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"width\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"height\" from=\"1\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.000000ms\" dur=\"14000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(11,10,155)\" fill=\"freeze\" />\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"15000.000000ms\" dur=\"15000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"x\" from=\"10\" to=\"25\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"y\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"width\" from=\"10\" to=\"10\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"height\" from=\"10\" to=\"40\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(11,10,155)\" to=\"rgb(11,11,11)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "<ellipse id=\"e\" cx=\"10\" cy=\"10\" rx=\"10\" ry=\"10\" fill=\"rgb(11,10,155)\" "
        + "visibility=\"hidden\" >\n"
        + "<set attributeName=\"visibility\" from=\"hidden\"    to=\"visible\" "
        + "begin=\"15000.000000ms\" dur=\"15000.000000ms\" fill=\"remove\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"cx\" from=\"10\" to=\"25\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"rx\" from=\"10\" to=\"10\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"ry\" from=\"10\" to=\"40\" fill=\"freeze\" visibility=\"visible\" />\n"
        + "<animate attributeType=\"xml\" begin=\"15000.000000ms\" dur=\"15000.000000ms\" "
        + "attributeName=\"fill\" from=\"rgb(11,10,155)\" to=\"rgb(11,11,11)\" fill=\"freeze\" "
        + "visibility=\"visible\" />\n"
        + "</ellipse>\n"
        + "</svg>\n");
  }

  // test translateToTime
  @Test
  public void testTranslateToTime() {
    assertEquals(view1.translateToTime(1), 1000.0, 0.01);
    assertEquals(view1.translateToTime(2), 2000.0, 0.01);
    assertEquals(view1.translateToTime(100), 100000.0, 0.01);

    tickRate = 5;
    view1 = new SVGAnimatorView(model, log, tickRate);
    assertEquals(view1.translateToTime(1), 200.0, 0.01);
    assertEquals(view1.translateToTime(2), 400.0, 0.01);
    assertEquals(view1.translateToTime(100), 20000.0, 0.01);
  }
}
