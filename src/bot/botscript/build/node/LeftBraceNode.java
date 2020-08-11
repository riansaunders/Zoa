package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class LeftBraceNode extends Node {

	public LeftBraceNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
		pos(syntacticParser.currentToken().pos());

	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.LBRACE;
	}

	@Override
	public Object getValue() {
		return "{";
	}
}