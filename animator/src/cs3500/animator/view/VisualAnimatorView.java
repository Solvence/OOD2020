package cs3500.animator.view;

import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;

/**
 * Represents a visual frame by frame translation of an animation using JFrame.
 */
public class VisualAnimatorView extends JFrame implements ActiveAnimatorView {
  VisualAnimatorPanel visualPanel;

  /**
   * Default Constructor
   * @param canvasTopLeft top left relative position of canvas
   * @param canvasSize dimensions of canvas
   */
  public VisualAnimatorView(Position2D canvasTopLeft, Dimension2D canvasSize) {
    super();
    this.setTitle("Visual View");
    this.setSize(canvasSize.getXDir(), canvasSize.getYDir());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // visual panel
    this.setLayout(new BorderLayout());
    visualPanel = new VisualAnimatorPanel(canvasTopLeft);
    visualPanel.setPreferredSize(new Dimension(canvasSize.getXDir(), canvasSize.getYDir()));
    this.add(visualPanel, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void render() throws IOException {
    this.repaint();
  }

  @Override
  public double translateToTime(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setShapes(List<Shape> shapes) {
    this.visualPanel.setShapes(shapes);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


}
