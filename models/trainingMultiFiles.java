/**
 * A class for training the model based on a set list of files.
 * @author enitiaty
 * @since 2024-01-21
 */
public class trainingMultiFiles {
  public static matrix o;

  /**
   * Actual training
   * @param args
   */
  public static void main(String[] args) {
    final float ETA = -0.02f;
    final int BATCHSIZE = 200;
    matrix[] a = fileIO.readWeights(".\\permFileLocs\\W_FINAL.txt");
    rowVector[] b = fileIO.readBiases(".\\permFileLocs\\b_FINAL.txt");
    denseLayer l1 = new denseLayer(a[0], b[0]);
    denseLayer l2 = new denseLayer(a[1], b[1]);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;

    String[] paths = new String[]{ ".\\datasets\\mnist_trainALLSCALEDDOWN.csv"};//Get paths of data
    
    
    for (String l:paths){
      pair<oneHotMatrix, matrix>[] data = fileIO.readTrainingData(BATCHSIZE, l, -1);//Get data

      for (pair<oneHotMatrix, matrix> i:data){
        
        count++;//used to track progress
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
        float L=lossCatCrossEntropy.calculateLoss(l2.getOutput(), i.getfirst());
        System.out.println(L);//Prints the loss
      }
    fileIO.dumpData(//Dump data after each epoch to prevent data loss
    new matrix[] {l1.getWeights(), l2.getWeights()}, 
    new rowVector[] {l1.getBias(), l2.getBias()},
    ".\\permFileLocs\\w_FINAL.txt", ".\\permFileLocs\\b_FINAL.txt",
    false);    
  }
  System.out.println("done");

}
  
}
