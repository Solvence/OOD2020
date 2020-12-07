package cs3500.animator.provider.adapters;

import cs3500.animator.provider.listener.InteractiveViewListener;
import cs3500.animator.provider.providerView.InteractiveView;
import cs3500.animator.view.AnimationView;
import java.io.IOException;

public class InteractiveViewAdapterFromProvider implements AnimationView {
  private final InteractiveView adaptee;
  private final InteractiveViewListener listener;

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
