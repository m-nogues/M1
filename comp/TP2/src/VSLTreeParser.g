tree grammar VSLTreeParser;

options {
	language     = Java;
	tokenVocab   = VSLParser;
	ASTLabelType = CommonTree;
}

s [SymbolTable ts] returns [Code3a code]
	: program[ts] {$code = $program.code;}
	;

program [SymbolTable ts] returns [Code3a code] @init {$code = new Code3a();}
	: ^(PROG (unit[ts] {$code.append($unit.code);})+)
	;

unit [SymbolTable ts] returns [Code3a code]
	: function[ts] {$code = $function.code;}
	| proto[ts] {$code = new Code3a();}
	;

function [SymbolTable ts] returns [Code3a code]
	: ^(FUNC_KW type {FunctionType t = new FunctionType($type.type_ret, false);} IDENT {ts.enterScope();} pl=param_list[ts, t] ^(BODY body=statement[ts]) {ts.leaveScope();} )
		{
			Operand3a op = ts.lookup($IDENT.text);
			LabelSymbol label = new LabelSymbol($IDENT.text);

			if (op == null) { // No declared prototype
				ts.insert($IDENT.text, new FunctionSymbol(label, t));
			} else if (op instanceof FunctionSymbol) {
				FunctionSymbol fs = (FunctionSymbol) op;
				if (!((FunctionType)fs.type).prototype) {
					Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
					System.exit(1);
				} if (!fs.type.isCompatible((Type)t)) {
					Errors.incompatibleTypes($IDENT, fs.type, t, null);
					System.exit(1);
				}
			}
			$code = Code3aGenerator.genFuncStart(label);
			$code.append($body.code);
			$code.append(Code3aGenerator.genFuncEnd());
		}
	;

proto [SymbolTable ts]
	: ^(PROTO_KW type {FunctionType t = new FunctionType($type.type_ret, true);} IDENT {ts.enterScope();} pl=param_list[ts, t] {ts.leaveScope();})
		{
			if(ts.lookup($IDENT.text) == null)
				ts.insert($IDENT.text, new FunctionSymbol(new LabelSymbol($IDENT.text), t));
			else {
				Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}
		}
	;

type returns [Type type_ret]
	: INT_KW {type_ret = Type.INT;}
	| VOID_KW {type_ret = Type.VOID;}
	;

param_list[SymbolTable ts, FunctionType type]
	: ^(PARAM (param[ts, type])*)
	;

param[SymbolTable ts, FunctionType type]
	: IDENT
		{
			if (ts.lookup($IDENT.text) == null) {
				VarSymbol vs = new VarSymbol(Type.INT, $IDENT.text, ts.getScope());
				vs.setParam();
				ts.insert($IDENT.text, vs);
				type.extend(Type.INT);
			} else {
        Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
        System.exit(1);
      }
		}
	| ^(ARRAY IDENT)
		{
			if(ts.lookup($IDENT.text) == null) {
				ArrayType t = new ArrayType(Type.INT, Integer.MAX_VALUE);
				VarSymbol vs = new VarSymbol(Type.INT, $IDENT.text, ts.getScope());
				vs.setParam();
				ts.insert($IDENT.text, vs);
				type.extend(t);
			} else {
        Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
        System.exit(1);
      }
		}
	;

statement [SymbolTable ts] returns [Code3a code]
	: ^(ASSIGN_KW e=expression[ts] statement_lhs[ts, $e.expAtt]) {$code = $statement_lhs.code;}
	| ^(RETURN_KW expression[ts])
		{
			if($expression.expAtt.type != Type.INT) {
				Errors.incompatibleTypes($RETURN_KW, Type.INT, $expression.expAtt.type, null);
				System.exit(1);
			}
			$code = Code3aGenerator.genReturn($expression.expAtt);
		}
	| ^(IF_KW if_cond = expression[ts] if_st=statement[ts] (else_st=statement[ts])?)
		{
			if ($else_st.code != null)
				$code = Code3aGenerator.genIfThenElse($if_cond.expAtt, $if_st.code, $else_st.code);
			else
				$code = Code3aGenerator.genIfThen($if_cond.expAtt, $if_st.code);
		}
	| ^(WHILE_KW wh_cond=expression[ts] while_st=statement[ts]) {$code = Code3aGenerator.genWhile($wh_cond.expAtt, $while_st.code);}
	| ^(FCALL_S IDENT argument_list[ts, null])
		{
			Operand3a op = ts.lookup($IDENT.text);
			if(op != null && op instanceof FunctionSymbol) {
				FunctionSymbol fs = (FunctionSymbol)op;
				if (((FunctionType)fs.type).getReturnType() == Type.VOID)
					$code = Code3aGenerator.genCall($argument_list.code, new LabelSymbol($IDENT.text));
				else {
					Errors.unknownIdentifier($IDENT, $IDENT.text, null);
					System.exit(1);
				}
			}
		}
  | ^(PRINT_KW print_list[ts]) {$code = $print_list.code;}
  | ^(READ_KW read_list[ts]) {$code = $read_list.code;}
	| code_sequence = block[ts] {$code = $code_sequence.code;}
	;

statement_lhs [SymbolTable ts, ExpAttribute exp] returns [Code3a code]
	: IDENT
		{
			Operand3a op = ts.lookup($IDENT.text);
			if (op != null) {
				if(exp.type instanceof ArrayType) {
					Errors.incompatibleTypes($IDENT, Type.INT, exp.type, null);
					System.exit(1);
				} if(op.type instanceof ArrayType){
					Errors.incompatibleTypes($IDENT, Type.INT, op.type, null);
					System.exit(1);
				}

				$code = Code3aGenerator.genAssign(exp, new ExpAttribute(Type.INT, new Code3a(), op));
			} else {
				Errors.unknownIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}
		}
	| array_elem[ts, exp] {$code = $array_elem.expAtt.code;}
	;

expression [SymbolTable ts] returns [ExpAttribute expAtt]
	: ^(PLUS e1=expression[ts] e2=expression[ts])
		{
			Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.ADD, e1, e2, temp);
			expAtt         = new ExpAttribute(ty, cod, temp);
		}
	| ^(MINUS e1=expression[ts] e2=expression[ts])
		{
			Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.SUB, e1, e2, temp);
			expAtt         = new ExpAttribute(ty, cod, temp);
		}
	| ^(MUL e1=expression[ts] e2=expression[ts])
		{
			Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.MUL, e1, e2, temp);
			expAtt         = new ExpAttribute(ty, cod, temp);
		}
	| ^(DIV e1=expression[ts] e2=expression[ts])
		{
			Type ty        = TypeCheck.checkBinOp(e1.type, e2.type);
			VarSymbol temp = SymbDistrib.newTemp();
			Code3a cod     = Code3aGenerator.genBinOp(Inst3a.TAC.DIV, e1, e2, temp);
			expAtt         = new ExpAttribute(ty, cod, temp);
		}
	| pe=primary_exp[ts] {expAtt = pe;}
	;

primary_exp [SymbolTable ts] returns [ExpAttribute expAtt]
	: INTEGER
		{
			ConstSymbol cs = new ConstSymbol(Integer.parseInt($INTEGER.text));
			expAtt = new ExpAttribute(Type.INT, new Code3a(), cs);
		}
	| IDENT
		{
			Operand3a id = ts.lookup($IDENT.text);
			if (id != null)
				expAtt = new ExpAttribute(id.type, new Code3a(), id);
			else {
				Errors.unknownIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}
		}
	| ^(FCALL IDENT argument_list[ts, null])
		{
			Operand3a op = ts.lookup($IDENT.text);
			if (op != null && op instanceof FunctionSymbol) {
				FunctionSymbol fs = (FunctionSymbol) op;
				if (((FunctionType)fs.type).getReturnType() != Type.VOID) {
					VarSymbol temp = SymbDistrib.newTemp();
					Code3a code = Code3aGenerator.genVar(temp);
					code.append(Code3aGenerator.genCallReturn($argument_list.code, new LabelSymbol($IDENT.text), temp));
					expAtt = new ExpAttribute(((FunctionType)fs.type).getReturnType(), code, temp);
				} else {
					Errors.incompatibleTypes($IDENT, Type.INT, ((FunctionType)fs.type).getReturnType(), null);
					System.exit(1);
				}
			} else {
				Errors.unknownIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}
		}
    | array_elem[ts, null] {$expAtt = $array_elem.expAtt;}
		| ^(NEGAT p=primary[ts])
		{
				Code3a code = new Code3a();
				code.append(new Inst3a(Inst3a.TAC.NEG, p.place, p.place, null));
				expAtt = new ExpAttribute(p.type, code, p.place);
		}
	;

primary [SymbolTable ts] returns [ExpAttribute expAtt]
	: pe=primary_exp[ts] { expAtt = pe; }
	;

block [SymbolTable ts] returns [Code3a code]
	: ^(BLOCK {ts.enterScope();} decl_block=declaration[ts] inst_block=inst_list[ts])
		{
			$code = $decl_block.code;
			$code.append($inst_block.code);
			ts.leaveScope();
		}
	| ^(BLOCK {ts.enterScope();} inst_block2=inst_list[ts])
		{
			$code = $inst_block2.code;
			ts.leaveScope();
		}
	;

declaration [SymbolTable ts] returns [Code3a code] @init {$code = new Code3a();}
	: ^(DECL (di=decl_item[ts] {$code.append($di.code);})+)
	;

decl_item [SymbolTable ts] returns [Code3a code]
	: IDENT
		{
			VarSymbol v = (VarSymbol)ts.lookup($IDENT.text);
			if(v != null && v.getScope() == ts.getScope()) {
				Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}

			v = new VarSymbol(Type.INT, $IDENT.text, ts.getScope());
			ts.insert($IDENT.text, v);
			code = Code3aGenerator.genVar(v);
		}
	| ^(ARDECL IDENT INTEGER)
		{
			VarSymbol v = (VarSymbol)ts.lookup($IDENT.text);
			if(v != null && v.getScope() == ts.getScope()) {
				Errors.redefinedIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}

			ArrayType t = new ArrayType(Type.INT, Integer.parseInt($INTEGER.text));
			v = new VarSymbol(t, $IDENT.text, ts.getScope());
			ts.insert($IDENT.text, v);
			$code = Code3aGenerator.genVar(v);
		}
	;

inst_list [SymbolTable ts] returns [Code3a code] @init {$code = new Code3a();}
	: ^(INST (st=statement[ts] {$code.append($st.code);})+)
	;


argument_list [SymbolTable ts, FunctionType t] returns [Code3a code]
	: (expression[ts]
		{
			$code = Code3aGenerator.genFuncArguments($expression.expAtt);
			if(t != null) {
				if($expression.expAtt.place.type == Type.I_CONST) // isCompatible will fail if we don't do this
					t.extend(Type.INT);
				else
					t.extend($expression.expAtt.place.type);
			}
		}
	)*
	;

print_list [SymbolTable ts] returns [Code3a code] @init {$code = new Code3a();}
	: (print_item[ts] {$code.append($print_item.code);} )*
	;

print_item [SymbolTable ts] returns [Code3a code]
	: TEXT {$code = Code3aGenerator.genPrintText(new Data3a($TEXT.text.substring(1,$TEXT.text.length() - 1)));}
  | expression[ts] {$code = Code3aGenerator.genPrintExpression($expression.expAtt);}
  ;

read_list [SymbolTable ts] returns [Code3a code] @init {$code = new Code3a();}
	: (read_item[ts] {$code.append($read_item.code);} )*
	;

read_item [SymbolTable ts] returns [Code3a code]
	: IDENT
		{
			VarSymbol v = (VarSymbol)ts.lookup($IDENT.text);
			if(v != null)
				$code = Code3aGenerator.genRead(v);
			else {
				Errors.unknownIdentifier($IDENT, $IDENT.text, null);
				System.exit(1);
			}
		}
	| array_elem[ts, null] { }
  ;

array_elem [SymbolTable ts, ExpAttribute exp] returns [ExpAttribute expAtt]
	: ^(ARELEM IDENT expression[ts])
		{
			if(exp != null) { // Affectation
				Operand3a op = ts.lookup($IDENT.text);
				Code3a c = Code3aGenerator.genArrayAssignment(exp, op, $expression.expAtt);
				$expAtt = new ExpAttribute(Type.INT, c, op);
			} else {
				VarSymbol tmp = SymbDistrib.newTemp();
				Code3a c = Code3aGenerator.genVar(tmp);
				Operand3a op = ts.lookup($IDENT.text);
				c.append(Code3aGenerator.genArrayAccess(tmp, op, $expression.expAtt));
				$expAtt = new ExpAttribute(Type.INT, c, tmp);
			}
		}
	;
