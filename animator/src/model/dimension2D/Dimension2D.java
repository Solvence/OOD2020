package model.dimension2D;

import java.util.Objects;

/**
 * This class represents a 2D shape's dimensions (bounding box of the shape)
 */
public final class Dimension2D {
  private final int xDir;
  private final int yDir;

  /**
   * Initialize this dimension object.
   * INVARIANT: xDir and yDir cannot be negative
   */
  public Dimension2D(int xDir, int yDir) {
    if (xDir < 0 || yDir < 0) {
      throw new IllegalArgumentException("Dimensions can't be negative");
    }
    this.xDir = xDir;
    this.yDir = yDir;
  }

  /**
   * Copy constructor.
   */
  public Dimension2D(Dimension2D dm) {
    this(dm.xDir, dm.yDir);
  }

  /**
   * Computes the dimension in the x direction
   * @return - the dimension in the x direction
   */
  public int getXDir() {
    return xDir;
  }

  /**
   * Computes the dimension in the y direction
   * @return - the dimension in the y direction
   */
  public int getYDir() {
    return yDir;
  }


  @Override
  public String toString() {
    return String.format("(%d, %d)", this.xDir, this.yDir);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Dimension2D)) {
      return false;
    }

    Dimension2D that = (Dimension2D) a;

    return (this.xDir == that.xDir) && (this.yDir == that.yDir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.xDir, this.yDir, "Dimension2D");
  }
}
