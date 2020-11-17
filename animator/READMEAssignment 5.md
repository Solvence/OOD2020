# HW 05 #
## Animator Model Implementation
For our implementation of the animator cs3500.animator.model we rely on 5 interfaces.
    
    - AnimatorModel: Represents the state of the animation and allows user to create Animated Objects 
                     and command them to do things.
                     
    - AnimatorView: Represents the visual component of an animation. Has the ability to render a animation.
                     
    - AnimatedObject: Represents a shape undergoing a string of commands. User can utilize animatedObjects 
                      to get shapes at a certian point in their animation or add commands to them.
                      
    - AnimatedObjectCommand: Represents a command that can be called on a shape within a given interval 
                             of time. As of now Animated Object commands consists of Move, ChangeSize, 
                             and ChangeColor.
                             
    - Shape: Represents a Shape with a size position and color. As of now the current implemented shapes 
              are for Rectangle and Ellipse.
              
              
Implementations of the above interfaces include:

    - BasicAnimatorModel: maintains the state of an Animation through a List of AnimatedObjects.
    
    - BasicTextualAnimatorView: renders a AnimatorModel in a textual format creating a table which outlines
                                the state of each animatedObject throughout its lifecycle. 
    
    - BasicAnimatedObject: Controls how a shape looks throughout its lifecycle of an animation. Keeps 
                           track of the baseShape before an animation and all commands that are to be 
                           called on it throughout the animation
                           
    - Move: Moves handles applying a move to a given shape from a start to end position within a given 
            time interval. Time interval and moveInterval are given to this object in its constructor.
            
    - ChangeSize: ChangeSize handles applying a size change to a given shape from a start to end size 
                  within a given time interval. Time interval and sizeInterval are given to this object 
                  in its constructor.
                  
    - ChangeColor: ChangeColor handles applying a color change to a given shape from a start to end 
                   color within a given time interval. Time interval and colorInterval are given to 
                   this object in its constructor.
                   
    - Rectangle: Represents a Rectangle shape with size length, height, color, and a Position. Position 
                 represents top left corner of size box.
                 
    - Ellipse: Represents a Ellipse shape with size Radius X-direction, Radius Y-direction, color, and 
               a Position. Position represents top left corner of size box.


Some Utility classes we used for our implementation include:

    - Position2D: Represents a 2D position on cartesian plan.
    - Dimension2D: Which represents the dimensions of the bounding box of a shape.
    
Invariants in design:

    - BasicAnimatorModel - Can't have two AnimatedObjects with the same name.

    - BasicTextualAnimatorView: Field must not be null.
    
    - BasicAnimatedObject: Field must not be null. Two or more of the same type of command can't overlap 
                           time intervals.
    
    - Move: StartPos or EndPos must not be null. Time intervals can't have negative start times or endtime, 
            and endtime must always be greater than starttime.
            
    - ChangeSize: StartSize or EndSize must not be null. Time intervals can't have negative start times or endtime, 
                  and endtime must always be greater than starttime.
                
    - ChangeColor: StartColor or EndColor must not be null. Time intervals can't have negative start times or endtime, 
                   and endtime must always be greater than starttime.
                   
    - Shapes: Dimensions can never be negative. Color and Position must always be non null.
                   
    - Dimension2D: XDir and YDir must be non negative.