package cs3500.animator.controller;

import static java.lang.Thread.sleep;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.ActiveAnimationView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a controller that plays through an animation frame by frame once.
 */
public class VisualAnimationController implements AnimationController {
  private final ActiveAnimationView view;
  private final AnimationModel model;
  private final double initialTickRate;

  /**
   * Constructs an animation controller for visual views.
   * @param view - the view to be rendered
   * @param model - the model that this animation operates under
   * @param initialTickRate - the tick rate of this visual animation
   * @throws IllegalArgumentException - if the view given is not supported by this controller, or
   *                                    the tick rate is not positive
   */
  public VisualAnimationController(AnimationView view, AnimationModel model, double initialTickRate)
      throws IllegalArgumentException{
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    if (!(view instanceof ActiveAnimationView)) {
      throw new IllegalArgumentException("This controller does not support that type of view");
    }
    if (initialTickRate <= 0) {
      throw new IllegalArgumentException("tick rate must be positive");
    }
    this.view = (ActiveAnimationView) view;
    this.model = model;
    this.initialTickRate = initialTickRate;
  }

  @Override
  public void go() {
    try {
      int tick = 0;
      view.makeVisible();
      while (view.isActive()) {
        List<Shape> shapes = new ArrayList<Shape>();
        for (String s : model.getAllShapeName()) {
          shapes.add(model.getShapeAt(s, tick));
        }
        view.setShapes(shapes);
        view.render();
        sleep((int) (1000 / initialTickRate));

        tick += 1;
      }
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
