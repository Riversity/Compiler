package ir.util;

import ir.node.stmt.IRBlock;

import java.util.ArrayList;
import java.util.HashMap;

public class IRGlobal {
  static int blockCount = 0;
  public HashMap<IRBlock, Integer> blockToNum;
  public ArrayList<IRBlock> numToBlock;
}
