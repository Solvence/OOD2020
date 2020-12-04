package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 * A Mock of our interactive view. This Mock contains all the methods that are called on the view in
 * the controller.
 */
public class MockView implements SuperView {

  Appendable ap;
  public JButton mockStartButton;
  public JButton mockPauseButton;
  public JButton mockRestartButton;
  public JCheckBox mockLoopButton;
  public JTextField mockchangeSpeed;


  /**
   * Constructor for Mockview. Takes in an appendable.
   *
   * @param ap a log
   */
  public MockView(Appendable ap) {
    this.ap = ap;
    mockStartButton = new JButton();
    mockPauseButton = new JButton();
    mockRestartButton = new JButton();
    mockLoopButton = new JCheckBox();
    mockchangeSpeed = new JTextField();

    this.mockLoopButton = new JCheckBox("Loop?");
    this.mockStartButton.setActionCommand("start");
    this.mockchangeSpeed.setActionCommand("speed");
    this.mockPauseButton.setActionCommand("pause");
    this.mockRestartButton.setActionCommand("restart");
    this.mockLoopButton.setActionCommand("loop");
  }

  @Override
  public void goAnimation() {
    try {
      ap.append("goAnimation called ");
    } catch (IOException e) {
      //
    }
  }

  @Override
  public void stop() {
    try {
      ap.append("stop called ");
    } catch (IOException e) {
      //
    }
  }


  @Override
  public void refresh() {
    try {
      ap.append("repaint view");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getSpeedChange() {
    try {
      ap.append("getSpeedChange called ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";

  }

  @Override
  public int changeTempo(int newTempo) {
    try {
      ap.append("changeTempo called ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public JButton getStartButton() {
    return mockStartButton;
  }


  @Override
  public JButton getPauseButton() {
    return mockPauseButton;
  }


  @Override
  public JButton getRestartButton() {
    return mockRestartButton;
  }


  @Override
  public JTextField getChangeSpeed() {
    try {
      ap.append("get Change Speed called ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  @Override
  public JCheckBox getLoopButton() {
    try {
      ap.append("get loop Button ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mockLoopButton;
  }

  @Override
  public void setTick() {
    try {
      ap.append("setTick method called ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void setLoopMode(boolean selected) {
    try {
      ap.append("boolean should loop run ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void display() throws IOException {
    ap.append("View is displayed");
  }


  @Override
  public String description() {
    return "";
  }


  @Override
  public String buildSVG() {
    return "";
  }


  @Override
  public Timer getTimer() {
    try {
      ap.append("getTimer called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Timer(0, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          ap.append("new timer");
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });
  }


  @Override
  public void setButtonListener(ActionListener listener) {
    mockStartButton.addActionListener(listener);
    mockPauseButton.addActionListener(listener);
    mockRestartButton.addActionListener(listener);
    mockLoopButton.addActionListener(listener);
  }


  @Override
  public void setTextListener(ActionListener actionListener) {
    mockchangeSpeed.addActionListener(actionListener);
  }

}
