import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs3500.animator.controller.ControllerFactory;
import cs3500.animator.controller.InteractiveAnimationController;
import cs3500.animator.controller.StaticAnimationController;
import cs3500.animator.controller.VisualAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.view.ActionableAnimationView;
import cs3500.animator.view.ActiveAnimationView;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.TextualAnimationView;
import cs3500.animator.view.VisualAnimationView;
import org.junit.Test;

/**
 * Tests for the ControllerFactory class.
 */
public class ControllerFactoryTest {

  /**
   * Tests that the build method properly throws an exception when it is supposed to.
   */
  @Test
  public void testBuildException() {
    AnimationModel model = BasicAnimationModel.builder()
        .setBounds(0, 0, 100, 100).build();
    AnimationView textView = new TextualAnimationView(model, System.out, 10.0);
    ActiveAnimationView visualView =
        new VisualAnimationView(new Position2D(0, 0), new Dimension2D(100, 100));
    ActionableAnimationView interactiveView =
        new InteractiveAnimationView(new Position2D(0, 0), new Dimension2D(100, 100));
    try {
      ControllerFactory.build("visual", model, 0.0, visualView);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      ControllerFactory.build("visual", model, -2.0, visualView);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      ControllerFactory.build("visual", model, 10.0, null);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      ControllerFactory.build("visual", null, 10.0, visualView);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      ControllerFactory.build("bruh", model, 10.0, visualView);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that the build method functions properly given proper inputs.
   */
  @Test
  public void testBuild() {
    AnimationModel model = BasicAnimationModel.builder()
        .setBounds(0, 0, 100, 100).build();
    AnimationView textView = new TextualAnimationView(model, System.out, 10.0);
    ActiveAnimationView visualView =
        new VisualAnimationView(new Position2D(0, 0), new Dimension2D(100, 100));
    ActionableAnimationView interactiveView =
        new InteractiveAnimationView(new Position2D(0, 0), new Dimension2D(100, 100));

    assertTrue(ControllerFactory.build("visual", model, 10.0, visualView)
        instanceof VisualAnimationController);
    assertTrue(ControllerFactory.build("interactive", model, 10.0, interactiveView)
        instanceof InteractiveAnimationController);
    assertTrue(ControllerFactory.build("text", model, 10.0, textView)
        instanceof StaticAnimationController);
  }
}
