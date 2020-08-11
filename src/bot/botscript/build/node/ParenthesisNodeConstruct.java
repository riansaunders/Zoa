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
public class ParenthesisNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.LPAREN && ParenthesisNode.paramCount(parser) != -1;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		System.out.println(parser.currentToken() + " | " + parser.peekToken(0));
		int paramCount = ParenthesisNode.paramCount(parser);
		System.out.println("PAram count: " + paramCount);
		ParenthesisNode node = new ParenthesisNode(parser, parser.fillTokens(paramCount));
		return node;
	}
}