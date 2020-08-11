package bot.botscript.build.node.visitor;

import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.node.ExitNode;
import bot.botscript.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class ExitNodeVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(ExitNode.class)) {
			return false;
		}
		node.parser().code().instruct(new Instruction(Instruction.EXIT));
		return true;
	}
}