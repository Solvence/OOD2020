package view;

import java.io.IOException;
import java.util.Map;
import model.AnimatorModel;
import model.animatedObject.AnimatedObject;
import model.animatedObjectCommand.AnimatedObjectCommand;

/**
 * Represents a textual translation of an animation
 */
public class BasicTextualAnimatorView implements AnimatorView {
  private final AnimatorModel model;
  private final Appendable log;

  /**
   * Loaded Constructor
   * @param model   - model to be displayed
   * @param log     - log of passed displays
   */
  BasicTextualAnimatorView(AnimatorModel model, Appendable log) {
    this.model = model;
    this.log = log;
  }

  BasicTextualAnimatorView(AnimatorModel model) {
    this(model, new StringBuilder());
  }


  @Override
  public void render() throws IOException {
    this.log.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder table = new StringBuilder();
    for (Map.Entry<String, AnimatedObject> e: this.model.getAnimatedObjects().entrySet()) {
      StringBuilder currentRow = new StringBuilder();
      String name = e.getKey();
      AnimatedObject animated = e.getValue();
      currentRow.append(String.format("Motion %s ", name));
      for (AnimatedObjectCommand command : animated.getCommands()) {
        int startTime = command.getStartTime();
        int endTime = command.getEndTime();
        Shape
      }

    }
  }
}
