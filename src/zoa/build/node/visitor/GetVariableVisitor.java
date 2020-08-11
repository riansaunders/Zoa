package zoa.build.node.visitor;

import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.node.GetVariableNode;
import zoa.lang.runtime.Instruction;

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
