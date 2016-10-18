// $ANTLR 3.5.1 /private/student/0/60/16002260/M1/comp/TP1/Turtle.g 2016-10-18 08:50:20

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TurtleLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public TurtleLexer() {} 
	public TurtleLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public TurtleLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/private/student/0/60/16002260/M1/comp/TP1/Turtle.g"; }

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:2:6: ( ',' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:2:8: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:3:6: ( '.' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:3:8: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:4:7: ( ';' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:4:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:5:7: ( '[' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:5:9: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:6:7: ( ']' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:6:9: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "IDENT"
	public final void mIDENT() throws RecognitionException {
		try {
			int _type = IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:27:7: ( '<' ID '>' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:27:15: '<' ID '>'
			{
			match('<'); 
			mID(); 

			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:28:8: ( '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' | ' ' )+ '\"' )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:28:10: '\"' ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' | ' ' )+ '\"'
			{
			match('\"'); 
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:28:14: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' | ' ' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==' '||LA1_0=='&'||LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:
					{
					if ( input.LA(1)==' '||input.LA(1)=='&'||input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:29:4: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' )+ )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:29:6: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' )+
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:29:25: ( 'a' .. 'z' | 'A' .. 'Z' | '-' | '_' | '0' .. '9' | '&' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='&'||LA2_0=='-'||(LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:
					{
					if ( input.LA(1)=='&'||input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:5: ( ( ' ' | '\\t' | ( '\\r' )? '\\n' )+ )
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:13: ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
			{
			// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:13: ( ' ' | '\\t' | ( '\\r' )? '\\n' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=4;
				switch ( input.LA(1) ) {
				case ' ':
					{
					alt4=1;
					}
					break;
				case '\t':
					{
					alt4=2;
					}
					break;
				case '\n':
				case '\r':
					{
					alt4=3;
					}
					break;
				}
				switch (alt4) {
				case 1 :
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:14: ' '
					{
					match(' '); 
					}
					break;
				case 2 :
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:18: '\\t'
					{
					match('\t'); 
					}
					break;
				case 3 :
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:23: ( '\\r' )? '\\n'
					{
					// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:23: ( '\\r' )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0=='\r') ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:30:23: '\\r'
							{
							match('\r'); 
							}
							break;

					}

					match('\n'); 
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | IDENT | STRING | ID | WS )
		int alt5=9;
		switch ( input.LA(1) ) {
		case ',':
			{
			alt5=1;
			}
			break;
		case '.':
			{
			alt5=2;
			}
			break;
		case ';':
			{
			alt5=3;
			}
			break;
		case '[':
			{
			alt5=4;
			}
			break;
		case ']':
			{
			alt5=5;
			}
			break;
		case '<':
			{
			alt5=6;
			}
			break;
		case '\"':
			{
			alt5=7;
			}
			break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt5=8;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt5=9;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 5, 0, input);
			throw nvae;
		}
		switch (alt5) {
			case 1 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:10: T__8
				{
				mT__8(); 

				}
				break;
			case 2 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:15: T__9
				{
				mT__9(); 

				}
				break;
			case 3 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:20: T__10
				{
				mT__10(); 

				}
				break;
			case 4 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:26: T__11
				{
				mT__11(); 

				}
				break;
			case 5 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:32: T__12
				{
				mT__12(); 

				}
				break;
			case 6 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:38: IDENT
				{
				mIDENT(); 

				}
				break;
			case 7 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:44: STRING
				{
				mSTRING(); 

				}
				break;
			case 8 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:51: ID
				{
				mID(); 

				}
				break;
			case 9 :
				// /private/student/0/60/16002260/M1/comp/TP1/Turtle.g:1:54: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
