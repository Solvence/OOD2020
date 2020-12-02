package cs3500.animator.model.shape;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.view.shapevisitor.ShapeVisitor;

/**
 * Represents a generic implementation of a shape.
 */
public abstract class AbstractShape implements Shape {
  protected final Color color;
  protected final Position2D position;
  protected final Dimension2D size;

  /**
   * Constructor with all fields.
   * @param width      width of bounding box of shape.
   * @param height     height of bounding box of shape.
   * @param color      Color of shape.
   * @param position   Position of top left corner of shapes bounding box.
   * @throws IllegalArgumentException if dimensions are negative or fields are null
   */
  AbstractShape(int width, int height, Color color, Position2D position) {
    if (width < 0 || height < 0 || color == null || position == null) {
      throw new IllegalArgumentException("can't have negative dimensions or null values");
    }
    this.size = new Dimension2D(width, height);
    this.color = color;
    this.position = position;
  }

  /**
   * Default Constructor.
   */
  AbstractShape() {
    this.size = null;
    this.color = null;
    this.position = null;
  }

  public abstract Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException;

  @Override
  public Position2D getPosition() {
    if (this.position == null) {
      return null;
    }
    return new Position2D(this.position);
  }

  @Override
  public Color getColor() {
    if (this.color == null) {
      return null;
    }
    return new Color(this.color);
  }

  @Override
  public Dimension2D getSize() {
    if (this.size == null) {
      return null;
    }
    return new Dimension2D(this.size);
  }

  @Override
  public abstract <R> R accept(ShapeVisitor<R> visitor);

  public abstract int hashCode();

  public abstract boolean equals(Object other);

  public abstract String toString();
}
