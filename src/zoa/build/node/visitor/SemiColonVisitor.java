package zoa.build.node.visitor;

import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.node.SemiColon;
import zoa.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class SemiColonVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(SemiColon.class)) {
			return false;
		}
		node.parser().code().instruct(new Instruction(Instruction.END));
		return true;
	}
}
