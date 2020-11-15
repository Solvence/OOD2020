package cs3500.animator.model.shapevisitor;

import cs3500.animator.model.color.Color;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;

/**
 * Represents a function object to render a header of a Shape's SVG component.
 */
public class RenderSVGHeader implements ShapeVisitor<String> {

  private final String name;

  /**
   * constructs a function object to render the SVG header.
   *
   * @param name - the name corresponding to the shape being rendered
   */
  public RenderSVGHeader(String name) {
    this.name = name;
  }

  @Override
  public String visitEllipse(Ellipse e) {
    Position2D pos = e.getPosition();
    Dimension2D dimension = e.getSize();
    Color color = e.getColor();

    return String.format("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" "
            + "ry=\"%d\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
        this.name, pos.getX(), pos.getY(), dimension.getXDir(), dimension.getYDir(),
        color.getRed(), color.getGreen(), color.getBlue());
  }

  @Override
  public String visitRectangle(Rectangle r) {
    Position2D pos = r.getPosition();
    Dimension2D dimension = r.getSize();
    Color color = r.getColor();

    return String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" "
            + "height=\"%d\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
        this.name, pos.getX(), pos.getY(), dimension.getXDir(), dimension.getYDir(),
        color.getRed(), color.getGreen(), color.getBlue());
  }

  @Override
  public String apply(Shape shape) {
    return shape.accept(this);
  }
}
