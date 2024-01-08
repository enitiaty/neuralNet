public class oneHot extends rowVector {

  public oneHot(int value){
    super(10);
  }

  public oneHot(int[] values){
    super(oneHot.helper(values));

  }

  public void assertTrue(int input){
    mat[0][input]=1;
  }

  public int findTrue(){
    return utilities.find(mat[0], 1);
  }

  public static float[] helper(int[] values){

    float[] out = new float[10];

    for (int i: values){
      out[i]=1.0f;
    }

    return out;
  }
  
}
