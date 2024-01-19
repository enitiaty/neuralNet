import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class myPanel extends JPanel{
  private int mx = -1;
  private int my = -1;
  Boolean flag = false;
  private int[] c = new int[2];
  private float[] mat = new float[784];
  private int[] convToCoords(int x, int y){
    mat[y/32*28+x/32]=1;
    mat[y/32*28+28+x/32]=1;
    mat[y/32*28+x/32+1]=1;
    mat[y/32*28+28+x/32+1]=1;

    return new int[]{x/32*32, y/32*32};
  }
  public myPanel(){
    setBorder(BorderFactory.createLineBorder(Color.black));
    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e){
        flag=true;
        mx=e.getX();
        my=e.getY();
        c = convToCoords(mx, my);
        // System.out.println(mx/32+", "+my/32);
        // System.out.println(my/32*28+mx/32);
        repaint(c[0], c[1], 64, 64);
      };
      public void mouseMoved(MouseEvent e){};
    });
  }
  public void reset(){
    for (int i = 0; i < 784; i++){mat[i]=0;}
    flag=false;
    repaint();
  }
  public float[] getMat(){
    return mat;
  }
  @Override
  public Dimension getPreferredSize(){
    return new Dimension(896, 896);
  }
  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    if (flag) g.fillRect(c[0], c[1], 64, 64);
  }
  

}