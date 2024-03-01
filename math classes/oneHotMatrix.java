/**
 * A class for a matrix whose rows are one hot encoded.
 * @author enitiaty
 * @since 2024-01-21
 */
public class oneHotMatrix extends matrix{

  /**
   * Constructor, constructs a batchsizex10 matrix, with truth values specified by vals.
   * @param batchSize
   * @param vals
   */
  public oneHotMatrix(int batchSize, int[] vals){
    super(batchSize, 10);
    for (int i = 0; i < batchSize; i++){
      mat[i][vals[i]]=1;
    }
  }

  /**
   * Constructor, sets mat to m, assumes m is formatted correctly.
   * @param m
   */
  public oneHotMatrix(float[][] m){
    super(m);
  }

  /**
   * Get the index'th row of the matrix
   * @param index
   * @return oneHot
   */
  public oneHot getOneHot(int index){
    return new oneHot(mat[index]);
  }
  
}
