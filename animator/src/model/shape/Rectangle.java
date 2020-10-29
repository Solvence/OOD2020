package model.shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
  Rectangle(int width, int height, Color color, Position2D position) {
    this.width = width;
    this.height = height;
    this.color = color;
    this.position = position;
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
  public List<Integer> getSize() {
    List<Integer> size = new ArrayList<Integer>();
    size.add(this.width);
    size.add(this.height);
    return size;
  }
}
