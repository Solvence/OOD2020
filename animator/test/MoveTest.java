import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.animatedObjectCommand.Move;
import model.position2d.Position2D;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Move Command class object.
 */
public class MoveTest {

  AnimatedObjectCommand c1;
  AnimatedObjectCommand c2;
  AnimatedObjectCommand c3;
  Shape s1;
  Shape s2;
  Shape s3;

  @Before
  public void setUp() {
    this.c1 = new Move(1, 15, new Position2D(0, 0), new Position2D(15, 0));
    this.c2 = new Move(20, 21, new Position2D(15, 0), new Position2D(6, 0));
    this.c3 = new Move(70, 75, new Position2D(0, 0), new Position2D(10, 25));
    this.s1 = new Rectangle(10, 10, new Color(10, 10, 10), new Position2D(0, 0));
    this.s2 = new Ellipse(14, 6, new Color(11, 10, 155), new Position2D(15, 0));
    this.s1 = new Rectangle(20, 12, new Color(10, 20, 10), new Position2D(111, 111));
  }

  // test constructor

  // apply

  // applicable

  // sametype

  // getstarttime

  // getendtime

}
