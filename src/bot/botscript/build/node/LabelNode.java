package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

/**
 *
 * @author Bot
 *
 */
public class LabelNode extends Node {

	private String labelName;

	public LabelNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
		labelName = syntacticParser.nextToken().val();
		pos(syntacticParser.currentToken().pos());
	}

	public static boolean accept(SyntacticParser p) {
		return p.currentToken().type() == TokenType.LABEL_DEC && p.peekToken().type() == TokenType.WORD;
	}

	@Override
	public String getValue() {
		return labelName;
	}
}