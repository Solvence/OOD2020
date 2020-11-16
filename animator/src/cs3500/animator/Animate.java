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
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ActiveAnimatorView;

import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.VisualAnimatorView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Animate {
  public static void main(String[] args) throws InterruptedException, IOException {
    AnimationReader reader = new AnimationReader();
    AnimatorModel model = reader.parseFile(new FileReader(new File("buildings.txt")),
        BasicAnimatorModel.builder());

    ActiveAnimatorView view3 = new VisualAnimatorView(model.getCanvasPosition(),
        model.getCanvasSize());

    AnimatorView view1 = new SVGAnimatorView(model, new FileWriter("TESTING.svg"),
        60);

    view1.render();


    Timer t = new Timer();
    view3.makeVisible();
    for (int i=0; i < 1000; i++) {

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
