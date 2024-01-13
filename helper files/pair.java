public class pair {
  public Object[] obj = new Object[2];
  public pair(oneHotMatrix o1, matrix o2){
    obj[0]=o1;
    obj[1]=o2;
  }
  public oneHotMatrix getfirst() {return (oneHotMatrix)obj[0];}
  public matrix getsecond() {return (matrix)obj[1];}
}
