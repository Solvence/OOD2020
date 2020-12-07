package cs3500.animator.provider.adapters;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobject.BasicAnimatedObject;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.ShapeFactory;
import cs3500.animator.provider.providerModel.Model;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.provider.providerModel.Shape;
import cs3500.animator.provider.providerModel.StateofShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This is an Adapter, which contains a delegate of a AnimationModel and translated calls to
 * a Model to work on a any Object that uses our Providers Model.
 */
public class AnimModelToPModelAdapter implements Model {
  private final AnimationModel adaptee;

  /**
   * Default Constructor for our adapter.
   * @param adaptee - instance of our model (should be prebuilt, but also can be mutated by methods
   *                implemented). Used as base for all operations.
   */
  public AnimModelToPModelAdapter(AnimationModel adaptee) {
    Objects.requireNonNull(adaptee);
    this.adaptee = adaptee;
  }

  @Override
  public void startAnimation(int heightBoard, int widthBoard, Shape... shapes) {
    for(Shape s : shapes) {
      adaptee.create(s.getName(), ShapeFactory.build(s.getForm().toLowerCase()));
    }
  }

  @Override
  public void addShapes(Shape shape) {
    adaptee.create(shape.getName(), ShapeFactory.build(shape.getForm().toLowerCase()));
  }

  @Override
  public void removeShapes(Shape shape) {
    adaptee.remove(shape.getName());
  }

  @Override
  public List<Shape> getShapes() {
    List<Shape> output = new ArrayList<>();
    for(String shapeName : adaptee.getAllShapeName()) {
      List<AnimatedObjectCommand> commands = adaptee.getCommandsForShape(shapeName);
      cs3500.animator.model.shape.Shape baseShape = adaptee.getShapeAt(shapeName, 1);
      AnimatedObject aob = new BasicAnimatedObject(baseShape, commands);
      output.add(new AnimObjToPShapeAdapter(aob, shapeName));
    }
    return output;
  }

  @Override
  public int getLastTick() {
    int endTick = 1;

    for (String s : adaptee.getAllShapeName()) {
      for (AnimatedObjectCommand command : adaptee.getCommandsForShape(s)) {
        if (command.getEndTime() > endTick) {
          endTick = command.getEndTime();
        }
      }
    }

    return endTick;
  }

  @Override
  public int getX() {
    return adaptee.getCanvasPosition().getX();
  }

  @Override
  public int getY() {
    return adaptee.getCanvasPosition().getY();
  }

  @Override
  public int getHeightBoard() {
    return adaptee.getCanvasSize().getYDir();
  }

  @Override
  public int getWidthBoard() {
    return adaptee.getCanvasSize().getXDir();
  }

  @Override
  public void addMotion(MotionAnimation motion, Shape shape) {
    Color startColor = motion.getStartColor();
    Color endColor = motion.getEndColor();
    cs3500.animator.model.color.Color newStartColor =
        new cs3500.animator.model.color.Color(startColor.getRed(),
            startColor.getGreen(), startColor.getBlue());
    cs3500.animator.model.color.Color newEndColor =
        new cs3500.animator.model.color.Color(endColor.getRed(),
            endColor.getGreen(), endColor.getBlue());

    adaptee.addMotion(shape.getName(), motion.getStartTick(), motion.getEndTick(),
        motion.getStartPosition(), motion.getEndPosition(), newStartColor, newEndColor,
        new Dimension2D(motion.getStartWidth(), motion.getStartHeight()),
        new Dimension2D(motion.getEndWidth(), motion.getEndHeight()));
  }

  @Override
  public void removeMotion(MotionAnimation motion, Shape shape) {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public List<StateofShape> stateofShapesTick(int tick) {
    return this.getShapes().stream()
        .map((item) -> item.getStateofShapeTick(tick)).filter(Objects::nonNull).collect(Collectors.toList());
  }

  @Override
  public String description() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.adaptee.initCanvas(new Position2D(x, y), new Dimension2D(width, height));
  }
}
