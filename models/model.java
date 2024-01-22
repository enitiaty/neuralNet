/**
 * A class holding the information required for the actual NN model itself.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class model {
  denseLayer l1, l2;
  /**
   * Reads the weights at the paths provided and creates 2 layers with the read data.
   * @param pathToWeights
   * @param pathToBias
   */
  public model(String pathToWeights, String pathToBias){
    matrix[] a = fileIO.readWeights(pathToWeights);
    rowVector[] b = fileIO.readBiases(pathToBias);
    l1 = new denseLayer(a[0], b[0]);
    l2 = new denseLayer(a[1], b[1]);
  }

  /**
   * Calculates the output of the NN given an input.
   * @param input
   * @return matrix
   */
  public matrix forward(matrix input){
    return 
    softmax.forward(//Runs through softmax
      l2.calculateOut(//Runs through layer 2
        ReLU.forward(//Runs through ReLU activation
          l1.calculateOut(input))));//Runs through layer 1
  }

  /**
   * float[] overload for matrix forward.
   * @param input
   * @return float[]
   */
  public float[] forward(float[] input){
    return forward(new matrix(new float[][]{input})).convertToArray()[0];
  }

  /**
   * Gives % accuracy on mnist_test dataset
   * @param args
   */
  public static void main(String[] args) {
    model model = new model(".\\permFileLocs\\w_HORIZONTAL.txt", ".\\permFileLocs\\b_HORIZONTAL.txt");//New model
    final int BATCHSIZE = 50;
    pair<oneHotMatrix, matrix>[] data = fileIO.readTrainingData(BATCHSIZE, ".\\datasets\\mnist_test.csv", -1);
    int c = 0;
    int l=0;
    for (pair<oneHotMatrix, matrix> i: data){
      l++;
      float[][] t = i.getfirst().convertToArray();
      matrix d = i.getsecond();
      float[][] o = model.forward(d).convertToArray();
      for (int j = 0; j < BATCHSIZE; j++){
        if (utilities.find(o[j], utilities.maxArr(o[j]))==utilities.find(t[j], 1)){
          c++;//Counts the ammount of times the the model was correct.
        }
      }
      System.out.println(l);//Prints progress (out of 10000/BATCHSIZE)
    }
    System.out.println(c);
    
  }
}
