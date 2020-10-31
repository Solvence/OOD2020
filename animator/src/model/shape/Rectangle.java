package model.shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  /**
   * A Rectangle's dimensions are invalid if the size does not specify "width" and "height"
   * and nothing else
   */
  @Override
  public Shape build(Position2D position, Color color, Map<String, Integer> size)
      throws IllegalArgumentException {
    if (size.size() != 2 || size.get("x-direction") == null || size.get("y-direction") == null) {
      throw new IllegalArgumentException("invalid dimensions");
    }
    return new Rectangle(size.get("x-direction"), size.get("y-direction"), color, position);
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
  public Map<String, Integer> getSize() {
    Map<String, Integer> size = new HashMap<String, Integer>();
    size.put("x-direction", this.width);
    size.put("y-direction", this.height);
    return size;
  }
}
