public class trainingMultiFiles {
  public static matrix o;
  public static void main(String[] args) {
    final float ETA = -0.02f;
    final int BATCHSIZE = 50;
    final int EPOCHCOUNT = 1;
    randomScalarTransform t = new randomScalarTransform(BATCHSIZE);
    final matrix TRANSFORMATION = t.returnTransform();
    denseLayer l1 = new denseLayer(784, 800);
    denseLayer l2 = new denseLayer(800, 10);
    // matrix[] a = fileIO.readWeights(".\\permFileLocs\\w1.txt");
    // rowVector[] b = fileIO.readBiases(".\\permFileLocs\\b1.txt");
    // denseLayer l1 = new denseLayer(a[0], b[0]);
    // denseLayer l2 = new denseLayer(a[1], b[1]);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;

    String[] paths = new String[]{".\\datasets\\mnist_train.csv", ".\\datasets\\mnist_trainALLSCALEDDOWN.csv"};
    
    
    for (String l:paths){
      pair[] data = fileIO.readTrainingData(BATCHSIZE, l, -1);

      for (pair i:data){
        
        count++;
        del.updateL1(l1);
        del.updateL2(l2);
        // System.out.println(matrix.multiplyMatrix(i.getsecond(), l1.getWeights()));
  
        del.setTruth(i.getfirst());
        l1.forward(i.getsecond());
        // l1.forward(matrix.multiplyMatrix(TRANSFORMATION, i.getsecond()));
        l2.forward(ReLU.forward(l1.getOutput()));

        rowVector l1B=del.l1WrtBias();
        matrix l1W=del.l1WrtWeight();
        rowVector l2B=del.l2WrtBias();
        matrix l2W=del.l2WrtWeight();
        


        l1.setWeights(
          matrix.matrixSum(
            l1.getWeights(),
            matrix.scalarMultiply(ETA, l1W))
        );
        // System.out.println(l1.getWeights());
        l1.setBias(
          matrix.matrixSum(
            l1.getBias(),
            matrix.scalarMultiply(ETA, l1B)
            )
          .toRowVector()  
        );
        // System.out.println(matrix.transpose(l2.getWeights()));
        // System.out.println(matrix.transpose(l2W));
        l2.setWeights(
          matrix.matrixSum(
            l2.getWeights(),
            matrix.scalarMultiply(ETA, l2W))
        );
        // System.out.println(matrix.transpose(l2.getWeights()));
        l2.setBias(
          matrix.matrixSum(
            l2.getBias(),
            matrix.scalarMultiply(ETA, l2B)
            )
            .toRowVector()  
        );
        // System.out.println(l1.getBias());
        System.out.println(count);
        float L=lossCatCrossEntropy.calculateLoss(l2.getOutput(), i.getfirst());
        System.out.println(L);
      }
    fileIO.dumpData(
    new matrix[] {l1.getWeights(), l2.getWeights()}, 
    new rowVector[] {l1.getBias(), l2.getBias()},
    ".\\permFileLocs\\w_HORIZONTAL.txt", ".\\permFileLocs\\b_HORIZONTAL.txt");
    // ".\\permFileLocs\\w"+(l%2+2)+".txt", ".\\permFileLocs\\b"+(l%2+2)+".txt");
    //l2.derivativeWrtWeight(), lossCatCrossEntropy.derivativeWrtNet(l2.getOutput(), truth)
    // System.out.println(matrix.scalarMultiply(1, del.l2WrtWeight()));
    
    // System.out.println(matrix.debugCount);
    
    
  }
  System.out.println("done");

}
  
}
