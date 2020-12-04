package cs3500.animator.controller;

import cs3500.animator.view.SuperView;
import cs3500.model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Class to represent a controller. This class contains methods to perform specific actions to the
 * view based on mouse events from the user. It is completely separate from the model.
 */
public class Controller implements IController, ActionListener {

  //private Model model;
  private SuperView view;
  public boolean pause;
  public boolean start;
  public boolean restart;
  public boolean loop;

  /**
   * Constructor to create an instance of a controller.
   *
   * @param model an animator Model
   * @param view  a view of type SuperView
   * @throws IOException throws an IOException
   */
  public Controller(Model model, SuperView view) throws IOException {
    //this.model = model;
    this.view = view;
    this.pause = false;
    this.start = false;
    this.restart = false;
    this.loop = false;
    this.view.setButtonListener(this);
    this.view.setTextListener(this);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("speed")) {
      String newSpeed = view.getSpeedChange();
      int newSpeedInt = Integer.parseInt(newSpeed);
      view.changeTempo(newSpeedInt);
      view.setTick();
      try {
        view.display();
        view.getTimer().start();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    } else if (e.getActionCommand().equals("start")) {
      view.goAnimation();
    } else if (e.getActionCommand().equals("pause")) {
      if (this.pause) {
        view.goAnimation();
      } else {
        view.stop();
      }
      pause = !pause;
    } else if (e.getActionCommand().equals("restart")) {
      view.setTick();
      view.goAnimation();
    } else if (e.getActionCommand().equals("loop")) {
      view.setLoopMode(view.getLoopButton().isSelected());
    }
  }
}
