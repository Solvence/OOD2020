package model.animatedobjectcommand;

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
   * Can this command be applied based on given time and the given shape.
   *
   * @param time - time of interest
   * @param s    - a given shape
   * @return true if time is valid and command can be run on given shape, false if time is less
   *         than command interval.
   */
  boolean applicable(int time, Shape s);

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

  /**
   * Are these two commands of the same type.
   *
   * @param other - the command that this command is being compared to
   * @return - whether this command is of the same type os the other command
   */
  boolean sameType(AnimatedObjectCommand other);

}
