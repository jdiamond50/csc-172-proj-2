import java.lang.Comparable;

public class HuffmanNode implements Comparable<HuffmanNode> {
  HuffmanNode left;
  HuffmanNode right;
  Byte val;
  Integer freq;

  public HuffmanNode(Byte val, Integer freq) {
    this.val = val;
    this.freq = freq;
  }

  public int getFreq() { return freq; }

  public Byte getVal() { return val; }

  public HuffmanNode getLeft() { return left; }

  public HuffmanNode getRight() { return right; }

  public void setLeft(HuffmanNode other) { left = other; }

  public void setRight(HuffmanNode other) { right = other; }

  public int compareTo(HuffmanNode other) { return freq.compareTo(other.getFreq()); }
}
