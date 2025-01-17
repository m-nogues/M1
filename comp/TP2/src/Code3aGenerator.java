/**
 * This class implements all the methods for 3a code generation (NOTE: this
 * class must be coded by the student; the methods indicated here can be seen as
 * a suggestion, but are not actually necessary).
 *
 * @author MLB
 */
public class Code3aGenerator {

	/**
	 * Generate code for an access to an array.
	 *
	 * @param dest
	 *            the destination
	 * @param arrayName
	 *            the array name
	 * @param index
	 *            the index
	 * @return the code 3 a
	 */
	public static Code3a genArrayAccess(VarSymbol dest, Operand3a arrayName, ExpAttribute index) {
		Code3a code = index.code;
		code.append(new Inst3a(Inst3a.TAC.TABVAR, dest, arrayName, index.place));
		return code;
	}

	/**
	 * Generate code for an assignment in an array.
	 *
	 * @param val
	 *            the value
	 * @param arrayName
	 *            the array name
	 * @param index
	 *            the index
	 * @return the code 3 a
	 */
	public static Code3a genArrayAssignment(ExpAttribute val, Operand3a arrayName, ExpAttribute index) {
		Code3a code = index.code;
		code.append(val.code);
		code.append(new Inst3a(Inst3a.TAC.VARTAB, arrayName, index.place, val.place));
		return code;
	}

	/**
	 * Generate code for an assignment.
	 *
	 * @param exp1
	 *            the expression 1
	 * @param exp2
	 *            the expression 2
	 * @return the code 3 a
	 */
	public static Code3a genAssign(ExpAttribute exp1, ExpAttribute exp2) {
		Code3a code = exp1.code;
		code.append(new Inst3a(Inst3a.TAC.COPY, exp2.place, exp1.place, null));
		return code;
	}

	/**
	 * Generate code for a binary operation.
	 *
	 * @param op
	 *            must be a code op: Inst3a.TAC.XXX
	 * @param exp1
	 *            the expression 1
	 * @param exp2
	 *            the expression 2
	 * @param temp
	 *            the temporary variable
	 * @return the code 3 a
	 */
	public static Code3a genBinOp(Inst3a.TAC op, ExpAttribute exp1, ExpAttribute exp2, Operand3a temp) {
		Code3a cod = exp1.code;
		cod.append(exp2.code);
		cod.append(genVar(temp));
		cod.append(new Inst3a(op, temp, exp1.place, exp2.place));
		return cod;
	}

	/**
	 * Generate code for a call to a function.
	 *
	 * @param code
	 *            the code
	 * @param funcName
	 *            the function name
	 * @return the code 3 a
	 */
	public static Code3a genCall(Code3a code, LabelSymbol funcName) {
		if (code == null)
			code = new Code3a();
		code.append(new Inst3a(Inst3a.TAC.CALL, null, funcName, null));
		return code;
	}

	/**
	 * Generate code for a return call.
	 *
	 * @param code
	 *            the code
	 * @param funcName
	 *            the function name
	 * @param varRet
	 *            the return variable
	 * @return the code 3 a
	 */
	public static Code3a genCallReturn(Code3a code, LabelSymbol funcName, VarSymbol varRet) {
		if (code == null)
			code = new Code3a();
		code.append(new Inst3a(Inst3a.TAC.CALL, varRet, funcName, null));
		return code;
	}

	/**
	 * Generate code for function arguments.
	 *
	 * @param exp
	 *            the expression
	 * @return the code 3 a
	 */
	public static Code3a genFuncArguments(ExpAttribute exp) {
		Code3a code = exp.code;
		code.append(new Inst3a(Inst3a.TAC.ARG, exp.place, null, null));
		return code;
	}

	/**
	 * Generate code for the end of a function.
	 *
	 * @return the code 3 a
	 */
	public static Code3a genFuncEnd() {
		return new Code3a(new Inst3a(Inst3a.TAC.ENDFUNC, null, null, null));
	}

	/**
	 * Generate code for the start of a function.
	 *
	 * @param var
	 *            the variable
	 * @return the code 3 a
	 */
	public static Code3a genFuncStart(LabelSymbol var) {
		Code3a code = new Code3a();
		code.append(new Inst3a(Inst3a.TAC.LABEL, var, null, null));
		code.append(new Inst3a(Inst3a.TAC.BEGINFUNC, null, null, null));
		return code;
	}

	/**
	 * Generate code for a if then statement.
	 *
	 * @param cond
	 *            the condition
	 * @param if_statement
	 *            the if statement
	 * @return the code 3 a
	 */
	public static Code3a genIfThen(ExpAttribute cond, Code3a if_statement) {
		Code3a code = cond.code;
		LabelSymbol ENDIF = SymbDistrib.newLabel();
		code.append(new Inst3a(Inst3a.TAC.IFZ, cond.place, ENDIF, null));
		code.append(if_statement);
		code.append(new Inst3a(Inst3a.TAC.LABEL, ENDIF, null, null));
		return code;
	}

	/**
	 * Generate code for a if then else statement.
	 *
	 * @param cond
	 *            the condition
	 * @param if_statement
	 *            the if statement
	 * @param else_statement
	 *            the else statement
	 * @return the code 3 a
	 */
	public static Code3a genIfThenElse(ExpAttribute cond, Code3a if_statement, Code3a else_statement) {
		Code3a code = cond.code;
		LabelSymbol ELSE = SymbDistrib.newLabel();
		LabelSymbol ENDIF = SymbDistrib.newLabel();
		code.append(new Inst3a(Inst3a.TAC.IFZ, cond.place, ELSE, null));
		code.append(if_statement);
		code.append(new Inst3a(Inst3a.TAC.GOTO, ENDIF, null, null));
		code.append(new Inst3a(Inst3a.TAC.LABEL, ELSE, null, null));
		code.append(else_statement);
		code.append(new Inst3a(Inst3a.TAC.LABEL, ENDIF, null, null));
		return code;
	}

	/**
	 * Generate code for a print expression call.
	 *
	 * @param exp
	 *            the expression
	 * @return the code 3 a
	 */
	public static Code3a genPrintExpression(ExpAttribute exp) {
		Code3a code = exp.code;
		code.append(new Inst3a(Inst3a.TAC.ARG, exp.place, null, null));
		code.append(new Inst3a(Inst3a.TAC.CALL, null, SymbDistrib.builtinPrintN, null));
		return code;
	}

	/**
	 * Generate code for a print text call.
	 *
	 * @param text
	 *            the text
	 * @return the code 3 a
	 */
	public static Code3a genPrintText(Data3a text) {
		Code3a code = new Code3a();
		code.appendData(text);
		code.append(new Inst3a(Inst3a.TAC.ARG, text.getLabel(), null, null));
		code.append(new Inst3a(Inst3a.TAC.CALL, null, SymbDistrib.builtinPrintS, null));
		return code;
	}

	/**
	 * Generate code for a read call.
	 *
	 * @param var
	 *            the variable
	 * @return the code 3 a
	 */
	public static Code3a genRead(VarSymbol var) {
		Code3a code = new Code3a();
		code.append(new Inst3a(Inst3a.TAC.CALL, var, SymbDistrib.builtinRead, null));
		return code;
	}

	/**
	 * Generate code for a return call.
	 *
	 * @param ret
	 *            the return
	 * @return the code 3 a
	 */
	public static Code3a genReturn(ExpAttribute ret) {
		Code3a code = ret.code;
		code.append(new Inst3a(Inst3a.TAC.RETURN, ret.place, null, null));
		return code;
	}

	/**
	 * Generates the 3a statement: VAR t.
	 *
	 * @param t
	 *            the t
	 * @return the code 3 a
	 */
	public static Code3a genVar(Operand3a t) {
		return new Code3a(new Inst3a(Inst3a.TAC.VAR, t, null, null));
	}

	/**
	 * Generate code for a while statement.
	 *
	 * @param cond
	 *            the condition
	 * @param statement
	 *            the statement
	 * @return the code 3 a
	 */
	public static Code3a genWhile(ExpAttribute cond, Code3a statement) {
		Code3a code = new Code3a();
		LabelSymbol WHILE = SymbDistrib.newLabel();
		LabelSymbol ENDWHILE = SymbDistrib.newLabel();
		code.append(new Inst3a(Inst3a.TAC.LABEL, WHILE, null, null));
		code.append(cond.code);
		code.append(new Inst3a(Inst3a.TAC.IFZ, cond.place, ENDWHILE, null));
		code.append(statement);
		code.append(new Inst3a(Inst3a.TAC.GOTO, WHILE, null, null));
		code.append(new Inst3a(Inst3a.TAC.LABEL, ENDWHILE, null, null));
		return code;
	}

	/**
	 * Instantiates a new code 3 a generator.
	 */
	// Constructor not needed
	private Code3aGenerator() {}
} // Code3aGenerator ***
