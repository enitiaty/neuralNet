import java.awt.Dimension;
import java.awt.Toolkit;

public class showGUI {
  public static void main(String[] args) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // System.out.println(((int)screenSize.getWidth()-896)/2);
    swingPaint sp = new swingPaint();
  }
}
