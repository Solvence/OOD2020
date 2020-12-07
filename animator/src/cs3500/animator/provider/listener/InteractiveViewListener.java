package cs3500.animator.provider.listener;

import cs3500.animator.provider.providerView.InteractiveView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An ActionListener built to support actions from our Providers Interactive view.
 *  Actions include:
 *    speed - increase or decrease speed based off int input.
 *    start - start animation.
 *    pause - pause or unpause animation.
 *    restart - restart animation.
 *    loop - have animation loop till button is unselected.
 */
public class InteractiveViewListener implements ActionListener {
  private final InteractiveView view;
  private boolean pause;

  /**
   * Default constructor for making a listener.
   * @param view - view to listen to.
   */
  public InteractiveViewListener(InteractiveView view) {
    this.view = view;
    this.pause = true;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "speed":
          int newTempo = Integer.parseInt(view.getSpeedChange());
          view.changeTempo(newTempo);
          view.setTick();
          break;
        case "start":
          view.goAnimation();
          break;
        case "pause":
          if ((this.pause)) {
            view.goAnimation();
          } else {
            view.stop();
          }
          this.pause = !pause;
          break;
        case "restart":
          view.setTick();
          view.goAnimation();
          break;
        case "loop":
          view.setLoopMode(view.getLoopButton().isSelected());
          break;
        default:
          throw new IllegalStateException("event doesn't exist");
      }
    } catch (NumberFormatException e1) {
      e1.printStackTrace();
    }
  }
}
