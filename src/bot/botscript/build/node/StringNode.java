package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class StringNode extends Node {

	private String value;

	public StringNode(SyntacticParser syntacticParser, String value) {
		super(syntacticParser);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}