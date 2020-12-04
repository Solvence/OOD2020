import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.actionlistener.AnimationActionListener;
import cs3500.animator.controller.actionlistener.InteractiveActionListener;
import java.awt.event.ActionEvent;
import org.junit.Test;

/**
 * Tests for the InteractiveActionListener class.
 */
public class InteractiveActionListenerTest {

  /**
   * Tests that the listener's constructor properly throws an exception when it's supposed to.
   */
  @Test
  public void testConstructorException() {
    try {
      AnimationActionListener listener = new InteractiveActionListener(0.0, 10);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      AnimationActionListener listener = new InteractiveActionListener(-2.0, 10);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      AnimationActionListener listener = new InteractiveActionListener(5.0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }

    try {
      AnimationActionListener listener = new InteractiveActionListener(5.0, -2);
      fail();
    } catch (IllegalArgumentException e) {
      // pass!
    }
  }

  /**
   * Tests that an unchanged listener properly returns the beginning tick when getTick() is called.
   */
  @Test
  public void testGetTick() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);
    assertEquals(listener.getTick(), 1);
  }

  /**
   * Tests that an unchanged listener properly returns the beginning tick rate when getTickRate() is
   * called.
   */
  @Test
  public void testGetTickRate() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);
    assertEquals(listener.getTickRate(), 5.0, .0001);
  }

  /**
   * Tests that an unpaused nonlooping listener properly increments the tick when incrementTick() is
   * called.
   */
  @Test
  public void testIncrementTick() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Play Button"));

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 3);

    listener.incrementTick();

    assertEquals(listener.getTick(), 4);
  }

  /**
   * tests that the pause/play functionality for actionPerformed functions properly.
   */
  @Test
  public void testPausePlay() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Play Button"));

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Pause Button"));

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Pause Button"));

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Play Button"));

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 3);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Play Button"));

    assertEquals(listener.getTick(), 3);

    listener.incrementTick();

    assertEquals(listener.getTick(), 4);
  }

  /**
   * tests that the speed up/down functionality for actionPerformed functions properly.
   */
  @Test
  public void testSpeedUpAndDown() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);

    assertEquals(listener.getTickRate(), 5.0, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Speed Up Button"));

    assertEquals(listener.getTickRate(), 10.0, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Speed Up Button"));

    assertEquals(listener.getTickRate(), 20.0, .0001);

    listener.incrementTick();

    assertEquals(listener.getTickRate(), 20.0, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Slow Down Button"));

    assertEquals(listener.getTickRate(), 10.0, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Slow Down Button"));

    assertEquals(listener.getTickRate(), 5.0, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Slow Down Button"));

    assertEquals(listener.getTickRate(), 2.5, .0001);

    listener.actionPerformed(new ActionEvent("Filler", 2, "Slow Down Button"));

    assertEquals(listener.getTickRate(), 1.25, .0001);
  }

  /**
   * Tests that the reset functionality for actionPerformed functions properly.
   */
  @Test
  public void testReset() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 100);

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Play Button"));

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 3);

    listener.incrementTick();

    assertEquals(listener.getTick(), 4);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Reset Button"));

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Reset Button"));

    assertEquals(listener.getTick(), 1);
  }

  /**
   * Tests the the looping functionality for the listener functions properly.
   */
  @Test
  public void testLooping() {
    AnimationActionListener listener = new InteractiveActionListener(5.0, 4);

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Play Button"));

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 3);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Loop Button"));

    assertEquals(listener.getTick(), 3);

    listener.incrementTick();

    assertEquals(listener.getTick(), 4);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Loop Button"));

    assertEquals(listener.getTick(), 1);

    listener.incrementTick();

    assertEquals(listener.getTick(), 2);

    listener.incrementTick();

    assertEquals(listener.getTick(), 3);

    listener.incrementTick();

    assertEquals(listener.getTick(), 4);

    listener.incrementTick();

    assertEquals(listener.getTick(), 5);

    listener.incrementTick();

    assertEquals(listener.getTick(), 6);

    listener.actionPerformed(new ActionEvent("Filler", 1, "Loop Button"));

    assertEquals(listener.getTick(), 6);

    listener.incrementTick();

    assertEquals(listener.getTick(), 1);
  }
}
