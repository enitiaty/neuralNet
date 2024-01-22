/**
 * Class for RectiLinear Unit activation function, used mainly in layer 1 of the network.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class ReLU implements activations {

  /**
   * Runs the ReLU function on each element of an input matrix, and returns the output
   * @param inputs
   * @return matrix
   */
  public static matrix forward(matrix inputs) {
    matrix out = new matrix(inputs.convertToArray());//clone matrix
    for (int i = 0; i < out.getWidth(); i++){
      for (int j = 0; j < out.getHeight(); j++){
        out.setVal(Float.max(0, out.getVal(i, j)), i, j);//sets each element in matrix to max(element, 0)
      }
    }
    return out;
  }

  /**
   * Returns the derivative of the ReLU function with respect to an input matrix
   * @param inputs
   * @return matrix
   */
  public static matrix derivative(matrix inputs){
    matrix out = new matrix(inputs.convertToArray());//clone matrix
    for (int i = 0; i < out.getWidth(); i++){
      for(int j = 0; j < out.getHeight(); j++){
        if (out.getVal(i, j)>=0){
          out.setVal(1f, i, j);//if the element is greater than 0, set it to 1
        }
        else{
          out.setVal(0f, i, j);//else set it to 0
        }
      }
    }
    return out;
  }
  
}
