public class identity extends matrix{
  public identity(int size){
    super(size, size);
    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        mat[i][j]=utilities.kDelta(i, j);
      }
    }
  }
}
