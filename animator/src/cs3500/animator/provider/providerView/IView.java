package cs3500.animator.provider.providerView;

import java.io.IOException;

/**
 * Interface to represent a view, either textual, visual, or SVG. Each view instance shares a common
 * diplay method that essentially renders the view.
 */
public interface IView extends SuperView {

  /**
   * Method to render the view based off the type of view it is. Each class that implements this
   * method overrides it to be specific to that view.
   */
  void display() throws IOException;

}
