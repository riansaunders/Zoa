package zoa.build.node.visitor;

import zoa.CodeBlock;
import zoa.build.CompilationError;
import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;
import zoa.build.node.IfNode;
import zoa.build.node.LeftBraceNode;
import zoa.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class IfNodeVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) throws CompilationError {
		if (!node.is(IfNode.class)) {
			return false;
		}
		IfNode iNode = (IfNode) node;
		Node condition = iNode.getValue();
		SyntacticParser parser = node.parser();
		CodeBlock code = parser.code();

		if (!parser.peekNode().is(LeftBraceNode.class)) {
			throw new CompilationError("Expected " + TokenType.LBRACE +", "+parser.peekNode() + ". " + iNode.pos(), node.pos());
		}
		code.instruct(new Instruction(Instruction.IF));
		parser.code(new CodeBlock("#if", code));
		System.out.println("New code: " + parser.code() + "," + condition);
		return true;
	}
}
