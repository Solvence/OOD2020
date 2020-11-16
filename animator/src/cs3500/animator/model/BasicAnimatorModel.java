package cs3500.animator.model;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.shape.ShapeFactory;
import cs3500.animator.util.AnimationBuilder;
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
   * objects with the same name key. INVARIANT: animatedObjects can't be null INVARIANT: Canvas must
   * be initialized using initCanvas before any shapes are initialized
   */
  private BasicAnimatorModel() {
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
  public Shape getShapeAt(String name, int time) throws IllegalArgumentException,
      IllegalStateException {
    if (time < 0 || !this.animatedObjects.containsKey(name)) {
      throw new IllegalArgumentException("Invalid time or shape name");
    }
    if (this.canvas.getPosition() == null || this.canvas.getSize() == null) {
      throw new IllegalStateException("Canvas has not been initialized yet");
    }
    return this.animatedObjects.get(name).getShape(time);
  }

  @Override
  public void addMotion(String name, int startTime, int endTime, Position2D startPosition,
      Position2D endPosition, Color startColor, Color endColor, Dimension2D startSize,
      Dimension2D endSize) throws IllegalArgumentException, IllegalStateException {
    if (!this.animatedObjects.containsKey(name) || startTime < 0 || endTime - startTime < 0
        || startPosition == null
        || endPosition == null || startColor == null || endColor == null || startSize == null
        || endSize == null) {
      throw new IllegalArgumentException("Invalid information for creating motion");
    }
    if (this.canvas.getPosition() == null || this.canvas.getSize() == null) {
      throw new IllegalStateException("Canvas has not been initialized yet");
    }
    animatedObjects.get(name).addCommand(
        new BasicCommand(startTime, endTime, startPosition, endPosition, startSize, endSize,
            startColor, endColor));
  }

  @Override
  public List<String> getAllShapeName() throws IllegalStateException {
    if (this.canvas.getPosition() == null || this.canvas.getSize() == null) {
      throw new IllegalStateException("Canvas has not been initialized yet");
    }
    return new ArrayList<>(this.animatedObjects.keySet());
  }

  @Override
  public List<AnimatedObjectCommand> getCommandsForShape(String name) throws IllegalStateException {
    if (!this.animatedObjects.containsKey(name)) {
      throw new IllegalArgumentException("No shape with given name exists.");
    }
    if (this.canvas.getPosition() == null || this.canvas.getSize() == null) {
      throw new IllegalStateException("Canvas has not been initialized yet");
    }
    return new ArrayList<>(this.animatedObjects.get(name).getCommands());
  }

  @Override
  public void initCanvas(Position2D pos, Dimension2D size)
      throws IllegalArgumentException, IllegalStateException {
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

  /**
   * Constructs a builder for configuring and then creating an animator model instance.
   *
   * @return a builder for configuring the model
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Represents a Builder class to assist with constructing objects of this model.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {
    private AnimatorModel model;

    /**
     * constructs a Builder.
     */
    public Builder() {
      model = new BasicAnimatorModel();
    }

    @Override
    public AnimatorModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.model.initCanvas(new Position2D(x, y), new Dimension2D(width, height));
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      this.model.create(name, ShapeFactory.build(type));
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      this.model.addMotion(name, t1, t2, new Position2D(x1, y1), new Position2D(x2, y2),
          new Color(r1, g1, b1), new Color(r2, g2, b2), new Dimension2D(w1, h1),
          new Dimension2D(w2, h2));
      return this;
    }
  }

}
