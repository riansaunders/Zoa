package zoa.build.node.visitor;

import java.util.LinkedList;

import zoa.build.ArithmeticNode;
import zoa.build.Node;
import zoa.build.NodeVisitor;
import zoa.build.SyntacticParser;

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
