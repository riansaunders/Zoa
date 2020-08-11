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
public class RightBraceNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.RBRACE;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		return new RightBraceNode(parser);
	}
}
