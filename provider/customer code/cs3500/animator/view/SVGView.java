package cs3500.animator.view;

import cs3500.model.Model;
import cs3500.model.MotionAnimation;
import cs3500.model.Shape2D;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * A class that represents an SVG view of an animation. The methods in this class use string
 * builders to format the data from the model into SVG code.
 */
public class SVGView implements IView {

  private final Model model;
  private FileWriter ap;
  private final int tempo;

  /**
   * Constructor to create an instance of SVG view. This constructor does not
   *
   * @param model the model
   * @param ap    the appendable
   * @param tempo the tempo
   * @throws IllegalArgumentException if any input is invalid (null model, invalid tick, etc).
   */
  public SVGView(Model model, Appendable ap, int tempo) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("null model");
    } else if (tempo < 1) {
      throw new IllegalArgumentException("invalid tempo");
    } else if (ap == null) {
      throw new IllegalArgumentException("null appendable");
    }
    this.model = model;
    try {
      this.ap = new FileWriter("output.svg", true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.tempo = tempo;
  }

  /**
   * Constructor to create an instance of SVG view.
   *
   * @param model the model
   * @param ap    the appendable
   * @param tempo the tempo
   * @throws IllegalArgumentException if any input is invalid (null model, invalid tick, etc).
   */
  public SVGView(Model model, Appendable ap, int tempo, String name) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("null model");
    } else if (tempo < 1) {
      throw new IllegalArgumentException("invalid tempo");
    } else if (ap == null) {
      throw new IllegalArgumentException("null appendable");
    }
    this.model = model;
    try {
      this.ap = new FileWriter(name, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.tempo = tempo;
  }


  @Override
  public void display() throws IOException {
    try {
      String s = this.buildSVG();
      ap.append(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.ap.close();
  }


  @Override
  public String buildSVG() {
    StringBuilder builder = new StringBuilder();
    builder.append("<svg width=\"");
    builder.append(this.model.getWidthBoard());
    builder.append("\" height=\"");
    builder.append(this.model.getHeightBoard());
    builder.append("\"");
    builder.append(
        " xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink= \"http://www.w3.org/1999/xlink\" >\n\n");

    for (Shape2D shape : this.model.getShapes()) {
      builder.append(buildShape(shape));
    }

    builder.append("</svg>");

    return builder.toString();
  }

  /**
   * Creates the SVG String associated with a shape.
   *
   * @param shape the shape added to the animation
   * @return a string representing SVG code for a shape
   */
  private String buildShape(Shape2D shape) {
    StringBuilder builder = new StringBuilder();
    if (shape.getForm().equals("rectangle")) {
      builder.append("<rect id=");
    } else if (shape.getForm().equals("ellipse")) {
      builder.append("<ellipse id=");
    }
    try {
      builder.append("\"").append(shape.getName()).append("\" ");
      builder.append("x=\"").append(shape.getMotionsofShape().get(0).getStartPosition().getX())
          .append("\" ");
      builder.append("y=\"").append(shape.getMotionsofShape().get(0).getStartPosition().getY())
          .append("\" ");
      builder.append("width=\"").append(shape.getShapeWidth()).append("\" ");
      builder.append("height=\"").append(shape.getShapeHeight()).append("\" ");
      builder.append("fill=\"rgb(")
          .append(shape.getMotionsofShape().get(0).getStartColor().getRed()).append(",")
          .append(shape.getMotionsofShape().get(0).getStartColor().getGreen()).append(",")
          .append(shape.getMotionsofShape().get(0).getStartColor().getBlue()).append(")\" ");
      builder.append("visibility=\"visible\" >\n");
      for (MotionAnimation motion : shape.getMotionsofShape()) {
        builder.append(buildMotion(motion, shape));
      }
    } catch (NullPointerException n) {
      builder.append("\"").append(shape.getName()).append("\" ");
      builder.append("x=\"0\" ");
      builder.append("y=\"0\" ");
      builder.append("width=\"0\" ");
      builder.append("height=\"0\" ");
      builder.append("fill=\"rgb(0,0,0)\" ");
      builder.append("visibility=\"visible\" >\n");
    }

    if (shape.getForm().equals("rectangle")) {
      builder.append("</rect>\n\n");
    } else if (shape.getForm().equals("ellipse")) {
      builder.append("</ellipse>\n\n");
    }
    return builder.toString();
  }

  /**
   * Creates the string representing a motion in SVG format.
   *
   * @param motion the motion to be analyzed
   * @param shape  the shape with that motion
   * @return the string representing a motion
   */
  private String buildMotion(MotionAnimation motion, Shape2D shape) {
    StringBuilder builder = new StringBuilder();

    if (motion.getEndPosition().getX() != motion.getStartPosition().getX()) {
      if (shape.getForm().equals("ellipse")) {
        builder.append(createAttribute("cx", motion,
            motion.getStartPosition().getX(), motion.getEndPosition().getX()));
      } else {
        builder.append(createAttribute("x", motion,
            motion.getStartPosition().getX(), motion.getEndPosition().getX()));
      }
    }
    if (motion.getEndPosition().getY() != motion.getStartPosition().getY()) {
      if (shape.getForm().equals("ellipse")) {
        builder.append(createAttribute("cy", motion,
            motion.getStartPosition().getY(), motion.getEndPosition().getY()));
      } else {
        builder.append(createAttribute("y", motion,
            motion.getStartPosition().getY(), motion.getEndPosition().getY()));
      }
    }
    if (motion.getStartWidth() != motion.getEndWidth()) {
      builder.append(createAttribute("width", motion, (double) motion.getStartWidth(),
          (double) motion.getEndWidth()));
    }
    if (motion.getStartHeight() != motion.getEndHeight()) {
      builder.append(createAttribute("height", motion, (double) motion.getStartHeight(),
          (double) motion.getEndHeight()));
    }
    if (motion.getStartColor() == motion.getEndColor()) {
      builder.append(createAttributeColor("fill", motion, motion.getStartColor(),
          motion.getEndColor()));
    }
    return builder.toString();
  }

  /**
   * Creates the SVG string representing an animation given the type of animation thats occurring
   * and the starting and ending attributes of that animation.
   *
   * @param str    the type of attribute that is being animated
   * @param motion the motion occurring
   * @param start  the starting state of the motion
   * @param end    the ending state of the motion
   * @return an SVG string representation of an attribute
   */
  private String createAttribute(String str, MotionAnimation motion, Double start,
      Double end) {
    StringBuilder builder = new StringBuilder();
    builder.append("     <animate attributeType=\"xml\" ");
    builder.append("begin=\"").append((double) (motion.getStartTick() / tempo) * 1000)
        .append("ms\" ");
    builder.append("dur=\"").append((double) (motion.getEndTick() / tempo) * 1000).append("ms\" ");

    builder.append("attributeName=\"").append(str).append("\" ");

    builder.append("from=\"").append(start).append("\" ");
    builder.append("to=\"").append(end).append("\" ");
    builder.append("fill=\"freeze\" />");
    builder.append("\n");

    return builder.toString();
  }

  /**
   * Creates the SVG string representing an animation in which the color of the shape is changed.
   *
   * @param str    the type of attribute that is being animated
   * @param motion the motion occurring
   * @param start  the starting state of the motion
   * @param end    the ending state of the motion
   * @return an SVG string representation of an attribute
   */
  private String createAttributeColor(String str, MotionAnimation motion, Color start, Color end) {
    StringBuilder builder = new StringBuilder();
    builder.append("     <animate attributeType=\"xml\" ");
    builder.append("begin=\"").append((double) (motion.getStartTick() / tempo) * 1000)
        .append("ms\" ");
    builder.append("dur=\"").append((double) (motion.getEndTick() / tempo) * 1000).append("ms\" ");
    builder.append("attributeName=\"").append(str).append("\" ");

    builder.append("from=\"rbg(").append(start.getRed()).append(",").append(start.getBlue())
        .append(",").append(start.getGreen()).append(")\" ");
    builder.append("to=\"rbg(").append(end.getRed()).append(",").append(end.getBlue()).append(",")
        .append(end.getGreen()).append(")\" ");
    builder.append("fill=\"freeze\" />");
    builder.append("\n");

    return builder.toString();
  }


  @Override
  public String description() {
    throw new UnsupportedOperationException(
        "Description Method is only used for Textual View not SVG View");
  }


  @Override
  public void setButtonListener(ActionListener mouseAdapter) {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public void setTextListener(ActionListener actionListener) {
    throw new UnsupportedOperationException("This method is for the interactive View");

  }


  @Override
  public String getSpeedChange() {
    throw new UnsupportedOperationException("This method is for the interactive View");

  }

  @Override
  public int changeTempo(int newTempo) {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getStartButton() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getPauseButton() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JButton getRestartButton() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JTextField getChangeSpeed() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public JCheckBox getLoopButton() {
    throw new UnsupportedOperationException("not supported by the textual view.");
  }


  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("unsupported for SVG View");
  }


  @Override
  public void goAnimation() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void stop() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void setTick() {
    throw new UnsupportedOperationException("this method is for the interactive view");
  }


  @Override
  public void refresh() {
    throw new UnsupportedOperationException("this method is for the visual view");
  }


  @Override
  public void setLoopMode(boolean selected) {
    throw new UnsupportedOperationException("this method is for the visual view");
  }
}
