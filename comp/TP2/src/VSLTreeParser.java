// $ANTLR 3.5.2 ./src/VSLTreeParser.g 2016-10-28 15:28:32

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
	// ./src/VSLTreeParser.g:9:1: s[SymbolTable ts] returns [Code3a code] : ( ( unit[ts] )+ ) ;
	public final Code3a s(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a unit1 =null;

		code = new Code3a();
		try {
			// ./src/VSLTreeParser.g:10:2: ( ( ( unit[ts] )+ ) )
			// ./src/VSLTreeParser.g:10:4: ( ( unit[ts] )+ )
			{
			// ./src/VSLTreeParser.g:10:4: ( ( unit[ts] )+ )
			// ./src/VSLTreeParser.g:10:5: ( unit[ts] )+
			{
			// ./src/VSLTreeParser.g:10:5: ( unit[ts] )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==FUNC_KW||LA1_0==PROTO_KW) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// ./src/VSLTreeParser.g:10:6: unit[ts]
					{
					pushFollow(FOLLOW_unit_in_s61);
					unit1=unit(ts);
					state._fsp--;

					code.append(unit1);
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

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



	// $ANTLR start "unit"
	// ./src/VSLTreeParser.g:13:1: unit[SymbolTable ts] returns [Code3a code] : ( function[ts] | proto[ts] );
	public final Code3a unit(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a function2 =null;

		try {
			// ./src/VSLTreeParser.g:14:2: ( function[ts] | proto[ts] )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==FUNC_KW) ) {
				alt2=1;
			}
			else if ( (LA2_0==PROTO_KW) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// ./src/VSLTreeParser.g:14:4: function[ts]
					{
					pushFollow(FOLLOW_function_in_unit85);
					function2=function(ts);
					state._fsp--;

					code = function2;
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:15:4: proto[ts]
					{
					pushFollow(FOLLOW_proto_in_unit93);
					proto(ts);
					state._fsp--;

					code = new Code3a();
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
		return code;
	}
	// $ANTLR end "unit"



	// $ANTLR start "function"
	// ./src/VSLTreeParser.g:18:1: function[SymbolTable ts] returns [Code3a code] : ^( FUNC_KW type IDENT pl= param_list[ts, t] ^( BODY body= statement[ts] ) ) ;
	public final Code3a function(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		CommonTree IDENT4=null;
		Code3a body =null;
		Type type3 =null;

		try {
			// ./src/VSLTreeParser.g:19:2: ( ^( FUNC_KW type IDENT pl= param_list[ts, t] ^( BODY body= statement[ts] ) ) )
			// ./src/VSLTreeParser.g:19:4: ^( FUNC_KW type IDENT pl= param_list[ts, t] ^( BODY body= statement[ts] ) )
			{
			match(input,FUNC_KW,FOLLOW_FUNC_KW_in_function114); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_function116);
			type3=type();
			state._fsp--;

			FunctionType t = new FunctionType(type3, false);
			IDENT4=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_function120); 
			ts.enterScope();
			pushFollow(FOLLOW_param_list_in_function126);
			param_list(ts, t);
			state._fsp--;

			match(input,BODY,FOLLOW_BODY_in_function130); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_in_function134);
			body=statement(ts);
			state._fsp--;

			match(input, Token.UP, null); 

			ts.leaveScope();
			match(input, Token.UP, null); 


						Operand3a op = ts.lookup((IDENT4!=null?IDENT4.getText():null));
						LabelSymbol label = new LabelSymbol((IDENT4!=null?IDENT4.getText():null));	
						
						if (op == null) // No declared prototype
							ts.insert((IDENT4!=null?IDENT4.getText():null), new FunctionSymbol(label, t));
						else if (op instanceof FunctionSymbol) {
							FunctionSymbol fs = (FunctionSymbol) op;
							if (!((FunctionType)fs.type).prototype) {
								Errors.redefinedIdentifier(IDENT4, (IDENT4!=null?IDENT4.getText():null), null);
								System.exit(1);
							} if (!fs.type.isCompatible((Type)t)) {
								Errors.incompatibleTypes(IDENT4, fs.type, t, null);
								System.exit(1);				
							}
						}
						code = Code3aGenerator.genFuncStart(new VarSymbol((IDENT4!=null?IDENT4.getText():null)));
						code.append(body);
						code.append(Code3aGenerator.genFuncEnd());
					
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
	// $ANTLR end "function"



	// $ANTLR start "proto"
	// ./src/VSLTreeParser.g:42:1: proto[SymbolTable ts] : ^( PROTO_KW type IDENT ^( PARAM pl= param_list[ts, t] ) ) ;
	public final void proto(SymbolTable ts) throws RecognitionException {
		CommonTree IDENT6=null;
		Type type5 =null;

		try {
			// ./src/VSLTreeParser.g:43:2: ( ^( PROTO_KW type IDENT ^( PARAM pl= param_list[ts, t] ) ) )
			// ./src/VSLTreeParser.g:43:4: ^( PROTO_KW type IDENT ^( PARAM pl= param_list[ts, t] ) )
			{
			match(input,PROTO_KW,FOLLOW_PROTO_KW_in_proto158); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_proto160);
			type5=type();
			state._fsp--;

			FunctionType t = new FunctionType(type5, true);
			IDENT6=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_proto164); 
			ts.enterScope();
			match(input,PARAM,FOLLOW_PARAM_in_proto169); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_param_list_in_proto173);
			param_list(ts, t);
			state._fsp--;

			ts.leaveScope();
			match(input, Token.UP, null); 

			match(input, Token.UP, null); 


						if(ts.lookup((IDENT6!=null?IDENT6.getText():null)) == null)
							ts.insert((IDENT6!=null?IDENT6.getText():null), new FunctionSymbol(new LabelSymbol((IDENT6!=null?IDENT6.getText():null)), t));
						else {
							Errors.redefinedIdentifier(IDENT6, (IDENT6!=null?IDENT6.getText():null), null);
							System.exit(1);
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "proto"



	// $ANTLR start "type"
	// ./src/VSLTreeParser.g:54:1: type returns [Type type_ret] : ( INT_KW | VOID_KW );
	public final Type type() throws RecognitionException {
		Type type_ret = null;


		try {
			// ./src/VSLTreeParser.g:55:2: ( INT_KW | VOID_KW )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==INT_KW) ) {
				alt3=1;
			}
			else if ( (LA3_0==VOID_KW) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// ./src/VSLTreeParser.g:55:4: INT_KW
					{
					match(input,INT_KW,FOLLOW_INT_KW_in_type197); 
					type_ret = Type.INT;
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:56:4: VOID_KW
					{
					match(input,VOID_KW,FOLLOW_VOID_KW_in_type204); 
					type_ret = Type.VOID;
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
		return type_ret;
	}
	// $ANTLR end "type"



	// $ANTLR start "param_list"
	// ./src/VSLTreeParser.g:59:1: param_list[SymbolTable ts, FunctionType type] : ^( PARAM ( param[ts, type] )* ) ;
	public final void param_list(SymbolTable ts, FunctionType type) throws RecognitionException {
		try {
			// ./src/VSLTreeParser.g:60:2: ( ^( PARAM ( param[ts, type] )* ) )
			// ./src/VSLTreeParser.g:60:4: ^( PARAM ( param[ts, type] )* )
			{
			match(input,PARAM,FOLLOW_PARAM_in_param_list219); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ./src/VSLTreeParser.g:60:12: ( param[ts, type] )*
				loop4:
				while (true) {
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==IDENT||LA4_0==ARRAY) ) {
						alt4=1;
					}

					switch (alt4) {
					case 1 :
						// ./src/VSLTreeParser.g:60:13: param[ts, type]
						{
						pushFollow(FOLLOW_param_in_param_list222);
						param(ts, type);
						state._fsp--;

						}
						break;

					default :
						break loop4;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "param_list"



	// $ANTLR start "param"
	// ./src/VSLTreeParser.g:63:1: param[SymbolTable ts, FunctionType type] : ( IDENT | ^( ARRAY IDENT ) );
	public final void param(SymbolTable ts, FunctionType type) throws RecognitionException {
		CommonTree IDENT7=null;
		CommonTree IDENT8=null;

		try {
			// ./src/VSLTreeParser.g:64:2: ( IDENT | ^( ARRAY IDENT ) )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==IDENT) ) {
				alt5=1;
			}
			else if ( (LA5_0==ARRAY) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// ./src/VSLTreeParser.g:64:4: IDENT
					{
					IDENT7=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_param238); 

								if (ts.lookup((IDENT7!=null?IDENT7.getText():null)) == null) {
									VarSymbol vs = new VarSymbol(Type.INT, (IDENT7!=null?IDENT7.getText():null), ts.getScope());
									vs.setParam();
									ts.insert((IDENT7!=null?IDENT7.getText():null), vs);
									type.extend(Type.INT);
								}
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:73:4: ^( ARRAY IDENT )
					{
					match(input,ARRAY,FOLLOW_ARRAY_in_param248); 
					match(input, Token.DOWN, null); 
					IDENT8=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_param250); 
					match(input, Token.UP, null); 


								if(ts.lookup((IDENT8!=null?IDENT8.getText():null)) == null) {
									ArrayType t = new ArrayType(Type.INT, 0);
									VarSymbol vs = new VarSymbol(Type.INT, (IDENT8!=null?IDENT8.getText():null), ts.getScope());
									vs.setParam();
									ts.insert((IDENT8!=null?IDENT8.getText():null), vs);
									type.extend(t);
								}
							
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
	}
	// $ANTLR end "param"



	// $ANTLR start "statement"
	// ./src/VSLTreeParser.g:85:1: statement[SymbolTable ts] returns [Code3a code] : ( ^( ASSIGN_KW e= expression[ts] statement_lhs[ts, $e.expAtt] ) | ^( RETURN_KW expression[ts] ) | ^( IF_KW if_cond= expression[ts] if_st= statement[ts] (else_st= statement[ts] )? ) | ^( WHILE_KW wh_cond= expression[ts] while_st= statement[ts] ) | ^( FCALL_S IDENT argument_list[ts, t] ) | ^( PRINT_KW print_list[ts] ) | ^( READ_KW read_list[ts] ) |code_sequence= block[ts] );
	public final Code3a statement(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		CommonTree RETURN_KW11=null;
		CommonTree IDENT12=null;
		ExpAttribute e =null;
		ExpAttribute if_cond =null;
		Code3a if_st =null;
		Code3a else_st =null;
		ExpAttribute wh_cond =null;
		Code3a while_st =null;
		Code3a code_sequence =null;
		Code3a statement_lhs9 =null;
		ExpAttribute expression10 =null;
		Code3a argument_list13 =null;
		Code3a print_list14 =null;
		Code3a read_list15 =null;

		try {
			// ./src/VSLTreeParser.g:86:2: ( ^( ASSIGN_KW e= expression[ts] statement_lhs[ts, $e.expAtt] ) | ^( RETURN_KW expression[ts] ) | ^( IF_KW if_cond= expression[ts] if_st= statement[ts] (else_st= statement[ts] )? ) | ^( WHILE_KW wh_cond= expression[ts] while_st= statement[ts] ) | ^( FCALL_S IDENT argument_list[ts, t] ) | ^( PRINT_KW print_list[ts] ) | ^( READ_KW read_list[ts] ) |code_sequence= block[ts] )
			int alt7=8;
			switch ( input.LA(1) ) {
			case ASSIGN_KW:
				{
				alt7=1;
				}
				break;
			case RETURN_KW:
				{
				alt7=2;
				}
				break;
			case IF_KW:
				{
				alt7=3;
				}
				break;
			case WHILE_KW:
				{
				alt7=4;
				}
				break;
			case FCALL_S:
				{
				alt7=5;
				}
				break;
			case PRINT_KW:
				{
				alt7=6;
				}
				break;
			case READ_KW:
				{
				alt7=7;
				}
				break;
			case BLOCK:
				{
				alt7=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// ./src/VSLTreeParser.g:86:4: ^( ASSIGN_KW e= expression[ts] statement_lhs[ts, $e.expAtt] )
					{
					match(input,ASSIGN_KW,FOLLOW_ASSIGN_KW_in_statement273); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_statement277);
					e=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_statement_lhs_in_statement280);
					statement_lhs9=statement_lhs(ts, e);
					state._fsp--;

					match(input, Token.UP, null); 

					code = statement_lhs9;
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:87:4: ^( RETURN_KW expression[ts] )
					{
					RETURN_KW11=(CommonTree)match(input,RETURN_KW,FOLLOW_RETURN_KW_in_statement290); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_statement292);
					expression10=expression(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								if(expression10.type != Type.INT) {
									Errors.incompatibleTypes(RETURN_KW11, Type.INT, expression10.type, null);
									System.exit(1);
								}
								code = Code3aGenerator.genReturn(expression10);
							
					}
					break;
				case 3 :
					// ./src/VSLTreeParser.g:95:4: ^( IF_KW if_cond= expression[ts] if_st= statement[ts] (else_st= statement[ts] )? )
					{
					match(input,IF_KW,FOLLOW_IF_KW_in_statement304); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_statement310);
					if_cond=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_statement_in_statement315);
					if_st=statement(ts);
					state._fsp--;

					// ./src/VSLTreeParser.g:95:57: (else_st= statement[ts] )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==ASSIGN_KW||LA6_0==IF_KW||LA6_0==PRINT_KW||(LA6_0 >= READ_KW && LA6_0 <= RETURN_KW)||LA6_0==WHILE_KW||LA6_0==BLOCK||LA6_0==FCALL_S) ) {
						alt6=1;
					}
					switch (alt6) {
						case 1 :
							// ./src/VSLTreeParser.g:95:58: else_st= statement[ts]
							{
							pushFollow(FOLLOW_statement_in_statement321);
							else_st=statement(ts);
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


								if (else_st != null)
									code = Code3aGenerator.genIfThenElse(if_cond, if_st, else_st);
								else
									code = Code3aGenerator.genIfThen(if_cond, if_st);
							
					}
					break;
				case 4 :
					// ./src/VSLTreeParser.g:102:4: ^( WHILE_KW wh_cond= expression[ts] while_st= statement[ts] )
					{
					match(input,WHILE_KW,FOLLOW_WHILE_KW_in_statement335); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_statement339);
					wh_cond=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_statement_in_statement344);
					while_st=statement(ts);
					state._fsp--;

					match(input, Token.UP, null); 

					code = Code3aGenerator.genWhile(wh_cond, while_st);
					}
					break;
				case 5 :
					// ./src/VSLTreeParser.g:103:4: ^( FCALL_S IDENT argument_list[ts, t] )
					{
					match(input,FCALL_S,FOLLOW_FCALL_S_in_statement354); 
					match(input, Token.DOWN, null); 
					IDENT12=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_statement356); 
					FunctionType t = new FunctionType(Type.VOID, false);
					pushFollow(FOLLOW_argument_list_in_statement360);
					argument_list13=argument_list(ts, t);
					state._fsp--;

					match(input, Token.UP, null); 


								Operand3a op = ts.lookup((IDENT12!=null?IDENT12.getText():null));
								if(op != null && op instanceof FunctionSymbol) {
									FunctionSymbol fs = (FunctionSymbol)op;
									if (((FunctionType)fs.type).getReturnType() == Type.VOID)
										code = Code3aGenerator.genCall(argument_list13, new VarSymbol((IDENT12!=null?IDENT12.getText():null)));
									else {
										Errors.unknownIdentifier(IDENT12, (IDENT12!=null?IDENT12.getText():null), null);
										System.exit(1);
									} if (!fs.type.isCompatible((Type)t)) {
										Errors.incompatibleTypes(IDENT12, fs.type, t, null);
										System.exit(1);				
									}
								}
							
					}
					break;
				case 6 :
					// ./src/VSLTreeParser.g:119:7: ^( PRINT_KW print_list[ts] )
					{
					match(input,PRINT_KW,FOLLOW_PRINT_KW_in_statement375); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_print_list_in_statement377);
					print_list14=print_list(ts);
					state._fsp--;

					match(input, Token.UP, null); 

					code = print_list14;
					}
					break;
				case 7 :
					// ./src/VSLTreeParser.g:120:7: ^( READ_KW read_list[ts] )
					{
					match(input,READ_KW,FOLLOW_READ_KW_in_statement390); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_read_list_in_statement392);
					read_list15=read_list(ts);
					state._fsp--;

					match(input, Token.UP, null); 

					code = read_list15;
					}
					break;
				case 8 :
					// ./src/VSLTreeParser.g:121:4: code_sequence= block[ts]
					{
					pushFollow(FOLLOW_block_in_statement405);
					code_sequence=block(ts);
					state._fsp--;

					code = code_sequence;
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
		return code;
	}
	// $ANTLR end "statement"



	// $ANTLR start "statement_lhs"
	// ./src/VSLTreeParser.g:124:1: statement_lhs[SymbolTable ts, ExpAttribute exp] returns [Code3a code] : ( IDENT | array_elem[ts, exp] );
	public final Code3a statement_lhs(SymbolTable ts, ExpAttribute exp) throws RecognitionException {
		Code3a code = null;


		CommonTree IDENT16=null;
		ExpAttribute array_elem17 =null;

		try {
			// ./src/VSLTreeParser.g:125:2: ( IDENT | array_elem[ts, exp] )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==IDENT) ) {
				alt8=1;
			}
			else if ( (LA8_0==ARELEM) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// ./src/VSLTreeParser.g:125:4: IDENT
					{
					IDENT16=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_statement_lhs425); 

								Operand3a op = ts.lookup((IDENT16!=null?IDENT16.getText():null));
								if (op != null) {
									if(exp.type instanceof ArrayType) {
										Errors.incompatibleTypes(IDENT16, Type.INT, exp.type, null);
										System.exit(1);
									} if(op.type instanceof ArrayType){
										Errors.incompatibleTypes(IDENT16, Type.INT, op.type, null);
										System.exit(1);
									}
									
									code = Code3aGenerator.genAssign(exp, new ExpAttribute(Type.INT, new Code3a(), new VarSymbol((IDENT16!=null?IDENT16.getText():null))));
								} else {
									Errors.unknownIdentifier(IDENT16, (IDENT16!=null?IDENT16.getText():null), null);
									System.exit(1);
								}
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:143:4: array_elem[ts, exp]
					{
					pushFollow(FOLLOW_array_elem_in_statement_lhs434);
					array_elem17=array_elem(ts, exp);
					state._fsp--;

					code = array_elem17.code;
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
		return code;
	}
	// $ANTLR end "statement_lhs"



	// $ANTLR start "expression"
	// ./src/VSLTreeParser.g:146:1: expression[SymbolTable ts] returns [ExpAttribute expAtt] : ( ^( PLUS e1= expression[ts] e2= expression[ts] ) | ^( MINUS e1= expression[ts] e2= expression[ts] ) | ^( MUL e1= expression[ts] e2= expression[ts] ) | ^( DIV e1= expression[ts] e2= expression[ts] ) |pe= primary_exp[ts] );
	public final ExpAttribute expression(SymbolTable ts) throws RecognitionException {
		ExpAttribute expAtt = null;


		ExpAttribute e1 =null;
		ExpAttribute e2 =null;
		ExpAttribute pe =null;

		try {
			// ./src/VSLTreeParser.g:147:2: ( ^( PLUS e1= expression[ts] e2= expression[ts] ) | ^( MINUS e1= expression[ts] e2= expression[ts] ) | ^( MUL e1= expression[ts] e2= expression[ts] ) | ^( DIV e1= expression[ts] e2= expression[ts] ) |pe= primary_exp[ts] )
			int alt9=5;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt9=1;
				}
				break;
			case MINUS:
				{
				alt9=2;
				}
				break;
			case MUL:
				{
				alt9=3;
				}
				break;
			case DIV:
				{
				alt9=4;
				}
				break;
			case IDENT:
			case INTEGER:
			case ARELEM:
			case FCALL:
				{
				alt9=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// ./src/VSLTreeParser.g:147:4: ^( PLUS e1= expression[ts] e2= expression[ts] )
					{
					match(input,PLUS,FOLLOW_PLUS_in_expression455); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression459);
					e1=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression464);
					e2=expression(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
								VarSymbol temp = SymbDistrib.newTemp();
								Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.ADD, e1, e2, temp);
								expAtt         = new ExpAttribute(ty, cod, temp);
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:154:4: ^( MINUS e1= expression[ts] e2= expression[ts] )
					{
					match(input,MINUS,FOLLOW_MINUS_in_expression476); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression480);
					e1=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression485);
					e2=expression(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
								VarSymbol temp = SymbDistrib.newTemp();
								Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.SUB, e1, e2, temp);
								expAtt         = new ExpAttribute(ty, cod, temp);
							
					}
					break;
				case 3 :
					// ./src/VSLTreeParser.g:161:4: ^( MUL e1= expression[ts] e2= expression[ts] )
					{
					match(input,MUL,FOLLOW_MUL_in_expression497); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression501);
					e1=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression506);
					e2=expression(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
								VarSymbol temp = SymbDistrib.newTemp();
								Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.MUL, e1, e2, temp);
								expAtt         = new ExpAttribute(ty, cod, temp);
							
					}
					break;
				case 4 :
					// ./src/VSLTreeParser.g:168:4: ^( DIV e1= expression[ts] e2= expression[ts] )
					{
					match(input,DIV,FOLLOW_DIV_in_expression518); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression522);
					e1=expression(ts);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression527);
					e2=expression(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
								VarSymbol temp = SymbDistrib.newTemp();
								Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.DIV, e1, e2, temp);
								expAtt         = new ExpAttribute(ty, cod, temp);
							
					}
					break;
				case 5 :
					// ./src/VSLTreeParser.g:175:4: pe= primary_exp[ts]
					{
					pushFollow(FOLLOW_primary_exp_in_expression540);
					pe=primary_exp(ts);
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
	// ./src/VSLTreeParser.g:178:1: primary_exp[SymbolTable ts] returns [ExpAttribute expAtt] : ( INTEGER | IDENT | ^( FCALL IDENT argument_list[ts, null] ) | array_elem[ts, null] );
	public final ExpAttribute primary_exp(SymbolTable ts) throws RecognitionException {
		ExpAttribute expAtt = null;


		CommonTree INTEGER18=null;
		CommonTree IDENT19=null;
		CommonTree IDENT20=null;
		Code3a argument_list21 =null;
		ExpAttribute array_elem22 =null;

		try {
			// ./src/VSLTreeParser.g:179:2: ( INTEGER | IDENT | ^( FCALL IDENT argument_list[ts, null] ) | array_elem[ts, null] )
			int alt10=4;
			switch ( input.LA(1) ) {
			case INTEGER:
				{
				alt10=1;
				}
				break;
			case IDENT:
				{
				alt10=2;
				}
				break;
			case FCALL:
				{
				alt10=3;
				}
				break;
			case ARELEM:
				{
				alt10=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// ./src/VSLTreeParser.g:179:4: INTEGER
					{
					INTEGER18=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_primary_exp560); 

								ConstSymbol cs = new ConstSymbol(Integer.parseInt((INTEGER18!=null?INTEGER18.getText():null)));
								expAtt = new ExpAttribute(Type.INT, new Code3a(), cs);
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:184:4: IDENT
					{
					IDENT19=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_primary_exp569); 

								Operand3a id = ts.lookup((IDENT19!=null?IDENT19.getText():null));
								if (id != null)
									expAtt = new ExpAttribute(id.type, new Code3a(), id);
								else {
									Errors.unknownIdentifier(IDENT19, (IDENT19!=null?IDENT19.getText():null), null);
									System.exit(1);
								}
							
					}
					break;
				case 3 :
					// ./src/VSLTreeParser.g:194:4: ^( FCALL IDENT argument_list[ts, null] )
					{
					match(input,FCALL,FOLLOW_FCALL_in_primary_exp579); 
					match(input, Token.DOWN, null); 
					IDENT20=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_primary_exp581); 
					pushFollow(FOLLOW_argument_list_in_primary_exp583);
					argument_list21=argument_list(ts, null);
					state._fsp--;

					match(input, Token.UP, null); 


								Operand3a op = ts.lookup((IDENT20!=null?IDENT20.getText():null));
								if (op != null && op instanceof FunctionSymbol) {
									FunctionSymbol fs = (FunctionSymbol) op;
									if (((FunctionType)fs.type).getReturnType() != Type.VOID) {
										VarSymbol temp = SymbDistrib.newTemp();
										Code3a code = Code3aGenerator.genVar(temp);
										code.append(Code3aGenerator.genCallReturn(argument_list21, new VarSymbol((IDENT20!=null?IDENT20.getText():null)), temp));
										expAtt = new ExpAttribute(new FunctionType(fs.type), code, temp);
									} else {
										Errors.incompatibleTypes(IDENT20, Type.INT, ((FunctionType)fs.type).getReturnType(), null);
										System.exit(1);
									}
								} else {
									Errors.unknownIdentifier(IDENT20, (IDENT20!=null?IDENT20.getText():null), null);
									System.exit(1);
								}
							
					}
					break;
				case 4 :
					// ./src/VSLTreeParser.g:213:7: array_elem[ts, null]
					{
					pushFollow(FOLLOW_array_elem_in_primary_exp597);
					array_elem22=array_elem(ts, null);
					state._fsp--;

					expAtt = array_elem22;
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



	// $ANTLR start "block"
	// ./src/VSLTreeParser.g:216:1: block[SymbolTable ts] returns [Code3a code] : ( ^( BLOCK decl_block= declaration[ts] inst_block= inst_list[ts] ) | ^( BLOCK inst_block2= inst_list[ts] ) );
	public final Code3a block(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a decl_block =null;
		Code3a inst_block =null;
		Code3a inst_block2 =null;

		try {
			// ./src/VSLTreeParser.g:217:2: ( ^( BLOCK decl_block= declaration[ts] inst_block= inst_list[ts] ) | ^( BLOCK inst_block2= inst_list[ts] ) )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==BLOCK) ) {
				int LA11_1 = input.LA(2);
				if ( (LA11_1==DOWN) ) {
					int LA11_2 = input.LA(3);
					if ( (LA11_2==DECL) ) {
						alt11=1;
					}
					else if ( (LA11_2==INST) ) {
						alt11=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 11, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 11, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// ./src/VSLTreeParser.g:217:4: ^( BLOCK decl_block= declaration[ts] inst_block= inst_list[ts] )
					{
					match(input,BLOCK,FOLLOW_BLOCK_in_block618); 
					ts.enterScope();
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_declaration_in_block624);
					decl_block=declaration(ts);
					state._fsp--;

					pushFollow(FOLLOW_inst_list_in_block629);
					inst_block=inst_list(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								code = decl_block;
								code.append(inst_block);
								ts.leaveScope();
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:223:4: ^( BLOCK inst_block2= inst_list[ts] )
					{
					match(input,BLOCK,FOLLOW_BLOCK_in_block641); 
					ts.enterScope();
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_inst_list_in_block647);
					inst_block2=inst_list(ts);
					state._fsp--;

					match(input, Token.UP, null); 


								code = inst_block2;
								ts.leaveScope();
							
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
		return code;
	}
	// $ANTLR end "block"



	// $ANTLR start "declaration"
	// ./src/VSLTreeParser.g:230:1: declaration[SymbolTable ts] returns [Code3a code] : ^( DECL (di= decl_item[ts] )+ ) ;
	public final Code3a declaration(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a di =null;

		code = new Code3a();
		try {
			// ./src/VSLTreeParser.g:232:2: ( ^( DECL (di= decl_item[ts] )+ ) )
			// ./src/VSLTreeParser.g:232:4: ^( DECL (di= decl_item[ts] )+ )
			{
			match(input,DECL,FOLLOW_DECL_in_declaration677); 
			match(input, Token.DOWN, null); 
			// ./src/VSLTreeParser.g:232:11: (di= decl_item[ts] )+
			int cnt12=0;
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==IDENT||LA12_0==ARDECL) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// ./src/VSLTreeParser.g:232:12: di= decl_item[ts]
					{
					pushFollow(FOLLOW_decl_item_in_declaration682);
					di=decl_item(ts);
					state._fsp--;

					code.append(di);
					}
					break;

				default :
					if ( cnt12 >= 1 ) break loop12;
					EarlyExitException eee = new EarlyExitException(12, input);
					throw eee;
				}
				cnt12++;
			}

			match(input, Token.UP, null); 

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
	// $ANTLR end "declaration"



	// $ANTLR start "decl_item"
	// ./src/VSLTreeParser.g:235:1: decl_item[SymbolTable ts] returns [Code3a code] : ( IDENT | ^( ARDECL IDENT INTEGER ) );
	public final Code3a decl_item(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		CommonTree IDENT23=null;
		CommonTree IDENT24=null;
		CommonTree INTEGER25=null;

		try {
			// ./src/VSLTreeParser.g:236:2: ( IDENT | ^( ARDECL IDENT INTEGER ) )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==IDENT) ) {
				alt13=1;
			}
			else if ( (LA13_0==ARDECL) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// ./src/VSLTreeParser.g:236:4: IDENT
					{
					IDENT23=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_decl_item706); 

								VarSymbol v = (VarSymbol)ts.lookup((IDENT23!=null?IDENT23.getText():null));
								if(v != null && v.getScope() == ts.getScope()) {
									Errors.redefinedIdentifier(IDENT23, (IDENT23!=null?IDENT23.getText():null), null);
									System.exit(1);
								}
								
								v = new VarSymbol(Type.INT, (IDENT23!=null?IDENT23.getText():null), ts.getScope());
								ts.insert((IDENT23!=null?IDENT23.getText():null), v);
								code = Code3aGenerator.genVar(v);
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:248:4: ^( ARDECL IDENT INTEGER )
					{
					match(input,ARDECL,FOLLOW_ARDECL_in_decl_item716); 
					match(input, Token.DOWN, null); 
					IDENT24=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_decl_item718); 
					INTEGER25=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_decl_item720); 
					match(input, Token.UP, null); 


								VarSymbol v = (VarSymbol)ts.lookup((IDENT24!=null?IDENT24.getText():null));
								if(v != null && v.getScope() == ts.getScope()) {
									Errors.redefinedIdentifier(IDENT24, (IDENT24!=null?IDENT24.getText():null), null);
									System.exit(1);
								}
								
								ArrayType t = new ArrayType(Type.INT, Integer.parseInt((INTEGER25!=null?INTEGER25.getText():null)));
								v = new VarSymbol(t, (IDENT24!=null?IDENT24.getText():null), ts.getScope());
								ts.insert((IDENT24!=null?IDENT24.getText():null), v);
								code = Code3aGenerator.genVar(v);
							
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
		return code;
	}
	// $ANTLR end "decl_item"



	// $ANTLR start "inst_list"
	// ./src/VSLTreeParser.g:263:1: inst_list[SymbolTable ts] returns [Code3a code] : ^( INST (st= statement[ts] )* ) ;
	public final Code3a inst_list(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a st =null;

		code = new Code3a();
		try {
			// ./src/VSLTreeParser.g:264:2: ( ^( INST (st= statement[ts] )* ) )
			// ./src/VSLTreeParser.g:264:4: ^( INST (st= statement[ts] )* )
			{
			match(input,INST,FOLLOW_INST_in_inst_list748); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ./src/VSLTreeParser.g:264:11: (st= statement[ts] )*
				loop14:
				while (true) {
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==ASSIGN_KW||LA14_0==IF_KW||LA14_0==PRINT_KW||(LA14_0 >= READ_KW && LA14_0 <= RETURN_KW)||LA14_0==WHILE_KW||LA14_0==BLOCK||LA14_0==FCALL_S) ) {
						alt14=1;
					}

					switch (alt14) {
					case 1 :
						// ./src/VSLTreeParser.g:264:12: st= statement[ts]
						{
						pushFollow(FOLLOW_statement_in_inst_list753);
						st=statement(ts);
						state._fsp--;

						code.append(st);
						}
						break;

					default :
						break loop14;
					}
				}

				match(input, Token.UP, null); 
			}

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
	// $ANTLR end "inst_list"



	// $ANTLR start "argument_list"
	// ./src/VSLTreeParser.g:268:1: argument_list[SymbolTable ts, FunctionType t] returns [Code3a code] : ( expression[ts] )* ;
	public final Code3a argument_list(SymbolTable ts, FunctionType t) throws RecognitionException {
		Code3a code = null;


		ExpAttribute expression26 =null;

		try {
			// ./src/VSLTreeParser.g:269:2: ( ( expression[ts] )* )
			// ./src/VSLTreeParser.g:269:4: ( expression[ts] )*
			{
			// ./src/VSLTreeParser.g:269:4: ( expression[ts] )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==DIV||LA15_0==IDENT||LA15_0==INTEGER||(LA15_0 >= MINUS && LA15_0 <= MUL)||LA15_0==PLUS||LA15_0==ARELEM||LA15_0==FCALL) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// ./src/VSLTreeParser.g:269:5: expression[ts]
					{
					pushFollow(FOLLOW_expression_in_argument_list778);
					expression26=expression(ts);
					state._fsp--;


								code = Code3aGenerator.genFuncArguments(expression26);
								if(t != null) {
									if(expression26.place.type == Type.I_CONST) // isCompatible will fail if we don't do this
										t.extend(Type.INT);
									else
										t.extend(expression26.place.type);
								}
							
					}
					break;

				default :
					break loop15;
				}
			}

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
	// $ANTLR end "argument_list"



	// $ANTLR start "print_list"
	// ./src/VSLTreeParser.g:282:1: print_list[SymbolTable ts] returns [Code3a code] : ( print_item[ts] )+ ;
	public final Code3a print_list(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a print_item27 =null;

		code = new Code3a();
		try {
			// ./src/VSLTreeParser.g:283:2: ( ( print_item[ts] )+ )
			// ./src/VSLTreeParser.g:283:4: ( print_item[ts] )+
			{
			// ./src/VSLTreeParser.g:283:4: ( print_item[ts] )+
			int cnt16=0;
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==DIV||LA16_0==IDENT||LA16_0==INTEGER||(LA16_0 >= MINUS && LA16_0 <= MUL)||LA16_0==PLUS||LA16_0==TEXT||LA16_0==ARELEM||LA16_0==FCALL) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// ./src/VSLTreeParser.g:283:5: print_item[ts]
					{
					pushFollow(FOLLOW_print_item_in_print_list810);
					print_item27=print_item(ts);
					state._fsp--;

					code.append(print_item27);
					}
					break;

				default :
					if ( cnt16 >= 1 ) break loop16;
					EarlyExitException eee = new EarlyExitException(16, input);
					throw eee;
				}
				cnt16++;
			}

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
	// $ANTLR end "print_list"



	// $ANTLR start "print_item"
	// ./src/VSLTreeParser.g:286:1: print_item[SymbolTable ts] returns [Code3a code] : ( TEXT | expression[ts] );
	public final Code3a print_item(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		CommonTree TEXT28=null;
		ExpAttribute expression29 =null;

		try {
			// ./src/VSLTreeParser.g:287:2: ( TEXT | expression[ts] )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==TEXT) ) {
				alt17=1;
			}
			else if ( (LA17_0==DIV||LA17_0==IDENT||LA17_0==INTEGER||(LA17_0 >= MINUS && LA17_0 <= MUL)||LA17_0==PLUS||LA17_0==ARELEM||LA17_0==FCALL) ) {
				alt17=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// ./src/VSLTreeParser.g:287:4: TEXT
					{
					TEXT28=(CommonTree)match(input,TEXT,FOLLOW_TEXT_in_print_item833); 
					code = Code3aGenerator.genPrintText(new Data3a((TEXT28!=null?TEXT28.getText():null)));
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:288:7: expression[ts]
					{
					pushFollow(FOLLOW_expression_in_print_item843);
					expression29=expression(ts);
					state._fsp--;

					code = Code3aGenerator.genPrintExpression(expression29);
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
		return code;
	}
	// $ANTLR end "print_item"



	// $ANTLR start "read_list"
	// ./src/VSLTreeParser.g:291:1: read_list[SymbolTable ts] returns [Code3a code] : ( read_item[ts] )+ ;
	public final Code3a read_list(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		Code3a read_item30 =null;

		code = new Code3a();
		try {
			// ./src/VSLTreeParser.g:292:2: ( ( read_item[ts] )+ )
			// ./src/VSLTreeParser.g:292:4: ( read_item[ts] )+
			{
			// ./src/VSLTreeParser.g:292:4: ( read_item[ts] )+
			int cnt18=0;
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==IDENT||LA18_0==ARELEM) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// ./src/VSLTreeParser.g:292:5: read_item[ts]
					{
					pushFollow(FOLLOW_read_item_in_read_list872);
					read_item30=read_item(ts);
					state._fsp--;

					code.append(read_item30);
					}
					break;

				default :
					if ( cnt18 >= 1 ) break loop18;
					EarlyExitException eee = new EarlyExitException(18, input);
					throw eee;
				}
				cnt18++;
			}

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
	// $ANTLR end "read_list"



	// $ANTLR start "read_item"
	// ./src/VSLTreeParser.g:295:1: read_item[SymbolTable ts] returns [Code3a code] : ( IDENT | array_elem[ts, null] );
	public final Code3a read_item(SymbolTable ts) throws RecognitionException {
		Code3a code = null;


		CommonTree IDENT31=null;

		try {
			// ./src/VSLTreeParser.g:296:2: ( IDENT | array_elem[ts, null] )
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==IDENT) ) {
				alt19=1;
			}
			else if ( (LA19_0==ARELEM) ) {
				alt19=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// ./src/VSLTreeParser.g:296:4: IDENT
					{
					IDENT31=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_read_item896); 

								VarSymbol v = (VarSymbol)ts.lookup((IDENT31!=null?IDENT31.getText():null));
								if(v != null)
									code = Code3aGenerator.genRead(v);
								else {
									Errors.unknownIdentifier(IDENT31, (IDENT31!=null?IDENT31.getText():null), null);
									System.exit(1);
								}
							
					}
					break;
				case 2 :
					// ./src/VSLTreeParser.g:306:4: array_elem[ts, null]
					{
					pushFollow(FOLLOW_array_elem_in_read_item905);
					array_elem(ts, null);
					state._fsp--;

					 
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
		return code;
	}
	// $ANTLR end "read_item"



	// $ANTLR start "array_elem"
	// ./src/VSLTreeParser.g:309:1: array_elem[SymbolTable ts, ExpAttribute exp] returns [ExpAttribute expAtt] : ^( ARELEM IDENT expression[ts] ) ;
	public final ExpAttribute array_elem(SymbolTable ts, ExpAttribute exp) throws RecognitionException {
		ExpAttribute expAtt = null;


		CommonTree IDENT32=null;
		ExpAttribute expression33 =null;

		try {
			// ./src/VSLTreeParser.g:310:2: ( ^( ARELEM IDENT expression[ts] ) )
			// ./src/VSLTreeParser.g:310:4: ^( ARELEM IDENT expression[ts] )
			{
			match(input,ARELEM,FOLLOW_ARELEM_in_array_elem928); 
			match(input, Token.DOWN, null); 
			IDENT32=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_array_elem930); 
			pushFollow(FOLLOW_expression_in_array_elem932);
			expression33=expression(ts);
			state._fsp--;

			match(input, Token.UP, null); 


						if(exp != null) { // Affectation
							VarSymbol v = new VarSymbol((IDENT32!=null?IDENT32.getText():null));
							Code3a c = Code3aGenerator.genArrayAssignment(exp, v, expression33);
							expAtt = new ExpAttribute(Type.INT, c, v);
						} else {
							VarSymbol tmp = SymbDistrib.newTemp();
							Code3a c = Code3aGenerator.genArrayAccess(tmp, new VarSymbol((IDENT32!=null?IDENT32.getText():null)), expression33);
							expAtt = new ExpAttribute(Type.INT, c, tmp);
						}
					
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
	// $ANTLR end "array_elem"

	// Delegated rules



	public static final BitSet FOLLOW_unit_in_s61 = new BitSet(new long[]{0x0000000008002002L});
	public static final BitSet FOLLOW_function_in_unit85 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_proto_in_unit93 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNC_KW_in_function114 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_function116 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_IDENT_in_function120 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_param_list_in_function126 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_BODY_in_function130 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_function134 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PROTO_KW_in_proto158 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_proto160 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_IDENT_in_proto164 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_PARAM_in_proto169 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_list_in_proto173 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INT_KW_in_type197 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_KW_in_type204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_in_param_list219 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_in_param_list222 = new BitSet(new long[]{0x0000010000004008L});
	public static final BitSet FOLLOW_IDENT_in_param238 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARRAY_in_param248 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IDENT_in_param250 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASSIGN_KW_in_statement273 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_statement277 = new BitSet(new long[]{0x0000008000004000L});
	public static final BitSet FOLLOW_statement_lhs_in_statement280 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_KW_in_statement290 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_statement292 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_KW_in_statement304 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_statement310 = new BitSet(new long[]{0x00002210C4008020L});
	public static final BitSet FOLLOW_statement_in_statement315 = new BitSet(new long[]{0x00002210C4008028L});
	public static final BitSet FOLLOW_statement_in_statement321 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_KW_in_statement335 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_statement339 = new BitSet(new long[]{0x00002210C4008020L});
	public static final BitSet FOLLOW_statement_in_statement344 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FCALL_S_in_statement354 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IDENT_in_statement356 = new BitSet(new long[]{0x0000108002C14208L});
	public static final BitSet FOLLOW_argument_list_in_statement360 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRINT_KW_in_statement375 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_print_list_in_statement377 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_KW_in_statement390 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_read_list_in_statement392 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_block_in_statement405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_statement_lhs425 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_elem_in_statement_lhs434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_expression455 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression459 = new BitSet(new long[]{0x0000108002C14200L});
	public static final BitSet FOLLOW_expression_in_expression464 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MINUS_in_expression476 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression480 = new BitSet(new long[]{0x0000108002C14200L});
	public static final BitSet FOLLOW_expression_in_expression485 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MUL_in_expression497 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression501 = new BitSet(new long[]{0x0000108002C14200L});
	public static final BitSet FOLLOW_expression_in_expression506 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIV_in_expression518 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression522 = new BitSet(new long[]{0x0000108002C14200L});
	public static final BitSet FOLLOW_expression_in_expression527 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_primary_exp_in_expression540 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_primary_exp560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_primary_exp569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FCALL_in_primary_exp579 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IDENT_in_primary_exp581 = new BitSet(new long[]{0x0000108002C14208L});
	public static final BitSet FOLLOW_argument_list_in_primary_exp583 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_array_elem_in_primary_exp597 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block618 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_declaration_in_block624 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_inst_list_in_block629 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_BLOCK_in_block641 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_inst_list_in_block647 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DECL_in_declaration677 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_decl_item_in_declaration682 = new BitSet(new long[]{0x0000004000004008L});
	public static final BitSet FOLLOW_IDENT_in_decl_item706 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARDECL_in_decl_item716 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IDENT_in_decl_item718 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_INTEGER_in_decl_item720 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INST_in_inst_list748 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_inst_list753 = new BitSet(new long[]{0x00002210C4008028L});
	public static final BitSet FOLLOW_expression_in_argument_list778 = new BitSet(new long[]{0x0000108002C14202L});
	public static final BitSet FOLLOW_print_item_in_print_list810 = new BitSet(new long[]{0x0000108202C14202L});
	public static final BitSet FOLLOW_TEXT_in_print_item833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_print_item843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_item_in_read_list872 = new BitSet(new long[]{0x0000008000004002L});
	public static final BitSet FOLLOW_IDENT_in_read_item896 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_elem_in_read_item905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARELEM_in_array_elem928 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IDENT_in_array_elem930 = new BitSet(new long[]{0x0000108002C14200L});
	public static final BitSet FOLLOW_expression_in_array_elem932 = new BitSet(new long[]{0x0000000000000008L});
}
