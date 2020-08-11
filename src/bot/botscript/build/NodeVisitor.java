package bot.botscript.build;

import bot.botscript.build.node.visitor.ArithmeticVisitor;
import bot.botscript.build.node.visitor.AssignNodeVisitor;
import bot.botscript.build.node.visitor.ExitNodeVisitor;
import bot.botscript.build.node.visitor.IfNodeVisitor;
import bot.botscript.build.node.visitor.InvokeNodeVisitor;
import bot.botscript.build.node.visitor.LabelVisitor;
import bot.botscript.build.node.visitor.LeftBraceVisitor;
import bot.botscript.build.node.visitor.RightBraceVisitor;
import bot.botscript.build.node.visitor.SemiColonVisitor;

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