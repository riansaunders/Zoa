package zoa.build.node;

import zoa.build.Node;
import zoa.build.NodeConstruct;
import zoa.build.SyntacticParser;
import zoa.build.Token;
import zoa.build.TokenType;

/**
 * 
 * @author rian
 *
 */
public class AssignNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.prevNode() != null && (parser.prevNode().is(AssignNode.class)
				&& parser.currentToken().type() == TokenType.COMMA && parser.peekToken().type() == TokenType.WORD
				&& parser.peekToken(2).type() == TokenType.EQUAL
				|| parser.prevNode().is(GetVariableNode.class) && parser.currentToken().type() == TokenType.EQUAL_SIGN);
	}

	@Override
	public Node construct(SyntacticParser parser) {
		// , word =
		if (parser.currentToken().type() == TokenType.COMMA)
			parser.nextToken(2);
		AssignNode assign = new AssignNode(parser, parser.prevToken().val());
		/* Equals sign */
		parser.nextToken();
		assign.child(parser.construct());
		for (;;) {
			Token token = parser.peekToken(1);
			if (token.type() != TokenType.EQUAL_SIGN)
				break;
			parser.nextToken(2);
			assign.child(parser.construct());
		}
		return assign;
	}
}
