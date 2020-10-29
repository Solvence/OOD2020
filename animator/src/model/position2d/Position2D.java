package model.position2d;

import java.util.Objects;

/**
 * This class represents a 2D position
 */
public final class Position2D {
  private final int  x;
  private final int y;

  /**
   * Initialize this object to the specified position
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor
   */
  public Position2D(Position2D v) {
    this(v.x, v.y);
  }



  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }


  @Override
  public String toString() {
    return String.format("(%d, %d)", this.x, this.y);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return (this.x == that.x) && (this.y == that.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
