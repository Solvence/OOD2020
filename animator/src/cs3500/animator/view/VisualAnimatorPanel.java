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

  public void setShapes(List<Shape> shapes) {
    this.shapes = new ArrayList<Shape>(shapes);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(topLeftCanvasCorner.getX(), topLeftCanvasCorner.getY());
    AffineTransform originalTransform = g2d.getTransform();
    g2d.scale(1,-1);
    for (Shape s: shapes) {
      new VisualShapeVisitor(g2d).apply(s);
      // visitor
    }
    g2d.setTransform(originalTransform);
  }
}
