package cs3500.animator.view;

import cs3500.model.AnimatorModel;
import cs3500.model.Model;
import cs3500.model.Shape2D;
import cs3500.model.StateofShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * This panel represents where the animation will be drawn.
 */
public class AnimationPanel extends JPanel { //implements ActionListener {

  public Model model;
  private int tempo;
  List<StateofShape> listofStates;

  /**
   * A default no argument constructor for an animation panel.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.model = new AnimatorModel(700, 500, new ArrayList<Shape2D>());
    setSize(700, 500);
    this.tempo = tempo;
    listofStates = new ArrayList<>();
    setLayout(new BorderLayout());

  }

  /**
   * Overriding the getPreferredSize method to have the dimensions provided by the model.
   *
   * @return a new Dimension with the model's dimensions
   */
  @Override
  public Dimension getPreferredSize() {
    Dimension d = super.getPreferredSize();
    d.width = model.getWidthBoard();
    d.height = model.getHeightBoard();
    return d;
  }


  /**
   * Updates the list of shapes to be the list of shape states.
   *
   * @param shapesState the state of state
   */
  public void updateShapes(List<StateofShape> shapesState) {
    listofStates = shapesState;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (StateofShape state : listofStates) {
      try {
        switch (state.getForm()) {
          case ("ellipse"):
            g2d.setColor(state.getC());
            g2d.fillOval(
                state.getX() - model.getX(),
                state.getY() - model.getY(),
                state.getW(),
                state.getH());
            break;
          case ("rectangle"):
            g2d.setColor(state.getC());
            g2d.fillRect(
                state.getX() - model.getX(),
                state.getY() - model.getY(),
                state.getW(),
                state.getH());
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + state.getForm());
        }
      } catch (NullPointerException e) {
        //
      }
    }
  }
}
