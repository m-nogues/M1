tree grammar VSLTreeParser;

options {
	language     = Java;
	tokenVocab   = VSLParser;
	ASTLabelType = CommonTree;
}

s [SymbolTable symTab] returns [Code3a code]
	: st=statement[symTab] { code = st; }
	;

statement [SymbolTable symTab] returns [Code3a code]
	: e=expression[symTab] { code = e.code; }
	| ^(ASSIGN_KW e=expression IDENT)
		{
			Operand3a o = symTab.lookup($IDENT.text);
			code = Code3aGenerator.genAssign(o, e);
		}
	| ^(ASSIGN_KW e=expression aAtt=array_elem)
		{
			code = Code3aGenerator.genAssignArray(aAtt, e);
		}
	;
	
array_elem [SymbolTable symTab] returns [ArrayAttributes aAtt]
	: ^(ARELEM  IDENT e=expression)
		{
			op = symTab.lookup($IDENT.text);
			aAtt = new ArrayAttributes(op, e);
		}
	;

expression [SymbolTable symTab] returns [ExpAttribute expAtt]
	: ^(PLUS e1=expression[symTab] e2=expression[symTab]) 
		{ 
			Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.ADD, temp, e1, e2);
			expAtt = new ExpAttribute(ty, cod, temp);
		}
	| ^(MINUS e1=expression[symTab] e2=expression[symTab]) 
		{ 
			Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.SUB, temp, e1, e2);
			expAtt = new ExpAttribute(ty, cod, temp);
		}
	| ^(MUL e1=expression[symTab] e2=expression[symTab]) 
		{ 
			Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.MUL, temp, e1, e2);
			expAtt = new ExpAttribute(ty, cod, temp);
		}
	| ^(DIV e1=expression[symTab] e2=expression[symTab]) 
		{ 
			Type ty = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod = Code3aGenerator.genBinOp(Inst3a.TAC.DIV, temp, e1, e2);
			expAtt = new ExpAttribute(ty, cod, temp);
		}
	| pe=primary_exp[symTab] { expAtt = pe; }
	;

primary_exp [SymbolTable symTab] returns [ExpAttribute expAtt]
	: INTEGER
		{
			ConstSymbol cs = new ConstSymbol(Integer.parseInt($INTEGER.text));
			expAtt = new ExpAttribute(Type.INT, new Code3a(), cs);
		}
	| IDENT
		{
			Operand3a id = symTab.lookup($IDENT.text);
			expAtt = new ExpAttribute(id.type, new Code3a(), id);
		}
	| "-" INTEGER
		{
			ConstSymbol cs = new ConstSymbol(-Integer.parseInt($INTEGER.text));
			expAtt = new ExpAttribute(Type.INT, new Code3a(), cs);
		}
	;
