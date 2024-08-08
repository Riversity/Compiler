// Generated from Mx.g4 by ANTLR 4.13.2
package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Void=2, Bool=3, Int=4, String=5, New=6, Class=7, Null=8, This=9, 
		For=10, While=11, Break=12, Continue=13, True=14, False=15, If=16, Else=17, 
		Return=18, LeftParen=19, RightParen=20, LeftBracket=21, RightBracket=22, 
		LeftBrace=23, RightBrace=24, Dot=25, Add=26, Sub=27, Mul=28, Div=29, Mod=30, 
		Arrow=31, Less=32, Leq=33, Greater=34, Geq=35, Equal=36, NotEqual=37, 
		LogicAnd=38, LogicOr=39, LogicNot=40, LeftShift=41, RightShift=42, BitAnd=43, 
		BitOr=44, BitXor=45, BitNot=46, Assign=47, Increment=48, Decrement=49, 
		Semi=50, Colon=51, Question=52, Identifier=53, Decimal=54, WhiteSpace=55, 
		BlockComment=56, LineComment=57, StringConst=58, FHead=59, FBody=60, FTail=61, 
		FAtom=62;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "Void", "Bool", "Int", "String", "New", "Class", "Null", "This", 
			"For", "While", "Break", "Continue", "True", "False", "If", "Else", "Return", 
			"LeftParen", "RightParen", "LeftBracket", "RightBracket", "LeftBrace", 
			"RightBrace", "Dot", "Add", "Sub", "Mul", "Div", "Mod", "Arrow", "Less", 
			"Leq", "Greater", "Geq", "Equal", "NotEqual", "LogicAnd", "LogicOr", 
			"LogicNot", "LeftShift", "RightShift", "BitAnd", "BitOr", "BitXor", "BitNot", 
			"Assign", "Increment", "Decrement", "Semi", "Colon", "Question", "Identifier", 
			"Decimal", "WhiteSpace", "BlockComment", "LineComment", "StringConst", 
			"EscapeChar", "FHead", "FBody", "FTail", "FAtom", "FChar"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'void'", "'bool'", "'int'", "'string'", "'new'", "'class'", 
			"'null'", "'this'", "'for'", "'while'", "'break'", "'continue'", "'true'", 
			"'false'", "'if'", "'else'", "'return'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'.'", "'+'", "'-'", "'*'", "'/'", "'%'", "'->'", "'<'", 
			"'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", 
			"'>>'", "'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "';'", "':'", 
			"'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Void", "Bool", "Int", "String", "New", "Class", "Null", 
			"This", "For", "While", "Break", "Continue", "True", "False", "If", "Else", 
			"Return", "LeftParen", "RightParen", "LeftBracket", "RightBracket", "LeftBrace", 
			"RightBrace", "Dot", "Add", "Sub", "Mul", "Div", "Mod", "Arrow", "Less", 
			"Leq", "Greater", "Geq", "Equal", "NotEqual", "LogicAnd", "LogicOr", 
			"LogicNot", "LeftShift", "RightShift", "BitAnd", "BitOr", "BitXor", "BitNot", 
			"Assign", "Increment", "Decrement", "Semi", "Colon", "Question", "Identifier", 
			"Decimal", "WhiteSpace", "BlockComment", "LineComment", "StringConst", 
			"FHead", "FBody", "FTail", "FAtom"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000>\u019c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001*\u0001*\u0001"+
		"+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00011\u00011\u00012\u00012\u00013\u00013\u0001"+
		"4\u00014\u00054\u0131\b4\n4\f4\u0134\t4\u00015\u00015\u00055\u0138\b5"+
		"\n5\f5\u013b\t5\u00015\u00035\u013e\b5\u00016\u00016\u00016\u00016\u0001"+
		"7\u00017\u00017\u00017\u00057\u0148\b7\n7\f7\u014b\t7\u00017\u00017\u0001"+
		"7\u00017\u00017\u00018\u00018\u00018\u00018\u00058\u0156\b8\n8\f8\u0159"+
		"\t8\u00018\u00018\u00019\u00019\u00019\u00059\u0160\b9\n9\f9\u0163\t9"+
		"\u00019\u00019\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0003:\u016d"+
		"\b:\u0001;\u0001;\u0001;\u0001;\u0005;\u0173\b;\n;\f;\u0176\t;\u0001;"+
		"\u0001;\u0001<\u0001<\u0005<\u017c\b<\n<\f<\u017f\t<\u0001<\u0001<\u0001"+
		"=\u0001=\u0005=\u0185\b=\n=\f=\u0188\t=\u0001=\u0001=\u0001>\u0001>\u0001"+
		">\u0001>\u0005>\u0190\b>\n>\f>\u0193\t>\u0001>\u0001>\u0001?\u0001?\u0001"+
		"?\u0001?\u0003?\u019b\b?\u0002\u0149\u0161\u0000@\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e"+
		"3g4i5k6m7o8q9s:u\u0000w;y<{=}>\u007f\u0000\u0001\u0000\b\u0002\u0000A"+
		"Zaz\u0004\u000009AZ__az\u0001\u000019\u0001\u000009\u0003\u0000\t\n\r"+
		"\r  \u0002\u0000\n\n\r\r\u0001\u0000\\\\\u0003\u0000\n\n\"\"$$\u01a8\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000"+
		"\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000"+
		"\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K"+
		"\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000"+
		"\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y"+
		"\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000"+
		"\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000"+
		"\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g"+
		"\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000"+
		"\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000"+
		"\u0000q\u0001\u0000\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000w"+
		"\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000"+
		"\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0001\u0081\u0001\u0000\u0000"+
		"\u0000\u0003\u0083\u0001\u0000\u0000\u0000\u0005\u0088\u0001\u0000\u0000"+
		"\u0000\u0007\u008d\u0001\u0000\u0000\u0000\t\u0091\u0001\u0000\u0000\u0000"+
		"\u000b\u0098\u0001\u0000\u0000\u0000\r\u009c\u0001\u0000\u0000\u0000\u000f"+
		"\u00a2\u0001\u0000\u0000\u0000\u0011\u00a7\u0001\u0000\u0000\u0000\u0013"+
		"\u00ac\u0001\u0000\u0000\u0000\u0015\u00b0\u0001\u0000\u0000\u0000\u0017"+
		"\u00b6\u0001\u0000\u0000\u0000\u0019\u00bc\u0001\u0000\u0000\u0000\u001b"+
		"\u00c5\u0001\u0000\u0000\u0000\u001d\u00ca\u0001\u0000\u0000\u0000\u001f"+
		"\u00d0\u0001\u0000\u0000\u0000!\u00d3\u0001\u0000\u0000\u0000#\u00d8\u0001"+
		"\u0000\u0000\u0000%\u00df\u0001\u0000\u0000\u0000\'\u00e1\u0001\u0000"+
		"\u0000\u0000)\u00e3\u0001\u0000\u0000\u0000+\u00e5\u0001\u0000\u0000\u0000"+
		"-\u00e7\u0001\u0000\u0000\u0000/\u00e9\u0001\u0000\u0000\u00001\u00eb"+
		"\u0001\u0000\u0000\u00003\u00ed\u0001\u0000\u0000\u00005\u00ef\u0001\u0000"+
		"\u0000\u00007\u00f1\u0001\u0000\u0000\u00009\u00f3\u0001\u0000\u0000\u0000"+
		";\u00f5\u0001\u0000\u0000\u0000=\u00f7\u0001\u0000\u0000\u0000?\u00fa"+
		"\u0001\u0000\u0000\u0000A\u00fc\u0001\u0000\u0000\u0000C\u00ff\u0001\u0000"+
		"\u0000\u0000E\u0101\u0001\u0000\u0000\u0000G\u0104\u0001\u0000\u0000\u0000"+
		"I\u0107\u0001\u0000\u0000\u0000K\u010a\u0001\u0000\u0000\u0000M\u010d"+
		"\u0001\u0000\u0000\u0000O\u0110\u0001\u0000\u0000\u0000Q\u0112\u0001\u0000"+
		"\u0000\u0000S\u0115\u0001\u0000\u0000\u0000U\u0118\u0001\u0000\u0000\u0000"+
		"W\u011a\u0001\u0000\u0000\u0000Y\u011c\u0001\u0000\u0000\u0000[\u011e"+
		"\u0001\u0000\u0000\u0000]\u0120\u0001\u0000\u0000\u0000_\u0122\u0001\u0000"+
		"\u0000\u0000a\u0125\u0001\u0000\u0000\u0000c\u0128\u0001\u0000\u0000\u0000"+
		"e\u012a\u0001\u0000\u0000\u0000g\u012c\u0001\u0000\u0000\u0000i\u012e"+
		"\u0001\u0000\u0000\u0000k\u013d\u0001\u0000\u0000\u0000m\u013f\u0001\u0000"+
		"\u0000\u0000o\u0143\u0001\u0000\u0000\u0000q\u0151\u0001\u0000\u0000\u0000"+
		"s\u015c\u0001\u0000\u0000\u0000u\u016c\u0001\u0000\u0000\u0000w\u016e"+
		"\u0001\u0000\u0000\u0000y\u0179\u0001\u0000\u0000\u0000{\u0182\u0001\u0000"+
		"\u0000\u0000}\u018b\u0001\u0000\u0000\u0000\u007f\u019a\u0001\u0000\u0000"+
		"\u0000\u0081\u0082\u0005,\u0000\u0000\u0082\u0002\u0001\u0000\u0000\u0000"+
		"\u0083\u0084\u0005v\u0000\u0000\u0084\u0085\u0005o\u0000\u0000\u0085\u0086"+
		"\u0005i\u0000\u0000\u0086\u0087\u0005d\u0000\u0000\u0087\u0004\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0005b\u0000\u0000\u0089\u008a\u0005o\u0000\u0000"+
		"\u008a\u008b\u0005o\u0000\u0000\u008b\u008c\u0005l\u0000\u0000\u008c\u0006"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0005i\u0000\u0000\u008e\u008f\u0005"+
		"n\u0000\u0000\u008f\u0090\u0005t\u0000\u0000\u0090\b\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005s\u0000\u0000\u0092\u0093\u0005t\u0000\u0000\u0093"+
		"\u0094\u0005r\u0000\u0000\u0094\u0095\u0005i\u0000\u0000\u0095\u0096\u0005"+
		"n\u0000\u0000\u0096\u0097\u0005g\u0000\u0000\u0097\n\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0005n\u0000\u0000\u0099\u009a\u0005e\u0000\u0000\u009a"+
		"\u009b\u0005w\u0000\u0000\u009b\f\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0005c\u0000\u0000\u009d\u009e\u0005l\u0000\u0000\u009e\u009f\u0005a"+
		"\u0000\u0000\u009f\u00a0\u0005s\u0000\u0000\u00a0\u00a1\u0005s\u0000\u0000"+
		"\u00a1\u000e\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005n\u0000\u0000\u00a3"+
		"\u00a4\u0005u\u0000\u0000\u00a4\u00a5\u0005l\u0000\u0000\u00a5\u00a6\u0005"+
		"l\u0000\u0000\u00a6\u0010\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005t\u0000"+
		"\u0000\u00a8\u00a9\u0005h\u0000\u0000\u00a9\u00aa\u0005i\u0000\u0000\u00aa"+
		"\u00ab\u0005s\u0000\u0000\u00ab\u0012\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0005f\u0000\u0000\u00ad\u00ae\u0005o\u0000\u0000\u00ae\u00af\u0005r"+
		"\u0000\u0000\u00af\u0014\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005w\u0000"+
		"\u0000\u00b1\u00b2\u0005h\u0000\u0000\u00b2\u00b3\u0005i\u0000\u0000\u00b3"+
		"\u00b4\u0005l\u0000\u0000\u00b4\u00b5\u0005e\u0000\u0000\u00b5\u0016\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b7\u0005b\u0000\u0000\u00b7\u00b8\u0005r\u0000"+
		"\u0000\u00b8\u00b9\u0005e\u0000\u0000\u00b9\u00ba\u0005a\u0000\u0000\u00ba"+
		"\u00bb\u0005k\u0000\u0000\u00bb\u0018\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0005c\u0000\u0000\u00bd\u00be\u0005o\u0000\u0000\u00be\u00bf\u0005n"+
		"\u0000\u0000\u00bf\u00c0\u0005t\u0000\u0000\u00c0\u00c1\u0005i\u0000\u0000"+
		"\u00c1\u00c2\u0005n\u0000\u0000\u00c2\u00c3\u0005u\u0000\u0000\u00c3\u00c4"+
		"\u0005e\u0000\u0000\u00c4\u001a\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005"+
		"t\u0000\u0000\u00c6\u00c7\u0005r\u0000\u0000\u00c7\u00c8\u0005u\u0000"+
		"\u0000\u00c8\u00c9\u0005e\u0000\u0000\u00c9\u001c\u0001\u0000\u0000\u0000"+
		"\u00ca\u00cb\u0005f\u0000\u0000\u00cb\u00cc\u0005a\u0000\u0000\u00cc\u00cd"+
		"\u0005l\u0000\u0000\u00cd\u00ce\u0005s\u0000\u0000\u00ce\u00cf\u0005e"+
		"\u0000\u0000\u00cf\u001e\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005i\u0000"+
		"\u0000\u00d1\u00d2\u0005f\u0000\u0000\u00d2 \u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0005e\u0000\u0000\u00d4\u00d5\u0005l\u0000\u0000\u00d5\u00d6\u0005"+
		"s\u0000\u0000\u00d6\u00d7\u0005e\u0000\u0000\u00d7\"\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d9\u0005r\u0000\u0000\u00d9\u00da\u0005e\u0000\u0000\u00da"+
		"\u00db\u0005t\u0000\u0000\u00db\u00dc\u0005u\u0000\u0000\u00dc\u00dd\u0005"+
		"r\u0000\u0000\u00dd\u00de\u0005n\u0000\u0000\u00de$\u0001\u0000\u0000"+
		"\u0000\u00df\u00e0\u0005(\u0000\u0000\u00e0&\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e2\u0005)\u0000\u0000\u00e2(\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005"+
		"[\u0000\u0000\u00e4*\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005]\u0000"+
		"\u0000\u00e6,\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005{\u0000\u0000\u00e8"+
		".\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005}\u0000\u0000\u00ea0\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ec\u0005.\u0000\u0000\u00ec2\u0001\u0000\u0000"+
		"\u0000\u00ed\u00ee\u0005+\u0000\u0000\u00ee4\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f0\u0005-\u0000\u0000\u00f06\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005"+
		"*\u0000\u0000\u00f28\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005/\u0000"+
		"\u0000\u00f4:\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005%\u0000\u0000\u00f6"+
		"<\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005-\u0000\u0000\u00f8\u00f9\u0005"+
		">\u0000\u0000\u00f9>\u0001\u0000\u0000\u0000\u00fa\u00fb\u0005<\u0000"+
		"\u0000\u00fb@\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005<\u0000\u0000\u00fd"+
		"\u00fe\u0005=\u0000\u0000\u00feB\u0001\u0000\u0000\u0000\u00ff\u0100\u0005"+
		">\u0000\u0000\u0100D\u0001\u0000\u0000\u0000\u0101\u0102\u0005>\u0000"+
		"\u0000\u0102\u0103\u0005=\u0000\u0000\u0103F\u0001\u0000\u0000\u0000\u0104"+
		"\u0105\u0005=\u0000\u0000\u0105\u0106\u0005=\u0000\u0000\u0106H\u0001"+
		"\u0000\u0000\u0000\u0107\u0108\u0005!\u0000\u0000\u0108\u0109\u0005=\u0000"+
		"\u0000\u0109J\u0001\u0000\u0000\u0000\u010a\u010b\u0005&\u0000\u0000\u010b"+
		"\u010c\u0005&\u0000\u0000\u010cL\u0001\u0000\u0000\u0000\u010d\u010e\u0005"+
		"|\u0000\u0000\u010e\u010f\u0005|\u0000\u0000\u010fN\u0001\u0000\u0000"+
		"\u0000\u0110\u0111\u0005!\u0000\u0000\u0111P\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0005<\u0000\u0000\u0113\u0114\u0005<\u0000\u0000\u0114R\u0001"+
		"\u0000\u0000\u0000\u0115\u0116\u0005>\u0000\u0000\u0116\u0117\u0005>\u0000"+
		"\u0000\u0117T\u0001\u0000\u0000\u0000\u0118\u0119\u0005&\u0000\u0000\u0119"+
		"V\u0001\u0000\u0000\u0000\u011a\u011b\u0005|\u0000\u0000\u011bX\u0001"+
		"\u0000\u0000\u0000\u011c\u011d\u0005^\u0000\u0000\u011dZ\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0005~\u0000\u0000\u011f\\\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0005=\u0000\u0000\u0121^\u0001\u0000\u0000\u0000\u0122\u0123"+
		"\u0005+\u0000\u0000\u0123\u0124\u0005+\u0000\u0000\u0124`\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0005-\u0000\u0000\u0126\u0127\u0005-\u0000\u0000"+
		"\u0127b\u0001\u0000\u0000\u0000\u0128\u0129\u0005;\u0000\u0000\u0129d"+
		"\u0001\u0000\u0000\u0000\u012a\u012b\u0005:\u0000\u0000\u012bf\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0005?\u0000\u0000\u012dh\u0001\u0000\u0000\u0000"+
		"\u012e\u0132\u0007\u0000\u0000\u0000\u012f\u0131\u0007\u0001\u0000\u0000"+
		"\u0130\u012f\u0001\u0000\u0000\u0000\u0131\u0134\u0001\u0000\u0000\u0000"+
		"\u0132\u0130\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000"+
		"\u0133j\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135"+
		"\u0139\u0007\u0002\u0000\u0000\u0136\u0138\u0007\u0003\u0000\u0000\u0137"+
		"\u0136\u0001\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a"+
		"\u013e\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c"+
		"\u013e\u00050\u0000\u0000\u013d\u0135\u0001\u0000\u0000\u0000\u013d\u013c"+
		"\u0001\u0000\u0000\u0000\u013el\u0001\u0000\u0000\u0000\u013f\u0140\u0007"+
		"\u0004\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0006"+
		"6\u0000\u0000\u0142n\u0001\u0000\u0000\u0000\u0143\u0144\u0005/\u0000"+
		"\u0000\u0144\u0145\u0005*\u0000\u0000\u0145\u0149\u0001\u0000\u0000\u0000"+
		"\u0146\u0148\t\u0000\u0000\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0148"+
		"\u014b\u0001\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u0149"+
		"\u0147\u0001\u0000\u0000\u0000\u014a\u014c\u0001\u0000\u0000\u0000\u014b"+
		"\u0149\u0001\u0000\u0000\u0000\u014c\u014d\u0005*\u0000\u0000\u014d\u014e"+
		"\u0005/\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0150\u0006"+
		"7\u0000\u0000\u0150p\u0001\u0000\u0000\u0000\u0151\u0152\u0005/\u0000"+
		"\u0000\u0152\u0153\u0005/\u0000\u0000\u0153\u0157\u0001\u0000\u0000\u0000"+
		"\u0154\u0156\b\u0005\u0000\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0156"+
		"\u0159\u0001\u0000\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0157"+
		"\u0158\u0001\u0000\u0000\u0000\u0158\u015a\u0001\u0000\u0000\u0000\u0159"+
		"\u0157\u0001\u0000\u0000\u0000\u015a\u015b\u00068\u0000\u0000\u015br\u0001"+
		"\u0000\u0000\u0000\u015c\u0161\u0005\"\u0000\u0000\u015d\u0160\u0003u"+
		":\u0000\u015e\u0160\b\u0006\u0000\u0000\u015f\u015d\u0001\u0000\u0000"+
		"\u0000\u015f\u015e\u0001\u0000\u0000\u0000\u0160\u0163\u0001\u0000\u0000"+
		"\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000"+
		"\u0000\u0162\u0164\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000"+
		"\u0000\u0164\u0165\u0005\"\u0000\u0000\u0165t\u0001\u0000\u0000\u0000"+
		"\u0166\u0167\u0005\\\u0000\u0000\u0167\u016d\u0005\"\u0000\u0000\u0168"+
		"\u0169\u0005\\\u0000\u0000\u0169\u016d\u0005\\\u0000\u0000\u016a\u016b"+
		"\u0005\\\u0000\u0000\u016b\u016d\u0005n\u0000\u0000\u016c\u0166\u0001"+
		"\u0000\u0000\u0000\u016c\u0168\u0001\u0000\u0000\u0000\u016c\u016a\u0001"+
		"\u0000\u0000\u0000\u016dv\u0001\u0000\u0000\u0000\u016e\u016f\u0005f\u0000"+
		"\u0000\u016f\u0170\u0005\"\u0000\u0000\u0170\u0174\u0001\u0000\u0000\u0000"+
		"\u0171\u0173\u0003\u007f?\u0000\u0172\u0171\u0001\u0000\u0000\u0000\u0173"+
		"\u0176\u0001\u0000\u0000\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174"+
		"\u0175\u0001\u0000\u0000\u0000\u0175\u0177\u0001\u0000\u0000\u0000\u0176"+
		"\u0174\u0001\u0000\u0000\u0000\u0177\u0178\u0005$\u0000\u0000\u0178x\u0001"+
		"\u0000\u0000\u0000\u0179\u017d\u0005$\u0000\u0000\u017a\u017c\u0003\u007f"+
		"?\u0000\u017b\u017a\u0001\u0000\u0000\u0000\u017c\u017f\u0001\u0000\u0000"+
		"\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000"+
		"\u0000\u017e\u0180\u0001\u0000\u0000\u0000\u017f\u017d\u0001\u0000\u0000"+
		"\u0000\u0180\u0181\u0005$\u0000\u0000\u0181z\u0001\u0000\u0000\u0000\u0182"+
		"\u0186\u0005$\u0000\u0000\u0183\u0185\u0003\u007f?\u0000\u0184\u0183\u0001"+
		"\u0000\u0000\u0000\u0185\u0188\u0001\u0000\u0000\u0000\u0186\u0184\u0001"+
		"\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0189\u0001"+
		"\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0189\u018a\u0005"+
		"\"\u0000\u0000\u018a|\u0001\u0000\u0000\u0000\u018b\u018c\u0005f\u0000"+
		"\u0000\u018c\u018d\u0005\"\u0000\u0000\u018d\u0191\u0001\u0000\u0000\u0000"+
		"\u018e\u0190\u0003\u007f?\u0000\u018f\u018e\u0001\u0000\u0000\u0000\u0190"+
		"\u0193\u0001\u0000\u0000\u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0191"+
		"\u0192\u0001\u0000\u0000\u0000\u0192\u0194\u0001\u0000\u0000\u0000\u0193"+
		"\u0191\u0001\u0000\u0000\u0000\u0194\u0195\u0005\"\u0000\u0000\u0195~"+
		"\u0001\u0000\u0000\u0000\u0196\u019b\u0003u:\u0000\u0197\u0198\u0005$"+
		"\u0000\u0000\u0198\u019b\u0005$\u0000\u0000\u0199\u019b\b\u0007\u0000"+
		"\u0000\u019a\u0196\u0001\u0000\u0000\u0000\u019a\u0197\u0001\u0000\u0000"+
		"\u0000\u019a\u0199\u0001\u0000\u0000\u0000\u019b\u0080\u0001\u0000\u0000"+
		"\u0000\u000e\u0000\u0132\u0139\u013d\u0149\u0157\u015f\u0161\u016c\u0174"+
		"\u017d\u0186\u0191\u019a\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}