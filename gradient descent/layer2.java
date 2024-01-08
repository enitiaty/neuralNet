public class layer2 {
  public static matrix derivativeWrtWeight(layer_dense layer2, oneHot trueness){
    return matrix.multiplyMatrix(layer2.derivativeWrtWeight(), lossCatCrossEntropy.derivative(layer2.getOutput().toRowVector(), trueness));
  }
  public static rowVector derivativeWrtBias(layer_dense layer2, oneHot trueness){
    int dim = layer2.getWeights().getHeight();
    identity I = new identity(dim);
    return matrix.multiplyMatrix(lossCatCrossEntropy.derivative(layer2.getOutput().toRowVector(), trueness), I).toRowVector();
  }
}

