package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

public class LeftBraceNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.LBRACE;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		return new LeftBraceNode(parser);
	}
}