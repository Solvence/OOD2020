package model.animatedobjectcommand;

import java.util.Objects;
import model.dimension2d.Dimension2D;
import model.shape.Shape;

/**
 * Represents a command to change the size of an animated Shape.
 */
public class ChangeSize extends AbstractCommand {

  private final Dimension2D startSize;
  private final Dimension2D endSize;

  /**
   * Construct a Move Command. INVARIANT: startSize and endSize cannot be null
   *
   * @param startTime Beginning of time interval for running command
   * @param endTime   End of time interval for running command
   * @param startSize Beginning size before Move Command
   * @param endSize   End size after move Command
   * @throws IllegalArgumentException - if time interval is invalid or the dimensions are negative
   */
  public ChangeSize(int startTime, int endTime, Dimension2D startSize,
      Dimension2D endSize)
      throws IllegalArgumentException {
    super(startTime, endTime);
    if (startSize == null || endSize == null) {
      throw new IllegalArgumentException("Invalid size inputs");
    }
    if (startSize.getXDir() < 0 || startSize.getYDir() < 0 ||
        endSize.getXDir() < 0 || endSize.getYDir() < 0) {
      throw new IllegalArgumentException("dimensions cannot be negative");
    }
    this.startSize = startSize;
    this.endSize = endSize;
  }

  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    if (!this.applicable(time, s)) {
      throw new IllegalArgumentException("Invalid time");
    } else if (time >= this.endTime) {
      return s.build(s.getPosition(), s.getColor(), this.endSize);
    }
    int timeFromStart = time - this.startTime;
    int totalTimeFrame = this.endTime - this.startTime;
    int totalXChange = endSize.getXDir() - this.startSize.getXDir();
    int totalYChange = endSize.getYDir() - this.startSize.getYDir();
    int currentXDir = (timeFromStart * totalXChange) / totalTimeFrame
        + this.startSize.getXDir();
    int currentYDir = (timeFromStart * totalYChange) / totalTimeFrame
        + this.startSize.getYDir();

    return s.build(s.getPosition(), s.getColor(), new Dimension2D(currentXDir, currentYDir));
  }

  @Override
  public boolean applicable(int time, Shape s) {
    if (time < this.startTime) {
      return false;
    }
    return startSize.equals(s.getSize());
  }

  @Override
  public boolean sameType(AnimatedObjectCommand other) {
    return other instanceof ChangeSize;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ChangeSize)) {
      return false;
    } else {
      ChangeSize otherChangeSize = (ChangeSize) other;
      return this.startTime == otherChangeSize.startTime
          && this.endTime == otherChangeSize.endTime
          && this.startSize.equals(otherChangeSize.startSize)
          && this.endSize.equals(otherChangeSize.endSize);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.startTime, this.endTime, this.startSize, this.endSize,
        "ChangeSize");
  }
}
