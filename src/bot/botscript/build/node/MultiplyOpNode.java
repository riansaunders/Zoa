package bot.botscript.build.node;

import bot.botscript.build.ArithmeticNode;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class MultiplyOpNode extends ArithmeticNode {

	public MultiplyOpNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
	}

	public static boolean accept(SyntacticParser parser) {
		return ArithmeticNode.accept(TokenType.ASTERISK, parser);
	}
}