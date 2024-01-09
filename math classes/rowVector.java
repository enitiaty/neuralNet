public class rowVector extends matrix {
  
  public rowVector(int d){
    super(1, d);
  }

  public rowVector(float[] d){
    super(new float[][]{d});
  }

  public float getVal(int index){
    return mat[0][index];
  }

  public void setVal(float val, int index){
    mat[0][index]=val;
  }

  public float[] convertRVToArray(){
    return mat[0];
  }

  public static rowVector rowVectorSum(rowVector a, rowVector b){
    rowVector c = new rowVector(a.convertRVToArray());
    for (int i = 0; i < a.getHeight(); i++){
      c.setVal(a.getVal(i)+b.getVal(i), i);
    }
    return c;
  }
}
