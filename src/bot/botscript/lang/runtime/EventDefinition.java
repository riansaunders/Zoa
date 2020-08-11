package bot.botscript.lang.runtime;

import bot.botscript.Functional;

/**
 *
 * @author Bot
 *
 */
public class EventDefinition extends Functional {

	public final String name;

	public EventDefinition(String name) {
		this.name = name;
	}
}