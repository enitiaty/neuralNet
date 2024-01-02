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

  public void setVal(float val, int index){
    mat[0][index]=val;
  }

  public float[] convertVToArray(){
    return mat[0];
  }

  public static vector vectorSum(vector a, vector b){
    vector c = new vector(a.convertVToArray());
    for (int i = 0; i < a.getHeight(); i++){
      c.setVal(a.getVal(i)+b.getVal(i), i);
    }
    return c;
  }
}
