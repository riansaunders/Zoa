package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;

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
