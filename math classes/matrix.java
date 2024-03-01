import java.util.Arrays;
/**
 * A class for matricies and their operations.
 * @author enitiaty
 * @since 2024-01-21
 */
public class matrix {
  protected int width;
  protected int height;
  protected float[][] mat; // actual matrix
  public static long debugCount=0;

  /**
   * Initializes an empty matrix with size wxh.
   * @param w
   * @param h
   */  
  public matrix(int w, int h) {
    width = w;
    height = h;
    mat = new float[w][h];
  }

  /**
   * Initializes a matrix with elements d.
   * @param d
   */
  public matrix(float[][] d) {
    mat = d.clone();
    width = mat.length;
    height = mat[0].length;
  }

  /**
   * Prints the dimensions of the matrix object.
   */
  public void printDims() {
    System.out.println("" + width + "x" + height);
    ;
  }
  
  /**
   * Get number of rows of matrix.
   * @return int
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get number of columns of matrix.
   * @return int
   */
  public int getHeight() {
    return height;
  }

  /**
   * Set value at x, y.
   * @param val
   * @param x
   * @param y
   */
  public void setVal(float val, int x, int y) {
    mat[x][y] = val;
  }

  /**
   * Get value at x, y.
   * @param x
   * @param y
   * @return float
   */
  public float getVal(int x, int y) {
    return mat[x][y];
  }

  /**
   * Convert matrix to array.
   * @return float[][]
   */
  public float[][] convertToArray() {
    float[][] out = new float[mat.length][mat[0].length];
    for (int i = 0; i < mat.length; i++){
      out[i]=mat[i].clone();//Creates a deep copy.
    }
    return out;
  }

  /**
   * Adds external matrix to existing matrix elementwise.
   * @param a
   * @throws RunetimeException
   */
  public void addMatrix(matrix a) {
    if (width-a.getWidth()+height-a.getHeight()!=0){//Checks for dimension mismatch
      throw new RuntimeException("dimension mismatch "+a.getWidth()+"x"+a.getHeight()+","+width+"x"+height);
    }
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        mat[i][j] += a.getVal(i, j);
      }
    }
  }

  /**
   * Add a to each row of matrix.
   * @param a
   */
  public void matAddRowVector(rowVector a) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[i][j] += a.getVal(j);
      }
    }
  }

  /**
   * Add a to each col of matrix.
   * @param a
   */
  public void matAddColVector(colVector a) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[j][i] += a.getVal(j);
      }
    }
  }

  /**
   * Converts a matrix to a row matrix by summing all its columns.
   * @return rowVector
   */
  public rowVector toRowVector() {
    return new rowVector(utilities.sumCols(mat));
  }

  /**
   * Converts a matrix to a column matrix.
   * @return colVector
   */
  public colVector toColVector() {
    return new colVector(matrix.transpose(new matrix(mat)).convertToArray()[0]);//Gets row vector of transpose of matrix
  }

  /**
   * Multiplies each element in matrix by scalar.
   * @param scalar
   */
  public void multiplyScalar(float scalar) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        mat[i][j] *= scalar;
      }
    }
  }

  /**
   * Converts matrix to a string.
   * @return String
   */
  @Override
  public String toString() {
    String output = "";
    for (int i = 0; i < width; i++) {
      output += Arrays.toString(mat[i]) + "\n";
    }
    return output;
  }

  /**
   * Static method to multiply a scalar with a matrix elementwise.
   * @param scalar
   * @param a
   * @return matrix
   */
  public static matrix scalarMultiply(float scalar, matrix a) {
    matrix c = new matrix(a.convertToArray());
    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        c.setVal(c.getVal(i, j) * scalar, i, j);
      }
    }
    return c;
  }

  /**
   * Multiplies two matrix together by standard definition.
   * @param a
   * @param b
   * @return matrix
   * @throws RunteimException
   */
  public static matrix multiplyMatrix(matrix a, matrix b) {
    if (a.getHeight() != b.getWidth()) {//Checks for dimension mismatch
      throw new RuntimeException("size mismatch "+a.getWidth()+"x"+a.getHeight()+","+b.getWidth()+"x"+b.getHeight());
    }

    matrix ret = new matrix(a.getWidth(), b.getHeight());

    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < b.getHeight(); j++) {
        float temp = 0;
        for (int k = 0; k < a.getHeight(); k++) {
          temp += a.getVal(i, k) * b.getVal(k, j);
          debugCount++;//Counter for debugging
        }
        ret.setVal(temp, i, j);
      }
    }
    return ret;
  }

  /**
   * Adds two matricies together.
   * @param a
   * @param b
   * @return matrix
   */
  public static matrix matrixSum(matrix a, matrix b) {
    matrix c = new matrix(a.convertToArray());
    c.addMatrix(b);
    return c;
  }

  /**
   * Transposes the input matrix.
   * @param a
   * @return matrix
   */
  public static matrix transpose(matrix a) {
    matrix ret = new matrix(a.getHeight(), a.getWidth());

    for (int i = 0; i < a.getWidth(); i++) {
      for (int j = 0; j < a.getHeight(); j++) {
        ret.setVal(a.getVal(i, j), j, i);
      }
    }

    return ret;
  }

  /**
   * Hadamard product: matrix product defined elementwise
   * @param a
   * @param b
   * @return matrix
   * @throws RunetimeException
   */
  public static matrix hadProd(matrix a, matrix b) {
    if (a.getHeight() - b.getHeight() + a.getWidth() - b.getWidth() != 0) {//Checks for dimension mismatch
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
}
