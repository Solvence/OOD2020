package cs3500.animator.provider.providerView;


import cs3500.animator.provider.providerModel.Model;
import cs3500.animator.provider.providerModel.StateofShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

/**
 * Implementation of the View Interface, uses Java Swing in order to draw the results of an
 * animation. Displays an error message using a pop-up dialog box.
 */
public class VisualView extends JFrame implements IView {

  protected final Model model;
  protected AnimationPanel animationPanel;
  protected int tick;
  protected Timer timer;
  protected int tempo;
  protected boolean loopMode;

  /**
   * Public constructor.
   * @param model represents a model
   * @param tempo represents a tempo
   */
  public VisualView(Model model, int tempo) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("The model provided is null");
    }
    if (tempo < 1) {
      throw new IllegalArgumentException(
          "The tempo is less than 1, and the speed cannot be less than 1tick/second");
    }
    this.model = model;
    this.animationPanel = new AnimationPanel();

    //this.g = this.animationPanel.getGraphics();
    this.tempo = tempo;
    this.loopMode = false;
  }

  /**
   * Initialization of everything needed to create the frame.
   */
  protected void initialization() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JScrollPane scrollPane = new JScrollPane(this.animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    this.add(scrollPane, BorderLayout.CENTER);
    this.setTitle("Animationsssss🎃🎢🎪🎿⚽🎙🥁");
    this.setSize(new Dimension(model.getWidthBoard(), model.getHeightBoard()));

    this.pack();
    this.setVisible(true);
  }


  @Override
  public void display() {
    //initialization of
    this.initialization();
    timer = new Timer(1000 / tempo, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateState();
        tick++;
        if (tick >= model.getLastTick()) {
          timer.stop();
        }
        repaint();
      }
    });
    timer.start();
  }


  @Override
  public void setLoopMode(boolean selected) {
    loopMode = selected;
  }


  @Override
  public String description() {
    throw new UnsupportedOperationException(
        "Description Method is only used for Textual View not Visual View");
  }


  @Override
  public String buildSVG() {
    throw new UnsupportedOperationException("BuildSVG Method is unsupported for the visual view");
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


  public int changeTempo(int newTempo) {
    return this.tempo = newTempo;
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
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public Timer getTimer() {
    return timer;
  }


  @Override
  public void goAnimation() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public void stop() {
    throw new UnsupportedOperationException("This method is for the interactive View");
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Updates the shape that are used by the panel and repaints the screen.
   */
  protected void updateState() {
    List<StateofShape> shapesState = model.stateofShapesTick(tick);
    this.animationPanel.updateShapes(shapesState);
  }

  /**
   * Sets the tick to be 0.
   */
  public void setTick() {
    tick = 0;
  }
}
