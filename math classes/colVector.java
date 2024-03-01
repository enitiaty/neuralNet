/**
 * A class for column vectors.
 * @author enitiaty
 * @since 2024-01-21
 */
public class colVector extends matrix {

  /**
   * Contructor, takes int d and contructs a column vector of that size.
   * @param d
   */
  public colVector(int d){
    super(d, 1);
  }

  /**
   * Constructor, takes float[] d and initializes vector elements to d.
   * @param d
   */
  public colVector(float[] d){
    super(new float[][]{d}); //Creates dx1 matrix.
    int temp = width;
    width=height;
    mat=matrix.transpose(new matrix(new float[][]{d})).convertToArray();//Sets mat to d
    height=temp;
  }

  /**
   * Get value at index.
   * @param index
   * @return float
   */
  public float getVal(int index){
    return mat[index][0];
  }

  /**
   * Set value at index.
   * @param val
   * @param index
   */
  public void setVal(float val, int index){
    mat[index][0]=val;
  }

  /**
   * Converts the column vector to an array.
   * @return float[]
   */
  public float[] convertCVToArray(){
    float[] out = new float[mat.length];//Creates output array
    for (int i = 0; i < mat.length; i++){
      out[i]=mat[i][0];//Sets each element
    }
    return out;
  }

  /**
   * Returns the elementwise sum of 2 column vectors.
   * @param a
   * @param b
   * @return colVector
   */
  public static colVector colVectorSum(colVector a, colVector b){
    colVector c = new colVector(a.convertCVToArray());
    for (int i = 0; i < a.getHeight(); i++){
      c.setVal(a.getVal(i)+b.getVal(i), i);
    }
    return c;
  }
}
