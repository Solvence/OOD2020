package cs3500.animator.model;

import cs3500.animator.model.color.Color;
import java.util.Map;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;

/**
 * Represents the cs3500.animator.model backend component for an animation application that will draw an animation
 * according to how it is described in text. The cs3500.animator.model itself supports operations and observations
 * that a user who is running an animation will likely want to perform. The cs3500.animator.model then processes
 * these operations and maintains the state of the animation.
 */
public interface AnimatorModel {

  /**
   * Adds a new shape with a given name to our animator.
   *
   * @param name - name of the shape
   * @param s    - type of shape
   * @throws IllegalArgumentException if s is null or if shape with given name already exists.
   */
  void create(String name, Shape s) throws IllegalStateException;

  /**
   * Returns a List of copies of the shapes being animated by this Model at the given time.
   *
   * @param time time frame to be rendered
   * @return List of copies of shapes
   * @throws IllegalArgumentException if time is negative
   */
  Map<String, Shape> getStateAt(int time) throws IllegalArgumentException;

  /**
   * Move a shape with the given name to a given point during the given interval of time.
   *
   * @param s             - name of shape to be moved
   * @param startTime     - time when moving should begin
   * @param endTime       - time when moving should end
   * @param startPosition - position shape will be before move
   * @param endPosition   - position shape will be after move
   * @throws IllegalArgumentException if given shape doesn't exist, if Position is null, or if time
   *                                  interval is invalid
   */
  void move(String s, int startTime, int endTime, Position2D startPosition, Position2D endPosition)
      throws IllegalArgumentException;

  /**
   * Change the color of a shape with the given name during the given interval of time.
   *
   * @param s          - name of shape who's color will change
   * @param startTime  - time when color change should begin
   * @param endTime    - time when color change should end
   * @param startColor - color that the shape will be as time interval has begun
   * @param endColor   - color that the shape will be after time interval has passed
   * @throws IllegalArgumentException if given shape doesn't exist, if changeColor is null, or if
   *                                  time interval is invalid
   */
  void changeColor(String s, int startTime, int endTime, Color startColor, Color endColor)
      throws IllegalArgumentException;

  /**
   * Change the size of a shape with the given name during the given interval of time. Resizes
   * (stretches) the bounding box of the shape.
   *
   * @param s               - name of shape who's color will change
   * @param startTime       - time when size change should begin
   * @param endTime         - time when size change should end
   * @param startDimensions - the size of the shape in the beginning
   * @param endDimensions   - the size of the shape after the transformation
   * @throws IllegalArgumentException if given shape doesn't exist, if height or width are negative,
   *                                  or if time interval is invalid
   */
  void changeSize(String s, int startTime, int endTime, Dimension2D startDimensions,
      Dimension2D endDimensions)
      throws IllegalArgumentException;

  /**
   * Get all the objects being animated within this animator.
   *
   * @return all the objects being animated within this animator
   */
  Map<String, AnimatedObject> getAnimatedObjects();

}
