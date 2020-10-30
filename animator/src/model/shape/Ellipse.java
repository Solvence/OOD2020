package model.shape;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.position2d.Position2D;

/**
 * Represents an Oval Shape.
 */
class Ellipse implements Shape {
  final private int xRad;
  final private int yRad;
  private final Color color;
  private final Position2D position;


  /**
   * Construct an oval.
   * @param xRad       Radius in x direction
   * @param yRad       Radius in y direction
   * @param color      Color of oval
   * @param position   Position of oval
   */
  Ellipse(int xRad, int yRad, Color color, Position2D position) {
    this.xRad = xRad;
    this.yRad = yRad;
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
  public Map<String, Integer> getSize() {
    Map<String, Integer> size = new HashMap<String, Integer>();
    size.put("x-radius", this.xRad);
    size.put("y-radius", this.yRad);
    return size;
  }
}
