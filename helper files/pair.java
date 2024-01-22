/**
 * Custom java analogue to c++'s std::pair.
 * @author Jimmy Zhang
 * @since 2024-01-21
 */
public class pair<E, F> {
  public Object[] obj = new Object[2];
  /**
   * Constructor, sets the first and second element in object array;
   * @param o1
   * @param o2
   */
  public pair(Object o1, Object o2){
    obj[0]=o1;
    obj[1]=o2;
  }
  /**
   * Returns first element, typecasted to E.
   * @return E
   */
  @SuppressWarnings("unchecked")
  public E getfirst(){return (E)obj[0];}
  /**
   * Returns second element, typecasted to F.
   * @return F
   */
  @SuppressWarnings("unchecked")
  public F getsecond(){return (F)obj[1];}
  /**
   * Returns a string representing the pair, in the form "(element1, element2)"
   * @return String
   */
  @Override
  @SuppressWarnings("unchecked")
  public String toString(){
    return "("+(E)obj[0]+", "+(F)obj[1]+")";
  }
}
