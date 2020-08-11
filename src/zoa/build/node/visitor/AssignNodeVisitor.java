package zoa.build.node.visitor;

import java.util.LinkedList;
import java.util.List;

import zoa.CodeBlock;
import zoa.Variable;
import zoa.build.CompilationError;
import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.SyntacticParser;
import zoa.build.Token;
import zoa.build.TokenType;
import zoa.build.node.AssignNode;
import zoa.build.node.InvokeNode;
import zoa.build.node.LeftBraceNode;
import zoa.build.node.ParenthesisNode;
import zoa.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class AssignNodeVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(AssignNode.class)) {
			return false;
		}
		AssignNode assign = (AssignNode) node;
		for (Node child : assign.children()) {
			SyntacticParser parser = node.parser();
			if (child.is(ParenthesisNode.class)) {
				ParenthesisNode paren = (ParenthesisNode) child;
				Node next = parser.nextNode();
				if (assign.children().size() > 1) {
					throw new CompilationError("Cannot assign a method", child.pos());
				}
				if (!next.is(LeftBraceNode.class)) {
					throw new CompilationError("{ expected got " + next, node.pos());
				}
				List<Token> illegalParams = new LinkedList<Token>();
				paren.tokens.forEach((l) -> {
					if (l.type() != TokenType.WORD)
						illegalParams.add(l);
				});
				if (!illegalParams.isEmpty()) {
					throw new CompilationError(
							"Illegal parameters " + java.util.Arrays.toString(illegalParams.toArray()), next.pos());
				}
				parser.code(new CodeBlock(parser.code()));
				parser.code().instruct(new Instruction(Instruction.PUSH_BLOCK));
			} else if (child.is(InvokeNode.class)) {
				System.out.println("Invoke");
			} else {
				System.out.println("AN Child: " + child);
				Variable variable = parser.code().getVariable(assign.getValue());
				if (variable == null) {
					parser.code().pushVariable(new Variable(assign.getValue(), null));
				}
			}
			parser.code().instruct(new Instruction(Instruction.ASSIGN));
		}
		return true;
	}
}
