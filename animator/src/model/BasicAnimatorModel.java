package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import model.animatedObject.AnimatedObject;
import model.animatedObject.BasicAnimatedObject;
import model.animatedObjectCommand.ChangeColor;
import model.animatedObjectCommand.ChangeSize;
import model.animatedObjectCommand.Move;
import model.dimension2D.Dimension2D;
import model.position2d.Position2D;
import model.shape.Shape;

/**
 * A Basic Representation of a AnimatorModel, which animates basic Shapes.
 */
public class BasicAnimatorModel implements AnimatorModel {

  private Map<String, AnimatedObject> animatedObjects;

  /**
   * Default Constructor for BasicAnimatorModel.
   * INVARIANT: animatedObjects cannot contain two objects with the same name key
   */
  public BasicAnimatorModel() {
    this.animatedObjects = new HashMap<>();
  }


  @Override
  public void create(String name, Shape s) {
    if (s == null || this.animatedObjects.containsKey(name) || name == null) {
      throw new IllegalArgumentException("invalid inputs");
    }
    this.animatedObjects.put(name, new BasicAnimatedObject(s));
  }

  @Override
  public Map<String, Shape> getStateAt(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("time cannot be negative");
    }
    Map<String, Shape> shapesAtTime = new HashMap<>();
    for (Map.Entry<String, AnimatedObject> e : this.animatedObjects.entrySet()) {
      String currentName = e.getKey();
      AnimatedObject current = e.getValue();
      shapesAtTime.put(currentName, current.getShape(time));
    }
    return shapesAtTime;
  }

  @Override
  public void move(String s, int startTime, int endTime, Position2D startPosition,
      Position2D endPosition) throws IllegalArgumentException {
    this.animatedObjects.get(s)
        .addCommand(new Move(startTime, endTime, startPosition, endPosition));
  }

  @Override
  public void changeColor(String s, int startTime, int endTime, Color startColor, Color endColor)
      throws IllegalArgumentException {
    this.animatedObjects.get(s)
        .addCommand(new ChangeColor(startTime, endTime, startColor, endColor));

  }

  @Override
  public void changeSize(String s, int startTime, int endTime, Dimension2D startDimensions,
      Dimension2D endDimensions) throws IllegalArgumentException {
    this.animatedObjects.get(s).addCommand(new ChangeSize(startTime, endTime, startDimensions,
        endDimensions));
  }


  @Override
  public Map<String, AnimatedObject> getAnimatedObjects() {
    Map<String, AnimatedObject> animatedObjects = new HashMap<>();
    for (Map.Entry<String, AnimatedObject> e : this.animatedObjects.entrySet()) {
      String currentName = e.getKey();
      AnimatedObject current = e.getValue();
      animatedObjects.put(currentName, new BasicAnimatedObject(current));
    }
    return animatedObjects;
  }
}
