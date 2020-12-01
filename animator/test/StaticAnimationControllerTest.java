import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.StaticAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualAnimationView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Examples and tests for StaticAnimationController.
 */
public class StaticAnimationControllerTest {

  /**
   * Preoperly tests that the static controller throws an exception when expected and doesn't throw
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

  @Test
  public void testGo() {
    AnimationModel model = BasicAnimationModel.builder()
        .setBounds(20, 20, 200, 200).build();
    AnimationView view = new TextualAnimationView(model, System.out, 10.0);
    StaticAnimationController controller1 = new StaticAnimationController(view);

    controller1.go();
  }
}
