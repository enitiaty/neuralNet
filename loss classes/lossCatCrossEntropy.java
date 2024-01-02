import java.lang.Math;

public class lossCatCrossEntropy implements loss {
  public static float calculateLoss(vector input, oneHot trueness){
    return (float)-Math.log(input.getVal(trueness.findTrue()));
  }  
}
