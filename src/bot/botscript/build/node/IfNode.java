package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class IfNode extends Node {

	private Node condition;

	public IfNode(SyntacticParser parser) {
		super(parser);

	}

	public static boolean accept(SyntacticParser parser) {
		parser = parser.clone();
		if (parser.currentToken().type() != TokenType.IF)
			return false;
		if (parser.nextToken().type() != TokenType.LPAREN)
			return false;
		return ConditionBlock.accept(parser);
	}

	/* Condition */
	@Override
	public Node getValue() {
		return condition;
	}
}