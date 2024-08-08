// Generated from Mx.g4 by ANTLR 4.13.2
package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MxParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_statement = 2, RULE_typeName = 3, 
		RULE_bracket = 4, RULE_varDef = 5, RULE_varTerm = 6, RULE_funcDef = 7, 
		RULE_parameterList = 8, RULE_classDef = 9, RULE_argumentList = 10, RULE_expression = 11, 
		RULE_atom = 12, RULE_literalMultiList = 13, RULE_literalList = 14, RULE_literalAtom = 15, 
		RULE_fString = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "statement", "typeName", "bracket", "varDef", "varTerm", 
			"funcDef", "parameterList", "classDef", "argumentList", "expression", 
			"atom", "literalMultiList", "literalList", "literalAtom", "fString"
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

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007199254741180L) != 0)) {
				{
				setState(39);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(34);
					varDef();
					setState(35);
					match(Semi);
					}
					break;
				case 2:
					{
					setState(37);
					classDef();
					}
					break;
				case 3:
					{
					setState(38);
					funcDef();
					}
					break;
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(LeftBrace);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5505440537950158716L) != 0)) {
				{
				{
				setState(47);
				statement();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDefStmtContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public VarDefStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinueStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PurExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public PurExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPurExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPurExprStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseStmtContext extends StatementContext {
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfElseStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfElseStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfElseStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreakStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlockStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlockStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEmptyStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext initialExpr;
		public ExpressionContext condiExpr;
		public ExpressionContext stepExpr;
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				block();
				}
				break;
			case 2:
				_localctx = new VarDefStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				varDef();
				setState(57);
				match(Semi);
				}
				break;
			case 3:
				_localctx = new IfElseStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				match(If);
				setState(60);
				match(LeftParen);
				setState(61);
				expression(0);
				setState(62);
				match(RightParen);
				setState(63);
				statement();
				setState(66);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(64);
					match(Else);
					setState(65);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				match(Break);
				setState(69);
				match(Semi);
				}
				break;
			case 5:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				match(Continue);
				setState(71);
				match(Semi);
				}
				break;
			case 6:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(72);
				match(Return);
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5504314638034584384L) != 0)) {
					{
					setState(73);
					expression(0);
					}
				}

				setState(76);
				match(Semi);
				}
				break;
			case 7:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(77);
				match(While);
				setState(78);
				match(LeftParen);
				setState(79);
				expression(0);
				setState(80);
				match(RightParen);
				setState(81);
				statement();
				}
				break;
			case 8:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				match(For);
				setState(84);
				match(LeftParen);
				setState(87);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(85);
					((ForStmtContext)_localctx).initialExpr = expression(0);
					}
					break;
				case 2:
					{
					setState(86);
					varDef();
					}
					break;
				}
				setState(89);
				match(Semi);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5504314638034584384L) != 0)) {
					{
					setState(90);
					((ForStmtContext)_localctx).condiExpr = expression(0);
					}
				}

				setState(93);
				match(Semi);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5504314638034584384L) != 0)) {
					{
					setState(94);
					((ForStmtContext)_localctx).stepExpr = expression(0);
					}
				}

				setState(97);
				match(RightParen);
				setState(98);
				statement();
				}
				break;
			case 9:
				_localctx = new PurExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(99);
				expression(0);
				setState(100);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(102);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<BracketContext> bracket() {
			return getRuleContexts(BracketContext.class);
		}
		public BracketContext bracket(int i) {
			return getRuleContext(BracketContext.class,i);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007199254741052L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(106);
					bracket();
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BracketContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBracket(this);
		}
	}

	public final BracketContext bracket() throws RecognitionException {
		BracketContext _localctx = new BracketContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bracket);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(LeftBracket);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5504314638034584384L) != 0)) {
				{
				setState(113);
				expression(0);
				}
			}

			setState(116);
			match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<VarTermContext> varTerm() {
			return getRuleContexts(VarTermContext.class);
		}
		public VarTermContext varTerm(int i) {
			return getRuleContext(VarTermContext.class,i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			typeName();
			setState(119);
			varTerm();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(120);
				match(T__0);
				setState(121);
				varTerm();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarTermContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public LiteralMultiListContext literalMultiList() {
			return getRuleContext(LiteralMultiListContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarTerm(this);
		}
	}

	public final VarTermContext varTerm() throws RecognitionException {
		VarTermContext _localctx = new VarTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(Identifier);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(128);
				match(Assign);
				setState(131);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LeftBrace:
					{
					setState(129);
					literalMultiList();
					}
					break;
				case New:
				case Null:
				case This:
				case True:
				case False:
				case LeftParen:
				case Add:
				case Sub:
				case LogicNot:
				case BitNot:
				case Increment:
				case Decrement:
				case Identifier:
				case Decimal:
				case StringConst:
				case FHead:
				case FAtom:
					{
					setState(130);
					expression(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDef(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(135);
				typeName();
				}
				break;
			}
			setState(138);
			match(Identifier);
			setState(139);
			match(LeftParen);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007199254741052L) != 0)) {
				{
				setState(140);
				parameterList();
				}
			}

			setState(143);
			match(RightParen);
			setState(144);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			typeName();
			setState(147);
			match(Identifier);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(148);
				match(T__0);
				setState(149);
				typeName();
				setState(150);
				match(Identifier);
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(Class);
			setState(158);
			match(Identifier);
			setState(159);
			match(LeftBrace);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007199254741052L) != 0)) {
				{
				setState(164);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(160);
					varDef();
					setState(161);
					match(Semi);
					}
					break;
				case 2:
					{
					setState(163);
					funcDef();
					}
					break;
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(169);
			match(RightBrace);
			setState(170);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArgumentList(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			expression(0);
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(173);
				match(T__0);
				setState(174);
				expression(0);
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Decrement() { return getToken(MxParser.Decrement, 0); }
		public TerminalNode Increment() { return getToken(MxParser.Increment, 0); }
		public SelfExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSelfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSelfExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode Leq() { return getToken(MxParser.Leq, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode Geq() { return getToken(MxParser.Geq, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode BitAnd() { return getToken(MxParser.BitAnd, 0); }
		public TerminalNode BitXor() { return getToken(MxParser.BitXor, 0); }
		public TerminalNode BitOr() { return getToken(MxParser.BitOr, 0); }
		public TerminalNode LogicAnd() { return getToken(MxParser.LogicAnd, 0); }
		public TerminalNode LogicOr() { return getToken(MxParser.LogicOr, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBinaryExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BracketExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public BracketExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBracketExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBracketExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FormattedStringContext extends ExpressionContext {
		public FStringContext fString() {
			return getRuleContext(FStringContext.class,0);
		}
		public FormattedStringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFormattedString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFormattedString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParentExprContext extends ExpressionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public ParentExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParentExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Increment() { return getToken(MxParser.Increment, 0); }
		public TerminalNode Decrement() { return getToken(MxParser.Decrement, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LogicNot() { return getToken(MxParser.LogicNot, 0); }
		public TerminalNode BitNot() { return getToken(MxParser.BitNot, 0); }
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnaryExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewTypeContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public LiteralMultiListContext literalMultiList() {
			return getRuleContext(LiteralMultiListContext.class,0);
		}
		public NewTypeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewType(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Question() { return getToken(MxParser.Question, 0); }
		public TerminalNode Colon() { return getToken(MxParser.Colon, 0); }
		public TernaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTernaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTernaryExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignExpr(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case This:
			case True:
			case False:
			case Identifier:
			case Decimal:
			case StringConst:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(181);
				atom();
				}
				break;
			case FHead:
			case FAtom:
				{
				_localctx = new FormattedStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182);
				fString();
				}
				break;
			case LeftParen:
				{
				_localctx = new ParentExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				match(LeftParen);
				setState(184);
				expression(0);
				setState(185);
				match(RightParen);
				}
				break;
			case Increment:
			case Decrement:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Increment || _la==Decrement) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(188);
				expression(16);
				}
				break;
			case Add:
			case Sub:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(189);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Add || _la==Sub) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(190);
				expression(15);
				}
				break;
			case LogicNot:
			case BitNot:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==LogicNot || _la==BitNot) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(192);
				expression(14);
				}
				break;
			case New:
				{
				_localctx = new NewTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(193);
				match(New);
				setState(194);
				typeName();
				setState(198);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(195);
					match(LeftParen);
					setState(196);
					match(RightParen);
					}
					break;
				case 2:
					{
					setState(197);
					literalMultiList();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(259);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(257);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(203);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1879048192L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(204);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(206);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(207);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(209);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(210);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(212);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64424509440L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(213);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(215);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(216);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(218);
						((BinaryExprContext)_localctx).op = match(BitAnd);
						setState(219);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(221);
						((BinaryExprContext)_localctx).op = match(BitXor);
						setState(222);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(224);
						((BinaryExprContext)_localctx).op = match(BitOr);
						setState(225);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(227);
						((BinaryExprContext)_localctx).op = match(LogicAnd);
						setState(228);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(230);
						((BinaryExprContext)_localctx).op = match(LogicOr);
						setState(231);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(232);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(233);
						match(Assign);
						setState(234);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new TernaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(236);
						match(Question);
						setState(237);
						expression(0);
						setState(238);
						match(Colon);
						setState(239);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(241);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(242);
						match(Dot);
						setState(243);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new BracketExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(245);
						match(LeftBracket);
						setState(246);
						expression(0);
						setState(247);
						match(RightBracket);
						}
						break;
					case 15:
						{
						_localctx = new FunctionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(249);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(250);
						match(LeftParen);
						setState(252);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5504314638034584384L) != 0)) {
							{
							setState(251);
							argumentList();
							}
						}

						setState(254);
						match(RightParen);
						}
						break;
					case 16:
						{
						_localctx = new SelfExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(255);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(256);
						((SelfExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Increment || _la==Decrement) ) {
							((SelfExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(261);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NullContext extends AtomContext {
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public NullContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNull(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TFContext extends AtomContext {
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public TFContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTF(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends AtomContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public IdentifierContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIdentifier(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecimalContext extends AtomContext {
		public TerminalNode Decimal() { return getToken(MxParser.Decimal, 0); }
		public DecimalContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDecimal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringConstContext extends AtomContext {
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public StringConstContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStringConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStringConst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ThisContext extends AtomContext {
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public ThisContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterThis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitThis(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_atom);
		int _la;
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case This:
				_localctx = new ThisContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(262);
				match(This);
				}
				break;
			case Null:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(Null);
				}
				break;
			case True:
			case False:
				_localctx = new TFContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(264);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Identifier:
				_localctx = new IdentifierContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(265);
				match(Identifier);
				}
				break;
			case Decimal:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(266);
				match(Decimal);
				}
				break;
			case StringConst:
				_localctx = new StringConstContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(267);
				match(StringConst);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralMultiListContext extends ParserRuleContext {
		public LiteralListContext literalList() {
			return getRuleContext(LiteralListContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public List<LiteralMultiListContext> literalMultiList() {
			return getRuleContexts(LiteralMultiListContext.class);
		}
		public LiteralMultiListContext literalMultiList(int i) {
			return getRuleContext(LiteralMultiListContext.class,i);
		}
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public LiteralMultiListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalMultiList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteralMultiList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteralMultiList(this);
		}
	}

	public final LiteralMultiListContext literalMultiList() throws RecognitionException {
		LiteralMultiListContext _localctx = new LiteralMultiListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_literalMultiList);
		int _la;
		try {
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				literalList();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				match(LeftBrace);
				setState(272);
				literalMultiList();
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(273);
					match(T__0);
					setState(274);
					literalMultiList();
					}
					}
					setState(279);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(280);
				match(RightBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralListContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<LiteralAtomContext> literalAtom() {
			return getRuleContexts(LiteralAtomContext.class);
		}
		public LiteralAtomContext literalAtom(int i) {
			return getRuleContext(LiteralAtomContext.class,i);
		}
		public LiteralListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteralList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteralList(this);
		}
	}

	public final LiteralListContext literalList() throws RecognitionException {
		LiteralListContext _localctx = new LiteralListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literalList);
		int _la;
		try {
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				match(LeftBrace);
				setState(285);
				match(RightBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(286);
				match(LeftBrace);
				setState(287);
				literalAtom();
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(288);
					match(T__0);
					setState(289);
					literalAtom();
					}
					}
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(295);
				match(RightBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralAtomContext extends ParserRuleContext {
		public TerminalNode Decimal() { return getToken(MxParser.Decimal, 0); }
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public LiteralAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteralAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteralAtom(this);
		}
	}

	public final LiteralAtomContext literalAtom() throws RecognitionException {
		LiteralAtomContext _localctx = new LiteralAtomContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literalAtom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 306244774661243136L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FStringContext extends ParserRuleContext {
		public TerminalNode FHead() { return getToken(MxParser.FHead, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode FTail() { return getToken(MxParser.FTail, 0); }
		public List<TerminalNode> FBody() { return getTokens(MxParser.FBody); }
		public TerminalNode FBody(int i) {
			return getToken(MxParser.FBody, i);
		}
		public TerminalNode FAtom() { return getToken(MxParser.FAtom, 0); }
		public FStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFString(this);
		}
	}

	public final FStringContext fString() throws RecognitionException {
		FStringContext _localctx = new FStringContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fString);
		try {
			int _alt;
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FHead:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(301);
				match(FHead);
				setState(307);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(302);
						expression(0);
						setState(303);
						match(FBody);
						}
						} 
					}
					setState(309);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				setState(310);
				expression(0);
				setState(311);
				match(FTail);
				}
				}
				break;
			case FAtom:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(FAtom);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 20);
		case 13:
			return precpred(_ctx, 19);
		case 14:
			return precpred(_ctx, 18);
		case 15:
			return precpred(_ctx, 17);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u013d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000(\b\u0000\n\u0000\f\u0000+\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0005\u00011\b\u0001\n\u0001\f\u0001"+
		"4\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002C\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002K\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"X\b\u0002\u0001\u0002\u0001\u0002\u0003\u0002\\\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002`\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002h\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0005\u0003l\b\u0003\n\u0003\f\u0003o\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0003\u0004s\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005{\b\u0005\n\u0005\f\u0005~\t"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0084"+
		"\b\u0006\u0003\u0006\u0086\b\u0006\u0001\u0007\u0003\u0007\u0089\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u008e\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u0099\b\b\n\b\f\b\u009c\t\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0005\t\u00a5\b\t\n\t\f\t\u00a8\t\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0005\n\u00b0\b\n\n\n\f\n\u00b3\t\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u00c7\b\u000b\u0003\u000b\u00c9\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00fd\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u0102\b\u000b\n\u000b\f\u000b\u0105\t\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u010d\b\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u0114\b\r\n\r\f\r\u0117\t\r\u0001\r\u0001\r\u0003\r"+
		"\u011b\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u0123\b\u000e\n\u000e\f\u000e\u0126\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u012a\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0132\b\u0010\n"+
		"\u0010\f\u0010\u0135\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u013b\b\u0010\u0001\u0010\u0001\u0133\u0001\u0016\u0011"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \u0000\n\u0002\u0000\u0002\u000555\u0001\u000001\u0001\u0000"+
		"\u001a\u001b\u0002\u0000((..\u0001\u0000\u001c\u001e\u0001\u0000)*\u0001"+
		"\u0000 #\u0001\u0000$%\u0001\u0000\u000e\u000f\u0004\u0000\b\b\u000e\u000f"+
		"66::\u016d\u0000)\u0001\u0000\u0000\u0000\u0002.\u0001\u0000\u0000\u0000"+
		"\u0004g\u0001\u0000\u0000\u0000\u0006i\u0001\u0000\u0000\u0000\bp\u0001"+
		"\u0000\u0000\u0000\nv\u0001\u0000\u0000\u0000\f\u007f\u0001\u0000\u0000"+
		"\u0000\u000e\u0088\u0001\u0000\u0000\u0000\u0010\u0092\u0001\u0000\u0000"+
		"\u0000\u0012\u009d\u0001\u0000\u0000\u0000\u0014\u00ac\u0001\u0000\u0000"+
		"\u0000\u0016\u00c8\u0001\u0000\u0000\u0000\u0018\u010c\u0001\u0000\u0000"+
		"\u0000\u001a\u011a\u0001\u0000\u0000\u0000\u001c\u0129\u0001\u0000\u0000"+
		"\u0000\u001e\u012b\u0001\u0000\u0000\u0000 \u013a\u0001\u0000\u0000\u0000"+
		"\"#\u0003\n\u0005\u0000#$\u00052\u0000\u0000$(\u0001\u0000\u0000\u0000"+
		"%(\u0003\u0012\t\u0000&(\u0003\u000e\u0007\u0000\'\"\u0001\u0000\u0000"+
		"\u0000\'%\u0001\u0000\u0000\u0000\'&\u0001\u0000\u0000\u0000(+\u0001\u0000"+
		"\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*,\u0001"+
		"\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000,-\u0005\u0000\u0000\u0001"+
		"-\u0001\u0001\u0000\u0000\u0000.2\u0005\u0017\u0000\u0000/1\u0003\u0004"+
		"\u0002\u00000/\u0001\u0000\u0000\u000014\u0001\u0000\u0000\u000020\u0001"+
		"\u0000\u0000\u000023\u0001\u0000\u0000\u000035\u0001\u0000\u0000\u0000"+
		"42\u0001\u0000\u0000\u000056\u0005\u0018\u0000\u00006\u0003\u0001\u0000"+
		"\u0000\u00007h\u0003\u0002\u0001\u000089\u0003\n\u0005\u00009:\u00052"+
		"\u0000\u0000:h\u0001\u0000\u0000\u0000;<\u0005\u0010\u0000\u0000<=\u0005"+
		"\u0013\u0000\u0000=>\u0003\u0016\u000b\u0000>?\u0005\u0014\u0000\u0000"+
		"?B\u0003\u0004\u0002\u0000@A\u0005\u0011\u0000\u0000AC\u0003\u0004\u0002"+
		"\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000Ch\u0001\u0000"+
		"\u0000\u0000DE\u0005\f\u0000\u0000Eh\u00052\u0000\u0000FG\u0005\r\u0000"+
		"\u0000Gh\u00052\u0000\u0000HJ\u0005\u0012\u0000\u0000IK\u0003\u0016\u000b"+
		"\u0000JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000Lh\u00052\u0000\u0000MN\u0005\u000b\u0000\u0000NO\u0005\u0013"+
		"\u0000\u0000OP\u0003\u0016\u000b\u0000PQ\u0005\u0014\u0000\u0000QR\u0003"+
		"\u0004\u0002\u0000Rh\u0001\u0000\u0000\u0000ST\u0005\n\u0000\u0000TW\u0005"+
		"\u0013\u0000\u0000UX\u0003\u0016\u000b\u0000VX\u0003\n\u0005\u0000WU\u0001"+
		"\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000Y[\u00052\u0000\u0000Z\\\u0003\u0016\u000b\u0000"+
		"[Z\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000"+
		"\u0000]_\u00052\u0000\u0000^`\u0003\u0016\u000b\u0000_^\u0001\u0000\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0005\u0014"+
		"\u0000\u0000bh\u0003\u0004\u0002\u0000cd\u0003\u0016\u000b\u0000de\u0005"+
		"2\u0000\u0000eh\u0001\u0000\u0000\u0000fh\u00052\u0000\u0000g7\u0001\u0000"+
		"\u0000\u0000g8\u0001\u0000\u0000\u0000g;\u0001\u0000\u0000\u0000gD\u0001"+
		"\u0000\u0000\u0000gF\u0001\u0000\u0000\u0000gH\u0001\u0000\u0000\u0000"+
		"gM\u0001\u0000\u0000\u0000gS\u0001\u0000\u0000\u0000gc\u0001\u0000\u0000"+
		"\u0000gf\u0001\u0000\u0000\u0000h\u0005\u0001\u0000\u0000\u0000im\u0007"+
		"\u0000\u0000\u0000jl\u0003\b\u0004\u0000kj\u0001\u0000\u0000\u0000lo\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"n\u0007\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000pr\u0005\u0015"+
		"\u0000\u0000qs\u0003\u0016\u000b\u0000rq\u0001\u0000\u0000\u0000rs\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0005\u0016\u0000\u0000"+
		"u\t\u0001\u0000\u0000\u0000vw\u0003\u0006\u0003\u0000w|\u0003\f\u0006"+
		"\u0000xy\u0005\u0001\u0000\u0000y{\u0003\f\u0006\u0000zx\u0001\u0000\u0000"+
		"\u0000{~\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000"+
		"\u0000\u0000}\u000b\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"\u007f\u0085\u00055\u0000\u0000\u0080\u0083\u0005/\u0000\u0000\u0081\u0084"+
		"\u0003\u001a\r\u0000\u0082\u0084\u0003\u0016\u000b\u0000\u0083\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0086\u0001"+
		"\u0000\u0000\u0000\u0085\u0080\u0001\u0000\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\r\u0001\u0000\u0000\u0000\u0087\u0089\u0003\u0006"+
		"\u0003\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u00055\u0000"+
		"\u0000\u008b\u008d\u0005\u0013\u0000\u0000\u008c\u008e\u0003\u0010\b\u0000"+
		"\u008d\u008c\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0014\u0000\u0000"+
		"\u0090\u0091\u0003\u0002\u0001\u0000\u0091\u000f\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0003\u0006\u0003\u0000\u0093\u009a\u00055\u0000\u0000\u0094"+
		"\u0095\u0005\u0001\u0000\u0000\u0095\u0096\u0003\u0006\u0003\u0000\u0096"+
		"\u0097\u00055\u0000\u0000\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u0094"+
		"\u0001\u0000\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u0098"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0011"+
		"\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0005\u0007\u0000\u0000\u009e\u009f\u00055\u0000\u0000\u009f\u00a6\u0005"+
		"\u0017\u0000\u0000\u00a0\u00a1\u0003\n\u0005\u0000\u00a1\u00a2\u00052"+
		"\u0000\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a5\u0003\u000e"+
		"\u0007\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a8\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\u0018"+
		"\u0000\u0000\u00aa\u00ab\u00052\u0000\u0000\u00ab\u0013\u0001\u0000\u0000"+
		"\u0000\u00ac\u00b1\u0003\u0016\u000b\u0000\u00ad\u00ae\u0005\u0001\u0000"+
		"\u0000\u00ae\u00b0\u0003\u0016\u000b\u0000\u00af\u00ad\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u0015\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b5\u0006\u000b\uffff"+
		"\uffff\u0000\u00b5\u00c9\u0003\u0018\f\u0000\u00b6\u00c9\u0003 \u0010"+
		"\u0000\u00b7\u00b8\u0005\u0013\u0000\u0000\u00b8\u00b9\u0003\u0016\u000b"+
		"\u0000\u00b9\u00ba\u0005\u0014\u0000\u0000\u00ba\u00c9\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bc\u0007\u0001\u0000\u0000\u00bc\u00c9\u0003\u0016\u000b"+
		"\u0010\u00bd\u00be\u0007\u0002\u0000\u0000\u00be\u00c9\u0003\u0016\u000b"+
		"\u000f\u00bf\u00c0\u0007\u0003\u0000\u0000\u00c0\u00c9\u0003\u0016\u000b"+
		"\u000e\u00c1\u00c2\u0005\u0006\u0000\u0000\u00c2\u00c6\u0003\u0006\u0003"+
		"\u0000\u00c3\u00c4\u0005\u0013\u0000\u0000\u00c4\u00c7\u0005\u0014\u0000"+
		"\u0000\u00c5\u00c7\u0003\u001a\r\u0000\u00c6\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c9\u0001\u0000\u0000\u0000\u00c8\u00b4\u0001\u0000\u0000\u0000"+
		"\u00c8\u00b6\u0001\u0000\u0000\u0000\u00c8\u00b7\u0001\u0000\u0000\u0000"+
		"\u00c8\u00bb\u0001\u0000\u0000\u0000\u00c8\u00bd\u0001\u0000\u0000\u0000"+
		"\u00c8\u00bf\u0001\u0000\u0000\u0000\u00c8\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c9\u0103\u0001\u0000\u0000\u0000\u00ca\u00cb\n\f\u0000\u0000\u00cb"+
		"\u00cc\u0007\u0004\u0000\u0000\u00cc\u0102\u0003\u0016\u000b\r\u00cd\u00ce"+
		"\n\u000b\u0000\u0000\u00ce\u00cf\u0007\u0002\u0000\u0000\u00cf\u0102\u0003"+
		"\u0016\u000b\f\u00d0\u00d1\n\n\u0000\u0000\u00d1\u00d2\u0007\u0005\u0000"+
		"\u0000\u00d2\u0102\u0003\u0016\u000b\u000b\u00d3\u00d4\n\t\u0000\u0000"+
		"\u00d4\u00d5\u0007\u0006\u0000\u0000\u00d5\u0102\u0003\u0016\u000b\n\u00d6"+
		"\u00d7\n\b\u0000\u0000\u00d7\u00d8\u0007\u0007\u0000\u0000\u00d8\u0102"+
		"\u0003\u0016\u000b\t\u00d9\u00da\n\u0007\u0000\u0000\u00da\u00db\u0005"+
		"+\u0000\u0000\u00db\u0102\u0003\u0016\u000b\b\u00dc\u00dd\n\u0006\u0000"+
		"\u0000\u00dd\u00de\u0005-\u0000\u0000\u00de\u0102\u0003\u0016\u000b\u0007"+
		"\u00df\u00e0\n\u0005\u0000\u0000\u00e0\u00e1\u0005,\u0000\u0000\u00e1"+
		"\u0102\u0003\u0016\u000b\u0006\u00e2\u00e3\n\u0004\u0000\u0000\u00e3\u00e4"+
		"\u0005&\u0000\u0000\u00e4\u0102\u0003\u0016\u000b\u0005\u00e5\u00e6\n"+
		"\u0003\u0000\u0000\u00e6\u00e7\u0005\'\u0000\u0000\u00e7\u0102\u0003\u0016"+
		"\u000b\u0004\u00e8\u00e9\n\u0002\u0000\u0000\u00e9\u00ea\u0005/\u0000"+
		"\u0000\u00ea\u0102\u0003\u0016\u000b\u0002\u00eb\u00ec\n\u0001\u0000\u0000"+
		"\u00ec\u00ed\u00054\u0000\u0000\u00ed\u00ee\u0003\u0016\u000b\u0000\u00ee"+
		"\u00ef\u00053\u0000\u0000\u00ef\u00f0\u0003\u0016\u000b\u0001\u00f0\u0102"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f2\n\u0014\u0000\u0000\u00f2\u00f3\u0005"+
		"\u0019\u0000\u0000\u00f3\u0102\u00055\u0000\u0000\u00f4\u00f5\n\u0013"+
		"\u0000\u0000\u00f5\u00f6\u0005\u0015\u0000\u0000\u00f6\u00f7\u0003\u0016"+
		"\u000b\u0000\u00f7\u00f8\u0005\u0016\u0000\u0000\u00f8\u0102\u0001\u0000"+
		"\u0000\u0000\u00f9\u00fa\n\u0012\u0000\u0000\u00fa\u00fc\u0005\u0013\u0000"+
		"\u0000\u00fb\u00fd\u0003\u0014\n\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000"+
		"\u00fe\u0102\u0005\u0014\u0000\u0000\u00ff\u0100\n\u0011\u0000\u0000\u0100"+
		"\u0102\u0007\u0001\u0000\u0000\u0101\u00ca\u0001\u0000\u0000\u0000\u0101"+
		"\u00cd\u0001\u0000\u0000\u0000\u0101\u00d0\u0001\u0000\u0000\u0000\u0101"+
		"\u00d3\u0001\u0000\u0000\u0000\u0101\u00d6\u0001\u0000\u0000\u0000\u0101"+
		"\u00d9\u0001\u0000\u0000\u0000\u0101\u00dc\u0001\u0000\u0000\u0000\u0101"+
		"\u00df\u0001\u0000\u0000\u0000\u0101\u00e2\u0001\u0000\u0000\u0000\u0101"+
		"\u00e5\u0001\u0000\u0000\u0000\u0101\u00e8\u0001\u0000\u0000\u0000\u0101"+
		"\u00eb\u0001\u0000\u0000\u0000\u0101\u00f1\u0001\u0000\u0000\u0000\u0101"+
		"\u00f4\u0001\u0000\u0000\u0000\u0101\u00f9\u0001\u0000\u0000\u0000\u0101"+
		"\u00ff\u0001\u0000\u0000\u0000\u0102\u0105\u0001\u0000\u0000\u0000\u0103"+
		"\u0101\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104"+
		"\u0017\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0106"+
		"\u010d\u0005\t\u0000\u0000\u0107\u010d\u0005\b\u0000\u0000\u0108\u010d"+
		"\u0007\b\u0000\u0000\u0109\u010d\u00055\u0000\u0000\u010a\u010d\u0005"+
		"6\u0000\u0000\u010b\u010d\u0005:\u0000\u0000\u010c\u0106\u0001\u0000\u0000"+
		"\u0000\u010c\u0107\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000\u0000"+
		"\u0000\u010c\u0109\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000"+
		"\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d\u0019\u0001\u0000\u0000"+
		"\u0000\u010e\u011b\u0003\u001c\u000e\u0000\u010f\u0110\u0005\u0017\u0000"+
		"\u0000\u0110\u0115\u0003\u001a\r\u0000\u0111\u0112\u0005\u0001\u0000\u0000"+
		"\u0112\u0114\u0003\u001a\r\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0114"+
		"\u0117\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000\u0115"+
		"\u0116\u0001\u0000\u0000\u0000\u0116\u0118\u0001\u0000\u0000\u0000\u0117"+
		"\u0115\u0001\u0000\u0000\u0000\u0118\u0119\u0005\u0018\u0000\u0000\u0119"+
		"\u011b\u0001\u0000\u0000\u0000\u011a\u010e\u0001\u0000\u0000\u0000\u011a"+
		"\u010f\u0001\u0000\u0000\u0000\u011b\u001b\u0001\u0000\u0000\u0000\u011c"+
		"\u011d\u0005\u0017\u0000\u0000\u011d\u012a\u0005\u0018\u0000\u0000\u011e"+
		"\u011f\u0005\u0017\u0000\u0000\u011f\u0124\u0003\u001e\u000f\u0000\u0120"+
		"\u0121\u0005\u0001\u0000\u0000\u0121\u0123\u0003\u001e\u000f\u0000\u0122"+
		"\u0120\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000\u0000\u0000\u0124"+
		"\u0122\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125"+
		"\u0127\u0001\u0000\u0000\u0000\u0126\u0124\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0005\u0018\u0000\u0000\u0128\u012a\u0001\u0000\u0000\u0000\u0129"+
		"\u011c\u0001\u0000\u0000\u0000\u0129\u011e\u0001\u0000\u0000\u0000\u012a"+
		"\u001d\u0001\u0000\u0000\u0000\u012b\u012c\u0007\t\u0000\u0000\u012c\u001f"+
		"\u0001\u0000\u0000\u0000\u012d\u0133\u0005;\u0000\u0000\u012e\u012f\u0003"+
		"\u0016\u000b\u0000\u012f\u0130\u0005<\u0000\u0000\u0130\u0132\u0001\u0000"+
		"\u0000\u0000\u0131\u012e\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000"+
		"\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000"+
		"\u0000\u0000\u0134\u0136\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000"+
		"\u0000\u0000\u0136\u0137\u0003\u0016\u000b\u0000\u0137\u0138\u0005=\u0000"+
		"\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139\u013b\u0005>\u0000\u0000"+
		"\u013a\u012d\u0001\u0000\u0000\u0000\u013a\u0139\u0001\u0000\u0000\u0000"+
		"\u013b!\u0001\u0000\u0000\u0000 \')2BJW[_gmr|\u0083\u0085\u0088\u008d"+
		"\u009a\u00a4\u00a6\u00b1\u00c6\u00c8\u00fc\u0101\u0103\u010c\u0115\u011a"+
		"\u0124\u0129\u0133\u013a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}