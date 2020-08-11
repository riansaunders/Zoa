package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class NumberNode extends Node {

	public final int value;

	public NumberNode(SyntacticParser syntacticParser, int value) {
		super(syntacticParser);
		this.value = value;
		pos(syntacticParser.currentToken().pos());
	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.NUMBER
				|| syntacticParser.currentToken().type() == TokenType.DECIMAL;
	}

	@Override
	public Integer getValue() {
		return value;
	}
}