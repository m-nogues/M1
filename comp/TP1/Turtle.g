grammar Turtle;

doc :
        {int i = 0;} (e=s '.' {i++; System.out.println($e.nTriples + ".");})+ EOF {System.out.println("nombre de descriptions : " + i);}
        ;

s returns [String nTriples]                :
         IDENT {$nTriples = $IDENT.text + " ";} pred=p[$IDENT.text + " "] {$nTriples += $pred.nTriples;} (';' pred=p[$IDENT.text] {$nTriples += " .\n" + $IDENT.text + $pred.nTriples;})*
        ;

p[String S] returns [String nTriples]        :
        IDENT {$nTriples = $IDENT.text + " ";} {int i = 1;} (op=o|op=bloc[i]) {$nTriples += $op.nTriples;} (',' {i++;} (op=o|op=bloc[i]) {$nTriples += " .\n" + $S + $IDENT.text + " " + $op.nTriples;})*
        ;

o returns [String nTriples]                :
        IDENT {$nTriples = "<" + $IDENT.text + "> ";} | STRING {$nTriples = $STRING.text;}
        ;
       
bloc[int i] returns [String nTriples]        :
        '[' anon=a["_:v" + i] ']' {$nTriples = "_:v" + i + " .\n" + $anon.nTriples;}
        ;
       
a[String name] returns [String nTriples]:
        pred=p[$name] {$nTriples = $name + " " + $pred.nTriples;} (';' pred=p[$name] {$nTriples += " .\n" + $name + " " + $pred.nTriples;})*
        ;
        
IDENT	:       '<' ID '>';
STRING	:	'"' ('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9'|'&'|' ')+ '"';
ID	:	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9'|'&')+;
WS 	:       (' '|'\t'|'\r'? '\n')+ {skip();} ;