package cs3500.animator.model.animatedobject;

import java.util.List;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.shape.Shape;

/**
 * Represents an Object to be animated.
 */
public interface AnimatedObject {

  /**
   * Get the shape as it would be animated at given time.
   *
   * @param time time frame
   * @return shape as it would be animated at given time
   * @throws IllegalArgumentException if time is negative
   */
  Shape getShape(int time) throws IllegalArgumentException;

  /**
   * Adds a animation command to the collection of commands on a animated object based off of its
   * start time.
   *
   * @param command command object
   * @throws IllegalArgumentException if command is null or if interval overlaps with current
   *                                  commands
   */
  void addCommand(AnimatedObjectCommand command) throws IllegalArgumentException;

  /**
   * Get all the commands called on a animatedObject.
   *
   * @return all the commands called on a animatedObject
   */
  List<AnimatedObjectCommand> getCommands();

}