public class layerDerivs {
  private denseLayer l1;
  private denseLayer l2;
  private oneHotMatrix truth;
  public layerDerivs(denseLayer j, denseLayer k){
    l1=j;
    l2=k;
  }
  public void updateL1(denseLayer i){l1=i;}
  public void updateL2(denseLayer i){l2=i;}

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
    // lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth).printDims();
    // l2.derivativeWrtWeight().printDims();
    // l1.getOutput().printDims();
    // ReLU.forward(l1.getOutput()).printDims();
    // System.out.println(ReLU.forward(l1.getOutput()));
    // System.out.println(lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth));
    return matrix.multiplyMatrix(matrix.transpose(ReLU.forward(l1.getOutput())),
    lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth));
    // return matrix.multiplyMatrix(l2.derivativeWrtWeight(), lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth));
  }

  public rowVector l2WrtBias(){    
    return lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth).toRowVector();
  }

}
