package zoa.build.node.visitor;

import zoa.Script;
import zoa.build.CompilationError;
import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.SyntacticParser;
import zoa.build.node.RightBraceNode;

/**
 *
 * @author Bot
 *
 */
public class RightBraceVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(RightBraceNode.class)) {
			return false;
		}
		SyntacticParser syntacticParser = node.parser();
		if (syntacticParser.code() instanceof Script) {
			throw new CompilationError("} unexpected", node.pos());
		}
		syntacticParser.code(syntacticParser.code().parent());
		return true;
	}
}
