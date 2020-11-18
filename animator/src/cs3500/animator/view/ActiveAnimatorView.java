package cs3500.animator.view;

import cs3500.animator.model.shape.Shape;
import java.util.List;

/**
 * View that can deal with visualizing individual frames of an animation.
 */
public interface ActiveAnimatorView extends AnimatorView {

  /**
   * Set the shapes to be displayed in this frame.
   *
   * @param shapes list of shapes to be viewed
   */
  void setShapes(List<Shape> shapes);

  /**
   * Allows the canvas to be seen.
   */
  void makeVisible();

  /**
   * Is the animation still occurring.
   *
   * @return if the animation has been finished.
   */
  boolean isActive();
}
