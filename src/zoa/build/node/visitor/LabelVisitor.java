package zoa.build.node.visitor;

import java.util.Arrays;

import zoa.Label;
import zoa.build.CompilationError;
import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.SyntacticParser;
import zoa.build.TokenType;
import zoa.build.node.CommaParenthesisNode;
import zoa.build.node.LabelNode;
import zoa.build.node.LeftBraceNode;

/**
 *
 * @author Bot
 *
 */
public class LabelVisitor implements NodeVisitor {

	@Override
	public boolean visit(Node node) {
		if (!node.is(LabelNode.class)) {
			return false;
		}
		SyntacticParser syntacticParser = node.parser();
		LabelNode lbl = (LabelNode) node;
		String name = lbl.getValue();
		CommaParenthesisNode params = null;
		if (syntacticParser.peekNode().is(CommaParenthesisNode.class)) {
			params = (CommaParenthesisNode) syntacticParser.nextNode();
			System.out.println("In parenthesis -> " + Arrays.toString(params.getValue().toArray()));
		}
		if (!syntacticParser.peekNode().is(LeftBraceNode.class)) {
			throw new CompilationError(TokenType.LBRACE + " expected", syntacticParser.peekNode().pos());
		}
		Label label = syntacticParser.code().getLabel(name, params != null ? params.getValue().size() : 0);
		if (label == null) {
			syntacticParser.code().pushLabel(
					label = new Label(name, params != null ? params.getValue().size() : 0, syntacticParser.code()));
		}
		syntacticParser.code(label);
		return true;
	}
}
