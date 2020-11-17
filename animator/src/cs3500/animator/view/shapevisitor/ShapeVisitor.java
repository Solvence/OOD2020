package cs3500.animator.view.shapevisitor;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import java.util.function.Function;

/**
 * Represents a function object to be applied on a Shape.
 * @param <R> - The return type of this function object
 */
public interface ShapeVisitor<R> extends Function<Shape, R> {

  /**
   * applies this function on an Ellipse.
   * @param e - the ellipse that this function is being applied on
   * @return - the result of this function being applied on the given ellipse
   */
  R visitEllipse(Ellipse e);

  /**
   * applies this function on a Rectangle.
   * @param r - the rectangle that this function is being applied on
   * @return - the result of this function being applied on the given rectangle
   */
  R visitRectangle(Rectangle r);
}
