import java.util.ArrayList;

public class JBitSet {
  ArrayList<Boolean> data;

  public JBitSet() {
    data = new ArrayList<Boolean>();
  }

  @SuppressWarnings("unchecked")
  public JBitSet(ArrayList<Boolean> initData) {
    data = (ArrayList<Boolean>) initData.clone();
  }

  public ArrayList<Boolean> getData() {
    return data;
  }

  public int length() {
    return data.size();
  }

  public void append(Boolean bit) {
    data.add(bit);
  }

  public String toString() {
    String result = "";
    for (boolean bool : data) result += bool ? "1" : "0";
    return result;
  }
}
