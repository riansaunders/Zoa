package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class SemiColon extends Node {

	public SemiColon(SyntacticParser syntacticParser) {
		super(syntacticParser);
		pos(syntacticParser.currentToken().pos());

	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.SEMICOLON;
	}

	@Override
	public Object getValue() {
		return null;
	}
}