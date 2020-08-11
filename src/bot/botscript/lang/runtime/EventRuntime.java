package bot.botscript.lang.runtime;

/**
 *
 * @author Bot
 *
 */
public abstract class EventRuntime {

	public abstract void run(EventDefinition def, ScriptRuntime runtime);

}