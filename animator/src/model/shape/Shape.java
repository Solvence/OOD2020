package model.shape;

import java.awt.Color;
import java.util.List;
import model.position2d.Position2D;

/**
 * Represents a 2D shape.
 */
public interface Shape {

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
   * Getter for size of a shape.
   * @return size of a shape
   */
  List<Integer> getSize();
}
