package cs3500.animator.view;

import cs3500.model.Model;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Class to represent an interactive view, which adds functionality to the visual view without
 * changing any aspects of it. It does so by adding a control panel to the animation panel from
 * visual view. This control panel contains buttons to start, pause, restart, and loop the
 * animation.
 */
public class InteractiveView extends VisualView {

  ControlPanel controlPanel;
  public boolean restart;
  public boolean start;

  /**
   * Constructor.
   *
   * @param model the model
   * @param tempo the tempo
   */
  public InteractiveView(Model model, int tempo) {
    super(model, tempo);
    this.controlPanel = new ControlPanel();
    animationPanel.add(controlPanel, BorderLayout.SOUTH);
    this.initialization();

  }

  /**
   * Starts the timer.
   */
  public void goAnimation() {
    timer.start();
  }

  /**
   * Stops the timer.
   */
  public void stop() {
    timer.stop();
  }

  /**
   * Displays the interactive view. Creates a new timer with a given tempo and updates the tick. On
   * each tick, the shapes are repainted and visually displayed on the screen.
   */
  @Override
  public void display() {
    timer = new Timer(1000 / tempo, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tick++;
        updateState();
        if (tick >= model.getLastTick()) {
          if (loopMode) {
            setTick();
          } else {
            timer.stop();
          }
        }
        repaint();
      }
    });
  }

  /**
   * Assigns an action listener to each button so they can be pressed.
   *
   * @param listener the listener to be assigned
   */
  @Override
  public void setButtonListener(ActionListener listener) {
    getStartButton().addActionListener(listener);
    getPauseButton().addActionListener(listener);
    getRestartButton().addActionListener(listener);
    getLoopButton().addActionListener(listener);
  }

  /**
   * Getter method to return the start button.
   *
   * @return the start button
   */
  @Override
  public JButton getStartButton() {
    return controlPanel.getStartButtonPanel();
  }

  /**
   * Getter method to return the pause button.
   *
   * @return the pause button
   */
  @Override
  public JButton getPauseButton() {
    return controlPanel.getPauseButtonPanel();
  }

  /**
   * Getter method to return the restart button.
   *
   * @return the restart button
   */
  @Override
  public JButton getRestartButton() {
    return controlPanel.getRestartButtonPanel();
  }

  /**
   * Provides the view with an Action Listener for the button that should cause the program to
   * increase the speed.
   *
   * @param actionEvent the action listener
   */
  @Override
  public void setTextListener(ActionListener actionEvent) {
    getChangeSpeed().addActionListener(actionEvent);
  }

  /**
   * Gets the speed field.
   *
   * @return the text field
   */
  @Override
  public JTextField getChangeSpeed() {
    return controlPanel.getChangeSpeedPanel();
  }

  /**
   * Gets the loop checkbox from the ControlPanel.
   *
   * @return the checkbox
   */
  @Override
  public JCheckBox getLoopButton() {
    return controlPanel.getLoopButtonPanel();
  }

  /**
   * Returns the number typed by the user in the text box and resets the text box to be empty.
   *
   * @return a string representing the new speed
   */
  public String getSpeedChange() {
    String speedChange = this.getChangeSpeed().getText();
    this.getChangeSpeed().setText("");
    return speedChange;
  }
}