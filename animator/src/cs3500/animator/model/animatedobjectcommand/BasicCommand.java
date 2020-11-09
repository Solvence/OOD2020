package cs3500.animator.model.animatedobjectcommand;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;

public class BasicCommand implements AnimatedObjectCommand {
  private final int startTime;
  private final int endTime;
  private final Position2D startPosition;
  private final Position2D endPosition;
  private final Dimension2D startSize;
  private final Dimension2D endSize;
  private final Color startColor;
  private final Color endColor;

  public BasicCommand(int startTime, int endTime, Position2D startPosition,
      Position2D endPosition, Dimension2D startSize,
      Dimension2D endSize, Color startColor, Color endColor) throws IllegalArgumentException {
    if (startTime < 1 || endTime < startTime || startPosition == null
    || endPosition == null || startSize == null || endSize == null || startColor == null
    || endColor == null) {
      throw new IllegalArgumentException("invalid inputs");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.startSize = startSize;
    this.endSize = endSize;
    this.startColor = startColor;
    this.endColor = endColor;
  }

  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }
}
