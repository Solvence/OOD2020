package cs3500.animator.model;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.color.Color;
import java.util.List;
import cs3500.animator.model.dimension2d.Dimension2D;
import cs3500.animator.model.position2d.Position2D;
import cs3500.animator.model.shape.Shape;

/**
 * Represents the cs3500.animator.model backend component for an animation application that will
 * draw an animation according to how it is described in text. The cs3500.animator.model itself
 * supports operations and observations that a user who is running an animation will likely want to
 * perform. The cs3500.animator.model then processes these operations and maintains the state of the
 * animation.
 */
public interface AnimatorModel {

  /**
   * Adds a new shape with a given name to our animator.
   *
   * @param name - name of the shape
   * @param s    - type of shape
   * @throws IllegalArgumentException if s is null or if shape with given name already exists.
   */
  void create(String name, Shape s) throws IllegalStateException;

  /**
   * Returns a state of a shape with a given name at the given time.
   *
   * @param time time frame to be rendered
   * @param name name of shape to get state of.
   * @return a version of a shape after a given time of animation has elapsed.
   * @throws IllegalArgumentException if time is negative or if shape with given name doesn't exist
   * @throws IllegalStateException    if canvas is not initialized
   */
  Shape getShapeAt(String name, int time) throws IllegalArgumentException, IllegalStateException;

  /**
   * Add a motion to a shape with a given name that transitions the state of the shape over a given
   * time interval.
   *
   * @param name          Name of Shape
   * @param startTime     Start of time interval
   * @param endTime       End of time interval
   * @param startPosition Start position of shape at beginning of motion
   * @param endPosition   End position of shape at end of motion
   * @param startColor    Start Color of shape at beginning of motion
   * @param endColor      End Color of shape at end of motion
   * @param startSize     Start Size of shape at beginning of motion
   * @param endSize       End Size of shape at end of motion
   * @throws IllegalArgumentException if no shape with given name exits or if any field is null or
   *                                  if time interval is invalid.
   * @throws IllegalStateException    if the canvas has not yet been initialized
   */
  void addMotion(String name, int startTime, int endTime, Position2D startPosition,
      Position2D endPosition, Color startColor, Color endColor, Dimension2D startSize,
      Dimension2D endSize) throws IllegalArgumentException, IllegalStateException;

  /**
   * Get all the names of shapes currently in our animation.
   *
   * @return all the names of shapes currently in our animation.
   * @throws IllegalStateException if the canvas has not yet been initialized
   */
  List<String> getAllShapeName() throws IllegalStateException;

  /**
   * Get all the commands to be applied on a animated shape with a given name.
   *
   * @param name name of shape
   * @return all commands for shape with given name
   * @throws IllegalArgumentException if animated shape with given name doesn't exist.
   * @throws IllegalStateException    if the canvas has not yet been initialized
   */
  List<AnimatedObjectCommand> getCommandsForShape(String name) throws IllegalArgumentException,
      IllegalStateException;


  /**
   * Initialize the fields of the models canvas, if they haven't have been already.
   *
   * @param pos  Position of top left corner of canvas
   * @param size Dimensions of canvas.
   * @throws IllegalArgumentException either field is null
   * @throws IllegalStateException    if canvas has already been initialized.
   */
  void initCanvas(Position2D pos, Dimension2D size) throws IllegalArgumentException,
      IllegalStateException;

  /**
   * Get position of the models canvas.
   *
   * @return position of the models canvas.
   * @throws IllegalStateException if position of canvas hasn't been initialized.
   */
  Position2D getCanvasPosition() throws IllegalStateException;

  /**
   * Get size of the models canvas.
   *
   * @return size of the models canvas.
   * @throws IllegalStateException if size of canvas hasn't been initialized.
   */
  Dimension2D getCanvasSize() throws IllegalStateException;

  /**
   * Removes a shape from this model
   * @param name - the name of the shape to be removed
   * @throws IllegalArgumentException - if the specified Shape name doesn't exist
   */
  void remove(String name) throws IllegalArgumentException;


}
