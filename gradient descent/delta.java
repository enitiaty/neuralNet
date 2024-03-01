/**
 * Stores the derivative with respect to net of each layer.
 * @author enitiaty
 * @since 2024-01-21
 */
public class delta {
  /**
   * Returns a matrix delta 1 representing the derivative with respect to net of layer 1
   * @param layer
   * @param trueness
   * @return matrix
   */
  public static matrix delta1(denseLayer layer, oneHotMatrix trueness){
    return matrix.multiplyMatrix(lossCatCrossEntropy.derivativeWrtNet(layer.getOutput(), trueness), matrix.transpose(layer.getWeights()));
  }
}
