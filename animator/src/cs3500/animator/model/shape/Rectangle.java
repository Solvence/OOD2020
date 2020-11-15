package cs3500.animator.model.shape;

import cs3500.animator.model.color.Color;
import cs3500.animator.view.shapevisitor.ShapeVisitor;
import java.util.Objects;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;

/**
 * Represents a Rectangle Shape.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs a Rectangle. INVARIANT: width and height cannot be negative, and color and position
   * cannot be null.
   *
   * @param width    width of rectangle
   * @param height   height of rectangle
   * @param color    color of rectangle
   * @param position position of rectangle
   */
  public Rectangle(int width, int height, Color color, Position2D position) {
    super(width, height, color, position);
  }

  /**
   * Default Constructor
   */
  public Rectangle() {
    super();
  }

  @Override
  public Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException {
    if (position == null  && color == null && size == null) {
      return new Rectangle();
    } else if (position == null  || color == null || size == null) {
      throw new IllegalArgumentException("cannot construct an Rectangle with a mix of null and nonnull arguments");
    }
    return new Rectangle(size.getXDir(), size.getYDir(), color, position);
  }

  @Override
  public <R> R accept(ShapeVisitor<R> visitor) {
    return visitor.visitRectangle(this);
  }

  /**
   * An Rectangle is equal to an Object if the object is an Rectangle and has the same dimensions,
   * color, and position as this Rectangle.
   *
   * @param other - the other Object being compared with this Rectangle
   * @return - true if this Rectangle is equal to the Object, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof Rectangle
        && this.width == ((Rectangle) other).getSize().getXDir()
        && this.height == ((Rectangle) other).getSize().getYDir()
        && this.color.equals(((Rectangle) other).getColor())
        && this.position.equals(((Rectangle) other).getPosition());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, this.color, this.position,
        "Rectangle");
  }

  @Override
  public String toString() {
    return "Rectangle";
  }
}
