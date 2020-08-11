package zoa.build.node;

import zoa.build.Node;
import zoa.build.NodeConstruct;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

/**
 * 
 * @author rian
 *
 */
public class NumberNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.NUMBER || parser.currentToken().type() == TokenType.DECIMAL;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		String num = parser.currentToken().val();
		boolean negative = parser.prevToken().type() == TokenType.MINUS;
		return new NumberNode(parser, Integer.parseInt(negative ? "-" + num : num));
	}
}
