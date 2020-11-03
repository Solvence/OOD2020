package model.shape;

import java.awt.Color;
import model.dimension2d.Dimension2D;
import model.position2d.Position2D;

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
}
