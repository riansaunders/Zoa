package bot.botscript;

/**
 *
 * @author Bot
 *
 */
public class Variable {

	private String name;
	private Object value;

	public Variable(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String name() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}