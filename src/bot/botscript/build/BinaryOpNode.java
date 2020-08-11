package bot.botscript.build;

public abstract class BinaryOpNode<T> extends Node {

	public BinaryOpNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
	}

	@Override
	public abstract BinaryOp<T> getValue();
}