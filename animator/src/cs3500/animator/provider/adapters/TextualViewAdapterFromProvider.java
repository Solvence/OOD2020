package cs3500.animator.provider.adapters;

import cs3500.animator.view.AnimationView;
import java.io.FileWriter;
import java.io.IOException;
import cs3500.animator.provider.providerView.TextualView;


public class TextualViewAdapterFromProvider implements AnimationView {
  private final TextualView adaptee;
  private final Appendable log;

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
