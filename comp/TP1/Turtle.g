grammar Turtle;

doc :
	{int i = 0;} (e=s '.' {i++; System.out.println($e.nTriples + ".");})+ EOF {System.out.println("nombre de descriptions : " + i);}
	;

s returns [String nTriples]	:
	'<' ID {$nTriples = "<" + $ID.text + "> ";}'>' pred=p[$ID.text] {$nTriples += $pred.nTriples;} (';' pred=p[$ID.text] {$nTriples += " .\n<" + $ID.text + "> " + $pred.nTriples;})*
	;

p[String S] returns [String nTriples]	:
	'<' ID {$nTriples = "<" + $ID.text + "> ";}'>' op=o {$nTriples += $op.nTriples;} (',' op=o {$nTriples += " .\n<" + $S + "> <" + $ID.text + "> " + $op.nTriples;})*
	;

o returns [String nTriples]	:
	'<' ID {$nTriples = "<" + $ID.text + "> ";}'>' | '"' {$nTriples = "\"";} (ID {$nTriples += " " + $ID.text;})+ '"' {$nTriples += " \"";}
	;

ID :	('a'..'z'|'A'..'Z'|'-'|'_'|'0'..'9')+ ;
WS :	(' '|'\t'|'\r'? '\n')+ {skip();} ;
