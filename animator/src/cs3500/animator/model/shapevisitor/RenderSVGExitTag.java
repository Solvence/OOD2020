package cs3500.animator.model.shapevisitor;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;

/**
 * Represents a function object to render an exit tag for a component in an SVG file.
 */
public class RenderSVGExitTag implements ShapeVisitor<StringBuilder> {

  @Override
  public StringBuilder visitEllipse(Ellipse e) {
    return new StringBuilder("</ellipse>\n");
  }

  @Override
  public StringBuilder visitRectangle(Rectangle r) {
    return new StringBuilder("</rect>\n");
  }

  @Override
  public StringBuilder apply(Shape shape) {
    return shape.accept(this);
  }
}
