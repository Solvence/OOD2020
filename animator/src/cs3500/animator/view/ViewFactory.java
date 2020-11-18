package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

/**
 * Represents a factory class to help construct Views given the name of the type of view.
 */
public class ViewFactory {

  /**
   * Builds a view given the type of view.
   *
   * @param type     - String representing the type of view to be constructed
   * @param model    - the model that the view will be based upon.
   * @param tickRate - the tick rate of the view in ticks/sec
   * @param out      - the output file to which the view will write to, if applicable
   * @return - a View to the user's specifications
   * @throws IllegalArgumentException - if invalid arguments are passed, or the specified view type
   *                                  is not found
   */
  public static AnimatorView build(String type, AnimatorModel model, double tickRate,
      Appendable out) throws IllegalArgumentException {
    if (type == null || model == null || out == null || tickRate <= 0 || out == null) {
      throw new IllegalArgumentException("Invalid arguments. Inputs cannot be null and tickRate "
          + "must be positive");
    }
    switch (type) {
      case "visual":
        return new VisualAnimatorView(model.getCanvasPosition(), model.getCanvasSize());
      case "text":
        return new TextualAnimatorView(model, out, tickRate);
      case "svg":
        return new SVGAnimatorView(model, out, tickRate);
      default:
        throw new IllegalArgumentException("invalid view type");
    }
  }
}
