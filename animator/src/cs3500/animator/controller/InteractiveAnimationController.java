package cs3500.animator.controller;

import static java.lang.Thread.sleep;

import cs3500.animator.controller.actionlistener.AnimationActionListener;
import cs3500.animator.controller.actionlistener.InteractiveActionListener;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.ActionableAnimationView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a controller for live animations that the user can interact with.
 */
public class InteractiveAnimationController implements AnimationController {

  private final ActionableAnimationView view;
  private final AnimationModel model;
  private final AnimationActionListener listener;

  /**
   * Constructs an animation controller for interactive views.
   *
   * @param view            - the view to be rendered
   * @param model           - the model that this animation operates under
   * @param initialTickRate - the initial tick rate of this interactive animation
   * @throws IllegalArgumentException - if the view given is not supported by this controller, or
   *                                  the tick rate is not positive
   */
  public InteractiveAnimationController(AnimationView view, AnimationModel model,
      double initialTickRate)
      throws IllegalArgumentException {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    if (!(view instanceof ActionableAnimationView)) {
      throw new IllegalArgumentException("This controller does not support that type of view");
    }
    if (initialTickRate <= 0) {
      throw new IllegalArgumentException("tick rate must be positive");
    }
    this.view = (ActionableAnimationView) view;
    this.model = model;
    this.listener = new InteractiveActionListener(initialTickRate, this.computeEndTick());
  }

  /**
   * Computes the last tick of the defined animation based off the commands in the given model.
   *
   * @return - the end tick
   */
  private int computeEndTick() {
    int endTick = 1;

    for (String s : this.model.getAllShapeName()) {
      for (AnimatedObjectCommand command : model.getCommandsForShape(s)) {
        if (command.getEndTime() > endTick) {
          endTick = command.getEndTime();
        }
      }
    }

    return endTick;
  }

  @Override
  public void start() {
    try {
      view.setListener(listener);
      view.makeVisible();
      while (view.isActive()) {
        List<Shape> shapes = new ArrayList<Shape>();
        for (String s : model.getAllShapeName()) {
          shapes.add(model.getShapeAt(s, this.listener.getTick()));
        }
        view.setShapes(shapes);
        view.render();
        sleep((int) (1000 / this.listener.getTickRate()));
        this.listener.incrementTick();
      }
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
