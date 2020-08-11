package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
