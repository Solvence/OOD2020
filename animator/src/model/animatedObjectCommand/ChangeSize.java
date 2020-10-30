package model.animatedObjectCommand;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import model.position2d.Position2D;
import model.shape.Shape;

public class ChangeSize implements AnimatedObjectCommand {

  private final int startTime;
  private final int endTime;
  private final Map<String, Integer> startSize;
  private final Map<String, Integer> endSize;

  /**
   * Construct a Move Command.
   * @param startTime            Beginning of time interval for running command
   * @param endTime              End of time interval for running command
   * @param startSize        Beginning Position before Move Command
   * @param endSize          End Position after move Command
   * @throws IllegalArgumentException
   */
  ChangeSize(int startTime, int endTime, Map<String, Integer> startSize,
      Map<String, Integer> endSize)
      throws IllegalArgumentException {
    if (endTime - startTime <= 0 || startSize == null || endSize == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    if (endSize.size() != startSize.size()) {
      throw new IllegalArgumentException("given maps do not have the same number of elements");
    }
    for (Entry<String, Integer> e : startSize.entrySet()) {
      if (endSize.get(e.getKey()) == null) {
        throw new IllegalArgumentException("given maps do not have the same keys");
      }
      //TODO: 0 size allowed?
      if (e.getValue() < 0 || endSize.get(e.getKey()) < 0) {
        throw new IllegalArgumentException("dimensions cannot be negative");
      }
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.startSize = startSize;
    this.endSize = endSize;
  }

  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    if (time < this.startTime) {
      throw new IllegalArgumentException("Invalid time");
    } else if (time >= this.endTime) {
      return s.build(s.getPosition(), s.getColor(), s.getSize());
    }
    keys=width, hegith

    int distX = this.endSize.get("width") - this.startSize.get("width");
    int distY = this.endSize.getY() - this.startSize.getY();

    int timeFromStart = time - startTime;
    int totalTimeFrame = this.endTime - this.startTime;

    int newX = (timeFromStart * distX) / (totalTimeFrame) + this.startSize.getX();
    int newY = (timeFromStart * distY) / (totalTimeFrame) + this.startSize.getY();

    return s.build(new Position2D(newX,newY), s.getColor(), s.getSize());
  }

  @Override
  public boolean applicable(int time, Shape s) {
    if (time < this.startTime) {
      return false;
    }
    for (Entry<String, Integer> e : startSize.entrySet()) {
      if (s.getSize().get(e.getKey()) == null) {
        return false;
      }
      if (!e.getValue().equals(s.getSize().get(e.getKey()))) {
        return false;
      }
    }
    return s.getSize().size() == startSize.size();
  }
}
