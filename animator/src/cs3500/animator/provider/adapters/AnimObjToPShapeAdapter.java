package cs3500.animator.provider.adapters;

import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.provider.providerModel.ProviderShape;
import cs3500.animator.provider.providerModel.StateofShape;
import java.awt.Color;
import java.util.List;

public class AnimObjToPShapeAdapter implements ProviderShape {
  private AnimatedObject adaptee;

  public AnimObjToPShapeAdapter(AnimatedObject adaptee) {
    this.adaptee = adaptee;
  }


  @Override
  public StateofShape getStateofShapeTick(int tick) {
    Shape shapeAtTick = adaptee.getShape(tick);
    cs3500.animator.model.color.Color colorAtTick = shapeAtTick.getColor();
    return new StateofShape(adaptee.getBaseShape().toString(), shapeAtTick.getPosition().getX(),
        shapeAtTick.getPosition().getY(), shapeAtTick.getSize().getXDir(),
        shapeAtTick.getSize().getYDir(),
        new Color(colorAtTick.getRed(), colorAtTick.getGreen(), colorAtTick.getBlue()));
  }

  @Override
  public void addMotionsToShape(MotionAnimation newMotion) {

  }

  @Override
  public Color getShapeColor() {
    return null;
  }

  @Override
  public int getShapeHeight() {
    return 0;
  }

  @Override
  public int getShapeWidth() {
    return 0;
  }

  @Override
  public Position2D getShapePos() {
    return null;
  }

  @Override
  public List<MotionAnimation> getMotionsofShape() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getForm() {
    return null;
  }

  @Override
  public String description() {
    return null;
  }
}
