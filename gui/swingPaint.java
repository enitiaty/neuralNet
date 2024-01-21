import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;


public class swingPaint{
  Dimension dim = new Dimension(896, 896);

  public swingPaint(){
    myPanel c = new myPanel();
    JFrame f = new JFrame();





    f.setMinimumSize(dim);
    f.setMaximumSize(dim);
    f.setLocationRelativeTo(null);
    c.setMaximumSize(new Dimension(896, 896));



    // f.add(reset, BorderLayout.NORTH);
    f.add(c, BorderLayout.CENTER);





    f.setVisible(true);
    f.addKeyListener(new KeyListener() {
      @Override 
      public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==32){
          c.reset();
        }
      };
      @Override 
      public void keyTyped(KeyEvent e){};
      @Override 
      public void keyReleased(KeyEvent e){};
    });
    
  }

 
} 
