package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.shapevisitor.RenderSVGAnimateTag;
import cs3500.animator.view.shapevisitor.RenderSVGExitTag;
import cs3500.animator.view.shapevisitor.RenderSVGHeader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a view to render an SVG file.
 */
public class SVGAnimatorView implements AnimatorView {
  private final AnimatorModel model;
  private final Appendable file;
  private final double tickRate; // in ticks/seconds

  /**
   * creates a view to render an SVG file.
   * @param model - the model that will support this view's animation
   * @param tickRate - the rate of the animation in ticks per second
   */
  public SVGAnimatorView(AnimatorModel model, Appendable out, double tickRate) {
    this.model = model;
    this.tickRate = tickRate;
    this.file = out;
  }

  @Override
  public void render() throws IOException {
    Dimension2D canvasSize = model.getCanvasSize();
    Position2D canvasPosition = model.getCanvasPosition();
    file.append(String.format("<svg viewBox = \"%d %d %d %d\" version=\"1.1\""
        + " xmlns=\"http://www.w3.org/2000/svg\">\n", canvasPosition.getX(), canvasPosition.getY(),
        canvasSize.getXDir(),
        canvasSize.getYDir()));

    for (String name: model.getAllShapeName()) {
      List<AnimatedObjectCommand> commands = model.getCommandsForShape(name);
      if (commands.size() == 0) {
        continue;
      }
      AnimatedObjectCommand firstCommand = commands.get(0);
      Shape baseShape = this.model.getShapeAt(name, 0);
      Shape initialShape = commands.get(0).apply(baseShape, firstCommand.getStartTime());

      file.append(new RenderSVGHeader(name).apply(initialShape));

      for (AnimatedObjectCommand command: commands) {
        double startTime = this.translateToTime(command.getStartTime());
        double endTime = this.translateToTime(command.getEndTime());

        file.append(new RenderSVGAnimateTag(command, startTime, endTime).apply(baseShape));
      }

      file.append(new RenderSVGExitTag().apply(baseShape));

    }


    file.append("</svg>\n");


    if (this.file instanceof FileWriter) {
      FileWriter file = (FileWriter) this.file;
      file.close();
    }

  }

  /**
   * returns the time in milliseconds.
   */
  @Override
  public double translateToTime(int tick) {
    return tick / tickRate * 1000;
  }
}
