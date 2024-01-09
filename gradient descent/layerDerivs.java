public class layerDerivs {
  private denseLayer l1;
  private denseLayer l2;
  private oneHotMatrix truth;
  public layerDerivs(denseLayer j, denseLayer k){
    l1=j;
    l2=k;
  }

  public void setTruth(oneHotMatrix t){
    truth=t;
  }

  public matrix l1WrtWeight(){
    return matrix.multiplyMatrix(l1.derivativeWrtWeight(), matrix.hadProd(delta.delta1(l2, truth), ReLU.derivative(l1.getOutput())));
  }
  
  public rowVector l1WrtBias(){
    return matrix.hadProd(delta.delta1(l2, truth), ReLU.derivative(l1.getOutput())).toRowVector();
  }

  public matrix l2WrtWeight(){
    return matrix.multiplyMatrix(l2.derivativeWrtWeight(), lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth));
  }

  public rowVector l2WrtBias(){
    int dim = l2.getWeights().getHeight();
    identity I = new identity(dim);
    return matrix.multiplyMatrix(lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth), I).toRowVector();
  }

}
