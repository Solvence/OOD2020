package cs3500.animator.view;

import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents an interactive animator view implementation that has a number of buttons to support
 * functionality such as Pausing, speeding up, slowing down, and resetting the animation.
 */
public class InteractiveAnimatorView extends VisualAnimatorView implements ActionableAnimatorView {
  private JButton pauseButton, speedUpButton, slowDownButton, resetButton;
  private JPanel buttonPanel;

  /**
   * Constructs an interactive animator view given the canvas arguments.
   * @param canvasPos - the top left position of the viewable canvas
   * @param canvasSize - the width and height of the viewable canvas
   */
  public InteractiveAnimatorView(Position2D canvasPos, Dimension2D canvasSize) {
    super(canvasPos, canvasSize);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //buttons
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

    this.pack();
  }

  @Override
  public void setListener(ActionListener listener) {
    pauseButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    slowDownButton.addActionListener(listener);
    resetButton.addActionListener(listener);
  }
}
