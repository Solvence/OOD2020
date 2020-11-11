package cs3500.animator.model.animatedobjectcommand;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;
import java.util.Objects;

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
    if (time > this.endTime || time < this.startTime) {
      throw new IllegalArgumentException("Time must be within this commands time interval");
    }
    // for key frames.
    if (startTime == endTime) {
      return s.build(this.endPosition, this.endColor, this.endSize);
    }

    int timeFromStart = time - startTime;
    int totalTimeFrame = this.endTime - this.startTime;

    int distRed = this.endColor.getRed() - this.startColor.getRed();
    int distGreen = this.endColor.getGreen() - this.startColor.getGreen();
    int distBlue = this.endColor.getBlue() - this.startColor.getBlue();

    int totalXChange = endSize.getXDir() - this.startSize.getXDir();
    int totalYChange = endSize.getYDir() - this.startSize.getYDir();

    int distX = this.endPosition.getX() - this.startPosition.getX();
    int distY = this.endPosition.getY() - this.startPosition.getY();

    int newRed = (timeFromStart * distRed) / (totalTimeFrame) + this.startColor.getRed();
    int newGreen = (timeFromStart * distGreen) / (totalTimeFrame) + this.startColor.getGreen();
    int newBlue = (timeFromStart * distBlue) / (totalTimeFrame) + this.startColor.getBlue();

    int currentXDir = (timeFromStart * totalXChange) / totalTimeFrame
        + this.startSize.getXDir();
    int currentYDir = (timeFromStart * totalYChange) / totalTimeFrame
        + this.startSize.getYDir();

    int newX = (timeFromStart * distX) / (totalTimeFrame) + this.startPosition.getX();
    int newY = (timeFromStart * distY) / (totalTimeFrame) + this.startPosition.getY();

    return s.build(new Position2D(newX, newY), new Color(newRed, newGreen, newBlue),
        new Dimension2D(currentXDir, currentYDir));
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(this.startTime, this.startPosition, this.startSize, this.startColor,
            this.endTime, this.endPosition, this.endSize, this.endColor, "BasicCommand");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof BasicCommand)) {
      return false;
    }
    BasicCommand other = (BasicCommand) obj;
    return other.startTime == this.startTime && this.startPosition.equals(other.startPosition)
        && this.startSize.equals(other.startSize) && this.startColor.equals(other.startColor)
        && other.endTime == this.endTime && this.endPosition.equals(other.endPosition)
        && this.endSize.equals(other.endSize) && this.endColor.equals(other.endColor);
  }

}
