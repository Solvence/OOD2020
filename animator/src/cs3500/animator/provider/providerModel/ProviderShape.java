package cs3500.animator.provider.providerModel;

import java.awt.Color;
import java.util.List;

/**
 * Interface to represent a shape. Has methods to return all the fields of a shape,
 * to add motions to a shape, get it's state at a certain tick, and a toString.
 */
public interface ProviderShape {

  /**
   * State of a shape at a given tick, and returns the state of the shape at a given moment
   * throughout the animation.
   *
   * @param tick given teick
   * @return a state of a shape at a given tick
   */
  StateofShape getStateofShapeTick(int tick);

  /**
   * Adds a motion to a shape. Checks to see whether the motion conflicts with any other motions.
   *
   * @param newMotion the motion to be added
   * @throws IllegalArgumentException if the motion conflicts with any of the other motions that a
   *                                  shape has (if it overlaps, starts or ends at the same time as
   *                                  another motion, etc)
   */
  void addMotionsToShape(cs3500.model.MotionAnimation newMotion);

  /**
   * Getter method to return the color of a shape.
   *
   * @return color of shape
   */
  Color getShapeColor();

  /**
   * Getter method to return the height of a shape.
   *
   * @return height of shape
   */
  int getShapeHeight();

  /**
   * Getter method to return the width if a shape.
   *
   * @return width of shape
   */
  int getShapeWidth();

  /**
   * Getter method to return the position of a shape.
   *
   * @return
   */
  Position2D getShapePos();

  /**
   * Getter method to return the list of all of a shapes motions.
   *
   * @return a list of a shapes motions
   */
  List<MotionAnimation> getMotionsofShape();

  /**
   * Getter method to return the name of a shape.
   *
   * @return the shape's name
   */
  String getName();

  /**
   * Determines the type of the shape.
   *
   * @return the type of the shape
   */
  String getForm();


  /**
   * Returns a string visualization of a shape. Displays values for all the fields of a shape except
   * for its motions.
   *
   * @return a string visualization of a shape
   */
  String description();

}
