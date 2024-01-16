import java.lang.Math;


public class denseLayer {
  private matrix weights;
  private rowVector bias;
  private matrix output;
  private matrix inputs;

  public denseLayer(int inputCount, int neuronCount){

    float[][] w = new float[inputCount][neuronCount];
    float[] b = new float[neuronCount];

    for (int i = 0; i < inputCount; i++){
      for (int j = 0; j < neuronCount; j++){
        w[i][j]=(float)(Math.random()*2-1);
        b[j]=(float)(Math.random()*2-1);
      }
    }

    weights = new matrix(w);
    bias = new rowVector(b);
  }

  public denseLayer(float[][] w, float[] b){
    weights = new matrix(w);
    bias = new rowVector(b);
  }
  public denseLayer(matrix w, rowVector b){
    weights=w;
    bias=b;
  }

  public matrix getWeights(){
    return weights;
  }

  public rowVector getBias(){
    return bias;
  }

  public void setWeights(matrix w){
    weights=w;
  }
  public void setBias(rowVector b){
    bias=b;
  }

  // public void forward(rowVector input){
  //   System.out.println(input.toString());
  //   System.out.println(weights.toString());
  //   matrix out = matrix.multiplyMatrix(input, weights);
  //   out.addMatrix(biases);
  //   output=out.torowVector();
  // }

  public matrix calculateOut(matrix input){
    matrix a = matrix.multiplyMatrix(input, weights);
    a.matAddRowVector(bias);
    return a;
  }
  public matrix getOutput(){
    return output;
  }

  public void forward(matrix input){
    inputs=new matrix(input.convertToArray());
    matrix a = matrix.multiplyMatrix(input, weights);
    a.matAddRowVector(new rowVector(bias.convertToArray()[0]));
    // System.out.println(input.toString());
    // System.out.println(weights.toString());
    output = a;
  }


  public matrix derivativeWrtWeight(){
    return matrix.transpose(inputs);
  }

  

}
