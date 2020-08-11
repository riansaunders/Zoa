package bot.botscript.build;

/**
 *
 * @author Bot
 *
 */
public enum TokenType {
	LESS_THAN("<"), LESS_THAN_OR("<"), EQUAL("=="), EQUAL_SIGN("="), PLUS("+"), MINUS("-"), ASTERISK("*"), COMPARATIVE_AND(
			"&&"), BITWISE_AND("&="), GRAVE_ACCENT("`"), TILDE("~"), LBRACK("["), RBRACK("]"), END("end"), DECIMAL(
			"Decimal"), NUMBER("Number"), WORD("Identifier"), ADD("+="), SUBTRACT("-="), DIVIDE("/="), DIVISION("/"), COMMA(
			","), RETURN("return"), STRING_LITERAL("\" String \""), AT("@ or jump"), LPAREN("("), RPAREN(")"), SEMICOLON(
			";"), RBRACE("}"), LBRACE("{"), DEF_VAR("var <field_name>"), COLON(":"), INVALID("<whitespace>"), GREATER_THAN_OR(
			">="), GREATER_THAN(">"), IF("if <condition>"), LINK("link <event> <lbl>"), LABEL_DEC("lbl <label> [<args>]"),
	EXIT("exit"), SWITCH("switch");
	String word;

	TokenType(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "'" + word + "'";
	}
}