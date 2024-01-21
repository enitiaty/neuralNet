import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class fileIO {

  public static void dumpData(matrix[] weights, rowVector[] biases, String weightPath, String biasPath){
    try{
      FileWriter fw = new FileWriter(weightPath);
      PrintWriter pw = new PrintWriter(fw);
      for (matrix i: weights){
        pw.write(i.toString()+"\n");
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
    try{
      FileWriter fw = new FileWriter(biasPath);
      PrintWriter pw = new PrintWriter(fw);
      for (rowVector i: biases){
        pw.write(i.toString());
      }
      pw.close();

    } catch (IOException e){
      System.out.println("error: "+e);
    }
  }

  public static matrix[] readWeights(String path){
    matrix[] out = new matrix[2];
    try {
      FileReader fr = new FileReader(path);
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
  
  public static rowVector[] readBiases(String path){
    rowVector[] out = new rowVector[2];
    try {
      FileReader fr = new FileReader(path);
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

  public static pair<oneHotMatrix, matrix>[] readTrainingData(int batchSize, String pathToFile, int lineLim){
    ArrayList<pair<oneHotMatrix, matrix>> out = new ArrayList<pair<oneHotMatrix, matrix>>();//matrix, matrix
    ArrayList<Integer> oH = new ArrayList<Integer>();
    ArrayList<float[]> fls = new ArrayList<float[]>();
    float[] temp = new float[769];
    int count = 0;
    try {
      FileReader fr = new FileReader(pathToFile);
      BufferedReader br = new BufferedReader(fr);
      String c;
      while (true){
        c=br.readLine();
        if (c.equals("f")||count==lineLim) break;
        temp = utilities.convertStrToFloatArr(c.split(","));


        
        oH.add((int)temp[0]);
        fls.add(Arrays.copyOfRange(temp, 1, temp.length));
        count++;
      }
      br.close();
    } catch (Exception e) {
      System.out.println("exception "+e);
    } 
    int[] oHA = new int[oH.size()];
    for (int i = 0; i < oH.size(); i++) oHA[i]=oH.get(i);
    float[][] flA = new float[fls.size()][768];
    for (int i = 0; i < fls.size(); i++) flA[i]=fls.get(i);
    lineLim=Math.max(lineLim, oH.size());
    if (lineLim%batchSize!=0||oH.size()%batchSize!=0){throw new RuntimeException("batch error");}
    for (int i = 0; i < lineLim/batchSize; i++){
      out.add(
        new pair<oneHotMatrix, matrix>(
          new oneHotMatrix(batchSize, Arrays.copyOfRange(oHA, i*batchSize, i*batchSize+batchSize)), 
          new matrix(Arrays.copyOfRange(flA, i*batchSize, i*batchSize+batchSize)))
      ); 
    }
    pair<oneHotMatrix, matrix>[] o = new pair[out.size()];
    for (int i = 0; i < out.size(); i++) o[i]=out.get(i);
    return o; //69
  }

  public static void writeCoords(float[][] a){
    try {
      FileWriter fw = new FileWriter(".\\permFileLocs\\coords.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (float[] i:a){
        pw.print("("+i[0]+","+i[1]+"),");
      }
      pw.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public static void writeTestCase(String correctNum, Float[] data){
    try {
      FileWriter fw = new FileWriter(".\\datasets\\user.csv", true);
      PrintWriter pw = new PrintWriter(fw);
      pw.print("\n"+correctNum+","+utilities.convertToCSV(data));
      pw.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    
  }

}
