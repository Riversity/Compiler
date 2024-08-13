grammar Mx;

// Parser

program : (varDef ';' | classDef | funcDef)* EOF;

block : '{' statement* '}';

statement : block                                               # BlockStmt
    | varDef ';'                                                # VarDefStmt
    | If '(' expression ')' statement
        (Else statement)?                                       # IfElseStmt
    | Break ';'                                                 # BreakStmt
    | Continue ';'                                              # ContinueStmt
    | Return expression? ';'                                    # ReturnStmt
    | While '(' expression ')' statement                        # WhileStmt
    | For '(' (initialExpr = expression | varDef)? ';' (condiExpr = expression)? ';' (stepExpr = expression)? ')' statement # ForStmt
    | expression ';'                                            # PurExprStmt
    | ';'                                                       # EmptyStmt
    ;

typeName : type = (Int | Bool | String | Void | Identifier) bracket*;
bracket : '[' expression? ']';

varDef : typeName varTerm (',' varTerm)*;
varTerm : Identifier ('=' (literalMultiList | expression))?;

funcDef : typeName? Identifier '(' parameterList? ')' block;
parameterList : typeName Identifier (',' typeName Identifier)*;

classDef : Class Identifier '{' (varDef ';' | funcDef)* '}' ';' ;

argumentList : expression (',' expression)*;

expression : (This | Null | (True | False) |
              Identifier | Decimal | StringConst)   # AtomExpr
    | fString                                       # FormattedString
    | '(' expression ')'                            # ParentExpr
    | expression '.' Identifier                     # MemberExpr
    | expression '[' expression ']'                 # BracketExpr
    | expression '(' argumentList? ')'              # FunctionExpr
    | expression op = ('--' | '++')                 # SelfExpr
    | <assoc=right> op = ('++' | '--') expression   # UnaryExpr
    | <assoc=right> op = ('+' | '-') expression     # UnaryExpr
    | <assoc=right> op = ('!' | '~') expression     # UnaryExpr
    | <assoc=right> New typeName ('(' ')' | literalMultiList)?  # NewType

    | expression op = ('*' | '/' | '%') expression              # BinaryExpr
    | expression op = ('+' | '-') expression                    # BinaryExpr
    | expression op = ('<<' | '>>') expression                  # BinaryExpr
    | expression op = ('<' | '<=' | '>' | '>=') expression      # BinaryExpr

    | expression op = ('==' | '!=') expression  # BinaryExpr
    | expression op = '&' expression            # BinaryExpr
    | expression op = '^' expression            # BinaryExpr
    | expression op = '|' expression            # BinaryExpr

    | expression op = '&&' expression           # BinaryExpr
    | expression op = '||' expression           # BinaryExpr

    | <assoc=right> expression '=' expression   # AssignExpr
    | <assoc=right> expression '?' expression ':' expression # TernaryExpr
    ;

// Lexer

Void    : 'void';
Bool    : 'bool';
Int     : 'int';
String  : 'string';

New     : 'new';
Class   : 'class';
Null    : 'null';
This    : 'this';

For     : 'for';
While   : 'while';
Break   : 'break';
Continue: 'continue';

True    : 'true';
False   : 'false';

If      : 'if';
Else    : 'else';

Return  : 'return';
LeftParen   : '(';
RightParen  : ')';
LeftBracket : '[';
RightBracket: ']';
LeftBrace   : '{';
RightBrace  : '}';

Dot : '.';
Add     : '+';
Sub     : '-';
Mul     : '*';
Div     : '/';
Mod     : '%';

Arrow   : '->';

Less    : '<';
Leq     : '<=';
Greater : '>';
Geq     : '>=';
Equal   : '==';
NotEqual   : '!=';

LogicAnd   : '&&';
LogicOr    : '||';
LogicNot   : '!';

LeftShift  : '<<';
RightShift : '>>';

BitAnd     : '&';
BitOr      : '|';
BitXor     : '^';
BitNot     : '~';

Assign     : '=';
Increment  : '++';
Decrement  : '--';

Semi       : ';';
Colon      : ':';
Question   : '?';

Identifier  : [a-zA-Z][a-zA-Z0-9_]*;
Decimal : [1-9][0-9]* | '0';

WhiteSpace  : [ \t\r\n] -> skip;
BlockComment    : '/*' .*? '*/' -> skip;
LineComment     : '//' ~[\r\n]* -> skip;

StringConst : '"' (EscapeChar | ~[\\])*? '"';
fragment EscapeChar : '\\"' | '\\\\' | '\\n';

literalMultiList : literalList | '{' literalMultiList (',' literalMultiList)* '}';
literalList : '{' '}' | '{' literalAtom (',' literalAtom)* '}';
literalAtom : Decimal | StringConst | Null | True | False;

fString : (FHead (expression FBody)*? expression FTail) | FAtom;
FHead : 'f"' FChar* '$';
FBody : '$' FChar* '$';
FTail : '$' FChar* '"';
FAtom : 'f"' FChar* '"';
fragment FChar : EscapeChar | '$$' | ~[$"\n];
