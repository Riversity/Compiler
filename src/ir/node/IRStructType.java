package ir.node;

import java.util.ArrayList;

import static ir.util.IRUtil.arrayToString;

public class IRStructType extends IRType {
  public ArrayList<IRType> members;
  public IRStructType(String name, ArrayList<IRType> members) {
    super(name);
    this.members = members;
  }

  @Override
  public String toString() {
    return "{ " + arrayToString(members, ", ") + " }";
  }
}
