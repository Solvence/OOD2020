package cs3500.animator;

import static java.lang.Thread.sleep;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.BasicAnimatorModel;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ActiveAnimatorView;

import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excellence {
  public static void main(String[] args) throws InterruptedException, IOException {
    String type = findView(args);
    double tickRate = findTickRate(args);
    Appendable out = findOut(args);
    String in = findIn(args);

    AnimationReader reader = new AnimationReader();
    AnimatorModel model = reader.parseFile(new FileReader(new File(in)),
        BasicAnimatorModel.builder());

    AnimatorView view = ViewFactory.build(type, model, tickRate, out);

    if (type.equals("visual")) {
      int tick = 0;
      ActiveAnimatorView newView = (ActiveAnimatorView) view;
      newView.makeVisible();
      while (newView.isActive()) {
        List<Shape> shapes = new ArrayList<Shape>();
        for (String s : model.getAllShapeName()) {
          shapes.add(model.getShapeAt(s, tick));
        }
        newView.setShapes(shapes);
        try {
          newView.render();
        } catch (IOException e) {
          e.printStackTrace();
        }
        sleep((int)(1000/tickRate));
        tick +=1;

      }

    }else{
      view.render();
    }
  }

  private static String findView(String[] args) {
    int viewIndex = -1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-view")) {
        viewIndex = i + 1;
      }
    }

    if (viewIndex == -1 || viewIndex > args.length - 1) {
      throw new IllegalArgumentException("view not defined");
    }
    return args[viewIndex];
  }

  private static double findTickRate(String[] args) {
    int tickRateIndex = -1;
    double tickRate = 1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-speed")) {
        tickRateIndex = i + 1;
      }
    }
    if (tickRateIndex == -1 || tickRateIndex > args.length - 1) {
      return tickRate;
    }
    return Double.parseDouble(args[tickRateIndex]);
  }

  private static Appendable findOut(String[] args) throws IOException {
    int outIndex = -1;
    Appendable out = System.out;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-out")) {
        outIndex = i + 1;
      }
    }
    if (outIndex == -1 || outIndex > args.length - 1) {
      return out;
    }
    return new FileWriter(args[outIndex]);
  }

  private static String findIn(String[] args) throws IOException {
    int inIndex = -1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        inIndex = i + 1;
      }
    }
    if (inIndex == -1 || inIndex > args.length - 1) {
      throw new IllegalArgumentException("in file not defined");
    }
    return args[inIndex];
  }

}
