package bot.botscript.build;

/**
 *
 * @author Bot
 *
 */
public class SourcePosition {

	public final int line;
	public final int column;
	public final int columnEnd;

	public SourcePosition(int line, int column, int columnEnd) {
		this.line = line;
		this.column = column;
		this.columnEnd = columnEnd;
	}

	@Override
	public String toString() {
		return "line " + line + " - " + column + ":" + columnEnd;
	}
}