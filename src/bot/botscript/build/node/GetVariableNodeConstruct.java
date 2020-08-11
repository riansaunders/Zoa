package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 * 
 * @author rian
 *
 */
public class GetVariableNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		TokenType nextType = parser.peekToken() != null ? parser.peekToken().type() : null;
		return parser.currentToken().type() == TokenType.WORD
				&& (nextType == TokenType.COMMA || nextType == TokenType.SEMICOLON || nextType == TokenType.RPAREN
						|| nextType == TokenType.INVALID || nextType == TokenType.EQUAL_SIGN);
	}

	@Override
	public Node construct(SyntacticParser parser) {
		return new GetVariableNode(parser, parser.currentToken().val(), parser.prevNode().is(ExternalFlagNode.class));
	}
}