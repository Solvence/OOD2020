package cs3500.animator.model.animatedobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.shape.Shape;

/**
 * Represent a Basic implementation of an AnimatedObject.
 */
public class BasicAnimatedObject implements AnimatedObject {

  private final Shape baseShape;
  private final List<AnimatedObjectCommand> commands;

  // INVARIANT: commands is always sorted by startTime

  /**
   * Loaded Constructor. INVARIANT: baseShape and commands cannot be null INVARIANT: two or more of
   * the same type of command cannot overlap time interval-wise INVARIANT: commands is sorted by
   * start time of the command interval
   *
   * @param baseShape - Shape to be animated
   * @param commands  - commands to be called on the shape
   */
  public BasicAnimatedObject(Shape baseShape, List<AnimatedObjectCommand> commands) {
    if (baseShape == null || commands == null) {
      throw new IllegalArgumentException("Shape and commands must not be null");
    }
    this.baseShape = baseShape;
    this.commands = new ArrayList<>();

    // make sure command sequence is valid.
    for (AnimatedObjectCommand cmd: commands) {
      this.addCommand(cmd);
    }
  }

  /**
   * Default Constructor.
   *
   * @param baseShape shape to be animated
   */
  public BasicAnimatedObject(Shape baseShape) {
    this(baseShape, new ArrayList<>());
  }

  /**
   * Copy Constructor.
   *
   * @param other some other AnimatedObject
   */
  public BasicAnimatedObject(AnimatedObject other) {
    this(other.getShape(0), other.getCommands());
  }


  @Override
  public Shape getShape(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("time cannot be negative");
    }
    int commandIndex = 0;
    Shape currentShape = baseShape.build(baseShape.getPosition(),
        baseShape.getColor(), baseShape.getSize());
    while (commandIndex < commands.size()
        && commands.get(commandIndex).applicable(time, currentShape)) {
      currentShape = commands.get(commandIndex).apply(currentShape, time);
      commandIndex += 1;
    }
    return currentShape;
  }

  @Override
  public void addCommand(AnimatedObjectCommand command) throws IllegalArgumentException {


    commands.add(command);
  }

  // We know returning references to original commands is okay, since we know their fields are
  // immutable.
  @Override
  public List<AnimatedObjectCommand> getCommands() {
    return new ArrayList<>(this.commands);
  }

  /**
   * A BasicAnimatedObject is equal to another Object if that object is also a BasicAnimated object
   * with equivalent commands (in the same order) and equivalent base shape.
   *
   * @param other - the Object that this Animated object is being compared to
   * @return - whether this BasicAnimatedObject is equal to the given object
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicAnimatedObject)) {
      return false;
    }
    BasicAnimatedObject otherAnimated = (BasicAnimatedObject) other;
    return this.commands.equals(otherAnimated.commands)
        && this.baseShape.equals(otherAnimated.baseShape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.baseShape, this.commands, "BasicAnimatedObject");
  }
}
