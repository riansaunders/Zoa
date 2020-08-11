package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class GetVariableNode extends Node {

	public final String variable;
	public final boolean global;

	public GetVariableNode(SyntacticParser syntacticParser, String variable, boolean global) {
		super(syntacticParser);
		this.variable = variable;
		this.global = global;
	}

	@Override
	public String getValue() {
		return variable;
	}
}