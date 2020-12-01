import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.animator.controller.actionlistener.AnimationActionListener;
import cs3500.animator.controller.actionlistener.InteractiveActionListener;
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
}
