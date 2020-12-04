package cs3500.animator.model.color;

import java.util.Objects;

/**
 * Represents a color as an rgb (Red Green Blue) value.
 */
public class Color {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Default Constructor.
   *
   * @param red   red component of rgb Color
   * @param green green component of rgb Color
   * @param blue  blue component of rgb Color
   * @throws IllegalArgumentException if any rgb value are less than zero or greater than 255.
   */
  public Color(int red, int green, int blue) throws IllegalArgumentException {
    if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("invalid rgb values");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Copy constructor.
   *
   * @param other Another color to be copied.
   * @throws NullPointerException if other is null.
   */
  public Color(Color other) throws NullPointerException {
    this(other.getRed(), other.getGreen(), other.getBlue());
  }

  /**
   * gets red component of this colors rgb value.
   *
   * @return red component of this colors rgb value.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * gets green component of this colors rgb value.
   *
   * @return green component of this colors rgb value.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * gets blue component of this colors rgb value.
   *
   * @return blue component of this colors rgb value.
   */
  public int getBlue() {
    return this.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, "Color");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Color)) {
      return false;
    }
    Color other = (Color) obj;
    return (other.red == this.red) && (other.green == this.green) && (other.blue == this.blue);
  }
}
