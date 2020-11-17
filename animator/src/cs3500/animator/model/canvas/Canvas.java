package cs3500.animator.model.canvas;

import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;

/**
 * Represents the physical components of a canvas for which shapes can be drawn on.
 */
public class Canvas {

  private Dimension2D size;
  private Position2D pos;


  /**
   * Default Constructor.
   */
  public Canvas() {
    this.size = null;
    this.pos = null;
  }

  /**
   * Set the fields of this Canvas if possible.
   *
   * @param size init dimensions of canvas
   * @param pos  init pos of top left corner of canvas
   * @throws IllegalArgumentException if either input is null
   * @throws IllegalStateException    if fields have already been initialized
   */
  public void initFields(Dimension2D size, Position2D pos)
      throws IllegalArgumentException, IllegalStateException {
    if (size == null || pos == null) {
      throw new IllegalArgumentException("Invalid fields");
    } else if (this.size != null || this.pos != null) {
      throw new IllegalStateException("Cant reinitialize fields of a stateful canvas");
    }
    this.pos = pos;
    this.size = size;
  }

  /**
   * Get a copy of the size of this canvas if it has been initialized.
   *
   * @return a copy of the size of this canvas if it has been initialized
   * @throws IllegalStateException if fields has not been initialized
   */
  public Dimension2D getSize() throws IllegalStateException {
    if (this.size == null) {
      throw new IllegalStateException("Size doesn't exist");
    }
    return new Dimension2D(this.size);
  }

  /**
   * Get a copy of the position of this canvas if it has been initialized.
   *
   * @return a copy of the position of this canvas if it has been initialized
   * @throws IllegalStateException if field has not been initialized
   */
  public Position2D getPosition() throws IllegalStateException {
    if (this.pos == null) {
      throw new IllegalStateException("Position doesn't exist");
    }
    return new Position2D(this.pos);
  }
}
