package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import model.AnimatorModel;
import model.animatedObject.AnimatedObject;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.shape.Shape;

/**
 * Represents a textual translation of an animation
 */
public class BasicTextualAnimatorView implements AnimatorView {
  private final AnimatorModel model;
  private final Appendable log;

  /**
   * Loaded Constructor
   * @param model   - model to be displayed
   * @param log     - log of passed displays
   */
  BasicTextualAnimatorView(AnimatorModel model, Appendable log) {
    this.model = model;
    this.log = log;
  }

  BasicTextualAnimatorView(AnimatorModel model) {
    this(model, new StringBuilder());
  }


  @Override
  public void render() throws IOException {
    this.log.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder table = new StringBuilder();


    for (Map.Entry<String, AnimatedObject> e: this.model.getAnimatedObjects().entrySet()) {
      ArrayList<Integer> times = new ArrayList<>();
      String name = e.getKey();
      AnimatedObject animated = e.getValue();

      for (AnimatedObjectCommand command : animated.getCommands()) {
        times.add(command.getStartTime());
        times.add(command.getEndTime());
      }
      Collections.sort(times);

      StringBuilder currentRow = new StringBuilder();


      for (int i = 1; i < times.size(); i++) {
        int startTime = times.get(i - 1);
        int endTime = times.get(i);
        Shape startShape = animated.getShape(startTime);
        Shape endShape = animated.getShape(endTime);
        Map<String, Integer> startSize = startShape.getSize();
        Map<String, Integer> endSize = endShape.getSize();
        Color startColor = startShape.getColor();
        Color endColor = endShape.getColor();

        currentRow.append(String.format("Motion %s %02d %03d %03d %02d %03d %03d %03d %03d",
            name, startTime, startShape.getPosition().getX(), startShape.getPosition().getY(),
            startSize.get("x-direction"), startSize.get("y-direction"), startColor.getRed(),
            startColor.getGreen(), startColor.getBlue()));

        currentRow.append(String.format("      %02d %03d %03d %02d %03d %03d %03d %03d\n",
            endTime, endShape.getPosition().getX(), endShape.getPosition().getY(),
            endSize.get("x-direction"), endSize.get("y-direction"), endColor.getRed(),
            endColor.getGreen(), endColor.getBlue()));
      }
      table.append(currentRow);
    }
    return table.toString();
  }
}
