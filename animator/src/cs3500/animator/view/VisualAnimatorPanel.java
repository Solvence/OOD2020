package cs3500.animator.view;

import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;
import cs3500.animator.view.shapevisitor.VisualShapeVisitor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents the object that draws shapes onto a canvas.
 */
public class VisualAnimatorPanel extends JPanel {
  private List<Shape> shapes;
  private final Position2D topLeftCanvasCorner;

  /**
   * Default Constructor.
   * @param topLeftCanvasCorner top left position of canvas
   */
  public VisualAnimatorPanel(Position2D topLeftCanvasCorner) {
    super();
    this.shapes = new ArrayList<Shape>();
    this.setBackground(Color.white);
    this.topLeftCanvasCorner = topLeftCanvasCorner;
  }

  /**
   * Set the shapes to be draw to the list of given shapes.
   * @param shapes  shapes to be set.
   */
  public void setShapes(List<Shape> shapes) {
    this.shapes = new ArrayList<Shape>(shapes);
  }

  /**
   * Paint all shapes in this objects list of shapes onto given canvas.
   * @param g canvas
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(topLeftCanvasCorner.getX(), topLeftCanvasCorner.getY());
    AffineTransform originalTransform = g2d.getTransform();
    for (Shape s: shapes) {
      new VisualShapeVisitor(g2d).apply(s);
      // visitor
    }
    g2d.setTransform(originalTransform);
  }
}
