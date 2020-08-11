package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.Token;
import bot.botscript.build.TokenType;

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