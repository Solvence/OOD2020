package model.shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
   * @param width    width of rectangle
   * @param height   height of rectangle
   * @param color    color of rectangle
   * @param position position of rectangle
   */
  public Rectangle(int width, int height, Color color, Position2D position) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("can't have negative dimensions");
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
}
