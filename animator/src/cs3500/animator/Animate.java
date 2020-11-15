package cs3500.animator;

import static java.lang.Thread.sleep;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.ActiveAnimatorView;

import cs3500.animator.view.VisualAnimatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Animate {
  public static void main(String[] args) throws InterruptedException {
    AnimatorModel model = new BasicAnimatorModel();
    model.initCanvas(new Position2D(1,1), new Dimension2D(1000,1000));
    ActiveAnimatorView view3 = new VisualAnimatorView(model.getCanvasPosition(), model.getCanvasSize());

    model.create("My Shape", new Rectangle(20, 10, new Color(1, 1, 199),
        new Position2D(3, 5)));

    model.create("My Other Shape", new Ellipse(15, 12, new Color(100, 0, 0),
        new Position2D(200, 100)));

    model.addMotion("My Shape", 1, 10, new Position2D(3, 5),
        new Position2D(100, 200), new Color(1, 1, 199), new Color(1, 1, 199)
        , new Dimension2D(20, 10), new Dimension2D(100, 40));

    model.addMotion("My Other Shape", 3, 12, new Position2D(200, 100),
        new Position2D(100, 200), new Color(100, 0, 0), new Color(0, 255, 0),
        new Dimension2D(15, 12), new Dimension2D(150, 200));

    model.addMotion("My Other Shape", 12, 20, new Position2D(100, 200),
        new Position2D(100, 200), new Color(0, 255, 0), new Color(0, 255, 0),
        new Dimension2D(150, 200), new Dimension2D(150, 200));


    Timer t = new Timer();
    view3.makeVisible();
    for (int i=0; i < 20; i++) {

          List<Shape> shapes = new ArrayList<Shape>();
          for (String s : model.getAllShapeName()) {
            shapes.add(model.getShapeAt(s, i));
          }
          view3.setShapes(shapes);
          try {
            view3.render();
          } catch (IOException e) {
            e.printStackTrace();
          }
          sleep(100);

    }



  }

  private static TimerTask wrap(Runnable r) {
    return new TimerTask() {

      @Override
      public void run() {
        r.run();
      }
    };
  }
}
