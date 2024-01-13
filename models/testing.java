import javax.management.relation.Relation;

public class testing {
  public static void main(String[] args) {
    final float ETA = -0.01f;
    denseLayer l1 = new denseLayer(784, 800);
    denseLayer l2 = new denseLayer(800, 10);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;

    pair[] data = fileIO.readTrainingData(50, ".\\permFileLocs\\mnist_test.csv", 1000);

    for (pair i:data){
      count++;
      del.updateL1(l1);
      del.updateL2(l2);
      // i.getsecond().printDims();
      // System.out.println(i.getsecond().toString());
      del.setTruth(i.getfirst());
      l1.forward(i.getsecond());
      // System.out.println(l1.getOutput());
      // System.out.println(ReLU.forward(l1.getOutput()));
      l2.forward(ReLU.forward(l1.getOutput()));
      // System.out.println(l2.getOutput());
      // System.out.println(softmax.forward(l2.getOutput()));

      l1.setBias(
        matrix.matrixSum(
          l1.getBias(),
          matrix.scalarMultiply(ETA, del.l1WrtBias())).toRowVector()  
      );
      l1.setWeights(
        matrix.matrixSum(
          l1.getWeights(),
          matrix.scalarMultiply(ETA, del.l1WrtWeight()))
      );
      l2.setBias(
        matrix.matrixSum(
          l2.getBias(),
          matrix.scalarMultiply(ETA, del.l2WrtBias())).toRowVector()  
      );
      l2.setWeights(
        matrix.matrixSum(
          l2.getWeights(),
          matrix.scalarMultiply(ETA, del.l2WrtWeight()))
      );
      System.out.println(count);
    }
    fileIO.dumpData(
    new matrix[] {l1.getWeights(), l2.getWeights()}, 
    new rowVector[] {l1.getBias(), l2.getBias()},
    ".\\permFileLocs\\w.txt", ".\\permFileLocs\\b.txt");
    System.out.println("done");

    
  }
  
}
