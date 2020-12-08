# Provider Critique

## Design

    Model and View seemed to reveal too much information. 
        - Shape interface has getters for properties of the shape to get animated, even though
          properties of a shape depend of tickRate, so having these methods seem not helpful
          becuase we don't know what state of the shape to get info of.  
        - View allows user to get instances of buttons, but clients generally may not need instances
          of the buttons, but instead a listener. 
        - View has getter for the timer allowing outside forces to mutate it. Seems like setters may
          be better in this case.
    
    Role of a Controller seemed irrelevant.
        - Providers delegate so much responsibility in generating the animation and the state of the 
          animation within the display() method, that it seems the only action a controller would have
          would be being a actionListener or just initializing one.
    
    No interface segregation (view has 1 REAL view) superview extends IView but has same non overriden
    methods
        - SuperView had methods for SVG rendering, but TextualView implemented it.
        - SuperView had methods for getting buttons, but why would TextualView need that?
        - IView extends SuperView but adds no functionality (redefines same method).
    

## Implementation

    View seemed too tightly coupled to Model and did too many things
        - View directly had an instance of the model and requested info directly from it (no intermediate)
        - View handled time, but didn't need to
        - View had so many methods that handled running a full animation, that the idea of a controller
          seemed irrelevant (display method for Interactive and Visual views)
           

## Documentation

    Documentation was generally there, but there seemed to be instances of Objects and methods
    that's purpose wasn't adequately specified. For example, method "description" occurs in most
    model and submodel objects, but isn't necesary in most cases. 
    Documentation was generally descriptive as we were generally able to figure things out.

## Limitations

    Look above.
    
    Adding new views seem pretty wierd and difficult. This is because of the lack of interface 
    segregation, when new views are added they must implement all methods for all views. This 
    is inconvenient and forces all view to change when new functionality is added (unless there is
    some sort of extension down the road).
