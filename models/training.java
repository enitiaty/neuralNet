

public class training {
  public static matrix o;
  public static void main(String[] args) {
    final float ETA = -0.001f;
    denseLayer l1 = new denseLayer(784, 800);
    denseLayer l2 = new denseLayer(800, 10);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;

    pair[] data = fileIO.readTrainingData(10, ".\\permFileLocs\\mnist_train.csv", -1);

    for (pair i:data){
      count++;
      del.updateL1(l1);
      del.updateL2(l2);
      // System.out.println(matrix.multiplyMatrix(i.getsecond(), l1.getWeights()));
 
      del.setTruth(i.getfirst());
      l1.forward(i.getsecond());
      l2.forward(ReLU.forward(l1.getOutput()));

      rowVector l1B=del.l1WrtBias();
      matrix l1W=del.l1WrtWeight();
      rowVector l2B=del.l2WrtBias();
      matrix l2W=del.l2WrtWeight();


      l1.setBias(
        matrix.matrixSum(
          l1.getBias(),
          matrix.scalarMultiply(ETA, l1B)
          )
        .toRowVector()  
      );
      // System.out.println(l1.getBias());
      // System.out.println(del.l1WrtBias());
      l1.setWeights(
        matrix.matrixSum(
          l1.getWeights(),
          matrix.scalarMultiply(ETA, l1W))
      );
      l2.setBias(
        matrix.matrixSum(
          l2.getBias(),
          matrix.scalarMultiply(ETA, l2B)).toRowVector()  
      );

      l2.setWeights(
        matrix.matrixSum(
          l2.getWeights(),
          matrix.scalarMultiply(ETA, l2W))
          // del.l2WrtWeight()
      );
      System.out.println(count);
      System.out.println(lossCatCrossEntropy.calculateLoss(l2.getOutput(), i.getfirst()));
    }
    //l2.derivativeWrtWeight(), lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth)
    // System.out.println(matrix.scalarMultiply(1, del.l2WrtWeight()));
    fileIO.dumpData(
    new matrix[] {l1.getWeights(), l2.getWeights()}, 
    new rowVector[] {l1.getBias(), l2.getBias()},
    ".\\permFileLocs\\w1.txt", ".\\permFileLocs\\b1.txt");
    System.out.println("done");
    // System.out.println(matrix.debugCount);

    
  }
  
}
