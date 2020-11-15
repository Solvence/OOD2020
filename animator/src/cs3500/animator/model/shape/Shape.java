package cs3500.animator.model.shape;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.view.shapevisitor.ShapeVisitor;

/**
 * Represents a 2D shape.
 */
public interface Shape {

  /**
   * Creates a new Shape with the given fields.
   *
   * @param position - the position of the Shape
   * @param color    - the color of the Shape
   * @param size     - a pair of integers representing the size dimensions of a shape's bounding
   *                 box.
   * @return a new Shape constructed with the given arguments
   * @throws IllegalArgumentException - if any of the parameters are null, or if the dimensions
   *                                  given in the size are invalid
   */
  Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException;

  /**
   * Getter for position of a shape.
   *
   * @return the position of the shape
   */
  Position2D getPosition();

  /**
   * Getter for color of a shape.
   *
   * @return color of a shape
   */
  Color getColor();

  /**
   * Getter for a pair of integers representing the size dimensions of a shape's bounding box.
   *
   * @return size of a shape
   */
  Dimension2D getSize();


  /**
   * Accepts a visitor and applies its functionality to this shape.
   * @param visitor - the visitor function object to be applied on this Shape
   * @param <R> - The return type of the given visitor
   * @return - The result of applying the visitor's functionality on this Shape
   */
  <R> R accept(ShapeVisitor<R> visitor);
}
