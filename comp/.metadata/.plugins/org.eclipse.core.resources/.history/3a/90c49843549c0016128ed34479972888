/**
 * This class implements all the methods for 3a code generation (NOTE: this
 * class must be coded by the student; the methods indicated here can be seen as
 * a suggestion, but are not actually necessary).
 *
 * @author MLB
 *
 */
public class Code3aGenerator {

	// Constructor not needed
	private Code3aGenerator() {
	}

	/**
	 * Generates the 3a statement: VAR t
	 **/
	public static Code3a genVar(Operand3a t) {
		Inst3a i = new Inst3a(Inst3a.TAC.VAR, t, null, null);
		return new Code3a(i);
	}

	/**
	 * Generate code for a binary operation
	 *
	 * @param op
	 *            must be a code op: Inst3a.TAC.XXX
	 */
	public static Code3a genBinOp(Inst3a.TAC op, ExpAttribute exp1,
			ExpAttribute exp2, Operand3a temp) {
		Code3a cod = exp1.code;
		cod.append(exp2.code);
		cod.append(genVar(temp));
		cod.append(new Inst3a(op, temp, exp1.place, exp2.place));
		return cod;
	}

	public static Code3a genAssign(ExpAttribute exp1, ExpAttribute exp2){
		Code3a code = exp1.code;
		code.append(new Inst3a(Inst3a.TAC.COPY, exp2.place, exp1.place, null));
		return code;
	}

	public static Code3a genIfThenElse(ExpAttribute cond, Code3a if_statement, Code3a else_statement) {
		Code3a code       = cond.code;
		LabelSymbol ELSE  = SymbDistrib.newLabel();
		LabelSymbol ENDIF = SymbDistrib.newLabel();
		Inst3a i          = new Inst3a(Inst3a.TAC.IFZ, cond.place, ELSE, null);
		code.append(i);
		code.append(if_statement);
		i = new Inst3a(Inst3a.TAC.GOTO, ENDIF, null, null);
		code.append(i);
		i = new Inst3a(Inst3a.TAC.LABEL, ELSE, null, null);
		code.append(i);
		code.append(else_statement);
		i = new Inst3a(Inst3a.TAC.LABEL, ENDIF, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genIfThen(ExpAttribute cond, Code3a if_statement) {
		Code3a code       = cond.code;
		LabelSymbol ENDIF = SymbDistrib.newLabel();
		Inst3a i          = new Inst3a(Inst3a.TAC.IFZ, cond.place, ENDIF ,null);
		code.append(i);
		code.append(if_statement);
		i = new Inst3a(Inst3a.TAC.LABEL, ENDIF, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genWhile(ExpAttribute cond, Code3a statement) {
		Code3a code          = new Code3a();
		LabelSymbol WHILE    = SymbDistrib.newLabel();
		LabelSymbol ENDWHILE = SymbDistrib.newLabel();
		Inst3a i             = new Inst3a(Inst3a.TAC.LABEL, WHILE, null ,null);
		code.append(i);
		code.append(cond.code);
		i = new Inst3a(Inst3a.TAC.IFZ, cond.place, ENDWHILE, null);
		code.append(i);
		code.append(statement);
		i = new Inst3a(Inst3a.TAC.GOTO, WHILE, null, null);
		code.append(i);
		i = new Inst3a(Inst3a.TAC.LABEL, ENDWHILE, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genFuncStart(VarSymbol var) {
		Code3a code = new Code3a();
		Inst3a i = new Inst3a(Inst3a.TAC.LABEL, var, null, null);
		code.append(i);
		i = new Inst3a(Inst3a.TAC.BEGINFUNC, null, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genFuncEnd() {
		Code3a code = new Code3a();
		Inst3a i = new Inst3a(Inst3a.TAC.ENDFUNC, null, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genReturn(ExpAttribute ret) {
		Code3a code = ret.code;
		Inst3a i = new Inst3a(Inst3a.TAC.RETURN, ret.place, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genCall(Code3a code, VarSymbol funcName) {
                if(code == null)
                    code = new Code3a();
		Inst3a i = new Inst3a(Inst3a.TAC.CALL, null, funcName, null);
		code.append(i);
		return code;
	}

	public static Code3a genCallReturn(Code3a code, VarSymbol funcName, VarSymbol varRet) {
                if(code == null)
                    code = new Code3a();
		Inst3a i = new Inst3a(Inst3a.TAC.CALL, varRet, funcName, null);
		code.append(i);
		return code;
	}

	public static Code3a genFuncArguments(ExpAttribute exp) {
		Code3a code = exp.code;
		Inst3a i = new Inst3a(Inst3a.TAC.ARG, exp.place, null, null);
		code.append(i);
		return code;
	}

	public static Code3a genPrintText(Data3a text) {
		Code3a code = new Code3a();
		code.appendData(text);
		Inst3a i = new Inst3a(Inst3a.TAC.ARG, text.getLabel(), null, null);
		code.append(i);
		i = new Inst3a(Inst3a.TAC.CALL, null, SymbDistrib.builtinPrintS, null);
		code.append(i);
		return code;
	}

	public static Code3a genPrintExpression(ExpAttribute exp) {
		Code3a code = exp.code;
		Inst3a i = new Inst3a(Inst3a.TAC.ARG, exp.place, null, null);
		code.append(i);
		i = new Inst3a(Inst3a.TAC.CALL, null, SymbDistrib.builtinPrintN, null);
		code.append(i);
		return code;
	}

	public static Code3a genRead(VarSymbol var) {
		Code3a code = new Code3a();
		Inst3a i = new Inst3a(Inst3a.TAC.CALL, var, SymbDistrib.builtinRead, null);
		code.append(i);
		return code;
	}

	public static Code3a genArrayAssignment(ExpAttribute val, VarSymbol arrayName,
		ExpAttribute index) {
		Code3a code = index.code;
		code.append(val.code);
		Inst3a i = new Inst3a(Inst3a.TAC.VARTAB, arrayName, index.place, val.place);
		code.append(i);
		return code;
	}

	public static Code3a genArrayAccess(VarSymbol dest, VarSymbol arrayName,
		ExpAttribute index) {
		Code3a code = index.code;
		Inst3a i = new Inst3a(Inst3a.TAC.TABVAR, dest, arrayName, index.place);
		code.append(i);
		return code;
	}
} // Code3aGenerator ***