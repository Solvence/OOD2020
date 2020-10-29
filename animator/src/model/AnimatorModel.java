package model;

import java.awt.Color;
import java.util.List;
import model.position2d.Position2D;
import model.shape.Shape;

/**
 * Represents the model component for a animation that displays shapes.
 */
public interface AnimatorModel {

  /**
   * Adds a new shape with a given name to our animator.
   *
   * @param name - name of the shape
   * @param s    - type of shape
   */
  void create(String name, Shape s);

  /**
   * Returns a List of copies of the shapes being animated by this Model at the given
   * time.
   *
   * @param time time frame to be rendered
   * @return List of copies of shapes
   */
  List<Shape> getStateAt(int time);

  /**
   * Move a shape with the given name to a given point during the given interval of time.
   *
   * @param s           - name of shape to be moved
   * @param startTime   - time when moving should begin
   * @param endTime     - time when moving should end
   * @param endPosition - position shape will be after move
   * @throws IllegalArgumentException if given shape doesn't exist, if Position is null, or if time
   *                                  interval is invalid
   */
  void move(String s, int startTime, int endTime, Position2D endPosition)
      throws IllegalArgumentException;

  /**
   * Change the color of a shape with the given name during the given interval of time.
   *
   * @param s           - name of shape who's color will change
   * @param startTime   - time when color change should begin
   * @param endTime     - time when color change should end
   * @param changeColor - color that the shape will be after time interval has passed
   * @throws IllegalArgumentException if given shape doesn't exist, if changeColor is null, or if
   *                                  time interval is invalid
   */
  void changeColor(String s, int startTime, int endTime, Color changeColor)
      throws IllegalArgumentException;

  /**
   * Change the size of a shape with the given name during the given interval of time.
   *
   * @param s         - name of shape who's color will change
   * @param startTime - time when size change should begin
   * @param endTime   - time when size change should end
   * @param height    - new height parameter of shape
   * @param width     - - new width parameter of shape
   * @throws IllegalArgumentException if given shape doesn't exist, if height or width are negative,
   *                                  or if time interval is invalid
   */
  void changeSize(String s, int startTime, int endTime, int height, int width)
      throws IllegalArgumentException;

}
