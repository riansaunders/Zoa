package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
