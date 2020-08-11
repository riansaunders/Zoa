package zoa.build.node.visitor;

import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.node.ExitNode;
import zoa.lang.runtime.Instruction;

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
