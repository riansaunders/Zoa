package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class ExternalFlagNode extends Node {

	public ExternalFlagNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
		pos(syntacticParser.currentToken().pos());
	}

	public static boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.GRAVE_ACCENT;
	}

	@Override
	public Boolean getValue() {
		return true;
	}
}