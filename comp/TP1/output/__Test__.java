import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        TurtleLexer lex = new TurtleLexer(new ANTLRFileStream("/private/student/0/60/16002260/M1/comp/TP1/output/__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        TurtleParser g = new TurtleParser(tokens, 49100, null);
        try {
            g.doc();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}