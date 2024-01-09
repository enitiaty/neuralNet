import java.lang.Math;

public class lossCatCrossEntropy implements loss {
  public static float calculateLoss(rowVector input, oneHot trueness){
    return (float)-Math.log(input.getVal(trueness.findTrue()));
  }  

  public static float calculateLoss(matrix input, oneHot trueness){
    float temp = 0;
    for (int i = 0; i < input.getWidth(); i++){
      temp+=lossCatCrossEntropy.calculateLoss(new rowVector(input.convertToArray()[i]), trueness);
    }
    return temp;
  }

  public static rowVector derivativeWrtNet(rowVector input, oneHot trueness){
    return rowVector.rowVectorSum(softmax.forward(input).toRowVector(), matrix.scalarMultiply(-1.0f, trueness).toRowVector());
  }

  public static matrix derivativeWrtNet(matrix input, oneHot trueness){
    matrix n = softmax.forward(input);
    n.matAddRowVector(matrix.scalarMultiply(-1, trueness).toRowVector());
    return n;
  }
}
