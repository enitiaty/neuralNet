public class trainingVisualized {
  public static matrix o;
  final static int EPOCHCOUNT = 1;
  /**
   * Train through the data EPOCHCOUNT times
   * @param EPOCHCOUNT
   */
  public static void main(String[] args) {
    final float ETA = -0.02f;
    final int BATCHSIZE = 50; //The batch size
    denseLayer l1 = new denseLayer(784, 800);
    denseLayer l2 = new denseLayer(800, 10);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;
    for (String i: new String[]{".\\visualizations\\w1.txt", ".\\visualizations\\w2.txt"}){
      fileIO.flushFile(i);
    }

    
    pair<oneHotMatrix, matrix>[] data = fileIO.readTrainingData(BATCHSIZE, ".\\datasets\\mnist_trainALLSCALEDDOWN.csv", -1);//Read data


    for (int l = 0; l < EPOCHCOUNT; l++){

      for (pair<oneHotMatrix, matrix> i:data){
        
        count++;//Used to track process
        del.updateL1(l1);
        del.updateL2(l2);
  
        del.setTruth(i.getfirst());//Getting output of second layer
        l1.forward(i.getsecond());
        l2.forward(ReLU.forward(l1.getOutput())); 

        rowVector l1B=del.l1WrtBias();//Get derivatives of loss with respect to each component of each layer
        matrix l1W=del.l1WrtWeight();
        rowVector l2B=del.l2WrtBias();
        matrix l2W=del.l2WrtWeight();
        
        l1.setWeights(//Set components of each layer based on their derivatives
          matrix.matrixSum(
            l1.getWeights(),
            matrix.scalarMultiply(ETA, l1W))
        );
        l1.setBias(
          matrix.matrixSum(
            l1.getBias(),
            matrix.scalarMultiply(ETA, l1B)
            )
          .toRowVector()  
        );
        l2.setWeights(
          matrix.matrixSum(
            l2.getWeights(),
            matrix.scalarMultiply(ETA, l2W))
        );
        l2.setBias(
          matrix.matrixSum(
            l2.getBias(),
            matrix.scalarMultiply(ETA, l2B)
            )
            .toRowVector()  
        );

        System.out.println(count);//Keeps trach of progress in console
        fileIO.writeTo(".\\visualizations\\w1.txt", l1W);
        fileIO.writeTo(".\\visualizations\\w2.txt", l2W);
      }
      }
  System.out.println("done");
}
  
}
