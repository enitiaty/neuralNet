import java.lang.Math;

public class lossCatCrossEntropy implements loss {
  public static float calculateLoss(rowVector input, oneHot trueness){
    return (float)-Math.log(input.getVal(trueness.findTrue()));
  }  

  public static float calculateLoss(matrix input, oneHotMatrix trueness){
    float temp = 0;
    input = softmax.forward(input);
    for (int i = 0; i < input.getWidth(); i++){
      temp+=lossCatCrossEntropy.calculateLoss(new rowVector(input.convertToArray()[i]), new oneHot(trueness.convertToArray()[i]));
    }
    return temp;
  }

  public static rowVector derivativeWrtNet(rowVector input, oneHot trueness){
    return rowVector.rowVectorSum(softmax.forward(input).toRowVector(), matrix.scalarMultiply(-1.0f, trueness).toRowVector());
  }

  public static matrix derivativeWrtNet(matrix input, oneHotMatrix trueness){
    matrix n = softmax.forward(input);
    // System.out.println(n);
    n.addMatrix(matrix.scalarMultiply(-1, trueness));
    // n.printDims();
    // n.printDims();
    return n;
  }
}
