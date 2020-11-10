package cs3500.animator.model.animatedobjectcommand;

import cs3500.animator.model.shape.Shape;

/**
 * Represents a command to be preformed on a shape within a time interval. Invariants - time
 * interval should never be negative.
 */
public interface AnimatedObjectCommand {

  /**
   * Apply command on given shape if within or after this commands interval.
   *
   * @param s    - the shape
   * @param time - time in interval to apply
   * @return shape which command has been applied on
   * @throws IllegalArgumentException if given time isn't within commands interval.
   */
  Shape apply(Shape s, int time) throws IllegalArgumentException;

  /**
   * Returns the time at which this command starts running.
   *
   * @return - the time at which this command starts running
   */
  int getStartTime();

  /**
   * Returns the time at which this command finishes running.
   *
   * @return - the time at which this command finishes running
   */
  int getEndTime();


}
