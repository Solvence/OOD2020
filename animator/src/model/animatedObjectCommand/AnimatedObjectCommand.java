package model.animatedObjectCommand;

import model.shape.Shape;

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
   * @throws IllegalArgumentException if time is negative or time is less than current interval
   */
  Shape apply(Shape s, int time) throws IllegalArgumentException;

  /**
   * Can this command be applied based on given time
   * @param time  - time of interest
   * @return true if time is valid, false if time is less than command interval.
   */
  boolean appliable(int time);

}
