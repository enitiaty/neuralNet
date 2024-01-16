import java.util.Arrays;

public class matrix {
  protected int width;
  protected int height;
  protected float[][] mat; // actual matrix
  public static long debugCount=0;

  public matrix(int w, int h) {
    width = w;
    height = h;
    mat = new float[w][h];
  }

  public matrix(float[][] d) {
    mat = d.clone();
    width = mat.length;
    height = mat[0].length;
  }

  public void printDims() {
    System.out.println("" + width + "x" + height);
    ;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setVal(float val, int x, int y) {
    mat[x][y] = val;
  }

  public float getVal(int x, int y) {
    return mat[x][y];
  }

  public float[][] convertToArray() {
    float[][] out = new float[mat.length][mat[0].length];
    for (int i = 0; i < mat.length; i++){
      out[i]=mat[i].clone();
    }
    return out;
  }

  public void addMatrix(matrix a) {
    if (width-a.getWidth()+height-a.getHeight()!=0){
      throw new RuntimeException("dimension mismatch "+a.getWidth()+"x"+a.getHeight()+","+width+"x"+height);
    }
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        mat[i][j] += a.getVal(i, j);
      }
    }
  }

  public void matAddRowVector(rowVector a) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[i][j] += a.getVal(j);
      }
    }
  }

  public void matAddColVector(colVector a) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[j][i] += a.getVal(j);
      }
    }
  }

  public rowVector toRowVector() {
    return new rowVector(mat[0]);
  }

  public colVector toColVector() {
    return new colVector(matrix.transpose(new matrix(mat)).convertToArray()[0]);
  }

  public void multiplyScalar(float scalar) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[i][j] *= scalar;
      }
    }
  }

  @Override
  public String toString() {
    String output = "";
    for (int i = 0; i < width; i++) {
      output += Arrays.toString(mat[i]) + "\n";
    }
    return output;
  }

  public static matrix scalarMultiply(float scalar, matrix a) {
    matrix c = new matrix(a.convertToArray());
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        c.setVal(c.getVal(i, j) * scalar, i, j);
      }
    }
    return c;
  }

  public static matrix multiplyMatrix(matrix a, matrix b) {
    if (a.getHeight() != b.getWidth()) {
      throw new RuntimeException("size mismatch "+a.getWidth()+"x"+a.getHeight()+","+b.getWidth()+"x"+b.getHeight());
    }

    matrix ret = new matrix(a.getWidth(), b.getHeight());

    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < b.getHeight(); j++) {
        float temp = 0;
        for (int k = 0; k < a.getHeight(); k++) {
          temp += a.getVal(i, k) * b.getVal(k, j);
          debugCount++;
        }
        ret.setVal(temp, i, j);
      }
    }

    return ret;
  }

  public static matrix matrixSum(matrix a, matrix b) {
    matrix c = new matrix(a.convertToArray());
    c.addMatrix(b);
    return c;
  }

  public static matrix transpose(matrix a) {
    matrix ret = new matrix(a.getHeight(), a.getWidth());

    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        ret.setVal(a.getVal(i, j), j, i);
      }
    }

    return ret;
  }

  public static matrix hadProd(matrix a, matrix b) {
    if (a.getHeight() - b.getHeight() + a.getWidth() - b.getWidth() != 0) {
      throw new RuntimeException("dimension mismatch "+a.getWidth()+"x"+a.getHeight()+","+b.getWidth()+"x"+b.getHeight());
    }
    matrix out = new matrix(a.getWidth(), a.getHeight());
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        out.setVal(a.getVal(i, j) * b.getVal(i, j), i, j);
      }
    }
    return out;
  }
  
  public static matrix hadDiv(matrix a, matrix b) {
    if (a.getHeight() - b.getHeight() + a.getWidth() - b.getWidth() != 0) {
      throw new RuntimeException("dimension mismatch "+a.getWidth()+"x"+a.getHeight()+","+b.getWidth()+"x"+b.getHeight());
    }
    matrix out = new matrix(a.getWidth(), a.getHeight());
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        out.setVal(a.getVal(i, j) / b.getVal(i, j), i, j);
      }
    }
    return out;
  }
}
