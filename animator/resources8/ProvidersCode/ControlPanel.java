package cs3500.animator.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to represent a panel with buttons to be added to the panel from the visual view. The
 * buttons on the control panel add functionality to the visual view without changing any aspects of
 * it. The control panel adds the ability to start, pause, restart, and loop the animation.
 */
public class ControlPanel extends JPanel {

  //private JLabel specificSpeed;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JTextField changeSpeed;
  private JCheckBox loopButton;

  /**
   * Default no-argument constructor.
   */
  public ControlPanel() {
    JLabel specificSpeed = new JLabel("Change Speed");
    this.startButton = new JButton("Start");
    this.pauseButton = new JButton("Pause");
    this.restartButton = new JButton("Restart");
    this.loopButton = new JCheckBox();
    this.changeSpeed = new JTextField();
    this.changeSpeed.setColumns(4);

    this.loopButton = new JCheckBox("Loop?");
    this.startButton.setActionCommand("start");
    this.changeSpeed.setActionCommand("speed");
    this.pauseButton.setActionCommand("pause");
    this.restartButton.setActionCommand("restart");
    this.loopButton.setActionCommand("loop");

    this.add(pauseButton);
    this.add(startButton);
    this.add(restartButton);
    this.add(specificSpeed);
    this.add(changeSpeed);
    this.add(loopButton);
  }

  /**
   * Gets the startButton.
   *
   * @return the startButton
   */
  public JButton getStartButtonPanel() {
    return startButton;
  }

  /**
   * Gets the Pause Button.
   *
   * @return the Pause button
   */
  public JButton getPauseButtonPanel() {
    return pauseButton;
  }

  /**
   * Gets the restartButton.
   *
   * @return the restartButton
   */
  public JButton getRestartButtonPanel() {
    return restartButton;
  }

  /**
   * Gets the speed provided by the user.
   *
   * @return the changed speed
   */
  public JTextField getChangeSpeedPanel() {
    return changeSpeed;
  }

  /**
   * Gets the loop check box button.
   *
   * @return the check box
   */
  public JCheckBox getLoopButtonPanel() {
    return loopButton;
  }
}

