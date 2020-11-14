package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.dimension2d.Dimension2D;
import java.io.FileWriter;
import java.io.IOException;

public class SVGAnimatorView implements AnimatorView{
  private final AnimatorModel model;
  private final FileWriter file;
  private final double tickRate;

  public SVGAnimatorView(AnimatorModel model, FileWriter file, double tickRate) {
    this.model = model;
    this.file = file;
    this.tickRate = tickRate;
  }

  @Override
  public void render() throws IOException {
    Dimension2D canvasSize = model.getCanvasSize();
    file.append("<svg width=\"%d\" height=\"%d\"  version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n", canvasSize.getXDir(), canvasSize.getYDir());

  }

  @Override
  public double translateToTime(int tick) {
    return 0;
  }
}
