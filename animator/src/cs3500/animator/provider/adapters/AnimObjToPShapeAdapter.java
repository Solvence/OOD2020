package cs3500.animator.provider.adapters;

import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.provider.providerModel.Shape;
import cs3500.animator.provider.providerModel.StateofShape;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An adapter object that uses a delegate of our definition of AnimatedObject to fulfills the tasks
 * asked upon a instance of our providers Shape object.
 */
public class AnimObjToPShapeAdapter implements Shape {
  private final AnimatedObject adaptee;
  private final String name;

  /**
   * Constructor.
   * @param adaptee - an instance of our AnimatedObject used to adapt functionality of providers
   *                  Shape interface.
   * @param name - name of shape that undergoes animation commands.
   */
  public AnimObjToPShapeAdapter(AnimatedObject adaptee, String name) {
    this.adaptee = adaptee;
    this.name = name;
  }


  @Override
  public StateofShape getStateofShapeTick(int tick) {
    cs3500.animator.model.shape.Shape shapeAtTick = adaptee.getShape(tick);
    cs3500.animator.model.color.Color colorAtTick = shapeAtTick.getColor();
    if (shapeAtTick.getPosition() == null || shapeAtTick.getSize() == null || shapeAtTick.getColor() == null ) {
      return null;
    }
    return new StateofShape(adaptee.getBaseShape().toString().toLowerCase(), shapeAtTick.getPosition().getX(),
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
    return adaptee.getBaseShape().toString().toLowerCase();
  }

  @Override
  public String description() {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }
}
