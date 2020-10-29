package model.shape;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.position2d.Position2D;

/**
 * Represents an Oval Shape.
 */
class Oval implements Shape {
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
  Oval(int xRad, int yRad, Color color, Position2D position) {
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
  public List<Integer> getSize() {
    List<Integer> size = new ArrayList<Integer>();
    size.add(this.xRad);
    size.add(this.yRad);
    return size;
  }
}
