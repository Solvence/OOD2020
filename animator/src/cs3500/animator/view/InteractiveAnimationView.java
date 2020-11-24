package cs3500.animator.view;

import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Represents an interactive animator view implementation that has a number of buttons to support
 * functionality such as pausing, playing, speeding up, slowing down, looping, and resetting
 * the animation.
 */
public class InteractiveAnimationView extends VisualAnimationView implements ActionableAnimationView {
  private JButton pauseButton, playButton, speedUpButton, slowDownButton, resetButton;
  private JToggleButton loopButton;
  private JPanel buttonPanel;

  /**
   * Constructs an interactive animator view given the canvas arguments.
   * @param canvasPos - the top left position of the viewable canvas
   * @param canvasSize - the width and height of the viewable canvas
   */
  public InteractiveAnimationView(Position2D canvasPos, Dimension2D canvasSize) {
    super(canvasPos, canvasSize);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //buttons
    playButton = new JButton("Play");
    playButton.setActionCommand("Play Button");
    buttonPanel.add(playButton);

    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause Button");
    buttonPanel.add(pauseButton);

    speedUpButton = new JButton("Speed Up");
    speedUpButton.setActionCommand("Speed Up Button");
    buttonPanel.add(speedUpButton);

    slowDownButton = new JButton("Slow Down");
    slowDownButton.setActionCommand("Slow Down Button");
    buttonPanel.add(slowDownButton);

    resetButton = new JButton("Reset");
    resetButton.setActionCommand("Reset Button");
    buttonPanel.add(resetButton);

    loopButton = new JToggleButton("Loop");
    loopButton.setActionCommand("Loop Button");
    buttonPanel.add(loopButton);

    this.pack();
  }

  @Override
  public void setListener(ActionListener listener) {
    playButton.addActionListener(listener);
    pauseButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    slowDownButton.addActionListener(listener);
    resetButton.addActionListener(listener);
    loopButton.addActionListener(listener);
  }
}
