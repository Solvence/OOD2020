package model.shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.dimension2D.Dimension2D;
import model.position2d.Position2D;

/**
 * Represents a Rectangle Shape.
 */
public class Rectangle implements Shape {

  private final int width;
  private final int height;
  private final Color color;
  private final Position2D position;

  /**
   * Constructs a Rectangle.
   * INVARIANT: width and height cannot be negative, and color and position cannot be null
   * @param width    width of rectangle
   * @param height   height of rectangle
   * @param color    color of rectangle
   * @param position position of rectangle
   */
  public Rectangle(int width, int height, Color color, Position2D position) {
    if (width < 0 || height < 0 || color == null || position == null) {
      throw new IllegalArgumentException("can't have negative dimensions or null inputs");
    }
    this.width = width;
    this.height = height;
    this.color = color;
    this.position = position;
  }

  @Override
  public Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException {
    return new Rectangle(size.getXDir(), size.getYDir(), color, position);
  }

  @Override
  public Position2D getPosition() {
    return new Position2D(this.position);
  }

  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }

  @Override
  public Dimension2D getSize() {
    return new Dimension2D(this.width, this.height);
  }

  /**
   * An Rectangle is equal to an Object if the object is an Rectangle and has the same dimensions,
   * color, and position as this Rectangle
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
