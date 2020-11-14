package cs3500.animator.model.shapevisitor;

import cs3500.animator.model.animatedobjectcommand.AnimatedObjectCommand;
import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.Shape;
import java.util.List;

/**
 * Represents a function object to render an animation tag in an SVG view for a given Shape
 */
public class RenderSVGAnimateTag implements ShapeVisitor<StringBuilder> {

  private final AnimatedObjectCommand command;
  private final double startTime;
  private final double endTime;

  /**
   * Constructs a function object to render the animate tag in an SVG Component for a Shape
   * @param command - the command that is tied to this Shape
   */
  public RenderSVGAnimateTag(AnimatedObjectCommand command, double startTime, double endTime) {
    this.command = command;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public StringBuilder visitEllipse(Ellipse e) {
    Shape initialShape = command.apply(e, command.getStartTime());
    Shape finalShape = command.apply(e, command.getEndTime());

    StringBuilder sb = new StringBuilder();

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
        + "attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getPosition().getX(),
        finalShape.getPosition().getX()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"cy\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getPosition().getY(),
        finalShape.getPosition().getY()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"rx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getSize().getXDir(),
        finalShape.getSize().getXDir()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"ry\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getSize().getYDir(),
        finalShape.getSize().getYDir()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getColor().getRed(),
        initialShape.getColor().getGreen(), initialShape.getColor().getBlue(),
        finalShape.getColor().getRed(), finalShape.getColor().getGreen(),
        finalShape.getColor().getBlue()));

    return sb;
  }

  @Override
  public StringBuilder visitRectangle(Rectangle r) {
    Shape initialShape = command.apply(r, command.getStartTime());
    Shape finalShape = command.apply(r, command.getEndTime());

    StringBuilder sb = new StringBuilder();

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getPosition().getX(),
        finalShape.getPosition().getX()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getPosition().getY(),
        finalShape.getPosition().getY()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getSize().getXDir(),
        finalShape.getSize().getXDir()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getSize().getYDir(),
        finalShape.getSize().getYDir()));

    sb.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\" dur=\"%fms\" "
            + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n",
        this.startTime, this.endTime - this.startTime, initialShape.getColor().getRed(),
        initialShape.getColor().getGreen(), initialShape.getColor().getBlue(),
        finalShape.getColor().getRed(), finalShape.getColor().getGreen(),
        finalShape.getColor().getBlue()));

    return sb;
  }

  @Override
  public StringBuilder apply(Shape shape) {
    return shape.accept(this);
  }
}
