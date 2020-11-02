package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import model.AnimatorModel;
import model.animatedObject.AnimatedObject;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.dimension2D.Dimension2D;
import model.shape.Shape;

/**
 * Represents a textual translation of an animation
 */
public class BasicTextualAnimatorView implements AnimatorView {

  private final AnimatorModel model;
  private final Appendable log;

  /**
   * Loaded Constructor
   *
   * @param model - model to be displayed
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

    for (Map.Entry<String, AnimatedObject> e : this.model.getAnimatedObjects().entrySet()) {
      ArrayList<Integer> times = new ArrayList<>();
      String name = e.getKey();
      AnimatedObject animated = e.getValue();

      for (AnimatedObjectCommand command : animated.getCommands()) {
        times.add(command.getStartTime());
        times.add(command.getEndTime());
      }
      Collections.sort(times);

      StringBuilder currentShapeOutput = new StringBuilder();
      currentShapeOutput.append("Shape - ").append(name).append(" - ")
          .append(animated.getShape(0).toString()).append("\n");

      currentShapeOutput.append(
          "Start: time | x | y | width| height| r | g | b |")
          .append("      End: time | x | y | width| height| r | g | b |\n");

      for (int i = 1; i < times.size(); i++) {
        int startTime = times.get(i - 1);
        int endTime = times.get(i);
        Shape startShape = animated.getShape(startTime);
        Shape endShape = animated.getShape(endTime);
        Dimension2D startSize = startShape.getSize();
        Dimension2D endSize = endShape.getSize();
        Color startColor = startShape.getColor();
        Color endColor = endShape.getColor();

        currentShapeOutput.append(String.format("Motion %s %02d %03d %03d %02d %03d %03d %03d %03d",
            name, startTime, startShape.getPosition().getX(), startShape.getPosition().getY(),
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
