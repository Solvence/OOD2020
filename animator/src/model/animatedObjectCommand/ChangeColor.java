package model.animatedObjectCommand;

import java.awt.Color;
import java.util.Objects;
import model.position2d.Position2D;
import model.shape.Shape;

public class ChangeColor extends AbstractCommand {

  private final Color startColor;
  private final Color endColor;

  /**
   * Construct a ChangeColor Command.
   * @param startTime            Beginning of time interval for running command
   * @param endTime              End of time interval for running command
   * @param startColor           Beginning color before Move Command
   * @param endColor             End color after move Command
   * @throws IllegalArgumentException - if either color is null or time interval is invalid
   */
  public ChangeColor(int startTime, int endTime, Color startColor, Color endColor)
      throws IllegalArgumentException {
    super(startTime, endTime);
    if (startColor == null || endColor == null) {
      throw new IllegalArgumentException("Invalid colors");
    }
    this.startColor = startColor;
    this.endColor = endColor;
  }


  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    if (!this.applicable(time, s)) {
      throw new IllegalArgumentException("Invalid time");
    } else if (time >= this.endTime) {
      return s.build(s.getPosition(), this.endColor, s.getSize());
    }
    int distRed = this.endColor.getRed() - this.startColor.getRed();
    int distGreen = this.endColor.getGreen() - this.startColor.getGreen();
    int distBlue = this.endColor.getBlue() - this.startColor.getBlue();

    int timeFromStart = time - startTime;
    int totalTimeFrame = this.endTime - this.startTime;

    int newRed = (timeFromStart * distRed) / (totalTimeFrame) + this.startColor.getRed();
    int newGreen = (timeFromStart * distGreen) / (totalTimeFrame) + this.startColor.getGreen();
    int newBlue = (timeFromStart * distBlue) / (totalTimeFrame) + this.startColor.getBlue();

    return s.build(s.getPosition(), new Color(newRed, newGreen, newBlue), s.getSize());
  }

  @Override
  public boolean applicable(int time, Shape s) {
    if (time < this.startTime) {
      return false;
    }
    return startColor.equals(s.getColor());
  }

  @Override
  public boolean sameType(AnimatedObjectCommand other) {
    return other instanceof ChangeColor;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ChangeColor)) {
      return false;
    } else {
      ChangeColor otherChangeColor = (ChangeColor) other;
      return this.startTime == otherChangeColor.startTime
          && this.endTime == otherChangeColor.endTime
          && this.startColor.equals(otherChangeColor.startColor)
          && this.endColor.equals(otherChangeColor.endColor);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.startTime, this.endTime, this.startColor, this.endColor);
  }
}
