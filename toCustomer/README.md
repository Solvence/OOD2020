## UI Explanation

Our UI has six buttons, one of which ("Loop") is toggleable.

- Play will play/resume the animation (the animation starts paused at first)
- Pause will pause the animation
- Speed Up will multiply the tick rate (ticks/sec) by a constant factor (currently 2.0)
- Slow Down will divide the tick rate by the same constant factor (currently 2.0)
- Reset will immediately set the tick of the animation to the beginning tick (1)
- Loop can be in the pressed or unpressed state. It starts in the unpressed state, meaning that
  the animation will not loop once it ends. The user can toggle this button by clicking on it.
  Once toggled, the animation will loop back to the beginning once it ends, and will continue 
  looping
  
Notes:

- Visual view's translateToTime() method throws an unsupported method exception since it renders
  based off of the state that the controller updates. The view itself does not deal with time
- All button functionality in our interactive view functions as expected
- Since InteractiveAnimationView and VisualAnimationView do not have an instance of the model, 
  their corresponding controller(s) must handle providing data to these views and also must have
  some way of listening to user input.
- An AnimatedObject represents a base shape and all the commands attached to it throughout the life
  cycle of the animation (may not be necessary)