import java.lang.Math;

/**
 * A class dedicated to calculating loss using categorical cross entropy.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class lossCatCrossEntropy implements loss {

  /**
   * Returns the cross categorical loss given a vector input and a one hot encoded vector trueness.
   * @param input
   * @param trueness
   * @return float
   */
  public static float calculateLoss(rowVector input, oneHot trueness){
    return (float)-Math.log(input.getVal(trueness.findTrue()));//Returns the negative natural logarithm of the element where trueness is 1
  }  
  
  /**
   * Returns the sum of the losses of each row, treated as a vector. Essentially a matrix overload.
   * @param input
   * @param trueness
   * @return float
   */
  public static float calculateLoss(matrix input, oneHotMatrix trueness){
    float temp = 0;
    input = softmax.forward(input);
    for (int i = 0; i < input.getWidth(); i++){
      temp+=lossCatCrossEntropy.calculateLoss(new rowVector(input.convertToArray()[i]), new oneHot(trueness.convertToArray()[i]));//Calculates loss and adds it to the sum
    }
    return temp;
  }

  /**
   * Returns the derivative of the loss function, with respect to input and a constant matrix trueness.
   * @param input
   * @param trueness
   * @return matrix
   */
  public static matrix derivativeWrtNet(matrix input, oneHotMatrix trueness){
    matrix n = softmax.forward(input);
    n.addMatrix(matrix.scalarMultiply(-1, trueness)); //Subtracts trueness from input
    return n;
  }
}
