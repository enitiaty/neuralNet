public class pair<E, F> {
  public Object[] obj = new Object[2];
  public pair(Object o1, Object o2){
    obj[0]=o1;
    obj[1]=o2;
  }
  @SuppressWarnings("unchecked")
  public E getfirst(){return (E)obj[0];}
  @SuppressWarnings("unchecked")
  public F getsecond(){return (F)obj[1];}
  @Override
  @SuppressWarnings("unchecked")
  public String toString(){
    return "("+(E)obj[0]+", "+(F)obj[1]+")";
  }
}
