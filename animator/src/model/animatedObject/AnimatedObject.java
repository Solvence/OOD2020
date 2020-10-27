package model.animatedObject;

import model.animatedObjectCommand.AnimatedObjectCommand;
import model.shape.Shape;

/**
 * Represents an Object to be animated.
 */
public interface AnimatedObject {

  /**
   * Get the shape as it would be animated at given time.
   *
   * @param time time frame
   * @return shape as it would be animated at given time
   */
  Shape getShape(int time);

  /**
   * Adds a animation command to preform on a animated object.
   *
   * @param command command object
   * @throws IllegalArgumentException if command is null or if interval overlaps with current
   *                                  commands
   */
  void addCommand(AnimatedObjectCommand command) throws IllegalArgumentException;

}
