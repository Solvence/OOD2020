package cs3500.animator.provider.adapters;

import cs3500.animator.view.AnimationView;
import java.io.FileWriter;
import java.io.IOException;
import cs3500.animator.provider.providerView.TextualView;

/**
 * Adapts our Providers notion of a TextualView to work alongside our promises made in our
 * interface AnimationView. Adapts complex methods inside providers code, to run smoothly within
 * our render method. Renders Providers TextualView with our methods.
 */
public class TextualViewAdapterFromProvider implements AnimationView {
  private final TextualView adaptee;
  private final Appendable log;

  /**
   * Constructor.
   * @param adaptee - Providers TextualView.
   * @param log - what is to be appended textual representation of our current model.
   */
  public TextualViewAdapterFromProvider(TextualView adaptee, Appendable log) {
    this.adaptee = adaptee;
    this.log = log;
  }

  @Override
  public void render() throws IOException {
    adaptee.display();
    if (log instanceof FileWriter) {
      ((FileWriter) log).close();
    }
  }

  @Override
  public double translateToTime(int tick) {
    throw new UnsupportedOperationException("This method is not needed at any point in the "
        + "animation");
  }
}
