package ir.node.ins;

import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import ir.node.IRType;

import java.util.ArrayList;

public class IRCall extends IRBaseInst {
  public IRVarInfo dest;
  public IRType type;
  public String funcName;
  public ArrayList<IRBaseInfo> args;
}
