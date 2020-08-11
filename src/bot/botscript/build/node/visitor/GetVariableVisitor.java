package bot.botscript.build.node.visitor;

import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.node.GetVariableNode;
import bot.botscript.lang.runtime.Instruction;

/**
 *
 * @author Rian
 *
 */
public class GetVariableVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(GetVariableNode.class)) {
			return false;
		}
		GetVariableNode var = (GetVariableNode) node;
		node.parser().code().instruct(
				new Instruction(var.global ? Instruction.GET_GLOBAL_VAR : Instruction.GET_VAR, var.getValue()));
		return true;
	}
}