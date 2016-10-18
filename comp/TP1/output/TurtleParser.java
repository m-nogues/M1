// $ANTLR 3.5.1 /private/student/0/60/16002260/M1/comp/TP1/Turtle.g 2016-10-18 08:50:20

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
@SuppressWarnings("all")
public class TurtleParser extends DebugParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "IDENT", "STRING", "WS", 
		"','", "'.'", "';'", "'['", "']'"
	};
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int ID=4;
	public static final int IDENT=5;
	public static final int STRING=6;
	public static final int WS=7;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public static final String[] ruleNames = new String[] {
		"invalidRule", "bloc", "a", "doc", "p", "s", "o"
	};

	public static final boolean[] decisionCanBacktrack = new boolean[] {
		false, // invalid decision
		false, false, false, false, false, false, false
	};

 
	public int ruleLevel = 0;
	public int getRuleLevel() { return ruleLevel; }
	public void incRuleLevel() { ruleLevel++; }
	public void decRuleLevel() { ruleLevel--; }
	public TurtleParser(TokenStream input) {
		this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
	}
	public TurtleParser(TokenStream input, int port, RecognizerSharedState state) {
		super(input, state);
		DebugEventSocketProxy proxy =
			new DebugEventSocketProxy(this, port, null);

		setDebugListener(proxy);
		try {
			proxy.handshake();
		}
		catch (IOException ioe) {
			reportError(ioe);
		}
	}

	public TurtleParser(TokenStream input, DebugEventListener dbg) {
		super(input, dbg, new RecognizerSharedState());
	}

	protected boolean evalPredicate(boolean result, String predicate) {
		dbg.semanticPredicate(result, predicate);
		return result;
	}

	@Override public String[] getTokenNames() { return TurtleParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/private/student/0/60/16002260/M1/comp/TP1/Turtle.g"; }



	// $ANTLR start "doc"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:3:1: doc : (e= s '.' )+ EOF ;
	public final void doc() throws RecognitionException {
		String e =null;

		try { dbg.enterRule(getGrammarFileName(), "doc");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(3, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:3:5: ( (e= s '.' )+ EOF )
			dbg.enterAlt(1);

			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:4:9: (e= s '.' )+ EOF
			{
			dbg.location(4,9);
			int i = 0;dbg.location(4,22);
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:4:22: (e= s '.' )+
			int cnt1=0;
			try { dbg.enterSubRule(1);

			loop1:
			while (true) {
				int alt1=2;
				try { dbg.enterDecision(1, decisionCanBacktrack[1]);

				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENT) ) {
					alt1=1;
				}

				} finally {dbg.exitDecision(1);}

				switch (alt1) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:4:23: e= s '.'
					{
					dbg.location(4,24);
					pushFollow(FOLLOW_s_in_doc23);
					e=s();
					state._fsp--;
					dbg.location(4,27);
					match(input,9,FOLLOW_9_in_doc25); dbg.location(4,31);
					i++; System.out.println(e + ".");
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					dbg.recognitionException(eee);

					throw eee;
				}
				cnt1++;
			}
			} finally {dbg.exitSubRule(1);}
			dbg.location(4,79);
			match(input,EOF,FOLLOW_EOF_in_doc31); dbg.location(4,83);
			System.out.println("nombre de descriptions : " + i);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(5, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "doc");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

	}
	// $ANTLR end "doc"



	// $ANTLR start "s"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:7:1: s returns [String nTriples] : IDENT pred= p[$IDENT.text + \" \"] ( ';' pred= p[$IDENT.text] )* ;
	public final String s() throws RecognitionException {
		String nTriples = null;


		Token IDENT1=null;
		String pred =null;

		try { dbg.enterRule(getGrammarFileName(), "s");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(7, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:7:44: ( IDENT pred= p[$IDENT.text + \" \"] ( ';' pred= p[$IDENT.text] )* )
			dbg.enterAlt(1);

			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:8:10: IDENT pred= p[$IDENT.text + \" \"] ( ';' pred= p[$IDENT.text] )*
			{
			dbg.location(8,10);
			IDENT1=(Token)match(input,IDENT,FOLLOW_IDENT_in_s78); dbg.location(8,16);
			nTriples = (IDENT1!=null?IDENT1.getText():null) + " ";dbg.location(8,53);
			pushFollow(FOLLOW_p_in_s84);
			pred=p((IDENT1!=null?IDENT1.getText():null) + " ");
			state._fsp--;
			dbg.location(8,75);
			nTriples += pred;dbg.location(8,106);
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:8:106: ( ';' pred= p[$IDENT.text] )*
			try { dbg.enterSubRule(2);

			loop2:
			while (true) {
				int alt2=2;
				try { dbg.enterDecision(2, decisionCanBacktrack[2]);

				int LA2_0 = input.LA(1);
				if ( (LA2_0==10) ) {
					alt2=1;
				}

				} finally {dbg.exitDecision(2);}

				switch (alt2) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:8:107: ';' pred= p[$IDENT.text]
					{
					dbg.location(8,107);
					match(input,10,FOLLOW_10_in_s90); dbg.location(8,115);
					pushFollow(FOLLOW_p_in_s94);
					pred=p((IDENT1!=null?IDENT1.getText():null));
					state._fsp--;
					dbg.location(8,131);
					nTriples += " .\n" + (IDENT1!=null?IDENT1.getText():null) + pred;
					}
					break;

				default :
					break loop2;
				}
			}
			} finally {dbg.exitSubRule(2);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(9, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "s");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return nTriples;
	}
	// $ANTLR end "s"



	// $ANTLR start "p"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:11:1: p[String S] returns [String nTriples] : IDENT (op= o |op= bloc[i] ) ( ',' (op= o |op= bloc[i] ) )* ;
	public final String p(String S) throws RecognitionException {
		String nTriples = null;


		Token IDENT2=null;
		String op =null;

		try { dbg.enterRule(getGrammarFileName(), "p");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(11, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:11:46: ( IDENT (op= o |op= bloc[i] ) ( ',' (op= o |op= bloc[i] ) )* )
			dbg.enterAlt(1);

			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:9: IDENT (op= o |op= bloc[i] ) ( ',' (op= o |op= bloc[i] ) )*
			{
			dbg.location(12,9);
			IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_p136); dbg.location(12,15);
			nTriples = (IDENT2!=null?IDENT2.getText():null) + " ";dbg.location(12,48);
			int i = 1;dbg.location(12,61);
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:61: (op= o |op= bloc[i] )
			int alt3=2;
			try { dbg.enterSubRule(3);
			try { dbg.enterDecision(3, decisionCanBacktrack[3]);

			int LA3_0 = input.LA(1);
			if ( ((LA3_0 >= IDENT && LA3_0 <= STRING)) ) {
				alt3=1;
			}
			else if ( (LA3_0==11) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(3);}

			switch (alt3) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:62: op= o
					{
					dbg.location(12,64);
					pushFollow(FOLLOW_o_in_p145);
					op=o();
					state._fsp--;

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:67: op= bloc[i]
					{
					dbg.location(12,69);
					pushFollow(FOLLOW_bloc_in_p149);
					op=bloc(i);
					state._fsp--;

					}
					break;

			}
			} finally {dbg.exitSubRule(3);}
			dbg.location(12,79);
			nTriples += op;dbg.location(12,108);
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:108: ( ',' (op= o |op= bloc[i] ) )*
			try { dbg.enterSubRule(5);

			loop5:
			while (true) {
				int alt5=2;
				try { dbg.enterDecision(5, decisionCanBacktrack[5]);

				int LA5_0 = input.LA(1);
				if ( (LA5_0==8) ) {
					alt5=1;
				}

				} finally {dbg.exitDecision(5);}

				switch (alt5) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:109: ',' (op= o |op= bloc[i] )
					{
					dbg.location(12,109);
					match(input,8,FOLLOW_8_in_p156); dbg.location(12,113);
					i++;dbg.location(12,120);
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:120: (op= o |op= bloc[i] )
					int alt4=2;
					try { dbg.enterSubRule(4);
					try { dbg.enterDecision(4, decisionCanBacktrack[4]);

					int LA4_0 = input.LA(1);
					if ( ((LA4_0 >= IDENT && LA4_0 <= STRING)) ) {
						alt4=1;
					}
					else if ( (LA4_0==11) ) {
						alt4=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 4, 0, input);
						dbg.recognitionException(nvae);
						throw nvae;
					}

					} finally {dbg.exitDecision(4);}

					switch (alt4) {
						case 1 :
							dbg.enterAlt(1);

							// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:121: op= o
							{
							dbg.location(12,123);
							pushFollow(FOLLOW_o_in_p163);
							op=o();
							state._fsp--;

							}
							break;
						case 2 :
							dbg.enterAlt(2);

							// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:12:126: op= bloc[i]
							{
							dbg.location(12,128);
							pushFollow(FOLLOW_bloc_in_p167);
							op=bloc(i);
							state._fsp--;

							}
							break;

					}
					} finally {dbg.exitSubRule(4);}
					dbg.location(12,138);
					nTriples += " .\n" + S + (IDENT2!=null?IDENT2.getText():null) + " " + op;
					}
					break;

				default :
					break loop5;
				}
			}
			} finally {dbg.exitSubRule(5);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(13, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "p");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return nTriples;
	}
	// $ANTLR end "p"



	// $ANTLR start "o"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:15:1: o returns [String nTriples] : ( IDENT | STRING );
	public final String o() throws RecognitionException {
		String nTriples = null;


		Token IDENT3=null;
		Token STRING4=null;

		try { dbg.enterRule(getGrammarFileName(), "o");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(15, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:15:44: ( IDENT | STRING )
			int alt6=2;
			try { dbg.enterDecision(6, decisionCanBacktrack[6]);

			int LA6_0 = input.LA(1);
			if ( (LA6_0==IDENT) ) {
				alt6=1;
			}
			else if ( (LA6_0==STRING) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(6);}

			switch (alt6) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:16:9: IDENT
					{
					dbg.location(16,9);
					IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_o217); dbg.location(16,15);
					nTriples = "<" + (IDENT3!=null?IDENT3.getText():null) + "> ";
					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:16:57: STRING
					{
					dbg.location(16,57);
					STRING4=(Token)match(input,STRING,FOLLOW_STRING_in_o223); dbg.location(16,64);
					nTriples = (STRING4!=null?STRING4.getText():null);
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
		dbg.location(17, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "o");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return nTriples;
	}
	// $ANTLR end "o"



	// $ANTLR start "bloc"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:19:1: bloc[int i] returns [String nTriples] : '[' anon= a[\"_:v\" + i] ']' ;
	public final String bloc(int i) throws RecognitionException {
		String nTriples = null;


		String anon =null;

		try { dbg.enterRule(getGrammarFileName(), "bloc");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(19, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:19:46: ( '[' anon= a[\"_:v\" + i] ']' )
			dbg.enterAlt(1);

			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:20:9: '[' anon= a[\"_:v\" + i] ']'
			{
			dbg.location(20,9);
			match(input,11,FOLLOW_11_in_bloc269); dbg.location(20,17);
			pushFollow(FOLLOW_a_in_bloc273);
			anon=a("_:v" + i);
			state._fsp--;
			dbg.location(20,31);
			match(input,12,FOLLOW_12_in_bloc276); dbg.location(20,35);
			nTriples = "_:v" + i + " .\n" + anon;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(21, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "bloc");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return nTriples;
	}
	// $ANTLR end "bloc"



	// $ANTLR start "a"
	// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:23:1: a[String name] returns [String nTriples] : pred= p[$name] ( ';' pred= p[$name] )* ;
	public final String a(String name) throws RecognitionException {
		String nTriples = null;


		String pred =null;

		try { dbg.enterRule(getGrammarFileName(), "a");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(23, 0);

		try {
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:23:41: (pred= p[$name] ( ';' pred= p[$name] )* )
			dbg.enterAlt(1);

			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:24:9: pred= p[$name] ( ';' pred= p[$name] )*
			{
			dbg.location(24,13);
			pushFollow(FOLLOW_p_in_a316);
			pred=p(name);
			state._fsp--;
			dbg.location(24,23);
			nTriples = name + " " + pred;dbg.location(24,67);
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:24:67: ( ';' pred= p[$name] )*
			try { dbg.enterSubRule(7);

			loop7:
			while (true) {
				int alt7=2;
				try { dbg.enterDecision(7, decisionCanBacktrack[7]);

				int LA7_0 = input.LA(1);
				if ( (LA7_0==10) ) {
					alt7=1;
				}

				} finally {dbg.exitDecision(7);}

				switch (alt7) {
				case 1 :
					dbg.enterAlt(1);

					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:24:68: ';' pred= p[$name]
					{
					dbg.location(24,68);
					match(input,10,FOLLOW_10_in_a322); dbg.location(24,76);
					pushFollow(FOLLOW_p_in_a326);
					pred=p(name);
					state._fsp--;
					dbg.location(24,86);
					nTriples += " .\n" + name + " " + pred;
					}
					break;

				default :
					break loop7;
				}
			}
			} finally {dbg.exitSubRule(7);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(25, 8);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "a");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return nTriples;
	}
	// $ANTLR end "a"

	// Delegated rules



	public static final BitSet FOLLOW_s_in_doc23 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_doc25 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_EOF_in_doc31 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_s78 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_p_in_s84 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_10_in_s90 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_p_in_s94 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_IDENT_in_p136 = new BitSet(new long[]{0x0000000000000860L});
	public static final BitSet FOLLOW_o_in_p145 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_bloc_in_p149 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_8_in_p156 = new BitSet(new long[]{0x0000000000000860L});
	public static final BitSet FOLLOW_o_in_p163 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_bloc_in_p167 = new BitSet(new long[]{0x0000000000000102L});
	public static final BitSet FOLLOW_IDENT_in_o217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_o223 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_bloc269 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_a_in_bloc273 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_bloc276 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_p_in_a316 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_10_in_a322 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_p_in_a326 = new BitSet(new long[]{0x0000000000000402L});
}
