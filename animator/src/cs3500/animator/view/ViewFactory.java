package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;

public class ViewFactory {
  public static AnimatorView build(String type, AnimatorModel model, double tickRate, Appendable out) throws IllegalArgumentException{
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
