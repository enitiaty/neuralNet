/**
 * A class for training the model based on a set number of EPOCHS.
 * @author enitiaty
 * @since 2024-01-21
 */
public class trainingEpochs {
  public static matrix o;
  /**
   * Train through the data EPOCHCOUNT times
   * @param EPOCHCOUNT
   */
  public static void train(int EPOCHCOUNT) {
    final float ETA = -0.02f;
    final int BATCHSIZE = 1; //The batch size
    matrix[] a = fileIO.readWeights(".\\permFileLocs\\w_FINAL_copy.txt");//Load previously trained data
    rowVector[] b = fileIO.readBiases(".\\permFileLocs\\b_FINAL_copy.txt");
    denseLayer l1 = new denseLayer(a[0], b[0]);
    denseLayer l2 = new denseLayer(a[1], b[1]);
    layerDerivs del = new layerDerivs(l1, l2);
    int count = 0;

    
    pair<oneHotMatrix, matrix>[] data = fileIO.readTrainingData(BATCHSIZE, ".\\datasets\\user.csv", -1);//Read data
    float[][] coords = new float[data.length*EPOCHCOUNT][2];


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
        float L=lossCatCrossEntropy.calculateLoss(l2.getOutput(), i.getfirst());
        System.out.println(L);//Prints the loss
        coords[count-1][0]=(float)count-1;//Generated coords for iterations:loss graph
        coords[count-1][1]=L;
      }
    fileIO.dumpData(//Dump data after each epoch to prevent data loss
    new matrix[] {l1.getWeights(), l2.getWeights()}, 
    new rowVector[] {l1.getBias(), l2.getBias()},
    ".\\permFileLocs\\w_FINAL_copy.txt", ".\\permFileLocs\\b_FINAL_copy.txt",
    false);
  }
  fileIO.writeCoords(coords);
  System.out.println("done");
}
  
}
