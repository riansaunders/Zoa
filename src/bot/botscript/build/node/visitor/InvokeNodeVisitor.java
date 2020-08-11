package bot.botscript.build.node.visitor;

import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.node.InvokeNode;
import bot.botscript.build.node.InvokeNode.MethodCall;
import bot.botscript.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class InvokeNodeVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(InvokeNode.class)) {
			return false;
		}
		MethodCall target = ((InvokeNode) node).getValue();
		SyntacticParser parser = node.parser();
		boolean system = false;
		if (parser.code().getLabel(target.name, target.numArgs) == null) {
			if (parser.runtime().getFunction(target.name, target.numArgs) == null) {
				// throw new CompilationError("Function " + target.name + "(" +
				// target.numArgs + ") not found", node.pos());
			} else {
				system = true;
			}
		}
		parser.code().instruct(
				new Instruction(system ? Instruction.INVOKE_SYSTEM : Instruction.INVOKE, target.name + ":"
						+ target.numArgs));
		return true;
	}
}