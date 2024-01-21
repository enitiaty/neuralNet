import javax.management.RuntimeErrorException;

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

  public static pair<Integer, Float>[] sortDecimal(pair<Integer, Float>[] input){
    pair<Integer, Float>[] out = input.clone();
    int len = out.length;
    for (int i = 0; i < len; i++){
      for (int j = 0; j < len-1; j++){
        if (out[j].getsecond()>out[j+1].getsecond()){
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

  public static pair<Integer, Float>[] assignIndex(float[] probs){
    pair<Integer, Float>[] j = new pair[probs.length];
    for (int i = 0; i < 10; i++) j[i]=new pair<Integer, Float>(i, probs[i]);
    return j;
  }

  public static String formatPair(pair<Integer, Float> in){
    return ""+in.getfirst()+": "+(int)(in.getsecond()*10000)/100.0+"%";
  }

  public static <E> String convertToCSV(E[] in){
    String out = "";
    for (int i = 0; i < in.length; i++){
      if (i!=in.length-1){out+=in[i]+",";}
      else out+=in[i]+"";
    }
    return out;
  }

  public static Float[] wrapFloat(float[] in){
    Float[] out = new Float[in.length];
    for (int i = 0; i < in.length; i++){out[i]=(Float)in[i];}
    return out;
  }
  
}
