package cs3500.animator.view.shapevisitor;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;

/**
 * Represents a function object to render an exit tag for a component in an SVG file.
 */
public class RenderSVGExitTag implements ShapeVisitor<String> {

  @Override
  public String visitEllipse(Ellipse e) {
    return "</ellipse>\n";
  }

  @Override
  public String visitRectangle(Rectangle r) {
    return "</rect>\n";
  }

  @Override
  public String apply(Shape shape) {
    return shape.accept(this);
  }
}
