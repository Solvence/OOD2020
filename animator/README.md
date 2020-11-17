# HW 06 #
### Animator Views Implementation

##Changes from Assignment 5:
Changes to Design:
    
    - Changed getAnimatedObjects to two seperate methods: getShapeNames and getCommands in order to
      not reveal as much information to the users
    - Add canvas field to model which must be initialized using the initCanvas method before any 
      other methods are run. Also added a get method for both the position and dimension of the
      canvas in the model.
    - Added Canvas object to support Canvas functionality in the model
    - Allowed functionality of adding keyframes: motions which have the same start and end time
    - Merged "move", "changeColor", and "changeSize" methods into one method called "addMotion"
      which handles change functionality for all three types of motion. This better supports the
      input style which we assumed would always specify all three fields (position, size, color)
    - Shapes can now be initialized with null fields. This allows users to use the Shape class
      without necessarily needing to specify the position, size, and color fields. This is useful
      when you simply need access to an arbitrary Rectangle's methods, for example
    - Added Color class which replaced the references to java.awt.Color in the model from the 
      previous assignment
    - created AbstractShape class to abstract common functionality within all currently 
      implemented Shapes
    - changed getShapes(time) to getShapeAt(String, time) to only get one specified shape at a time
      as opposed to all shapes. This was done because a user may not need or care about all the
      shapes at a given time.

New Assumptions:

    - A valid input must have all motions for a certain shape inserted in order of start time. The
      start time of a motion must be equal to the end time of the previous motion, if there is one.
      Our new model design enforces this.
    - A valid input cannot have overlapping motions. Our model design enforces this.
    - A valid input has the canvas initialized before any motions are declared. Our model design
      enforces this
    - valid input will specify all fields of motion (position, size, color) when wishing to add a
      motion command.
    - this minimum start time of a command is 1. Our model design enforces this.

##Implementation for Assignment 6 - View Design:

    - All views branch from the central interface AnimatorView, which includes functionality
      for rendering the animation and converting from a given tick to its respective time
        - the textual and svg view directly implement this interface, which do not care about
          the time at which they are rendered, they only care about the animation as a whole
    - Another interface is ActiveAnimatorView, which extends AnimatorView. This represents an
      animation that is rendered at a certain time, which is stateful and may support live user
      input in the future.
        - the visual view implements this interface as it needs to be able to render the animation
          frame by frame.

Visitor Design for Views:

    - SVGView uses three visitors: one to render the header of a Shape's section
      in the SVG output, one to render an animate tag in the body of the SVG output, and one to
      render the exit tag of the Shape's section in the SVG output. 
    - We decided to use this visitor pattern since the functionality for implementing these SVG tags
      may be specific to the Shape itself. i.e. a Rectangle's SVG output is different than the
      output for an Ellipse. Having these visitors ensures that the view itself isn't providing
      the Shape-specific functionality, preserving the object-oriented nature of our design
    - VisualView also uses a visitor as well: VisualShapeVisitor. Given a Graphics object, this
      visitor renders the specific Shape onto the Graphics object.
      
More details for VisualView:

    - Along with implementing the ActiveAnimatorView interface, VisualView extends the JFrame class,
      which allows us to render our animation as a single frame in a window.
      
    - The Panel allows us to support the Shapes we want from the model and allows us to render 
      these shapes in the drawing. The Shapes here can then be set and re-rendered.
              
Factory/Builder:

    - Added a ShapeFactory which constructs a Shape based on a String specifying the type. This may
      be useful when reading input and translating it to Shapes.
    - Added a ViewFactory method to costruct a view based on a String specifying the type.
    - Added a builder class in the model to assist in constructing model objects from input files
    
Main:

    - added a main "Excellence" class to process command line arguments and run the program.