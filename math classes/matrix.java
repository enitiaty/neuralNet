import java.util.Arrays;

public class matrix {
  private int width;
  private int height;
  protected float[][] mat; //actual matrix

  public matrix(int w, int h){
    width = w;
    height = h;
    mat = new float[w][h];
  }

  public matrix(float[][] d){
    mat=d.clone();
    width=mat.length;
    height=mat[0].length;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }

  public void setVal(float val, int x, int y){
    mat[x][y]=val;
  }
  
  public float getVal(int x, int y){
    return mat[x][y];
  }

  public float[][] convertToArray(){
    return mat;
  }

  public void addMatrix(matrix a){
    for (int i = 0; i < a.getWidth(); i++){
      for (int j = 0; j < a.getHeight(); j++){
        mat[i][j]+=a.getVal(i, j);
      }
    }
  }
  
  public void matAddVector(vector a){
    for (int i = 0; i < width; i++){
      for (int j = 0; j < height; j++){
        mat[i][j]+=a.getVal(j);
      }
    }  
  }

  public vector toVector(){
    return new vector(mat[0]);
  }

  public void multiplyScalar(float scalar){
    for (int i = 0; i < width; i++){
      for (int j = 0; j < height; j++){
        mat[i][j]*=scalar;
      }
    }
  }
  
  @Override
  public String toString(){
    String output="";
    for (int i = 0; i < width; i++){
      output+=Arrays.toString(mat[i])+"\n";
    }
    return output;
  }

  public static matrix scalarMultiply(float scalar, matrix a){
    matrix c = new matrix(a.convertToArray());
    for (int i = 0; i < a.getWidth(); i++){
      for (int j = 0; j < a.getHeight(); j++){
        c.setVal(c.getVal(i, j)*scalar, i, j);
      }
    }
    return c;
  }

  public static matrix multiplyMatrix(matrix a, matrix b){
    if (a.getHeight()!=b.getWidth()){
      throw new RuntimeException("size mismatch");
    }

    matrix ret = new matrix(a.getWidth(), b.getHeight());

    for (int i = 0; i < a.getWidth(); i++){
      for (int j = 0; j < b.getHeight(); j++){
        float temp = 0;
        for (int k = 0; k < a.getHeight(); k++){
          temp+=a.getVal(i, k)*b.getVal(k, j);
        }
        ret.setVal(temp, i, j);
      }
    }

    return ret;
  }
  
  public static matrix matrixSum(matrix a, matrix b){
    matrix c = new matrix(a.convertToArray());
    c.addMatrix(b);
    return c;
  }

  public static matrix transpose(matrix a){
    matrix ret = new matrix(a.getHeight(), a.getWidth());

    for (int i = 0; i < a.getWidth(); i++){
      for (int j = 0; j < a.getHeight(); j++){
        ret.setVal(a.getVal(i, j), j, i);
      }
    }

    return ret;
  }


}