package cs3500.model;

import java.util.List;

/**
 * A model to represent an animated scene. Has fields for the dimensions of the board, the tick, and
 * the list of shapes in the animation.
 */
public interface Model {

  /**
   * Begins a new animation. Initializes the values of the tick, canvas dimensions, and shapes.
   *
   * @param heightBoard the height of the canvas
   * @param widthBoard  the width of the canvas
   * @param shapes      the list of shapes in the animation
   * @throws IllegalArgumentException if any parameters are null, if any of a shapes motions are
   *                                  invalid, ...
   */
  void startAnimation(int heightBoard, int widthBoard, Shape2D... shapes);

  /**
   * Adds a shape to the animation.
   *
   * @param shape the shape to be added
   */
  void addShapes(Shape2D shape);

  /**
   * Removes a shape from the animation.
   *
   * @param shape the shape to be removed
   * @throws IllegalArgumentException if the shape does not exist in the model
   */
  void removeShapes(Shape2D shape);

  /**
   * Returns the list of all the shapes in the model.
   *
   * @return a list of shapes
   */
  List<Shape2D> getShapes();


  /**
   * Returns the last tick from the list of motions of a shape.
   *
   * @return a tick that is the last tick of the list of motions for a specific shape
   */
  int getLastTick();

  /**
   * Gets the leftMost x.
   *
   * @return leftMost x
   */
  int getX();

  /**
   * Gets the leftmost y.
   *
   * @return leftmost y
   */
  int getY();

  /**
   * Returns the height of the board.
   *
   * @return value of height of the board
   */
  int getHeightBoard();

  /**
   * Returns the width of the board.
   *
   * @return value of the width of the board
   */
  int getWidthBoard();

  /**
   * Adds a motion from a certain shapes list of motions.
   *
   * @param motion the motion to be removed
   * @param shape  the index of the motion to be removed
   */
  void addMotion(cs3500.model.MotionAnimation motion, Shape2D shape);

  /**
   * Removes a motion from a certain shapes list of motions.
   *
   * @param motion the motion to be removed
   * @param shape  the index of the motion to be removed
   */
  void removeMotion(MotionAnimation motion, Shape2D shape);


  /**
   * Returns a state of a shape at specific Tick.
   *
   * @return returns the state of a shape
   */
  List<StateofShape> stateofShapesTick(int tick);

  /**
   * Returns a string visualization of an animation. For each motion, it displays the start and end
   * tick of that motion, along with the values for the shape's fields before and after the motion
   * occurs.
   *
   * @return a string representing an animation
   */
  String description();

  /**
   * Replaces the values of the the fields of a model with the given inputs.
   *
   * @param x      the new x value
   * @param y      the new y value
   * @param width  the new width
   * @param height the new height
   */
  void setBounds(int x, int y, int width, int height);
}
