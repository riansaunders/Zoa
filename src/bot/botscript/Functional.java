package bot.botscript;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bot
 *
 */
public class Functional {

	private final List<Parameter> params = new LinkedList<Parameter>();

	protected void addParam(Parameter p) {
		params.add(p);
	}

	public final Parameter getParam(String name) {
		for (Parameter p : params) {
			if (p.name.equals(name)) {
				return p;
			}
		}
		return null;
	}

	public final Collection<Parameter> params() {
		return Collections.unmodifiableCollection(params);
	}
}