package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;

public class ReturnNode extends Node {

//	private String value;

	public ReturnNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
	//	SyntacticParser copy = syntacticParser.clone();
	//	copy.nextNode();
	}

	public static boolean accept(SyntacticParser syntacticParser) {
		return syntacticParser.currentToken().type() == TokenType.RETURN;
	}

	@Override
	public String getValue() {
		return null;
	}
}