package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * A AnimatorView that is capable of telling if user input has been preformed.
 */
public interface InteractiveAnimatorView extends ActiveAnimatorView{

  /**
   * Adds a Listener to a component, so we may know when an action has been preformed on it.
   * @param listener Object that expects an action, and processes the result of the action.
   */
  void setListener(ActionListener listener);

}
