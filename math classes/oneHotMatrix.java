public class oneHotMatrix extends matrix{
  public oneHotMatrix(int batchSize, int[] vals){
    super(batchSize, 10);
    for (int i = 0; i < batchSize; i++){
      mat[i][vals[i]]=1;
    }
  }
  public oneHotMatrix(float[][] m){
    super(m);
  }

  public oneHot getOneHot(int index){
    return new oneHot(mat[index]);
  }
  
}
