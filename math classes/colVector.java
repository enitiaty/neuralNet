
public class colVector extends matrix {
  public colVector(int d){
    super(d, 1);
  }

  public colVector(float[] d){
    super(new float[][]{d});
    int temp = width;
    width=height;
    mat=matrix.transpose(new matrix(new float[][]{d})).convertToArray();
    height=temp;
  }

  public float getVal(int index){
    return mat[index][0];
  }

  public void setVal(float val, int index){
    mat[index][0]=val;
  }

  public float[] convertCVToArray(){
    float[] out = new float[mat.length];
    for (int i = 0; i < mat.length; i++){
      out[i]=mat[i][0];
    }
    return out;
  }

  public static colVector colVectorSum(colVector a, colVector b){
    colVector c = new colVector(a.convertCVToArray());
    for (int i = 0; i < a.getHeight(); i++){
      c.setVal(a.getVal(i)+b.getVal(i), i);
    }
    return c;
  }
}
