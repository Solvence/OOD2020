package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.model.shapevisitor.RenderSVGAnimateTag;
import cs3500.animator.model.shapevisitor.RenderSVGExitTag;
import cs3500.animator.model.shapevisitor.RenderSVGHeader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a view to render an SVG file
 */
public class SVGAnimatorView implements AnimatorView{
  private final AnimatorModel model;
  private final FileWriter file;
  private final double tickRate; // in ticks/seconds

  /**
   * creates a view to render an SVG file
   * @param model - the model that will support this view's animation
   * @param file - the filewriter which this svg will be outputted
   * @param tickRate - the rate of the animation in ticks per second
   */
  public SVGAnimatorView(AnimatorModel model, FileWriter file, double tickRate) {
    this.model = model;
    this.file = file;
    this.tickRate = tickRate;
  }

  @Override
  public void render() throws IOException {
    Dimension2D canvasSize = model.getCanvasSize();
    file.write(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\""
        + " xmlns=\"http://www.w3.org/2000/svg\">\n", canvasSize.getXDir(),
        canvasSize.getYDir()));

    for (String name: model.getAllShapeName()) {
      List<AnimatedObjectCommand> commands = model.getCommandsForShape(name);
      AnimatedObjectCommand firstCommand = commands.get(0);
      Shape baseShape = this.model.getShapeAt(name, 0);
      Shape initialShape = commands.get(0).apply(baseShape, firstCommand.getStartTime());

      file.write(new RenderSVGHeader(name).apply(initialShape).toString());

      for (AnimatedObjectCommand command: commands) {
        double startTime = this.translateToTime(command.getStartTime());
        double endTime = this.translateToTime(command.getEndTime());

        file.write(new RenderSVGAnimateTag(command, startTime, endTime).apply(baseShape).toString());
      }

      file.write(new RenderSVGExitTag().apply(baseShape).toString());

    }


    file.write("</svg>\n");

    file.close();

  }

  /**
   * returns the time in milliseconds
   */
  @Override
  public double translateToTime(int tick) {
    return tick / tickRate * 1000;
  }
}
