package bot.botscript.lang.runtime;

import java.util.HashMap;
import java.util.Map;

import bot.botscript.InvocationException;
import bot.botscript.Script;

/**
 *
 * @author Bot
 *
 */
public abstract class ScriptRuntime {

	private Map<EventDefinition, EventRuntime> events = new HashMap<EventDefinition, EventRuntime>();

	protected void define(EventDefinition definition, EventRuntime runtime) {
		events.put(definition, runtime);
	}

	public Object run(Script script) throws InvocationException {
		return script;
	}

	public EventDefinition getEvent(String name) {
		for (EventDefinition d : events.keySet()) {
			if (d.name.equals(name)) {
				return d;
			}
		}
		return null;
	}

	public Object getFunction(String name, int numArgs) {
		// TODO Auto-generated method stub
		return null;
	}
}