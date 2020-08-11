package bot.botscript.build.node.visitor;

import java.util.LinkedList;

import bot.botscript.build.ArithmeticNode;
import bot.botscript.build.Node;
import bot.botscript.build.NodeVisitor;
import bot.botscript.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class ArithmeticVisitor implements NodeVisitor {

	LinkedList<ArithmeticNode> arithmetic = new LinkedList<ArithmeticNode>();
	
	@Override
	public boolean visit(Node node) {
		if (!node.is(ArithmeticNode.class)) {
			return false;
		}
		SyntacticParser parser = node.parser();
		arithmetic.clear();
		arithmetic.add((ArithmeticNode) node);
		while (parser.peekNode().is( ArithmeticNode.class)) {
			arithmetic.push((ArithmeticNode) parser.nextNode());
		}
		return true;
	}
}