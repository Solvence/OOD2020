package cs3500.model;

import java.awt.Color;

/**
 * Represents the state of shape at any tick of the progression of the animation.
 * This class includes all the fields that a shape has including the form, x and y position
 * width and height, and color.
 */
public class StateofShape {

  private final String form;
  private final int x;
  private final int y;
  private final int w;
  private final int h;
  private final Color c;

  /**
   * Constructor of state of shape.
   *
   * @param form form shape
   * @param x    x-position of the shape
   * @param y    y-position of the shape
   * @param w    w-width of the shape
   * @param h    h-heiht of the shape
   * @param c    c-color of the shape
   */
  public StateofShape(String form, int x, int y, int w, int h, Color c) {
    this.form = form;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.c = c;
  }

  /**
   * Gets the form or type of the shape.
   *
   * @return the form of the shape
   */
  public String getForm() {
    return form;
  }

  /**
   * Gets the x-position of the shape.
   *
   * @return the x-position of the shape
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y-position of the shape.
   *
   * @return the y-position of the shape
   */
  public int getY() {
    return y;
  }

  /**
   * Gets the width of the shape.
   *
   * @return the width of the shape
   */
  public int getW() {
    return w;
  }

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape
   */
  public int getH() {
    return h;
  }

  /**
   * Gets the color of the shape.
   *
   * @return the color of the shape
   */
  public Color getC() {
    return c;
  }

}
