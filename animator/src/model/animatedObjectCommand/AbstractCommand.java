package model.animatedObjectCommand;

import model.position2d.Position2D;
import model.shape.Shape;

public abstract class AbstractCommand implements AnimatedObjectCommand {
  protected final int startTime;
  protected final int endTime;

  /**
   * Constructs a command on a animated Shape
   * @param startTime - the time at which this animation command starts to execute
   * @param endTime - the time at which this animation command is finished
   */
  AbstractCommand(int startTime, int endTime) {
    if (endTime - startTime <= 0 || startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Invalid time interval");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public abstract Shape apply(Shape s, int time) throws IllegalArgumentException;

  @Override
  public abstract boolean applicable(int time, Shape s);

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public abstract boolean sameType(AnimatedObjectCommand other);
}