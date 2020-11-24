package cs3500.animator.controller.actionlistener;

import java.awt.event.ActionEvent;

/**
 * Represents an action listener for an interactive animation that supports user inputs for
 * pausing, playing, speeding up, slowing down, looping, and resetting.
 */
public class InteractiveActionListener implements AnimationActionListener {
  // speeding up the animation will multiply the tick rate by SPEED_MODIFIER
  // slowing down the animation will divide the tick rate by SPEED_MODIFIER
  private static double SPEED_MODIFIER = 2.0;

  private int tick;
  private double tickRate;
  private boolean isPaused;
  private int currentEndTick;
  private boolean isLooping;

  /**
   * Constructs an action listener.
   * @param initialTickRate - the initial tick rate of the animation
   * @param currentEndTick - the end tick of the animation
   * @throws IllegalArgumentException - if the initial tick rate is not positive
   */
  public InteractiveActionListener(double initialTickRate, int currentEndTick)
      throws IllegalArgumentException {
    if (initialTickRate <= 0 || currentEndTick <= 0) {
      throw new IllegalArgumentException("tick rate and end tick must be positive");
    }
    this.tick = 1;
    this.tickRate = initialTickRate;
    this.isPaused = true;
    this.currentEndTick = currentEndTick;
    this.isLooping = false;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public double getTickRate() {
    return this.tickRate;
  }

  @Override
  public void incrementTick() {
    if (!this.isPaused) {
      if (this.isLooping && this.tick >= this.currentEndTick) {
        this.tick = 1;
      } else {
        this.tick += 1;
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Pause Button":
        this.isPaused = true;
        break;
      case "Play Button":
        this.isPaused = false;
        break;
      case "Speed Up Button":
        this.tickRate *= SPEED_MODIFIER;
        break;
      case "Slow Down Button":
        this.tickRate /= SPEED_MODIFIER;
        break;
      case "Loop Button":
        this.isLooping = !this.isLooping;
        break;
      case "Reset Button":
        this.tick = 1;
        break;
      default:
        break;
    }
  }
}
