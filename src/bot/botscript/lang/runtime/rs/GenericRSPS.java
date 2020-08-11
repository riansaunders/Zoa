package bot.botscript.lang.runtime.rs;

import bot.botscript.Parameter;
import bot.botscript.lang.runtime.EventDefinition;
import bot.botscript.lang.runtime.ScriptRuntime;

/**
 *
 * @author Bot
 *
 */
public class GenericRSPS extends ScriptRuntime {

	public GenericRSPS() {
		define(new IFButtonOne(), null);
		define(new ObjClickWear(), null);
		define(new ObjClickRemove(), null);
		define(new CooldownFin(), null);
	}

	private static class IFButtonOne extends EventDefinition {

		public IFButtonOne() {
			super("if_button1");
			addParam(new Parameter("interface_id", Integer.class, -1));
			addParam(new Parameter("button_id", Integer.class, -1));
		}
	}

	private static class ObjClickWear extends EventDefinition {

		public ObjClickWear() {
			super("obj_click_wear");
			addParam(new Parameter("item_id", Integer.class, null));
		}
	}

	private static class ObjClickRemove extends EventDefinition {

		public ObjClickRemove() {
			super("obj_click_remove");
			addParam(new Parameter("item_id", Integer.class, null));
		}
	}

	private static class CooldownFin extends EventDefinition {

		public CooldownFin() {
			super("cooldown_fin");
			addParam(new Parameter("cooldown_name", String.class, null));
		}
	}
}