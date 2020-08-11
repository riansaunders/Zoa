package zoa.build.node;

import zoa.build.ArithmeticNode;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
