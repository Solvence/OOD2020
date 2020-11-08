package cs3500.animator.model;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.color.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobject.BasicAnimatedObject;
import cs3500.animator.model.animatedobjectcommand.ChangeColor;
import cs3500.animator.model.animatedobjectcommand.ChangeSize;
import cs3500.animator.model.animatedobjectcommand.Move;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;

/**
 * A Basic Representation of a AnimatorModel, which animates basic Shapes.
 */
public class BasicAnimatorModel implements AnimatorModel {

  private Map<String, AnimatedObject> animatedObjects;

  /**
   * Default Constructor for BasicAnimatorModel. INVARIANT: animatedObjects cannot contain two
   * objects with the same name key. INVARIANT: animatedObjects can't be null
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
  public Shape getShapeAt(String name, int time) throws IllegalArgumentException {
    if (time < 0 || !this.animatedObjects.containsKey(name)) {
      throw new IllegalArgumentException("Invalid time or shape name");
    }
    return this.animatedObjects.get(name).getShape(time);
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
  public List<String> getAllShapeName() {
    return new ArrayList<>(this.animatedObjects.keySet());
  }

  @Override
  public List<AnimatedObjectCommand> getCommandsForShape(String name) {
    if (!this.animatedObjects.containsKey(name)) {
      throw new IllegalArgumentException("No shape with given name exists.");
    }
    return new ArrayList<>(this.animatedObjects.get(name).getCommands());
  }


}
