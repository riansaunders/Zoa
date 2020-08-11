package zoa.build;

import zoa.build.node.visitor.ArithmeticVisitor;
import zoa.build.node.visitor.AssignNodeVisitor;
import zoa.build.node.visitor.ExitNodeVisitor;
import zoa.build.node.visitor.IfNodeVisitor;
import zoa.build.node.visitor.InvokeNodeVisitor;
import zoa.build.node.visitor.LabelVisitor;
import zoa.build.node.visitor.LeftBraceVisitor;
import zoa.build.node.visitor.RightBraceVisitor;
import zoa.build.node.visitor.SemiColonVisitor;

/**
 *
 * @author Bot
 *
 */
public interface NodeVisitor {

	public static final NodeVisitor[] impl = new NodeVisitor[] { new AssignNodeVisitor(), new LabelVisitor(),
			new SemiColonVisitor(), new LeftBraceVisitor(), new RightBraceVisitor(), new ArithmeticVisitor(), new ExitNodeVisitor(),
			new InvokeNodeVisitor(), new IfNodeVisitor() };

	public boolean visit(Node node) throws CompilationError;

	public static NodeVisitor get(Class<? extends NodeVisitor> tourist) {
		for (NodeVisitor v : NodeVisitor.impl) {
			if (v.getClass() == tourist) {
				return v;
			}
		}
		return null;
	}
}
