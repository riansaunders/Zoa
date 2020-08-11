package zoa.build.node;

import java.util.List;

import zoa.build.Node;
import zoa.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class CommaParenthesisNode extends Node {

	public CommaParenthesisNode(SyntacticParser syntacticParser) {
		super(syntacticParser);

	}

	@Override
	public List<String> getValue() {
		return null;
	}
}
