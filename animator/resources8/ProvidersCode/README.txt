README

DESIGN

MODEL
Interface:Model
Class: AnimatorModel
Explanation: The model contains all information necessary to make
              an animation. This information includes a list of shapes that
              each have their own list of motions. The model contains getter Methods
              to facilitate the retrieval or adding information to the model without mutation.
VIEW
Interface: Interface View
Classes: VisualView, TextualView, SVGView, AnimationPanel
Explanation:  The three views actually run the animation with the single run method found in the interface.
              It can either generate a txt. file or an svg.file but can also ouput a String description of an animation
              to the console. It can also display an animation with the help of a JFrame. AnimationPanel is a panel that allows
MAIN
Class:Excellence
Explanation: Excellence class runs the main method. It takes in command line inputs specified by the client.
              These inputs include specification on the file or animation to run, the view to use to animate, the speed of the animation
              and which output file to use. The class throws IllegalArgument/IllegalStateException if certain inputs are invalid
Class: ViewCreator
Explanation:  This class has only one static public method and it creates a view based off the string name
              provided with the provided command line inputs as well
widthBoard and HeightBoard represent the canvas
listofShapes represent the listofShapes that will be used by the model
the tick is set to -1 since the animation has not begun yet

CHANGES TO ANIMATORMODEL
1. removed the tick field, the model should not be tracking the tick rather this is a job for the controller
2. Made our fields private
3. added public static BuilderClass that builds an animatorModel class
3. added a method that retrieves the last tick from a list of motions of a shape, in other words
    for a single shape, retrieves the list of motinons,
    retrieves the last motion of this list, retrieves the end tick of that motion.
4. added getter methods to retrieve the leftmost x and leftmost y
5. added a method to add motions to a specific shape
6. added a method to update the x, y, width, and height fields

CHANGES TO SHAPE CLASS
1. created an enum for shapes that includes a rectangle and ellipse
2. Created A Shape interface
3. created a class State of shapes that retrieves
   the information of a shape including the form, the x and y coordinates,
   the width and height and the color
4. inside Shape2D class, added a getStateofShapeTick class that takes in a tick
   and returns a stateofShape and depending on the tick it will do the tweeening

CHANGES TO MOTIONANIMATION CLASS
1. split up the description method into a main description with two helpers (beginning
   description and end description)

ASSIGNMENT 7 CHANGES
NEW INTERACTIVE VIEW
1. interactive view extends the visual view
2. Created a control pannel that has all of the buttons
3. Used set Action Commands(for testing purposes) and
ActionListeners for our buttons and One TextField

NEW CONTROLLER
1. Controller has only two methods
2. we are essentially injecting our view with our controller

