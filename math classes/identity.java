/**
 * Class for the nxn identity matrix.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class identity extends matrix{

  /**
   * Constructs the nxn identity matrix.
   * @param size
   */
  public identity(int size){
    super(size, size);
    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        mat[i][j]=utilities.kDelta(i, j);//Uses the kroenecker delta, definition of identity matrix.
      }
    }
  }
}
