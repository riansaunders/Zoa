package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class ExitNode extends Node {

	public ExitNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
		syntacticParser.nextToken();
	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.EXIT
				&& syntacticParser.peekToken().type() == TokenType.SEMICOLON;
	}

	@Override
	public Object getValue() {
		return null;
	}
}
