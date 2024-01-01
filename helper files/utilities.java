public class utilities {

  public static float maxArr(float[] inputs){
    float highest=-1f/0f;

    for (int i = 0; i < inputs.length; i++){
      if (inputs[i]>highest){
        highest=inputs[i];
      }
    }
    
    return highest;
  }
  
}
