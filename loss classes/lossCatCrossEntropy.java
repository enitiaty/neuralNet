import java.lang.Math;

public class lossCatCrossEntropy implements loss {
  public static float calculateLoss(rowVector input, oneHot trueness){
    return (float)-Math.log(input.getVal(trueness.findTrue()));
  }  

  public static rowVector derivative(rowVector input, oneHot trueness){
    return rowVector.rowVectorSum(softmax.forward(input).toRowVector(), matrix.scalarMultiply(-1.0f, trueness).toRowVector());
  }
}
