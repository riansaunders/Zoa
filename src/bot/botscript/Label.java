package bot.botscript;


/**
 *
 * @author Bot
 *
 */
public class Label extends CodeBlock {

	int numArgs;

	public Label(String name, int numArgs, CodeBlock parent) {
		super(name, parent);
		this.numArgs = numArgs;
	}

	public int argCount() {
		return numArgs;
	}

	public Object getValue() {
		return null;
	}
}