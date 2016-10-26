// $ANTLR 3.5.2 ./src/VSLTreeParser.g 2016-10-26 16:48:14

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class VSLTreeParser extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASCII", "ASSIGN_KW", "COM", "COMMENT", 
		"DIGIT", "DIV", "DO_KW", "ELSE_KW", "FI_KW", "FUNC_KW", "IDENT", "IF_KW", 
		"INTEGER", "INT_KW", "LB", "LC", "LETTER", "LP", "MINUS", "MUL", "OD_KW", 
		"PLUS", "PRINT_KW", "PROTO_KW", "RB", "RC", "READ_KW", "RETURN_KW", "RP", 
		"TEXT", "THEN_KW", "VOID_KW", "WHILE_KW", "WS", "ARDECL", "ARELEM", "ARRAY", 
		"BLOCK", "BODY", "DECL", "FCALL", "FCALL_S", "INST", "NEGAT", "PARAM", 
		"PROG"
	};
	public static final int EOF=-1;
	public static final int ASCII=4;
	public static final int ASSIGN_KW=5;
	public static final int COM=6;
	public static final int COMMENT=7;
	public static final int DIGIT=8;
	public static final int DIV=9;
	public static final int DO_KW=10;
	public static final int ELSE_KW=11;
	public static final int FI_KW=12;
	public static final int FUNC_KW=13;
	public static final int IDENT=14;
	public static final int IF_KW=15;
	public static final int INTEGER=16;
	public static final int INT_KW=17;
	public static final int LB=18;
	public static final int LC=19;
	public static final int LETTER=20;
	public static final int LP=21;
	public static final int MINUS=22;
	public static final int MUL=23;
	public static final int OD_KW=24;
	public static final int PLUS=25;
	public static final int PRINT_KW=26;
	public static final int PROTO_KW=27;
	public static final int RB=28;
	public static final int RC=29;
	public static final int READ_KW=30;
	public static final int RETURN_KW=31;
	public static final int RP=32;
	public static final int TEXT=33;
	public static final int THEN_KW=34;
	public static final int VOID_KW=35;
	public static final int WHILE_KW=36;
	public static final int WS=37;
	public static final int ARDECL=38;
	public static final int ARELEM=39;
	public static final int ARRAY=40;
	public static final int BLOCK=41;
	public static final int BODY=42;
	public static final int DECL=43;
	public static final int FCALL=44;
	public static final int FCALL_S=45;
	public static final int INST=46;
	public static final int NEGAT=47;
	public static final int PARAM=48;
	public static final int PROG=49;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public VSLTreeParser(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public VSLTreeParser(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return VSLTreeParser.tokenNames; }
	@Override public String getGrammarFileName() { return "./src/VSLTreeParser.g"; }



	// $ANTLR start "s"
	// ./src/VSLTreeParser.g:9:1: s[SymbolTable symTab] returns [Code3a code] : e= expression[symTab] ;
	public final Code3a s(SymbolTable symTab) throws RecognitionException {
		Code3a code = null;


		ExpAttribute e =null;

		try {
			// ./src/VSLTreeParser.g:10:3: (e= expression[symTab] )
			// ./src/VSLTreeParser.g:10:5: e= expression[symTab]
			{
			pushFollow(FOLLOW_expression_in_s60);
			e=expression(symTab);
			state._fsp--;

			 code = e.code; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return code;
	}
	// $ANTLR end "s"



	// $ANTLR start "expression"
	// ./src/VSLTreeParser.g:13:1: expression[SymbolTable symTab] returns [ExpAttribute expAtt] : ( ^( PLUS e1= expression[symTab] e2= expression[symTab] ) | ^( MINUS e1= expression[symTab] e2= expression[symTab] ) | ^( MUL e1= expression[symTab] e2= expression[symTab] ) | ^( DIV e1= expression[symTab] e2= expression[symTab] ) |pe= primary_exp[symTab] );
	public final ExpAttribute expression(SymbolTable symTab) throws RecognitionException {
		ExpAttribute expAtt = null;


		ExpAttribute e1 =null;
		ExpAttribute e2 =null;
		ExpAttribute pe =null;

		try {
			// ./src/VSLTreeParser.g:14:3: ( ^( PLUS e1= expression[symTab] e2= expression[symTab] ) | ^( MINUS e1= expression[symTab] e2= expression[symTab] ) | ^( MUL e1= expression[symTab] e2= expression[symTab] ) | ^( DIV e1= expression[symTab] e2= expression[symTab] ) |pe= primary_exp[symTab] )
			int alt1=5;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt1=1;
				}
				break;
			case MINUS:
				{
				alt1=2;
				}
				break;
			case MUL:
				{
				alt1=3;
				}
				break;
			case DIV:
				{
				alt1=4;
				}
				break;
			case IDENT:
			case INTEGER:
				{
				alt1=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// ./src/VSLTreeParser.g:14:5: ^( PLUS e1= expression[symTab] e2= expression[symTab] )
					{
					match(input,PLUS,FOLLOW_PLUS_in_expression83); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression87);
					e1=expression(symTab);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression92);
					e2=expression(symTab);
					state._fsp--;

					match(input, Token.UP, null); 

					 
					      Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
					      VarSymbol temp = SymbDistrib.newTemp();
					      Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.ADD, temp, e1, e2);
					      expAtt = new ExpAttribute(ty, cod, temp);
					    
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:21:5: ^( MINUS e1= expression[symTab] e2= expression[symTab] )
					{
					match(input,MINUS,FOLLOW_MINUS_in_expression108); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression112);
					e1=expression(symTab);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression117);
					e2=expression(symTab);
					state._fsp--;

					match(input, Token.UP, null); 

					 
					      Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
					      VarSymbol temp = SymbDistrib.newTemp();
					      Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.SUB, temp, e1, e2);
					      expAtt = new ExpAttribute(ty, cod, temp);
					    
					}
					break;
				case 3 :
					// ./src/VSLTreeParser.g:28:5: ^( MUL e1= expression[symTab] e2= expression[symTab] )
					{
					match(input,MUL,FOLLOW_MUL_in_expression133); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression137);
					e1=expression(symTab);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression142);
					e2=expression(symTab);
					state._fsp--;

					match(input, Token.UP, null); 

					 
					      Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
					      VarSymbol temp = SymbDistrib.newTemp();
					      Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.MUL, temp, e1, e2);
					      expAtt = new ExpAttribute(ty, cod, temp);
					    
					}
					break;
				case 4 :
					// ./src/VSLTreeParser.g:35:5: ^( DIV e1= expression[symTab] e2= expression[symTab] )
					{
					match(input,DIV,FOLLOW_DIV_in_expression158); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression162);
					e1=expression(symTab);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression167);
					e2=expression(symTab);
					state._fsp--;

					match(input, Token.UP, null); 

					 
					      Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
					      VarSymbol temp = SymbDistrib.newTemp();
					      Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.DIV, temp, e1, e2);
					      expAtt = new ExpAttribute(ty, cod, temp);
					    
					}
					break;
				case 5 :
					// ./src/VSLTreeParser.g:42:5: pe= primary_exp[symTab]
					{
					pushFollow(FOLLOW_primary_exp_in_expression184);
					pe=primary_exp(symTab);
					state._fsp--;

					 expAtt = pe; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expAtt;
	}
	// $ANTLR end "expression"



	// $ANTLR start "primary_exp"
	// ./src/VSLTreeParser.g:46:1: primary_exp[SymbolTable symTab] returns [ExpAttribute expAtt] : ( INTEGER | IDENT );
	public final ExpAttribute primary_exp(SymbolTable symTab) throws RecognitionException {
		ExpAttribute expAtt = null;


		CommonTree INTEGER1=null;
		CommonTree IDENT2=null;

		try {
			// ./src/VSLTreeParser.g:47:3: ( INTEGER | IDENT )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==INTEGER) ) {
				alt2=1;
			}
			else if ( (LA2_0==IDENT) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// ./src/VSLTreeParser.g:47:5: INTEGER
					{
					INTEGER1=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_primary_exp211); 

					      ConstSymbol cs = new ConstSymbol(Integer.parseInt((INTEGER1!=null?INTEGER1.getText():null)));
					      expAtt = new ExpAttribute(Type.INT, new Code3a(), cs);
					    
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:52:5: IDENT
					{
					IDENT2=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_primary_exp223); 

					      Operand3a id = symTab.lookup((IDENT2!=null?IDENT2.getText():null));
					      expAtt = new ExpAttribute(id.type, new Code3a(), symTab.lookup((IDENT2!=null?IDENT2.getText():null)));
					    
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expAtt;
	}
	// $ANTLR end "primary_exp"

	// Delegated rules



	public static final BitSet FOLLOW_expression_in_s60 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_expression83 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression87 = new BitSet(new long[]{0x0000000002C14200L});
	public static final BitSet FOLLOW_expression_in_expression92 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MINUS_in_expression108 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression112 = new BitSet(new long[]{0x0000000002C14200L});
	public static final BitSet FOLLOW_expression_in_expression117 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MUL_in_expression133 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression137 = new BitSet(new long[]{0x0000000002C14200L});
	public static final BitSet FOLLOW_expression_in_expression142 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIV_in_expression158 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression162 = new BitSet(new long[]{0x0000000002C14200L});
	public static final BitSet FOLLOW_expression_in_expression167 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_primary_exp_in_expression184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_primary_exp211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_primary_exp223 = new BitSet(new long[]{0x0000000000000002L});
}
