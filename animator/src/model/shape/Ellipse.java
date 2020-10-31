package model.shape;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dimension2D.Dimension2D;
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
  public Ellipse(int xRad, int yRad, Color color, Position2D position) {
    if (xRad < 0 || yRad < 0) {
      throw new IllegalArgumentException("can't have negative dimensions");
    }
    this.xRad = xRad;
    this.yRad = yRad;
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
    return new Dimension2D(this.xRad, this.yRad);
  }
}
