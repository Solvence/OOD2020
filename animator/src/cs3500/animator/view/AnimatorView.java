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
}
