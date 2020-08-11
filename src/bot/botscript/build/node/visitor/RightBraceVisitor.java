package bot.botscript.build.node.visitor;

import bot.botscript.Script;
import bot.botscript.build.CompilationError;
import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.node.RightBraceNode;

/**
 *
 * @author Bot
 *
 */
public class RightBraceVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(RightBraceNode.class)) {
			return false;
		}
		SyntacticParser syntacticParser = node.parser();
		if (syntacticParser.code() instanceof Script) {
			throw new CompilationError("} unexpected", node.pos());
		}
		syntacticParser.code(syntacticParser.code().parent());
		return true;
	}
}