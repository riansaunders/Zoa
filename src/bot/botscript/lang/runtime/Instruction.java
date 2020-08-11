package bot.botscript.lang.runtime;

/**
 *
 * @author Bot
 *
 */
public class Instruction {

	public static final int END = 1;
	public static final int PUSH_VAR = 2;
	public static final int ADD = 3;
	public static final int BEGIN_FOR_EACH = 4;
	public static final int GET_VAR = 5;
	public static final int ASSIGN = 6;
	public static final int INVOKE_SYSTEM = 11;
	public static final int INVOKE = 12;
	public static final int GET_CONSTANT = 7;
	public static final int GET_SCRIPT_VAR = 10;
	public static final int GET_GLOBAL_VAR = 15;
	public static final int CONCAT = 16;
	public static final int RETURN = 17;
	public static final int SUBTRACT = 18;
	public static final int BEGIN_LABEL = 19;
	public static final int LINK_EVENT = 20;
	public static final int END_ASSIGN = 21;
	public static final int MULTIPLY = 22;
	public static final int DIVIDE = 23;
	public static final int END_INVOKE = 24;
	public static final int EXIT = 25;
	public static final int IF = 26;
	public static final int PUSH_BLOCK = 27;

	private int op;
	private String parameter;

	public Instruction(int op) {
		this(op, "");
	}

	public Instruction(int op, String args) {
		this.op = op;
		parameter = args;
	}

	public int op() {
		return op;
	}

	public String sub() {
		return parameter;
	}

	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		switch (op) {
		case END:
			bldr.append("end");
			break;
		case PUSH_VAR:
			bldr.append("push_var");
			break;
		case GET_VAR:
			bldr.append("get_var");
			break;
		case GET_CONSTANT:
			bldr.append("get_constant");
			break;
		case ADD:
			bldr.append("add");
			break;
		case BEGIN_FOR_EACH:
			bldr.append("begin_for_each");
			break;
		case ASSIGN:
			bldr.append("assign");
			break;
		case SUBTRACT:
			bldr.append("subtract");
			break;
		case INVOKE:
			bldr.append("invoke");
			break;
		case RETURN:
			bldr.append("return");
			break;
		case INVOKE_SYSTEM:
			bldr.append("invoke_system");
			break;
		case GET_SCRIPT_VAR:
			bldr.append("get_script_var");
			break;
		}
		if (!parameter.isEmpty()) {
			bldr.append(" ");
			bldr.append("// ");
			bldr.append(parameter);
		}
		bldr.append(" |");
		bldr.append(" op " + op);
		// bldr.append("\n");
		return bldr.toString();
	}
}