package model.animatedObjectCommand;

import java.util.Objects;
import model.position2d.Position2D;
import model.shape.Shape;

public class Move implements AnimatedObjectCommand {

  private final int startTime;
  private final int endTime;
  private final Position2D startPosition;
  private final Position2D endPosition;

  /**
   * Construct a Move Command.
   * @param startTime            Beginning of time interval for running command
   * @param endTime              End of time interval for running command
   * @param startPosition        Beginning Position before Move Command
   * @param endPosition          End Position after move Command
   * @throws IllegalArgumentException
   */
  Move(int startTime, int endTime, Position2D startPosition, Position2D endPosition)
      throws IllegalArgumentException {
    if (endTime - startTime <= 0 || startPosition == null || endPosition == null) {
      throw new IllegalArgumentException("Invalid time interval");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    if (time < this.startTime) {
      throw new IllegalArgumentException("Invalid time");
    } else if (time >= this.endTime) {
      return s.build(this.endPosition, s.getColor(), s.getSize());
    }
    int distX = this.endPosition.getX() - this.startPosition.getX();
    int distY = this.endPosition.getY() - this.startPosition.getY();

    int timeFromStart = time - startTime;
    int totalTimeFrame = this.endTime - this.startTime;

    int newX = (timeFromStart * distX) / (totalTimeFrame) + this.startPosition.getX();
    int newY = (timeFromStart * distY) / (totalTimeFrame) + this.startPosition.getY();

    return s.build(new Position2D(newX,newY), s.getColor(), s.getSize());
  }

  @Override
  public boolean applicable(int time, Shape s) {
    if (time < this.startTime) {
      return false;
    }
    return startPosition.equals(s.getPosition());
  }
}
