package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.ControllerFactory;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.util.AnimationReader;

import cs3500.animator.view.AnimationView;
import cs3500.animator.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the main class with a method that our animation is ran through. When executing the
 * main method, command line inputs can be inputted, representing what how the user wants the
 * animation to be made/dsplayed.
 */
public class Excellence {

  /**
   * The main method through which our animation program is run through.
   *
   * @param args - the command line arguments specifying how the animation will be made/displayed
   * @throws InterruptedException - if the process becomes interrupted abruptly
   * @throws IOException          - if the input/output processes do not function properly
   */
  public static void main(String[] args) throws InterruptedException, IOException {
    try {
      String type = findView(args);
      double tickRate = findTickRate(args);
      Appendable out = findOut(args);
      String in = findIn(args);

      AnimationReader reader = new AnimationReader();
      AnimationModel model = reader.parseFile(new FileReader(new File(in)),
          BasicAnimationModel.builder());

      AnimationView view = ViewFactory.build(type, model, tickRate, out);

      AnimationController controller = ControllerFactory.build(type, model, tickRate, view);

      controller.start();
    } catch (IllegalArgumentException | IOException | IllegalStateException
        | NullPointerException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * searches the command line arguments for the specified view type.
   *
   * @param args - the command line arguments
   * @return - a String representing the view type
   */
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

  /**
   * searches the command line arguments for the speed.
   *
   * @param args - the command line arguments
   * @return - the tick rate in ticks/sec of the animation (default=1)
   */
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

  /**
   * searches the command line arguments for the output file specification.
   *
   * @param args - the command line arguments
   * @return - the output file that the animation will write to (default=System.out), if applicable
   * @throws IOException - if a process cannot be completed when reading the output file
   */
  private static Appendable findOut(String[] args) throws IOException {
    int outIndex = -1;
    Appendable out = System.out;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-out")) {
        outIndex = i + 1;
      }
    }
    if (outIndex == -1 || outIndex > args.length - 1 || args[outIndex].equals("out")) {
      return out;
    }
    return new FileWriter(args[outIndex]);
  }

  /**
   * Searches the command line arguments to find the input file.
   *
   * @param args - the command line arguments
   * @return - the input file for the animation
   * @throws IOException - if the process of taking in the input file cannot be completed
   */
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
