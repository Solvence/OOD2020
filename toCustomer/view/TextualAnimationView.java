package cs3500.animator.view;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.position2d.Position2D;
import java.io.FileWriter;
import java.io.IOException;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.shape.Shape;

/**
 * Represents a textual translation of an animation.
 */
public class TextualAnimationView implements AnimationView {

  private final AnimationModel model;
  private final Appendable log;
  private final double ticksPerSecond;

  /**
   * Loaded Constructor.
   *
   * @param model          - cs3500.animator.model to be displayed
   * @param log            - log of passed displays
   * @param ticksPerSecond - the number of ticks that the animation goes through per second
   * @throws IllegalArgumentException - if ticksPerSecond is nonpositive, or model/log is null
   */
  public TextualAnimationView(AnimationModel model, Appendable log, double ticksPerSecond)
      throws IllegalArgumentException {
    if (ticksPerSecond <= 0 || model == null || log == null) {
      throw new IllegalArgumentException("model/log can't be null and ticksPerSecond must "
          + "be positive");
    }
    this.model = model;
    this.log = log;
    this.ticksPerSecond = ticksPerSecond;
  }



  @Override
  public void render() throws IOException {
    this.log.append(this.toString());
    if (this.log instanceof FileWriter) {
      FileWriter file = (FileWriter) this.log;
      file.close();
    }
  }

  @Override
  public double translateToTime(int tick) {
    return tick / ticksPerSecond;
  }

  @Override
  public String toString() {
    //String.format(".2f", d)
    StringBuilder table = new StringBuilder();

    Dimension2D canvasSize = this.model.getCanvasSize();
    Position2D canvasPosition = this.model.getCanvasPosition();
    table.append("Canvas ").append(canvasPosition.getX()).append(" ").append(canvasPosition.getY())
        .append(" ").append(canvasSize.getXDir()).append(" ").append(canvasSize.getYDir())
        .append("\n");

    for (String animatedObjectNames : this.model.getAllShapeName()) {

      StringBuilder currentShapeOutput = new StringBuilder();

      currentShapeOutput.append("Shape - ").append(animatedObjectNames).append(" - ")
          .append(this.model.getShapeAt(animatedObjectNames, 0).toString()).append("\n");

      for (AnimatedObjectCommand command : this.model.getCommandsForShape(animatedObjectNames)) {
        int startTime = command.getStartTime();
        int endTime = command.getEndTime();
        Shape startShape = this.model.getShapeAt(animatedObjectNames, startTime);
        Shape endShape = this.model.getShapeAt(animatedObjectNames, endTime);
        Dimension2D startSize = startShape.getSize();
        Dimension2D endSize = endShape.getSize();
        Color startColor = startShape.getColor();
        Color endColor = endShape.getColor();

        currentShapeOutput.append(String.format("Motion %s %.2f %d %d %d %d %d %d %d",
            animatedObjectNames, this.translateToTime(startTime), startShape.getPosition().getX(),
            startShape.getPosition().getY(),
            startSize.getXDir(), startSize.getYDir(), startColor.getRed(),
            startColor.getGreen(), startColor.getBlue()));

        currentShapeOutput.append(String.format("      %.2f %d %d %d %d %d %d %d\n",
            this.translateToTime(endTime), endShape.getPosition().getX(),
            endShape.getPosition().getY(),
            endSize.getXDir(), endSize.getYDir(), endColor.getRed(),
            endColor.getGreen(), endColor.getBlue()));

      }
      table.append(currentShapeOutput);
    }
    return table.toString();
  }
}
