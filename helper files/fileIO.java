import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A collection of methods to aid in writing and reading required data from csv files.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class fileIO {

  /**
   * Writes the weights and biases to the files specified.
   * @param weights
   * @param biases
   * @param weightPath
   * @param biasPath
   * @throws IOException
   */
  public static void dumpData(matrix[] weights, rowVector[] biases, String weightPath, String biasPath){
    try{//Open weight file
      FileWriter fw = new FileWriter(weightPath);
      PrintWriter pw = new PrintWriter(fw);
      for (matrix i: weights){
        pw.write(i.toString()+"\n");//Converts each weight matrix to a string and writes it to the file
      }
      pw.close();
    } catch (IOException e){
      System.out.println("error: "+e);//Error handling
    }
    try{//Open bias file
      FileWriter fw = new FileWriter(biasPath);
      PrintWriter pw = new PrintWriter(fw);
      for (rowVector i: biases){
        pw.write(i.toString());//Converts each bias row vector to a string and writes it to the file.
      }
      pw.close();
    } catch (IOException e){
      System.out.println("error: "+e);
    }
  }

  /**
   * Reads an array of weight matricies, located at path.
   * @param path
   * @return matrix[]
   * @throws IOException
   */
  public static matrix[] readWeights(String path){
    matrix[] out = new matrix[2];
    try {//Open file at path
      FileReader fr = new FileReader(path);
      BufferedReader br = new BufferedReader(fr);
      String temp;

      ArrayList<float[]> t = new ArrayList<float[]>();//Create arraylist to store each row of matrix
      
      while (true) {
        temp = br.readLine();
        if (temp.equals("")) break;//Once the code enounters an empty line, it stops
        t.add(utilities.convertStrToFloatArr(temp.substring(1, temp.length()-1).split(",")));//Add each row of floats to arraylist as vectors
      } 
      float[][] t1 = new float[t.size()][t.get(0).length];
      for (int i = 0; i < t.size(); i++){//Convert the arraylist into an array
        t1[i]=t.get(i);
      }
      out[0]=new matrix(t1);//Create a new matrix with the args
      
      t.clear();//Empties the arraylist
      
      while (true) {//Runs the same logic as above
        temp = br.readLine();
        if (temp.equals("")) break;
        t.add(utilities.convertStrToFloatArr(temp.substring(1, temp.length()-1).split(",")));
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
    return out;//returns both matricies
  }

  /**
   * Reads an array of bias row vectors, located at path.
   * @param path
   * @return rowVector[]
   * @throws IOException
   */
  public static rowVector[] readBiases(String path){
    rowVector[] out = new rowVector[2];
    try {
      FileReader fr = new FileReader(path);
      BufferedReader br = new BufferedReader(fr);
      String c=br.readLine();//Reads the first line
      out[0]=new rowVector(utilities.convertStrToFloatArr(c.substring(1, c.length()-1).split(",")));//Converts first line into row vector
      c=br.readLine();//Reads second line
      out[1]=new rowVector(utilities.convertStrToFloatArr(c.substring(1, c.length()-1).split(",")));//Converts second line into row vector
      br.close();
    } catch (Exception e) {
      System.out.println("exception "+e);
    }
    return out;//returns row vectors
  }

  /**
   * Reads a file to generate an array of truth matricies and array of input matricies.
   * @param batchSize
   * @param pathToFile
   * @param lineLim
   * @return pair[]
   * @throws IOException
   */
  public static pair<oneHotMatrix, matrix>[] readTrainingData(int batchSize, String pathToFile, int lineLim){
    ArrayList<pair<oneHotMatrix, matrix>> out = new ArrayList<pair<oneHotMatrix, matrix>>();
    ArrayList<Integer> oH = new ArrayList<Integer>();
    ArrayList<float[]> fls = new ArrayList<float[]>();//Create arraylists
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
        fls.add(Arrays.copyOfRange(temp, 1, temp.length));//insert to arraylists
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
    lineLim=Math.max(lineLim, oH.size());//Setting line limit
    if (lineLim%batchSize!=0||oH.size()%batchSize!=0){throw new RuntimeException("batch error");}
    for (int i = 0; i < lineLim/batchSize; i++){
      out.add(
        new pair<oneHotMatrix, matrix>(
          new oneHotMatrix(batchSize, Arrays.copyOfRange(oHA, i*batchSize, i*batchSize+batchSize)), 
          new matrix(Arrays.copyOfRange(flA, i*batchSize, i*batchSize+batchSize)))//Splitting the arraylists up into chunks for conversion
      ); 
    }
    pair<oneHotMatrix, matrix>[] o = new pair[out.size()];
    for (int i = 0; i < out.size(); i++) o[i]=out.get(i);
    return o; //Return array of pairs
  }

  /**
   * Write coordinates to generate loss:iteration {@link https://www.desmos.com/calculator/egs1mljcxe graphs}
   * @param a
   * @throws IOException
   */
  public static void writeCoords(float[][] a){
    try {
      FileWriter fw = new FileWriter(".\\permFileLocs\\coords.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (float[] i:a){
        pw.print("("+i[0]+","+i[1]+"),");//Convert each float array into string and write to file
      }
      pw.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  /**
   * Write data to a test file according to the correct number and some float array data.
   * @param correctNum
   * @param data
   * @throws IOException
   */
  public static void writeTestCase(String correctNum, Float[] data){
    try {
      FileWriter fw = new FileWriter(".\\datasets\\user.csv", true);
      PrintWriter pw = new PrintWriter(fw);
      pw.print("\n"+correctNum+","+utilities.convertToCSV(data));//Write data to file
      pw.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    
  }

}
