package zoa.build.node.visitor;

import zoa.CodeBlock;
import zoa.build.CompilationError;
import zoa.build.Node;
import zoa.build.NodeVisitor;

import zoa.build.node.AssignNode;
import zoa.build.node.CommaParenthesisNode;

import zoa.build.node.LeftBraceNode;
import zoa.lang.runtime.Instruction;

/**
 * 
 * @author rian
 *
 */
public class LeftBraceVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) throws CompilationError {
		if (!node.is(LeftBraceNode.class)) {
			return false;
		}
		Node paren = node.prev();
		if (paren.is(CommaParenthesisNode.class) &&  paren.prev().is(AssignNode.class)) {
			node.parser().code().instruct(new Instruction(Instruction.PUSH_BLOCK));
			node.parser().code(new CodeBlock(node.parser().code()));
		} else
			throw new CompilationError("Unexpected " + node.getValue(), node.pos());
		return true;
	}
}
