package model.animatedObject;

import java.util.ArrayList;
import java.util.List;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.shape.Shape;

public class BasicAnimatedObject implements AnimatedObject {
  private final Shape baseShape;
  private final List<AnimatedObjectCommand> commands;

  // INVARIANT: commands is always sorted by startTime

  BasicAnimatedObject(Shape baseShape) {
    this.baseShape = baseShape;
    this.commands = new ArrayList<AnimatedObjectCommand>();
  }

  @Override
  public Shape getShape(int time) {
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
}
