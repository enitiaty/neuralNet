import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;


public class swingPaint{

  public swingPaint(){
    model aiModel = new model(".\\permFileLocs\\w_HORIZONTAL.txt", ".\\permFileLocs\\b_HORIZONTAL.txt");
    myPanel c = new myPanel();
    JFrame f = new JFrame();
    f.setBounds(0, 0, 896, 896);
    f.setLocationRelativeTo(null);
    f.add(c);
    f.pack();
    f.setVisible(true);
    f.addKeyListener(new KeyListener() {
      @Override 
      public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==10){
          System.out.println(utilities.find(aiModel.forward(c.getMat()),utilities.maxArr(aiModel.forward(c.getMat()))));

        }
        else if (e.getKeyCode()==32){
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
