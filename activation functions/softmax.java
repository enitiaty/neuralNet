import java.lang.Math;

public class softmax implements activations {
  
  public static matrix forward(matrix inputs){
    matrix out = new matrix(inputs.convertToArray());
    int m = inputs.getWidth();
    int n = inputs.getHeight();
    float[] total = new float[m];
    float highest;

    for (int i = 0; i < m; i++){
      float tempTot=0;
      highest=utilities.maxArr(out.convertToArray()[i]);
      for (int j = 0; j < n; j++){
        float temp = (float)Math.exp(out.getVal(i, j)-highest);
        out.setVal(temp, i, j);
        tempTot+=temp;
      }
      total[i]=tempTot;
    }
    for (int i = 0; i < m; i++){
      for (int j = 0; j < n; j++){
        out.setVal(out.getVal(i, j)/total[i], i, j);
      }
    }
    return out;
  }
}
