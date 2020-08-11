package bot.botscript;

/**
 *
 * @author Bot
 *
 */
public class Parameter {

	public final String name;
	public final Class<?> inferredType;
	public final Object defaultValue;

	public Parameter(String name, Class<?> type, Object defaultValue) {
		this.name = name;
		this.inferredType = type;
		this.defaultValue = defaultValue;
	}
}