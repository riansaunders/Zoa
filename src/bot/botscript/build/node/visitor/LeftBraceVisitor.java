package bot.botscript.build.node.visitor;

import bot.botscript.CodeBlock;
import bot.botscript.build.CompilationError;
import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;

import bot.botscript.build.node.AssignNode;
import bot.botscript.build.node.CommaParenthesisNode;

import bot.botscript.build.node.LeftBraceNode;
import bot.botscript.lang.runtime.Instruction;

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