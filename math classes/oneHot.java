public class oneHot extends rowVector {

  public oneHot(int value){
    super(10);
  }

  public oneHot(float[] arr){
    super(arr);
  }



  public void assertTrue(int input){
    mat[0][input]=1;
  }

  public int findTrue(){
    return utilities.find(mat[0], 1);
  }


  
}
