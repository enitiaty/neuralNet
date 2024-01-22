/**
 * Class for derivative of Loss function with repect to each component of each layer.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class layerDerivs {
  private denseLayer l1;
  private denseLayer l2;
  private oneHotMatrix truth;

  /**
   * Takes in two layers, so that the derivatives can be calculated.
   * @param j
   * @param k
   */
  public layerDerivs(denseLayer j, denseLayer k){
    l1=j;
    l2=k;
  }

  /**
   * Update the first layer.
   * @param i
   */
  public void updateL1(denseLayer i){
    l1=i;
  }

  /**
   * Update the second layer.
   * @param i
   */
  public void updateL2(denseLayer i){
    l2=i;
  }

  /**
   * Sets the "truth matrix", a matrix whos rows denote what the correct output is.
   * @param t
   */
  public void setTruth(oneHotMatrix t){
    truth=t;
  }

  /**
   * Returns a matrix representing the derivative of loss with respect to the weight of layer 1.
   * @return matrix
   */
  public matrix l1WrtWeight(){
    return matrix.multiplyMatrix(l1.derivativeWrtWeight(), matrix.hadProd(delta.delta1(l2, truth), ReLU.derivative(l1.getOutput())));
  }

  /**
   * Returns a matrix representing the derivative of loss with respect to the bias of layer 1.
   * @return
   */  
  public rowVector l1WrtBias(){
    return matrix.hadProd(delta.delta1(l2, truth), ReLU.derivative(l1.getOutput())).toRowVector();
  }

  /**
   * Returns a matrix representing the derivative of loss with respect to the weight of layer 2.
   * @return
   */
  public matrix l2WrtWeight(){
    return matrix.multiplyMatrix(matrix.transpose(ReLU.forward(l1.getOutput())), lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth));
  }

  /**
   * Returns a matrix representing the derivative of loss with respect to the bias of layer 2.
   * @return
   */
  public rowVector l2WrtBias(){    
    return lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth).toRowVector();
  }

}
