package bot.botscript.build.node.visitor;

import bot.botscript.CodeBlock;
import bot.botscript.build.CompilationError;
import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;
import bot.botscript.build.node.IfNode;
import bot.botscript.build.node.LeftBraceNode;
import bot.botscript.lang.runtime.Instruction;

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