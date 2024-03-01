/**
 * A class for One hot encoded vectors, an vector where all the elements are 0, except one 1 which represents the correct answer.
 * @author enitiaty
 * @since 2024-01-21
 */
public class oneHot extends rowVector {

  /**
   * Initializes the one hot encoded vector to arr, assumes arr is formatted correctly.
   * @param arr
   */
  public oneHot(float[] arr){
    super(arr);
  }

  /**
   * Finds the index where the vector contains a 1.
   * @return int
   */
  public int findTrue(){
    return utilities.find(mat[0], 1);
  }
  
}
