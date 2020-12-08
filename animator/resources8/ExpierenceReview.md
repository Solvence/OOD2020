# Experience Review

## What do you wish you had done differently in your design/implementation? Did you learn any lessons from the code given to you, or the updates you had to make for your customers?

One thing we could have done differently within our design may be with the AnimatedObject class. 
Currently our AnimatedObject class maintains the existence of a base shape, which was a shape with null
fields. We realized that the only purpose of this shape was to know the type of shape to be animated,
so perhaps this could have been stored differently.

Otherwise, we generally believe that our design was pretty good. We split up business logic into sub-objects
so objects weren't doing too many tasks at once. We also believe we used helpful design patterns
like visitor pattern etc. to keep in line with SOLID and OOD ideals. 

We learned the importance of interface segregation from our providers code.


## What was your experience like with your providers?

Our providers were great and responded quickly whenever we needed help or clarifications.
