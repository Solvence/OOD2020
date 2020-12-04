package cs3500.animator.controller.actionlistener;

import java.awt.event.ActionListener;

/**
 * Represents an action listener that handles user input for various commands related to animating.
 */
public interface AnimationActionListener extends ActionListener {

  /**
   * Computes the current tick of the animation.
   * @return - the current tick of the animation
   */
  int getTick();

  /**
   * computes the tick rate of the animation.
   * @return - the tick rate
   */
  double getTickRate();

  /**
   * increments the tick of the animation, if necessary.
   */
  void incrementTick();
}
