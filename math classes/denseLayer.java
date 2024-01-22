import java.lang.Math;

/**
 * The actual layers in the NN.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class denseLayer {
  private matrix weights;
  private rowVector bias;
  private matrix output;
  private matrix inputs;

  /**
   * Constructor, randomizes weights and initializes biases to 0.
   * @param inputCount
   * @param neuronCount
   */
  public denseLayer(int inputCount, int neuronCount){
    float[][] w = new float[inputCount][neuronCount];
    float[] b = new float[neuronCount];

    for (int i = 0; i < inputCount; i++){
      for (int j = 0; j < neuronCount; j++){
        w[i][j]=(float)(Math.random()*2-1);//Randomizes weight array
      }
    }

    weights = new matrix(w);//Creates and initializes new array for weights and biases.
    bias = new rowVector(b);
  }

  /**
   * Contructor, initializes weight and bias to given input arrays.
   * @param w
   * @param b
   */
  public denseLayer(float[][] w, float[] b){
    weights = new matrix(w);
    bias = new rowVector(b);
  }

  /**
   * Constructor, inializes weight and bias to given input matricies.
   * @param w
   * @param b
   */
  public denseLayer(matrix w, rowVector b){
    weights=w;
    bias=b;
  }

  /**
   * Get weights of layer.
   * @return matrix
   */
  public matrix getWeights(){
    return weights;
  }

  /**
   * Get bias of layer.
   * @return rowVector.
   */
  public rowVector getBias(){
    return bias;
  }

  /**
   * Sets weight of layer.
   * @param w
   */
  public void setWeights(matrix w){
    weights=w;
  }

  /**
   * Sets bias of layer.
   * @param b
   */
  public void setBias(rowVector b){
    bias=b;
  }

  /**
   * Calculates the output of a layer given an input.
   * @param input
   * @return matrix
   */
  public matrix calculateOut(matrix input){
    matrix a = matrix.multiplyMatrix(input, weights);//Multiply input*weights
    a.matAddRowVector(bias);
    return a;
  }

  /**
   * Returns the output matrix.
   * @return matrix
   */
  public matrix getOutput(){
    return output;
  }

  /**
   * Calculates output and stores it in output variable.
   * @param input
   */
  public void forward(matrix input){
    inputs = new matrix(input.convertToArray());
    matrix a = matrix.multiplyMatrix(input, weights);//Multiply input*weights
    a.matAddRowVector(new rowVector(bias.convertToArray()[0]));
    output = a;
  }

  /**
   * Returns the derivate of the layer with respect to the weight.
   * @return matrix
   */
  public matrix derivativeWrtWeight(){
    return matrix.transpose(inputs);
  }

  

}
