package cs3500.animator.provider.providerModel;

import cs3500.animator.model.position2d.*;
import java.awt.Color;

/**
 * A class that represents a singular motion on a shape. It has fields to represent all the shape
 * fields that are changed when a shape is animated.
 */
public interface MotionAnimation {

  /**
   * Outputs a string description of a motion. Displays all the fields of the motion class to
   * represent the state of the shape after a motion.
   *
   * @return a String visualization of a motion
   **/
  String description();

  /**
   * Getter method to return the startTick of a motion.
   *
   * @return startTick
   */
  int getStartTick();

  /**
   * Getter method to return the endTick of a motion.
   *
   * @return endTick
   */
  int getEndTick();

  /**
   * Getter method to return the starting Color.
   *
   * @return starting Color
   */
  Color getStartColor();

  /**
   * Getter method to return the ending Color.
   *
   * @return ending Color
   */
  Color getEndColor();

  /**
   * Getter method to return starting height of shape at beginning of motion.
   *
   * @return returns the start height
   */
  int getStartHeight();

  /**
   * Getter method to return end height of shape at the end of motion.
   *
   * @return
   */
  int getEndHeight();

  /**
   * Getter method to return the starting width of the shape at the beginning of the motion.
   *
   * @return returns the starting Width
   */
  int getStartWidth();

  /**
   * Getter method to return the ending width of the shape at the end of the motion.
   *
   * @return returns the ending width
   */
  int getEndWidth();

  /**
   * Returns the starting position of a shape at beginning of motion.
   *
   * @return returns starting position of shape
   */
  Position2D getStartPosition();

  /**
   * Returns the ending position of a shape at the end of a motion.
   *
   * @return ending position of shape
   */
  Position2D getEndPosition();

  /**
   * Creates the beginning of a string description for a motion.
   *
   * @return a string representation of the beginning state of a motion
   */
  String begDescription();

  /**
   * Creates the ending of a string description for a motion.
   *
   * @return a string representation of the ending state of a motion
   */
  String endDescription();
}
