package cs3500.animator.provider.adapters;

import cs3500.animator.provider.listener.InteractiveViewListener;
import cs3500.animator.provider.providerView.InteractiveView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;

/**
 * Adapter class that allows our definition of needed functions of an AnimationView to work
 * alongside what needs to be called in our Providers InteractiveView, for an animation to
 * properly run. This adapter uses a delegate of our Providers InteractiveView and an
 * instance of a listener we have defined, to allow for a animation to fully be rendered,
 */
public class InteractiveViewAdapterFromProvider implements AnimationView {
  private final InteractiveView adaptee;
  private final InteractiveViewListener listener;

  /**
   * Constructor.
   * @param adaptee - Providers view to be adapted to work with our AnimatorView interface
   *                 promises.
   */
  public InteractiveViewAdapterFromProvider(
      InteractiveView adaptee) {
    this.adaptee = adaptee;
    this.listener = new InteractiveViewListener(adaptee);
  }


  @Override
  public void render() throws IOException {
    this.adaptee.setButtonListener(this.listener);
    this.adaptee.display();
  }

  @Override
  public double translateToTime(int tick) {
    throw new UnsupportedOperationException("This method isn't needed for adapter to function.");
  }
}
