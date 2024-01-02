import java.lang.Math;


public class layer_dense {
  private matrix weights;
  private vector bias;
  private matrix output;

  public layer_dense(int inputCount, int neuronCount){

    float[][] w = new float[inputCount][neuronCount];
    float[] b = new float[neuronCount];

    for (int i = 0; i < inputCount; i++){
      for (int j = 0; j < neuronCount; j++){
        w[i][j]=(float)(Math.random()*2-1);
        b[j]=0;
      }
    }

    weights = new matrix(w);
    bias = new vector(b);
  }

  public matrix getWeights(){
    return weights;
  }

  // public void forward(vector input){
  //   System.out.println(input.toString());
  //   System.out.println(weights.toString());
  //   matrix out = matrix.multiplyMatrix(input, weights);
  //   out.addMatrix(biases);
  //   output=out.toVector();
  // }

  public void forward(matrix input){
    System.out.println(input.toString());
    System.out.println(weights.toString());
    output = matrix.multiplyMatrix(input, weights);
    output.matAddVector(bias);
  }

  public matrix getOutput(){
    return output;
  }
  

}
