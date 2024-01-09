public class delta {
  // public static matrix dLayer(layer_dense layer, int layerC, matrix d, oneHot trueness;){
  //   if (layerC == 0){
  //     return lossCatCrossEntropy.derivative(matrix, trueness);
  //   }
  //   // return delta.dLayer(layer, layerC, d)
  //   return matrix.multiplyMatrix(dLayer(layer, layerC, d), matrix.transpose(layer.getWeights()));

  // }

  public static matrix delta1(layer_dense layer, oneHot trueness){
    return matrix.multiplyMatrix(lossCatCrossEntropy.derivativeWrtNet(layer.getOutput(), trueness), matrix.transpose(layer.getWeights()));
  }
}
