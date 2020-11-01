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
 * Represents an Ellipse Shape.
 */
public class Ellipse implements Shape {
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
    if (xRad < 0 || yRad < 0 || color == null || position == null) {
      throw new IllegalArgumentException("can't have negative dimensions or null values");
    }
    this.xRad = xRad;
    this.yRad = yRad;
    this.color = color;
    this.position = position;
  }

  @Override
  public Shape build(Position2D position, Color color, Dimension2D size)
      throws IllegalArgumentException {
    return new Ellipse(size.getXDir(), size.getYDir(), color, position);
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

  /**
   * An Ellipse is equal to an Object if the object is an Ellipse and has the same dimensions,
   * color, and position as this Ellipse
   * @param other - the other Object being compared with this Ellipse
   * @return - true if this Ellipse is equal to the Object, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof Ellipse
        && this.xRad == ((Ellipse) other).getSize().getXDir()
        && this.yRad == ((Ellipse) other).getSize().getYDir()
        && this.color.equals(((Ellipse) other).getColor())
        && this.position.equals(((Ellipse) other).getPosition());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.xRad, this.yRad, this.color, this.position);
  }
}
