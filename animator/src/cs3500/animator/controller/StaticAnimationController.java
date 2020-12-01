package cs3500.animator.controller;

import cs3500.animator.view.AnimationView;
import java.io.IOException;

/**
 * Represents a controller that only tells the view to render once. Useful for static views that
 * do not maintain a state.
 */
public class StaticAnimationController implements AnimationController {
  private final AnimationView view;

  /**
   * Constructs an animation controller for static views.
   * @param view - the view to be rendered
   * @throws IllegalArgumentException - if view does not exist
   */
  public StaticAnimationController(AnimationView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("view must exist");
    }
    this.view = view;
  }

  @Override
  public void go() {
    try {
      view.render();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
