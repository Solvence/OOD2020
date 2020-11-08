package cs3500.animator.view;

import cs3500.animator.model.color.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animatedobject.AnimatedObject;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.shape.Shape;

/**
 * Represents a textual translation of an animation.
 */
public class BasicTextualAnimatorView implements AnimatorView {

  private final AnimatorModel model;
  private final Appendable log;

  /**
   * Loaded Constructor.
   *
   * @param model - cs3500.animator.model to be displayed
   * @param log   - log of passed displays
   */
  public BasicTextualAnimatorView(AnimatorModel model, Appendable log) {
    this.model = model;
    this.log = log;
  }

  public BasicTextualAnimatorView(AnimatorModel model) {
    this(model, new StringBuilder());
  }


  @Override
  public void render() throws IOException {
    this.log.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder table = new StringBuilder();

    for (String animatedObjectNames : this.model.getAllShapeName()) {
      ArrayList<Integer> times = new ArrayList<>();

      for (AnimatedObjectCommand command : this.model.getCommandsForShape(animatedObjectNames)) {
        times.add(command.getStartTime());
        times.add(command.getEndTime());
      }
      Collections.sort(times);

      StringBuilder currentShapeOutput = new StringBuilder();
      currentShapeOutput.append("Shape - ").append(animatedObjectNames).append(" - ")
          .append(this.model.getShapeAt(animatedObjectNames, 0).toString()).append("\n");

      currentShapeOutput.append(
          "Start: time | x | y | width| height| r | g | b |")
          .append("      End: time | x | y | width| height| r | g | b |\n");

      for (int i = 1; i < times.size(); i++) {
        int startTime = times.get(i - 1);
        int endTime = times.get(i);
        Shape startShape = this.model.getShapeAt(animatedObjectNames, startTime);
        Shape endShape = this.model.getShapeAt(animatedObjectNames,endTime);
        Dimension2D startSize = startShape.getSize();
        Dimension2D endSize = endShape.getSize();
        Color startColor = startShape.getColor();
        Color endColor = endShape.getColor();

        currentShapeOutput.append(String.format("Motion %s %02d %03d %03d %02d %03d %03d %03d %03d",
            animatedObjectNames, startTime, startShape.getPosition().getX(), startShape.getPosition().getY(),
            startSize.getXDir(), startSize.getYDir(), startColor.getRed(),
            startColor.getGreen(), startColor.getBlue()));

        currentShapeOutput.append(String.format("      %02d %03d %03d %02d %03d %03d %03d %03d\n",
            endTime, endShape.getPosition().getX(), endShape.getPosition().getY(),
            endSize.getXDir(), endSize.getYDir(), endColor.getRed(),
            endColor.getGreen(), endColor.getBlue()));
      }
      table.append(currentShapeOutput);
    }
    return table.toString();
  }
}
