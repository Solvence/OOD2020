package cs3500.animator.view;

import cs3500.model.Model;

/**
 * This class is a factory for the different views of the animations. Creates a view for the
 * animation depending on a given input with the method create.
 */
public class ViewCreator {

  /**
   * Constructor that takes in all the parameters necessary to construct a view, including a name
   * parameter that is the name of the output file specified by the user.
   *
   * @param str   the type view
   * @param model the model
   * @param tempo the tempo
   * @param ap    the appendable
   * @param name  the name of the output file
   * @return an instance of a specific view
   */
  public static cs3500.animator.view.IView create(String str, Model model, int tempo, Appendable ap,
      String name) {
    cs3500.animator.view.IView view = null;
    switch (str) {
      case ("text"):
        view = new TextualView(model, ap, tempo);
        break;
      case ("svg"):
        view = new SVGView(model, ap, tempo, name);
        break;
      case ("visual"):
        view = new VisualView(model, tempo);
        break;
      case ("interactive"):
        view = new InteractiveView(model, tempo);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + str);
    }
    return view;
  }

  /**
   * Constructor that takes in only the parameters necessary to create an instance of any view to
   * System.out.
   *
   * @param str   the type of view
   * @param model the model
   * @param tempo the tempo
   * @param ap    the appendable
   * @return an instance of a specific view
   */
  public static cs3500.animator.view.IView create(String str, Model model, int tempo,
      Appendable ap) {
    IView view = null;
    switch (str) {
      case ("text"):
        view = new TextualView(model, ap, tempo);
        break;
      case ("svg"):
        view = new SVGView(model, ap, tempo);
        break;
      case ("visual"):
        view = new VisualView(model, tempo);
        break;
      case ("interactive"):
        view = new InteractiveView(model, tempo);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + str);
    }
    return view;
  }

}
