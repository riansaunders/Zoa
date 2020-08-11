package zoa.build.node;

import zoa.build.Node;
import zoa.build.NodeConstruct;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

public class StringNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.STRING_LITERAL;

	}

	@Override
	public Node construct(SyntacticParser parser) {
		return new StringNode(parser, parser.currentToken().val());
	}
}
