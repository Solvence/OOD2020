package cs3500.animator.view.shapevisitor;

import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a function object to render a shape onto a JFrame Canvas.
 */
public class VisualShapeVisitor implements ShapeVisitor<Void> {

  private final Graphics2D g2d;

  /**
   * Default Constructor.
   * @param g2d  2D graphic canvas
   */
  public VisualShapeVisitor(Graphics2D g2d) {
    this.g2d = g2d;
  }


  @Override
  public Void visitEllipse(Ellipse e) {
    if (cantDraw(e)) {
      return null;
    }
    cs3500.animator.model.color.Color ellipseColor = e.getColor();
    Position2D ellipsePosition = e.getPosition();
    Dimension2D ellipseSize = e.getSize();

    g2d.setColor(new Color(ellipseColor.getRed(), ellipseColor.getGreen(), ellipseColor.getBlue()));
    g2d.fillOval(ellipsePosition.getX(), ellipsePosition.getY(), ellipseSize.getXDir(),
        ellipseSize.getYDir());
    return null;
  }

  @Override
  public Void visitRectangle(Rectangle r) {
    if (cantDraw(r)) {
      return null;
    }
    cs3500.animator.model.color.Color rectColor = r.getColor();
    Position2D rectPosition = r.getPosition();
    Dimension2D rectSize = r.getSize();

    g2d.setColor(new Color(rectColor.getRed(), rectColor.getGreen(), rectColor.getBlue()));
    g2d.fillRect(rectPosition.getX(), rectPosition.getY(), rectSize.getXDir(),
        rectSize.getYDir());
    return null;
  }

  @Override
  public Void apply(Shape s) {
    return s.accept(this);
  }

  private boolean cantDraw(Shape s) {
    return (s.getSize() == null || s.getColor() == null || s.getPosition() == null);
  }

}
