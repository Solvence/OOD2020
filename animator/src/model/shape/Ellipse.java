package model.shape;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.position2d.Position2D;

/**
 * Represents an Ellipse Shape.
 */
class Ellipse implements Shape {
  final private int xRad;
  final private int yRad;
  private final Color color;
  private final Position2D position;


  /**
   * Construct an ellipse.
   * @param xRad       Radius in x direction
   * @param yRad       Radius in y direction
   * @param color      Color of ellipse
   * @param position   Position of ellipse
   */
  Ellipse(int xRad, int yRad, Color color, Position2D position) {
    if (xRad < 0 || yRad < 0) {
      throw new IllegalArgumentException("can't have negative dimensions");
    }
    this.xRad = xRad;
    this.yRad = yRad;
    this.color = color;
    this.position = position;
  }

  /**
   * A Ellipse's dimensions are invalid if the size does not specify "x-radius" and "y-radius"
   * and nothing else
   */
  @Override
  public Shape build(Position2D position, Color color, Map<String, Integer> size)
      throws IllegalArgumentException {
    if (size.size() != 2 || size.get("x-radius") == null || size.get("y-radius") == null) {
      throw new IllegalArgumentException("invalid dimensions");
    }
    return new Rectangle(size.get("x-radius"), size.get("y-radius"), color, position);
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
    size.put("x-radius", this.xRad);
    size.put("y-radius", this.yRad);
    return size;
  }
}
