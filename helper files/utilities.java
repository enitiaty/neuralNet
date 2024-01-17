public class utilities {

  public static float maxArr(float[] inputs){
    float highest=-1f/0f;

    for (int i = 0; i < inputs.length; i++){
      if (inputs[i]>highest){
        highest=inputs[i];
      }
    }
    
    return highest;
  }
  
  public static float sumArr(float[] inputs){
    float sum=0;

    for (int i = 0; i < inputs.length; i++){
      sum+=inputs[i];
    }

    return sum;
  }

  public static float[] sumCols(float[][] inputs){
    float[] out = new float[inputs[0].length];
    for (int i = 0; i < inputs.length; i++){
      for (int j = 0; j < inputs[i].length; j++){
        out[j]+=inputs[i][j];
      }
    }
    return out;
  }
  
  public static float[] sumCols(matrix inputs){
    return utilities.sumCols(inputs.convertToArray());
  }


  public static int find(float[] arr, float arg){
    for(int i = 0; i < arr.length; i++){
      if (arr[i]==arg){return i;}
    }
    return -1;
  }

  public static int kDelta(float i, float j){
    if (i==j) return 1;
    return 0;
  }
  
  public static int kDelta(int i, int j){
    if (i==j) return 1;
    return 0;
  }

  public static float[] convertStrToFloatArr(String[] input){
    float[] out = new float[input.length];
    for (int i = 0; i < input.length; i++){
      out[i]=Float.parseFloat(input[i]);
    }
    return out;
  }
  
}
