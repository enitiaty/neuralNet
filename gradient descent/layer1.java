public class layer1 {
  public static matrix derivativeWrtWeight(layer_dense layer, oneHot trueness){
    return matrix.multiplyMatrix(layer.derivativeWrtWeight(), matrix.hadProd(delta.delta1(layer, trueness), ReLU.derivative(layer.getOutput())));
  }
  public static rowVector derivativeWrtBias(layer_dense layer, oneHot trueness){
    return matrix.hadProd(delta.delta1(layer, trueness), ReLU.derivative(layer.getOutput())).toRowVector();
  }
}
