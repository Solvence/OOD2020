package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AnimationView;

/**
 * Represents a factory class to help construct controllers given the name of the type of view.
 */
public class ControllerFactory {

  /**
   * Builds a controller given the type of view.
   *
   * @param type     - String representing the type of view corresponding to the controller to be
   *                   constructed
   * @param model    - the model that the controller will be based upon.
   * @param tickRate - the tick rate of the controller in ticks/sec
   * @param view      - the view corresponding to the controller to be constructed
   * @return - a Controller to the user's specifications
   * @throws IllegalArgumentException - if invalid arguments are passed, or the specified view type
   *                                  is not found
   */
  public static AnimationController build(String type, AnimationModel model, double tickRate,
      AnimationView view) throws IllegalArgumentException {
    if (type == null || model == null || view == null || tickRate <= 0) {
      throw new IllegalArgumentException("Invalid arguments. Inputs cannot be null and tickRate "
          + "must be positive");
    }
    switch (type) {
      case "visual":
        return new VisualAnimationController(view, model, tickRate);
      case "text":
      case "svg":
      case "provider":
      case "provider-text":
        return new StaticAnimationController(view);
      case "interactive":
        return new InteractiveAnimationController(view, model, tickRate);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }
}
