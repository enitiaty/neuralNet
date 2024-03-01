import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Class for all panels and buttons in GUI.
 * @author enitiaty
 * @since 2024-01-21
 */
public class myPanel extends JPanel{
  model aiModel = new model(".\\permFileLocs\\w_FINAL_copy.txt", ".\\permFileLocs\\b_FINAL_copy.txt");

  private int mx = -1;
  private int my = -1;
  Boolean flag = false;
  private int[] c = new int[2];
  private float[] mat = new float[784];

  JButton retrain = new JButton("Retrain");
  JButton exit = new JButton("Exit");
  JButton r = new JButton("Reset");
  JButton cal = new JButton("Calculate");
  JButton addToData = new JButton("Add to test data");
  JLabel result = new JLabel("null: null%, null: null%, null: null%");

  JPanel topPanel = new JPanel();
  JPanel botPanel = new JPanel();

  /**
   * Process x and y to be in a 28x28 range.
   * @param x
   * @param y
   * @return int[2]
   */
  private int[] convToCoords(int x, int y){
    mat[y/32*28+x/32]=1;//Error handling
    mat[y/32*28+28+x/32]=1;
    mat[y/32*28+x/32+1]=1;
    mat[y/32*28+28+x/32+1]=1;

    return new int[]{x/32*32, y/32*32};
  }

  /**
   * Constructor, most of the code.
   */
  public myPanel(){
    setLayout(new BorderLayout());//Setting layout
    setBorder(BorderFactory.createLineBorder(Color.black));

    result.setFont(new Font("", Font.PLAIN, 28));//Setting text size of label
    topPanel.setMinimumSize(new Dimension(896, 64));
    
    retrain.setFocusable(false);//Setting focusability of buttons
    exit.setFocusable(false);
    r.setFocusable(false); 
    cal.setFocusable(false); 
    addToData.setFocusable(false); 


    addMouseMotionListener(new MouseMotionListener() {//Using mouselistener to get mouse x and y
      @Override
      public void mouseDragged(MouseEvent e){
        flag=true;
        mx=e.getX();
        my=e.getY();
        if (mx<0){mx=0;}
        if (my<64){my=64;}

        if (mx>832){mx=832;}
        if (my>736){my=736;}
        c = convToCoords(mx, my);
        repaint(c[0], c[1], 64, 64);
      };
      public void mouseMoved(MouseEvent e){};
    });
    // retrain.setVisible(true);

    topPanel.add(retrain);//Adding buttons to top and bottom panels
    topPanel.add(exit);
    topPanel.add(r);
    topPanel.add(cal);
    topPanel.add(addToData);
    botPanel.add(result);
    add(topPanel, BorderLayout.NORTH);
    add(botPanel, BorderLayout.PAGE_END);

    retrain.addActionListener(new ActionListener() {//Retrain the model button
      @Override
      public void actionPerformed(ActionEvent e) {        
        trainingEpochs.train(3);
      }
    });
    exit.addActionListener(new ActionListener() {//Exit button
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    r.addActionListener(new ActionListener() {//Reset canvas button
      @Override
      public void actionPerformed(ActionEvent e) {
        reset();
        result.setText("null: null%, null: null%, null: null%");
      }
    });
    cal.addActionListener(new ActionListener() {//Calculate probabilities button
      @Override
      public void actionPerformed(ActionEvent e) {
        float[] b =aiModel.forward(mat);
        pair[] f = utilities.assignIndex(b);
        f = utilities.sortDecimal(f);
        result.setText(utilities.formatPair(f[9])+", "+utilities.formatPair(f[8])+", "+utilities.formatPair(f[7]));
        
      }
    });
    addToData.addActionListener(new ActionListener() {//Add new training data button
      @Override
      public void actionPerformed(ActionEvent e) {
        String correctNum = JOptionPane.showInputDialog("input correct number: ");//Getting correct data from user
        try {
          int num = Integer.parseInt(correctNum);
          if (-1<num && num<10) fileIO.writeTestCase(correctNum, utilities.wrapFloat(mat));
        } catch (Exception A) {}
      }
    });
  }

  /**
   * Resets the canvas and array.
   */
  public void reset(){
    for (int i = 0; i < 784; i++){mat[i]=0;}
    flag=false;
    repaint();
  }

  /**
   * Gets the image as a 28x28 array.
   * @return float[]
   */
  public float[] getMat(){
    return mat;
  }

  /**
   * Gets preffered dimension of the JPanel.
   * @return Dimension
   */
  @Override
  public Dimension getPreferredSize(){
    return new Dimension(896, 896);
  }

  /**
   * Override of the paintComponent function.
   */
  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);//Call paint
    if (flag) g.fillRect(c[0], c[1], 64, 64);//Draw following cursor if flag is enabled. 
  }
  

}