package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

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