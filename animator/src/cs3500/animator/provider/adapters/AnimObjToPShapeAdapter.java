package cs3500.animator.provider.adapters;

import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.provider.providerModel.Shape;
import cs3500.animator.provider.providerModel.StateofShape;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class AnimObjToPShapeAdapter implements Shape {
  private final AnimatedObject adaptee;
  private final String name;

  public AnimObjToPShapeAdapter(AnimatedObject adaptee, String name) {
    this.adaptee = adaptee;
    this.name = name;
  }


  @Override
  public StateofShape getStateofShapeTick(int tick) {
    cs3500.animator.model.shape.Shape shapeAtTick = adaptee.getShape(tick);
    cs3500.animator.model.color.Color colorAtTick = shapeAtTick.getColor();
    return new StateofShape(adaptee.getBaseShape().toString(), shapeAtTick.getPosition().getX(),
        shapeAtTick.getPosition().getY(), shapeAtTick.getSize().getXDir(),
        shapeAtTick.getSize().getYDir(),
        new Color(colorAtTick.getRed(), colorAtTick.getGreen(), colorAtTick.getBlue()));
  }

  @Override
  public void addMotionsToShape(MotionAnimation newMotion) {
    this.adaptee.addCommand(new MotionAnimationToBasicCommandAdapter(newMotion));
  }

  @Override
  public Color getShapeColor() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public int getShapeHeight() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public int getShapeWidth() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public Position2D getShapePos() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }

  @Override
  public List<MotionAnimation> getMotionsofShape() {
    return adaptee.getCommands().stream()
        .map((item) -> new AnimatedObjectCommandToMotionAnimation(item)).collect(
        Collectors.toList());
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getForm() {
    return adaptee.getBaseShape().toString();
  }

  @Override
  public String description() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }
}
