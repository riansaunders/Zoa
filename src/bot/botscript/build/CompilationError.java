package bot.botscript.build;

/**
 *
 * @author Bot
 *
 */
public class CompilationError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompilationError(String message, SourcePosition pos) {
		super("An error has occured on line " + pos.line + " column(s) (" + pos.column + "-" + pos.columnEnd + "):\n"
				+ message);
	}

	public CompilationError(TokenType type, SourcePosition pos, Throwable cause) {
		super("An error has occured on line " + pos.line + " column(s) (" + pos.column + "-" + pos.columnEnd + "): ",
				cause);
	}
}