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
   * translate the given tick to unitful time.
   * @param tick the given tick frame to be converted
   * @return time value equivalent to tick based on some tick rate.
   */
  double translateToTime(int tick);
}
