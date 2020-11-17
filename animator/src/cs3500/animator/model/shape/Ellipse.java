package cs3500.animator.model.shape;


import cs3500.animator.model.color.Color;
import cs3500.animator.view.shapevisitor.ShapeVisitor;
import java.util.Objects;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;

/**
 * Represents an Ellipse Shape.
 */
public class Ellipse extends AbstractShape {

  /**
   * Construct an ellipse. INVARIANT: xRad and yRad cannot be negative, and color and position
   * cannot be null.
   *
   * @param xDirection     width of bounding box
   * @param yDirection     height of bounding box
   * @param color    Color of ellipse
   * @param position Position of ellipse
   */
  public Ellipse(int xDirection, int yDirection, Color color, Position2D position) {
    super(xDirection, yDirection, color, position);
  }

  /**
   * Default Constructor
   */
  public Ellipse() {
    super();
  }

  @Override
  public Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException {
    if (position == null  && color == null && size == null) {
      return new Ellipse();
    } else if (position == null  || color == null || size == null) {
      throw new IllegalArgumentException("cannot construct an Ellipse with a mix of null and nonnull arguments");
    }
    return new Ellipse(size.getXDir(), size.getYDir(), color, position);
  }

  @Override
  public <R> R accept(ShapeVisitor<R> visitor) {
    return visitor.visitEllipse(this);
  }

  /**
   * An Ellipse is equal to an Object if the object is an Ellipse and has the same dimensions,
   * color, and position as this Ellipse.
   *
   * @param other - the other Object being compared with this Ellipse
   * @return - true if this Ellipse is equal to the Object, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (!(other instanceof Ellipse)) {
      return false;
    }
    Ellipse that = (Ellipse) other;
    if (this.size == null && that.size == null && this.color == null && that.color == null
        && this.position == null && that.position == null) {
      return true;
    } else {
      return this.size.equals(that.size)
          && this.color.equals(that.color)
          && this.position.equals(that.position);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.size, this.color, this.position,
        "Ellipse");
  }

  @Override
  public String toString() {
    return "Ellipse";
  }
}
