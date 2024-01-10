import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class fileIO {

  public static void dumpData(matrix[] weights, rowVector[] biases){
    try{
      FileWriter fw = new FileWriter(".\\permFileLocs\\weights.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (matrix i: weights){
        pw.write(i.toString()+"\n");
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
    try{
      FileWriter fw = new FileWriter(".\\permFileLocs\\biases.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (rowVector i: biases){
        pw.write(i.toString());
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
  }

  public static matrix[] readWeights(){
    matrix[] out = new matrix[2];
    try {
      FileReader fr = new FileReader(".\\permFileLocs\\weights.txt");
      BufferedReader br = new BufferedReader(fr);
      String temp;

      ArrayList<float[]> t = new ArrayList<float[]>();
      
      while (true) {
        temp = br.readLine();
        if (temp.equals("")) break;
        t.add(utilities.convertStrToFloatArr(temp.substring(1, temp.length()-1).split(",")));
      } 
      float[][] t1 = new float[t.size()][t.get(0).length];
      for (int i = 0; i < t.size(); i++){
        t1[i]=t.get(i);
      }
      out[0]=new matrix(t1);
      
      t.clear();
      
      while (true) {
        temp = br.readLine();
        if (temp.equals("")) break;
        t.add(utilities.convertStrToFloatArr(temp.substring(1, temp.length()-1).split(",")));
        // t.add
      } 
      float[][] t2 = new float[t.size()][t.get(0).length];
      for (int i = 0; i < t.size(); i++){
        t2[i]=t.get(i);
      }
      out[1]=new matrix(t2);

      br.close();
    } catch (Exception e) {
      System.out.println("exception "+e);
    }
    return out;
  }
  
  public static rowVector[] readBiases(){
    rowVector[] out = new rowVector[2];
    try {
      FileReader fr = new FileReader(".\\permFileLocs\\biases.txt");
      BufferedReader br = new BufferedReader(fr);
      String c=br.readLine();
      out[0]=new rowVector(utilities.convertStrToFloatArr(c.substring(1, c.length()-1).split(",")));
      c=br.readLine();
      out[1]=new rowVector(utilities.convertStrToFloatArr(c.substring(1, c.length()-1).split(",")));
      br.close();
    } catch (Exception e) {
      System.out.println("exception "+e);
    }
    return out;
  }

  public static pair[] readTrainingData(int batchSize, String pathToFile){
    if (10000%batchSize!=0) throw new RuntimeException("batch error");
    ArrayList<pair> out = new ArrayList<pair>();//matrix, matrix
    ArrayList<Integer> oH = new ArrayList<Integer>();
    ArrayList<float[]> fls = new ArrayList<float[]>();
    float[] temp = new float[769];
    try {
      FileReader fr = new FileReader(pathToFile);
      BufferedReader br = new BufferedReader(fr);
      String c;
      while (true){
        c=br.readLine();
        if (c.equals("f")) break;
        temp = utilities.convertStrToFloatArr(c.substring(1, c.length()-1).split(","));

        
        oH.add((int)temp[0]);
        fls.add(Arrays.copyOfRange(temp, 1, temp.length));
        br.close();
      }
    } catch (Exception e) {
      System.out.println("exception "+e);
    } 
    return null; //69
  }

}
