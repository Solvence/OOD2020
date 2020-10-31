package view;

import java.io.IOException;

/**
 * Represents the view component for a animation that animates shapes.
 */
public interface AnimatorView {

  /**
   * Renders current state of model.
   * @throws IOException if rendering fails for some reason
   */
  void render() throws IOException;
}
