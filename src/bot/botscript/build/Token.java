package bot.botscript.build;

/**
 *
 * @author Bot
 *
 */
public class Token {

	public static final Token EMPTY = new Token().setType(TokenType.INVALID);
	private TokenType type = TokenType.INVALID;
	private String word = "";
	private SourcePosition pos;

	Token setType(TokenType type) {
		this.type = type;
		return this;
	}

	Token setWord(String val) {
		word = val;
		return this;
	}

	void src(SourcePosition pos) {
		this.pos = pos;
	}

	public SourcePosition pos() {
		return pos;
	}

	public TokenType type() {
		return type;
	}

	public String val() {
		return word;
	}

	@Override
	public String toString() {
		return "[token '" + word + "' on " + pos + "]";
	}

	public static boolean isEmpty(Token other) {
		return other == Token.EMPTY || other == null;
	}
}