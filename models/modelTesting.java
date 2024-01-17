import java.util.Arrays;

public class modelTesting {
  denseLayer l1;
  denseLayer l2;
  public modelTesting(String pathToWeights, String pathToBias){
    matrix[] a = fileIO.readWeights(pathToWeights);
    rowVector[] b = fileIO.readBiases(pathToBias);
    l1 = new denseLayer(a[0], b[0]);
    l2 = new denseLayer(a[1], b[1]);
    // l1 = new denseLayer(784, 800);
    // l2 = new denseLayer(800, 10);
  }

  public matrix test(matrix input){
    return 
    softmax.forward(
      l2.calculateOut(
        ReLU.forward(
          l1.calculateOut(input))));
  }

  public int sureness(float[] in){
    return utilities.find(in, utilities.maxArr(in));
  }

  public int[] sureness(float[][] in){
    int[] out = new int[in.length];
    for (int i = 0; i < in.length; i++){
      out[i]=sureness(in[i]);
    }
    return out;
  }

  public static void main(String[] args) {
    modelTesting model = new modelTesting(".\\permFileLocs\\w2.txt", ".\\permFileLocs\\b2.txt");
    final int BATCHSIZE = 50;
    pair[] data = fileIO.readTrainingData(BATCHSIZE, ".\\permFileLocs\\mnist_test.csv", -1);
    int c = 0;
    int l=0;
    for (pair i: data){
      l++;
      float[][] t = i.getfirst().convertToArray();
      matrix d = i.getsecond();
      float[][] o = model.test(d).convertToArray();
      for (int j = 0; j < BATCHSIZE; j++){
        // System.out.println(Arrays.toString(t[j]));
        // System.out.println(Arrays.toString(o[j]));
        // System.out.println();
        // System.out.println(utilities.find(o[j], utilities.maxArr(o[j])));
        // System.out.println(utilities.find(t[j], 1));
        
        if (utilities.find(o[j], utilities.maxArr(o[j]))==utilities.find(t[j], 1)){
          c++;
        }
        
      }
      System.out.println(l);
      
    }
    System.out.println(c);
    
  }
}
