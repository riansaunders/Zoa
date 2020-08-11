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