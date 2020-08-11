package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class RightBraceNode extends Node {

	public RightBraceNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
		pos(syntacticParser.currentToken().pos());
	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.RBRACE;
	}

	@Override
	public Object getValue() {
		return "}";
	}
}