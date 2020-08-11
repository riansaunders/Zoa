package bot.botscript.build.node;

import java.util.List;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

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