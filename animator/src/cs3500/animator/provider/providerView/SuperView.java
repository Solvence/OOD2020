package cs3500.animator.provider.providerView;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Interface implemented by all views that contains all the methods needed for any view. Methods
 * that aren't used in a particular throw unsupported argument exceptions within that particular
 * view class.
 */
public interface SuperView {

  /**
   * Method to render the view based off the type of view it is. Each class that implements this
   * method overrides it to be specific to that view.
   */
  void display() throws IOException;

  /**
   * Creates a string representing a textual view of an animation based on a model.
   *
   * @return a string representing a textual view
   */
  String description();

  /**
   * Method that returns a string that represents a complete SVG code.
   *
   * @return a string representing SVG code
   */
  String buildSVG();

  /**
   * Provides the view with a MouseAdapter for the button that should cause the program to process
   * the command (either start, pause, resume, restart).
   *
   * @param listener action listener
   */
  public void setButtonListener(ActionListener listener);

  /**
   * Provides the view with an Action Listener for the button that should cause the program to
   * increase the speed.
   *
   * @param actionListener action listener
   */
  public void setTextListener(ActionListener actionListener);

  /**
   * Gets the command typed by the user from the text box.
   *
   * @return a string representing the new speed
   */
  public String getSpeedChange();

  /**
   * Returns the new tempo that is specified by the user.
   *
   * @return the tempo
   */
  public int changeTempo(int newTempo);

  /**
   * Gets the startButton from the ControlPanel.
   *
   * @return returns the startButton
   */
  public JButton getStartButton();

  /**
   * Gets the PauseButton from the ControlPanel.
   *
   * @return returns the pause button
   */
  public JButton getPauseButton();

  /**
   * Gets the restartButton from the ControlPanel.
   *
   * @return returns the restartButton
   */
  public JButton getRestartButton();

  /**
   * Gets the speed field from the text field.
   *
   * @return returns the text fie
   */
  public JTextField getChangeSpeed();

  /**
   * Gets the loop checkbox from the ControlPanel.
   *
   * @return the checkbox
   */
  public JCheckBox getLoopButton();

  /**
   * Gets the timer from the visual view.
   *
   * @return returns the timer
   */
  Timer getTimer();

  /**
   * Starts the timer.
   */
  void goAnimation();

  /**
   * Stops the timer.
   */
  void stop();

  /**
   * View draws itself.
   */
  void refresh();

  /**
   * Sets the tick to 0.
   */
  void setTick();

  /**
   * A boolean to determine if the display should run on a loop.
   *
   * @param selected yes or no
   */
  void setLoopMode(boolean selected);
}