package model.shape;

import model.color.Color;
import model.dimension2d.Dimension2D;
import model.position2d.Position2D;

/**
 * Represents a generic implementation of a shape.
 */
public abstract class AbstractShape implements Shape {
  protected final Color color;
  protected final Position2D position;
  protected final int width;
  protected  final int height;

  /**
   * Default constructor.
   * @param width      width of bounding box of shape.
   * @param height     height of bounding box of shape.
   * @param color      Color of shape.
   * @param position   Position of top left corner of shapes bounding box.
   */
  AbstractShape(int width, int height, Color color, Position2D position) {
    if (width < 0 || height < 0 || color == null || position == null) {
      throw new IllegalArgumentException("can't have negative dimensions or null values");
    }
    this.width = width;
    this.height = height;
    this.color = color;
    this.position = position;
  }

  public abstract Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException;

  @Override
  public Position2D getPosition() {
    return new Position2D(this.position);
  }

  @Override
  public Color getColor() {
    return new Color(this.color);
  }

  @Override
  public Dimension2D getSize() {
    return new Dimension2D(this.width, this.height);
  }

  abstract public int hashCode();

  abstract public boolean equals(Object other);

  abstract public String toString();
}
