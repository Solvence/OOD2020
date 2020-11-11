package cs3500.animator.view;

import java.io.IOException;

/**
 * Represents the cs3500.animator.view component for a animation that animates shapes.
 */
public interface AnimatorView {

  /**
   * Renders current state of cs3500.animator.model.
   *
   * @throws IOException if rendering fails for some reason
   */
  void render() throws IOException;

  /**
   * translate the given time in seconds to a tick value
   * @param time time in seconds
   * @return tick value equivalent to time based on some tick rate.
   */
  int translateToTick(double time);
}
