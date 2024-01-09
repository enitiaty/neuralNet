import java.lang.Math;


public class layer_dense {
  private matrix weights;
  private rowVector bias;
  private matrix output;
  private matrix inputs;

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
    bias = new rowVector(b);
  }

  public matrix getWeights(){
    return weights;
  }

  public rowVector getBias(){
    return bias;
  }

  // public void forward(rowVector input){
  //   System.out.println(input.toString());
  //   System.out.println(weights.toString());
  //   matrix out = matrix.multiplyMatrix(input, weights);
  //   out.addMatrix(biases);
  //   output=out.torowVector();
  // }



  public void forward(matrix input){
    inputs=input;
    // System.out.println(input.toString());
    // System.out.println(weights.toString());
    output = matrix.multiplyMatrix(input, weights);
    output.matAddRowVector(bias);
  }

  public matrix getOutput(){
    return output;
  }

  public matrix derivativeWrtWeight(){
    return matrix.transpose(inputs);
  }

  

}
