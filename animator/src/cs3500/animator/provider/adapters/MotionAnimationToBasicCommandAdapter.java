package cs3500.animator.provider.adapters;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.animatedobjectcommand.BasicCommand;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.provider.providerModel.MotionAnimation;

/**
 * Translates a MotionAnimation object to a AnimatedObjectCommand by using getter methods to
 * construct an instance of a AnimatedObjectCommand with the fields of the MotionCommand.
 */
public class MotionAnimationToBasicCommandAdapter implements AnimatedObjectCommand {
  private final AnimatedObjectCommand translator;

  /**
   * Constructor / translator.
   * @param ma - MotionAnimation to be translated.
   */
  public MotionAnimationToBasicCommandAdapter(MotionAnimation ma) {
    java.awt.Color startColor = ma.getStartColor();
    java.awt.Color endColor = ma.getEndColor();
    translator = new BasicCommand(ma.getStartTick(), ma.getEndTick(), ma.getStartPosition(),
        ma.getEndPosition(), new Dimension2D(ma.getStartWidth(), ma.getStartHeight()),
        new Dimension2D(ma.getEndWidth(), ma.getEndHeight()),
        new Color(startColor.getRed(), startColor.getGreen(), startColor.getBlue()),
        new Color(endColor.getRed(), endColor.getGreen(), endColor.getBlue()));
  }

  @Override
  public Shape apply(Shape s, int time) throws IllegalArgumentException {
    return translator.apply(s, time);
  }

  @Override
  public int getStartTime() {
    return translator.getStartTime();
  }

  @Override
  public int getEndTime() {
    return translator.getEndTime();
  }
}
