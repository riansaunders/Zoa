package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

/**
 *
 * @author Bot
 *
 */
public class InvokeNode extends Node {

	public final MethodCall target;

	public static class MethodCall {
		public final String name;
		public final int numArgs;

		public MethodCall(String name, int numArgs) {
			this.name = name;
			this.numArgs = numArgs;
		}

		@Override
		public String toString() {
			return "[" + this.getClass().getSimpleName() + " name=" + name + ", num_args=" + numArgs + "]";
		}
	}

	public InvokeNode(SyntacticParser syntacticParser, MethodCall target) {
		super(syntacticParser);
		this.target = target;
	}

	@Override
	public MethodCall getValue() {
		return target;
	}
}