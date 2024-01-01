public class vector extends matrix {
  
  public vector(int d){
    super(1, d);
  }

  public vector(float[] d){
    super(new float[][]{d});
  }
}
