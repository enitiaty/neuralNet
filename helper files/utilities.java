/**
 * A class of misc functions that are used potentially only once.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class utilities {

  /**
   * Finds highest float in an array of floats.
   * @param inputs
   * @return float
   */
  public static float maxArr(float[] inputs){
    float highest=-1f/0f; //Set highest to infinity

    for (int i = 0; i < inputs.length; i++){
      if (inputs[i]>highest){
        highest=inputs[i]; //Decrease if less than highest
      }
    }
    
    return highest;
  }
  
  /**
   * Sums all float in an array of floats.
   * @param inputs
   * @return
   */
  public static float sumArr(float[] inputs){
    float sum=0;//Set sum to 0

    for (int i = 0; i < inputs.length; i++){
      sum+=inputs[i];//Add element to sum variable
    }

    return sum;
  }

  /**
   * Sum columns in an array of floats.
   * @param inputs
   * @return float[]
   */
  public static float[] sumCols(float[][] inputs){
    float[] out = new float[inputs[0].length];//Set output float[] to 0
    for (int i = 0; i < inputs.length; i++){
      for (int j = 0; j < inputs[i].length; j++){
        out[j]+=inputs[i][j];//Add element to sum array
      }
    }
    return out;
  }
  
  /**
   * Overload for matrix
   * @param inputs
   * @return float[]
   */
  public static float[] sumCols(matrix inputs){
    return utilities.sumCols(inputs.convertToArray());
  }

  /**
   * Given an float array and an argument, returns first index of argument in array. Returns -1 if element is not found.
   * @param arr
   * @param arg
   * @return int
   */
  public static int find(float[] arr, float arg){
    for(int i = 0; i < arr.length; i++){
      if (arr[i]==arg){return i;}
    }
    return -1;
  }

  /**
   * Kroenecker delta, defined as 1 if i==j and 0 otherwise.
   * @param i
   * @param j
   * @return int
   */
  public static int kDelta(float i, float j){
    if (i==j) return 1;
    return 0;
  }
  
  /**
   * Kroenecker delta overload to int arguments.
   * @param i
   * @param j
   * @return int
   */
  public static int kDelta(int i, int j){
    if (i==j) return 1;
    return 0;
  }

  /**
   * Takes an array of strings, parses them, and returns an array of floats.
   * @param input
   * @return float[]
   */
  public static float[] convertStrToFloatArr(String[] input){
    float[] out = new float[input.length];
    for (int i = 0; i < input.length; i++){
      out[i]=Float.parseFloat(input[i]);
    }
    return out;
  }

  /**
   * Takes an array of pairs and sorts them by the second argument.
   * Input takes the form of (int, float) where int is the number and float is the certainty.
   * @param input
   * @return
   */
  public static pair<Integer, Float>[] sortDecimal(pair<Integer, Float>[] input){
    pair<Integer, Float>[] out = input.clone();//Clone the input
    int len = out.length;
    for (int i = 0; i < len; i++){
      for (int j = 0; j < len-1; j++){
        if (out[j].getsecond()>out[j+1].getsecond()){//Bubble sort according to float argument
          int index = out[j].getfirst();
          float π = out[j].getsecond();
          out[j].obj[0]=out[j+1].getfirst();
          out[j].obj[1]=out[j+1].getsecond();
          out[j+1].obj[0]=index;
          out[j+1].obj[1]=π;
        }
      }
    }
    return out;
  }

  /**
   * Assigns an index to each element in the input float array.
   * @param probs
   * @return pair<Integer, Float>[]
   */
  public static pair<Integer, Float>[] assignIndex(float[] probs){
    pair<Integer, Float>[] j = new pair[probs.length];//Create unchecked pair array
    for (int i = 0; i < 10; i++) j[i]=new pair<Integer, Float>(i, probs[i]); //Assigns an index to each element
    return j;
  }

    /**
     * Formats a pair into a readable format, rounded to 2 decimal places.
     * @param in
     * @return String
     */
  public static String formatPair(pair<Integer, Float> in){
    return ""+in.getfirst()+": "+(int)(in.getsecond()*10000)/100.0+"%";//Rounding + string shenanigans
  }

  /**
   * Converts an array of type E into a csv formatted string.
   * @param <E>
   * @param in
   * @return String
   */
  public static <E> String convertToCSV(E[] in){
    String out = "";
    for (int i = 0; i < in.length; i++){
      if (i!=in.length-1){out+=in[i]+",";}//Adds element in input to string, with comma if not last element
      else out+=in[i]+"";//Adds last element with no comma.
    }
    return out;
  }

  /**
   * Converts each element in float array to Float (float wrapper).
   * @param in
   * @return Float[]
   */
  public static Float[] wrapFloat(float[] in){
    Float[] out = new Float[in.length];
    for (int i = 0; i < in.length; i++){out[i]=(Float)in[i];}
    return out;
  }
  
}
