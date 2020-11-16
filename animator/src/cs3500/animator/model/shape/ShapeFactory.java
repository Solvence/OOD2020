package cs3500.animator.model.shape;

/**
 * Represents a factory class to construct a Shape with a given type.
 */
public class ShapeFactory {

  /**
   * constructs a Shape with the given type.
   * @param name - the type of shape that you want constructed
   * @return - an instance of the shape the the given type
   * @throws IllegalArgumentException - if no shape has the given type
   */
  public static Shape build(String name) throws IllegalArgumentException {
    switch(name) {
      case "ellipse": return new Ellipse();
      case "rectangle": return new Rectangle();
      default: throw new IllegalArgumentException("Invalid Shape type");
    }

  }
}
