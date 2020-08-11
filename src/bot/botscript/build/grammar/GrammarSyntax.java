package bot.botscript.build.grammar;

import java.util.regex.Pattern;

/**
 * 
 * @author rian
 *
 */
public class GrammarSyntax {

	public static final Pattern COUNT_OF = Pattern.compile("[{](\\d+)([,]\\s\\w*)[}]");
	public static final Pattern SEASAW = Pattern.compile("\\/(\\w+)(\\s\\w+){0,1}\\/(\\d*)");
	public static final Pattern STRING_MODIFIER = Pattern.compile("=(.)+[/]!(i|u)", Pattern.CASE_INSENSITIVE);

	private GrammarSyntax() {
		throw new UnsupportedOperationException();
	}
}