package cs3500.animator.provider.providerView;

import cs3500.animator.provider.providerModel.Model;
import cs3500.animator.provider.providerModel.MotionAnimation;
import cs3500.animator.provider.providerModel.Shape;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * This class represents the textual description of the animation, with the ticks substituted with
 * actual time in seconds, depending on the tempo.
 */
public class TextualView implements IView {

  private final Model model;
  private final Appendable ap;
  //represents the tempo: tick/tempo ticks/second
  double tempo;

  /**
   * Constructor for an instance of Textual view.
   *getShapeColor
   * @param model the model to be used
   * @param ap    the appendable object
   * @param tempo the tempo
   */
  public TextualView(Model model, Appendable ap, int tempo) {
    //check to see if the model or appendable is null or the tempo is invalid
    if ((model == null)) {
      throw new IllegalArgumentException("the model is null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("the appendable is null");
    }
    if (tempo <= 0) {
      throw new IllegalArgumentException("the tempo is invalid");
    }
    this.model = model;
    this.tempo = tempo;
    this.ap = ap;
  }


  /**
   * Creates a string representing a textual view of an animation based on a model.
   *
   * @return a textual view of an animation
   */
  public String description() {
    String finalString = "";
    //line for the canvas
    finalString = finalString +
        "canvas " + model.getX() + " " + model.getY() + " " + model
        .getHeightBoard() + " " + model.getWidthBoard() + "\n";
    for (Shape shape : model.getShapes()) {
      finalString =
          finalString + "shape " + shape.getName() + " " + shape.getForm().toString() + "\n";

      for (MotionAnimation motion : shape.getMotionsofShape()) {
        finalString = finalString + "motion " + shape.getName() + " "
            + (double) motion.getStartTick() / tempo + " "
            + motion.begDescription() + " "
            + (double) motion.getEndTick() / tempo + " "
            + motion.endDescription() + "\n";
      }
    }
    return finalString;
  }


  @Override
  public String buildSVG() {
    throw new UnsupportedOperationException(
        "BuildSVG Method is only used for Textual View not SVG View");
  }


  @Override
  public void setButtonListener(ActionListener mouseAdapter) {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public void setTextListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public String getSpeedChange() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public int changeTempo(int newTempo) {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getStartButton() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getPauseButton() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getRestartButton() {
    throw new UnsupportedOperationException("not supported by textual view.");
  }


  @Override
  public JTextField getChangeSpeed() {
    throw new UnsupportedOperationException("not supported by the textual view.");
  }


  @Override
  public JCheckBox getLoopButton() {
    throw new UnsupportedOperationException("not supported by the textual view.");
  }


  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("not supported by the textual view.");
  }


  @Override
  public void goAnimation() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void stop() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void refresh() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void setLoopMode(boolean selected) {
    throw new UnsupportedOperationException("this method is for the visual view");
  }


  @Override
  public void setTick() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void display() {
    try {
      ap.append(description());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
