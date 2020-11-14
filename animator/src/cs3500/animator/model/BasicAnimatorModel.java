package cs3500.animator.model;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobject.BasicAnimatedObject;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.canvas.Canvas;

/**
 * A Basic Representation of a AnimatorModel, which animates basic Shapes.
 */
public class BasicAnimatorModel implements AnimatorModel {

  private final Map<String, AnimatedObject> animatedObjects;
  private final Canvas canvas;

  /**
   * Default Constructor for BasicAnimatorModel. INVARIANT: animatedObjects cannot contain two
   * objects with the same name key. INVARIANT: animatedObjects can't be null
   */
  public BasicAnimatorModel() {
    this.animatedObjects = new HashMap<>();
    this.canvas = new Canvas();
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
  public void addMotion(String name, int startTime, int endTime, Position2D startPosition,
      Position2D endPosition, Color startColor, Color endColor, Dimension2D startSize,
      Dimension2D endSize) throws IllegalArgumentException {
    if (!this.animatedObjects.containsKey(name) || startTime < 0 || endTime - startTime < 0
        || startPosition == null
        || endPosition == null || startColor == null || endColor == null || startSize == null
        || endSize == null) {
      throw new IllegalArgumentException("Invalid information for creating motion");
    }
    animatedObjects.get(name).addCommand(
        new BasicCommand(startTime, endTime, startPosition, endPosition, startSize, endSize,
            startColor, endColor));
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

  @Override
  public void initCanvas(Position2D pos, Dimension2D size) throws IllegalArgumentException, IllegalStateException{
    if (pos == null || size == null) {
      throw new IllegalArgumentException("Can't init canvas with null fields");
    }
    this.canvas.initFields(size, pos);
  }

  @Override
  public Position2D getCanvasPosition() {
    return new Position2D(this.canvas.getPosition());
  }

  @Override
  public Dimension2D getCanvasSize() {
    return new Dimension2D(this.canvas.getSize());

  }

}
