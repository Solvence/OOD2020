# HW 07 #
### Interactive View/Controller Implementation

New Design:
    
    - Created new interface "ActionableAnimationView" that extends ActiveAnimationView and is 
      intended to support interaction between the user and the live animation through the 
      functionality of listeners.
    - Created new class InteractiveanimationView that extends VisualAnimationView and implements
      ActionableAnimationView and provides functionality for buttons that perform actions such as
      starting, pausing, speeding up, slowing down, looping, and resetting the animation.
    - Created three controllers, all of which implement the central controller interface for
      animations: AnimationController
      - StaticAnimationController deals with rendering textual and SVG views
      - VisualAnimationController deals with processing data from the model to pass to visual
        views and deciding when to render them
      - InteractiveAnimationController deals with processing data from the model to pass to
        interactive views and deciding when to render them, as well as handling user input from
        the button presses
    - Created AnimationActionListener interface to deal with handling user inputs
    - Created InteractiveActionListener class to handle user input for various actions such as 
      playing, pausing, speeding up, slowing down, looping, and resetting the animation.
    - Created ControllerFactory factory class to create a controller based on the given type of
      view.
      
Added Support for Creating Animations:

    - Added BubbleSort class to represent a bubble sort algorithm in action. The class maintains
      an array of the current contents in the list to be sorted. The methods allow the algorithm
      to proceed step by step (where each "step" is a swap).
    - Added BubbleSortAnimation to represent a program to programatically generate a bubble sort
      animation in the text format that our animation input expects. The animation is generated
      through optional command line arguments as follows:
      - out: represents the file where the animation will be outputted. Default = System.out
      - length: the length of the list to be sorted. Default = 10
      - seed: the seed of the animation, if one wishes to predict the random generation (used for
        testing)
      - A valid command line arguments example would be "-out file.txt -length 25 -seed 1"
    - Added a file "dvd-player.txt" representing the input for an animation that we manually
      created to display the iconic dvd player logo movement.
      
Changes to Assignment 6 Code:

    - Added functionality in main Excellence method to print to the command line a friendly 
      message to indicate an error in setting up the animation or running the animation.