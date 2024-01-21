import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class myPanel extends JPanel{
  model aiModel = new model(".\\permFileLocs\\w_FINAL_copy.txt", ".\\permFileLocs\\b_FINAL_copy.txt");

  private int mx = -1;
  private int my = -1;
  Boolean flag = false;
  private int[] c = new int[2];
  private float[] mat = new float[784];

  JButton exit = new JButton("Exit");
  JButton r = new JButton("Reset");
  JButton cal = new JButton("Calculate");
  JButton addToData = new JButton("Add to test data");
  JLabel result = new JLabel("null: null%, null: null%, null: null%");

  JPanel topPanel = new JPanel();
  JPanel botPanel = new JPanel();


  private int[] convToCoords(int x, int y){
    mat[y/32*28+x/32]=1;
    mat[y/32*28+28+x/32]=1;
    mat[y/32*28+x/32+1]=1;
    mat[y/32*28+28+x/32+1]=1;

    return new int[]{x/32*32, y/32*32};
  }


  public myPanel(){
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createLineBorder(Color.black));

    result.setFont(new Font("", Font.PLAIN, 28));
    topPanel.setMinimumSize(new Dimension(896, 64));

    r.setFocusable(false); 
    cal.setFocusable(false); 
    addToData.setFocusable(false); 


    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e){
        flag=true;
        mx=e.getX();
        my=e.getY();
        if (mx<0){mx=0;}
        if (my<64){my=64;}

        if (mx>832){mx=832;}
        if (my>736){my=736;}
        System.out.println(mx+","+my);
        c = convToCoords(mx, my);
        repaint(c[0], c[1], 64, 64);
      };
      public void mouseMoved(MouseEvent e){};
    });

    // r.setLocation(new Point(0, 0));
    // r.setMinimumSize(new Dimension(50, 50));
    topPanel.add(exit);
    topPanel.add(r);
    topPanel.add(cal);
    topPanel.add(addToData);
    botPanel.add(result);
    add(topPanel, BorderLayout.NORTH);
    add(botPanel, BorderLayout.PAGE_END);

    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    r.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        reset();
      }
    });
    cal.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        float[] b =aiModel.forward(mat);
        pair[] f = utilities.assignIndex(b);
        f = utilities.sortDecimal(f);
        System.out.println(Arrays.toString(f));
        result.setText(utilities.formatPair(f[9])+", "+utilities.formatPair(f[8])+", "+utilities.formatPair(f[7]));
        
      }
    });
    addToData.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String correctNum = JOptionPane.showInputDialog("input correct number: ");
        fileIO.writeTestCase(correctNum, utilities.wrapFloat(mat));
        
      }
    });


    r.setVisible(true);
    cal.setVisible(true);
    result.setVisible(true);


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