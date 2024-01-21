public class randomScalarTransform implements transform{
  float[][] mat;
  public randomScalarTransform(int s){
    mat = new float[s][s];
    for (int i = 0; i < s; i++){
      mat[i][i]=(float)(Math.random()*0.99+0.01);
    }
  }


  @Override
  public matrix returnTransform() {
    return new matrix(mat);
  }

  
}
