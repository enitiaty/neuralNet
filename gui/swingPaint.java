import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A class for the JFrame.
 * @author enitiaty
 * @since 2024-01-21
 */
public class swingPaint{
  Dimension dim = new Dimension(896, 896);

  /**
   * Constructor.
   */
  public swingPaint(){
    myPanel c = new myPanel();//Declares panel and frame
    JFrame f = new JFrame();

    f.setMinimumSize(dim);//Set size of panel and frame
    f.setMaximumSize(dim);
    f.setLocationRelativeTo(null);
    c.setMaximumSize(new Dimension(896, 896));

    f.add(c);//Add panel to frame

    f.setVisible(true);//Set visibility of frame
  } 
} 
