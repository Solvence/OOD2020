package model.shape;

import java.awt.Color;
import java.util.Map;
import model.position2d.Position2D;

/**
 * Represents a 2D shape.
 */
public interface Shape {

  /**
   * Creates a new Shape with the given fields
   * @param position - the position of the Shape
   * @param color - the color of the Shape
   * @param size - a map of dimensions representing the size f the shape
   * @throws IllegalArgumentException - if any of the parameters are null, or if the dimensions
   * given in the size are invalid
   * @return a new Shape constructed with the given arguments
   */
  Shape build(Position2D position, Color color, Map<String, Integer> size)
      throws IllegalArgumentException;

  /**
   * Getter for position of a shape.
   * @return the position of the shape
   */
  Position2D getPosition();

  /**
   * Getter for color of a shape.
   * @return color of a shape
   */
  Color getColor();

  /**
   * Getter for a map representing the size dimensions of a shape.
   * @return size of a shape
   */
  Map<String, Integer> getSize();
}
