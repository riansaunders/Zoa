package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class AssignNode extends Node {

	private String variable;

	public AssignNode(SyntacticParser syntacticParser, String variable) {
		super(syntacticParser);
		this.variable = variable;

	}
	
	@Override
	public String getValue() {
		return variable;
	}
}
