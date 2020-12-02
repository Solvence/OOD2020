import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.StaticAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.TextualAnimationView;
import org.junit.Test;

/**
 * Examples and tests for StaticAnimationController.
 */
public class StaticAnimationControllerTest {

  /**
   * Properly tests that the static controller throws an exception when expected and doesn't throw
   * an exception otherwise.
   */
  @Test
  public void testConstructor() {
    try {
      StaticAnimationController controller1 = new StaticAnimationController(null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      AnimationView view2 = null;
      StaticAnimationController controller1 = new StaticAnimationController(view2);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      AnimationModel model = BasicAnimationModel.builder()
          .setBounds(20, 20, 200, 200).build();
      AnimationView view = new TextualAnimationView(model, System.out, 10.0);
      StaticAnimationController controller1 = new StaticAnimationController(view);
      // pass!
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  /**
   * Tests the go method in a static controller.
   */
  @Test
  public void testGo() {
    AnimationModel model = BasicAnimationModel.builder()
        .setBounds(20, 20, 200, 200).build();
    model.create("Bob", new Rectangle());
    model.addMotion("Bob", 1, 10, new Position2D(0, 0),
        new Position2D(10, 20), new Color(255, 0, 0),
        new Color(0, 180, 180), new Dimension2D(30, 30),
        new Dimension2D(40, 20));
    StringBuilder sb = new StringBuilder();
    AnimationView view = new TextualAnimationView(model, sb, 10.0);
    StaticAnimationController controller1 = new StaticAnimationController(view);

    assertEquals(sb.toString(), "");

    controller1.start();

    assertEquals(sb.toString(), "Canvas 20 20 200 200\n"
        + "Shape - Bob - Rectangle\n"
        + "Motion Bob 0.10 0 0 30 30 255 0 0      1.00 10 20 40 20 0 180 180\n");
  }
}
