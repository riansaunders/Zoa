package bot.botscript.build.node.visitor;

import java.util.Arrays;

import bot.botscript.Label;
import bot.botscript.build.CompilationError;
import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;
import bot.botscript.build.node.CommaParenthesisNode;
import bot.botscript.build.node.LabelNode;
import bot.botscript.build.node.LeftBraceNode;

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