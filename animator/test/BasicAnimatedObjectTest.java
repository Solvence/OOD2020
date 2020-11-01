import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.animatedObject.AnimatedObject;
import model.animatedObject.BasicAnimatedObject;
import model.animatedObjectCommand.AnimatedObjectCommand;
import model.animatedObjectCommand.ChangeColor;
import model.animatedObjectCommand.ChangeSize;
import model.animatedObjectCommand.Move;
import model.dimension2D.Dimension2D;
import model.position2d.Position2D;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

public class BasicAnimatedObjectTest {

  AnimatedObject ao1;
  AnimatedObject ao2;
  Shape s1;
  Shape s2;


  @Before
  public void setUp() {
    this.s1 = new Rectangle(10, 10, new Color(10, 10, 10), new Position2D(0, 0));
    this.s2 = new Ellipse(2, 6, new Color(14, 100, 22), new Position2D(15, 1));
    this.ao1 = new BasicAnimatedObject(s1);
    this.ao2 = new BasicAnimatedObject(s2);
  }

  // test constructor

  // test add command

  // test get shape at

  // test get commands
  /*
  @Test
  public void testGetCommands() {
    List<AnimatedObjectCommand> testList = new ArrayList<>();
    assertEquals(ao1.getCommands(), testList);
    ao1.addCommand(new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12)));
    AnimatedObjectCommand c1 = new Move(1, 10, new Position2D(10, 11), new Position2D(11, 12));
    testList.add(c1);
    assertEquals(ao1.getCommands(), testList);
    AnimatedObjectCommand c2 = new Move(10, 15, new Position2D(11, 15), new Position2D(22, 222));
    ao1.addCommand(new Move(10, 15, new Position2D(11, 15), new Position2D(22, 222)));
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c2);
    assertEquals(ao1.getCommands(), testList);
    AnimatedObjectCommand c3 = new ChangeSize(1, 10, new Dimension2D(11, 15),
        new Dimension2D(22, 222));
    AnimatedObjectCommand c4 = new ChangeColor(10, 15, new Color(11, 15, 10),
        new Color(100, 22, 222));
    ao1.addCommand(new ChangeSize(1, 10, new Dimension2D(11, 15), new Dimension2D(22, 222)));
    ao1.addCommand(new ChangeColor(10, 15, new Color(11, 15, 10), new Color(100, 22, 222)));
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c3);
    assertNotSame(ao1.getCommands(), testList);
    testList.add(c4);
    assertEquals(ao1.getCommands(), testList);




  }*/


}
