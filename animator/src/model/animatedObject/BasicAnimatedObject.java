package model.animatedObject;

import java.util.ArrayList;
import java.util.List;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.shape.Shape;

/**
 * Represent a Basic implementation of an AnimatedObject.
 */
public class BasicAnimatedObject implements AnimatedObject {
  private final Shape baseShape;
  private final List<AnimatedObjectCommand> commands;

  // INVARIANT: commands is always sorted by startTime

  /**
   * Loaded Constructor.
   * @param baseShape   - Shape to be animated
   * @param commands    - commands to be called on the shape
   */
  public BasicAnimatedObject(Shape baseShape, List<AnimatedObjectCommand> commands) {
    if (baseShape == null || commands == null ) {
      throw new IllegalArgumentException("Shape and commands must not be null");
    }
    this.baseShape = baseShape;
    this.commands = commands;
  }

  /**
   * Default Constructor.
   * @param baseShape shape to be animated
   */
  public BasicAnimatedObject(Shape baseShape) {
    this(baseShape, new ArrayList<>());
  }

  /**
   * Copy Constructor.
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
    for (AnimatedObjectCommand cmd : commands) {
      if ((command.getStartTime() >= cmd.getStartTime()
          && command.getStartTime() < cmd.getEndTime())
          || (command.getStartTime() <= cmd.getStartTime()
          && command.getEndTime() > cmd.getStartTime())) {
        if (command.sameType(cmd)) {
          throw new IllegalArgumentException("intervals cannot overlap for "
              + "commands of the same type");
        }
      }
    }
    int startIndex = 0;
    while (startIndex < commands.size()) {
      if (commands.get(startIndex).getStartTime() > command.getStartTime()) {
        commands.add(startIndex, command);
        return;
      }
      startIndex++;
    }
    commands.add(command);
  }

  // We know returning references to original commands is okay, since we know their fields are
  // immutable.
  @Override
  public List<AnimatedObjectCommand> getCommands() {
    return new ArrayList<>(this.commands);
  }
}
