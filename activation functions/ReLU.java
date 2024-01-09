public class ReLU implements activations {

  public static matrix forward(matrix inputs) {
    matrix out = new matrix(inputs.convertToArray());
    for (int i = 0; i < out.getWidth(); i++){
      for (int j = 0; j < out.getHeight(); j++){
        out.setVal(Float.max(0, out.getVal(i, j)), i, j);
      }
    }
    return out;
  }

  public static matrix derivative(matrix inputs){
    matrix out = new matrix(inputs.convertToArray());
    for (int i = 0; i < out.getWidth(); i++){
      for(int j = 0; j < out.getHeight(); j++){
        if (out.getVal(i, j)>=0){
          out.setVal(1f, i, j);
        }
        else{
          out.setVal(0f, i, j);
        }
      }
    }
    return out;
  }
  
}
