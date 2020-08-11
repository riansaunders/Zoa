package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
