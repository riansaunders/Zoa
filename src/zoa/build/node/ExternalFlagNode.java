package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
