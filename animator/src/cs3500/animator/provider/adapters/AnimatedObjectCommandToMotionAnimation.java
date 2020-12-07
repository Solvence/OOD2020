package cs3500.animator.provider.adapters;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.model.color.*;
import java.util.Objects;

/**
 * This is an Adapter, which contains a delegate of a AnimatedObjectCommand and translated calls to
 * a MotionAnimation to work on a AnimatedObjectCommand.
 */
public class AnimatedObjectCommandToMotionAnimation implements MotionAnimation {
  private final AnimatedObjectCommand delegate;
  private final Shape startShape; // this shape represents state of shape at beginning of command
  private final Shape endShape; // this shape represents state of shape at end of command

  /**
   * Default Constructor.
   * @param delegate delegate object who's functionality is used to implement methods for this
   *                 adapter
   */
  public AnimatedObjectCommandToMotionAnimation(
      AnimatedObjectCommand delegate) {
    Objects.requireNonNull(delegate);
    this.delegate = delegate;
    Shape local = new Rectangle();
    this.startShape = this.delegate.apply(local, this.delegate.getStartTime());
    this.endShape = this.delegate.apply(local, this.delegate.getEndTime());
  }

  @Override
  public String description() {
    StringBuilder s = new StringBuilder();
    s.append(this.begDescription()).append("   ").append(this.endDescription());
    return s.toString();
  }

  @Override
  public int getStartTick() {
    return delegate.getStartTime();
  }

  @Override
  public int getEndTick() {
    return this.delegate.getEndTime();
  }

  @Override
  public java.awt.Color getStartColor() {
    Color startColor = this.startShape.getColor();
    return new java.awt.Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue());
  }

  @Override
  public java.awt.Color getEndColor() {
    Color startColor = this.endShape.getColor();
    return new java.awt.Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue());
  }

  @Override
  public int getStartHeight() {
    return this.startShape.getSize().getYDir();
  }

  @Override
  public int getEndHeight() {
    return this.endShape.getSize().getYDir();
  }

  @Override
  public int getStartWidth() {
    return this.startShape.getSize().getXDir();
  }

  @Override
  public int getEndWidth() {
    return this.endShape.getSize().getXDir();
  }

  @Override
  public Position2D getStartPosition() {
    return this.startShape.getPosition();
  }

  @Override
  public Position2D getEndPosition() {
    return this.endShape.getPosition();
  }

  @Override
  public String begDescription() {
    StringBuilder s = new StringBuilder();
    Position2D startPos = this.getStartPosition();
    int startWidth = this.getStartWidth();
    int startHeight = this.getStartHeight();
    java.awt.Color startColor = this.getStartColor();
    s.append(String.format("%d %d %d %d %d %d %d",
        startPos.getX(),
        startPos.getY(),
        startWidth, startHeight, startColor.getRed(),
        startColor.getGreen(), startColor.getBlue()));
    return s.toString();
  }

  @Override
  public String endDescription() {
    StringBuilder s = new StringBuilder();
    Position2D endPos = this.getEndPosition();
    int endWidth = this.getEndWidth();
    int endHeight = this.getEndHeight();
    java.awt.Color endColor = this.getEndColor();
    s.append(String.format("%d %d %d %d %d %d %d",
        endPos.getX(),
        endPos.getY(),
        endWidth, endHeight, endColor.getRed(),
        endColor.getGreen(), endColor.getBlue()));
    return s.toString();
  }
}
