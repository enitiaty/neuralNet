public class vector extends matrix {
  
  public vector(int d){
    super(1, d);
  }

  public vector(float[] d){
    super(new float[][]{d});
  }

  public float getVal(int index){
    return mat[0][index];
  }

  public float[] convertVToArray(){
    return mat[0];
  }
}
