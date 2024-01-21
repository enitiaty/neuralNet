public class delta {
  // public static matrix dLayer(denseLayer layer, int layerC, matrix d, oneHot trueness;){
  //   if (layerC == 0){
  //     return lossCatCrossEntropy.derivative(matrix, trueness);
  //   }
  //   // return delta.dLayer(layer, layerC, d)
  //   return matrix.multiplyMatrix(dLayer(layer, layerC, d), matrix.transpose(layer.getWeights()));

  // }

  public static matrix delta1(denseLayer layer, oneHotMatrix trueness){
    return matrix.multiplyMatrix(lossCatCrossEntropy.derivativeWrtNet(layer.getOutput(), trueness), matrix.transpose(layer.getWeights()));
  }
}
