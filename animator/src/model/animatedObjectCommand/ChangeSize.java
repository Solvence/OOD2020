package model.animatedObjectCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import model.shape.Shape;

public class ChangeSize extends AbstractCommand {

  private final Map<String, Integer> startSize;
  private final Map<String, Integer> endSize;

  /**
   * Construct a Move Command.
   * @param startTime            Beginning of time interval for running command
   * @param endTime              End of time interval for running command
   * @param startSize        Beginning size before Move Command
   * @param endSize          End size after move Command
   * @throws IllegalArgumentException - if time interval is invalid, sizes are null, sizes do
   * not have the same keys, or the dimensions are negative
   */
  ChangeSize(int startTime, int endTime, Map<String, Integer> startSize,
      Map<String, Integer> endSize)
      throws IllegalArgumentException {
    super(startTime, endTime);
    if (startSize == null || endSize == null) {
      throw new IllegalArgumentException("Invalid size inputs");
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
    Map<String, Integer> newSize = new HashMap<String, Integer>();
    int timeFromStart = time - this.startTime;
    int totalTimeFrame = this.endTime - this.startTime;

    for (Entry<String, Integer> e : this.startSize.entrySet()) {
      String currentKey = e.getKey();
      int totalSizeChange = endSize.get(currentKey) - this.startSize.get(currentKey);
      newSize.put(currentKey, (timeFromStart * totalSizeChange) / totalTimeFrame
          + this.startSize.get(currentKey));
    }

    return s.build(s.getPosition(), s.getColor(), newSize);
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

  @Override
  public boolean sameType(AnimatedObjectCommand other) {
    return other instanceof ChangeSize;
  }
}
