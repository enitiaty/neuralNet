import java.lang.Math;
/**
 * Class for the softmax activation function, used to normalize the outputs from the second layer.
 * @author enitiaty
 * @since 2024-01-21
 */
public class softmax implements activations {

  /**
   * Runs the softmax function on each element of an input matrix, and returns the output
   * @param inputs
   * @return matrix
   */
  public static matrix forward(matrix inputs){
    matrix out = new matrix(inputs.convertToArray());//clone matrix
    int m = inputs.getWidth();
    int n = inputs.getHeight();
    float[] total = new float[m];//set temp variables
    float highest;

    for (int i = 0; i < m; i++){
      float tempTot=0;
      highest=utilities.maxArr(out.convertToArray()[i]);//get highest element in row of matrix
      for (int j = 0; j < n; j++){
        float temp = (float)Math.exp(out.getVal(i, j)-highest);//exponentiate each element
        out.setVal(temp, i, j);
        tempTot+=temp;//add each exponentiated element up
      }
      total[i]=tempTot;
    }
    for (int i = 0; i < m; i++){
      for (int j = 0; j < n; j++){
        out.setVal(out.getVal(i, j)/total[i], i, j);//divide each element by sum of elements in their row
      }//normalizes elements so that each row sums to 1
    }
    return out;
  }
}
