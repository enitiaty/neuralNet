import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fileIO {

  public static void dumpData(matrix[] weights, rowVector[] biases){
    try{
      FileWriter fw = new FileWriter(".\\perm file locs\\weights.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (matrix i: weights){
        pw.write(i.toString()+"\n");
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
    try{
      FileWriter fw = new FileWriter(".\\perm file locs\\biases.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (rowVector i: biases){
        pw.write(i.toString()+"\n");
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
  }

  public static matrix[] readWeights(){
    matrix[] out = new matrix[2];
    try {
      FileReader fr = new FileReader(".\\perm file locs\\weights.txt");
      BufferedReader br = new BufferedReader(fr);
      String temp = br.readLine();
      

    } catch (Exception e) {
      // TODO: handle exception
    }
    return out;
  }
}
