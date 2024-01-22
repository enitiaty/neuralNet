/**
 * A class for row vectors.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class rowVector extends matrix {
  
   /**
   * Contructor, takes int d and contructs a row vector of that size.
   * @param d
   */
  public rowVector(int d){
    super(1, d);
  }

  /**
   * Constructor, takes float[] d and initializes vector elements to d.
   * @param d
   */
  public rowVector(float[] d){
    super(new float[][]{d});
  }

  /**
   * Get value at index.
   * @param index
   * @return float
   */
  public float getVal(int index){
    return mat[0][index];
  }

  /**
   * Set value at index.
   * @param val
   * @param index
   */
  public void setVal(float val, int index){
    mat[0][index]=val;
  }

  /**
   * Converts the row vector to an array.
   * @return float[]
   */
  public float[] convertRVToArray(){
    return mat[0];
  }
  
  /**
   * Returns the elementwise sum of 2 column vectors.
   * @param a
   * @param b
   * @return colVector
   */
  public static rowVector rowVectorSum(rowVector a, rowVector b){
    rowVector c = new rowVector(a.convertRVToArray());
    for (int i = 0; i < a.getHeight(); i++){
      c.setVal(a.getVal(i)+b.getVal(i), i);
    }
    return c;
  }
}
