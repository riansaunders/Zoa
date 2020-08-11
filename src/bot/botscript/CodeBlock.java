package bot.botscript;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import bot.botscript.ListUtil.EntryAcceptor;
import bot.botscript.lang.RSArray;
import bot.botscript.lang.runtime.Instruction;

/**
 *
 * @author Bot
 *
 */
public class CodeBlock {
	// Our instruction stack
	protected final Stack<Instruction> instructions = new Stack<Instruction>();
	// Our variable stack
	private final LinkedList<Variable> variables = new LinkedList<Variable>();
	private LinkedList<Label> labels = new LinkedList<Label>();
	// Me
	private CodeBlock parent;
	public final String name;
	

	public static class ArrayAcceptor implements EntryAcceptor<Object> {
		@Override
		public boolean accept(Object entry) {
			if (!(entry instanceof RSArray)) {
				return false;
			}
			return ((RSArray) entry).equals(o);
		}

		Object o;

		public EntryAcceptor<Object> other(Object o) {
			this.o = o;
			return this;
		}
	};

	public CodeBlock(String name, CodeBlock parent) {
		this.name = name;
		this.parent = parent;
		if (this instanceof Script) {
			this.parent = this;
		}
	}

	public CodeBlock(CodeBlock parent) {
		this("", parent);
	}

	public void pushLabel(Label f) {
		labels.add(f);
	}

	public CodeBlock parent() {
		return parent;
	}

	public void pushVariable(Variable v) {
		if (varIndex(v.name()) == -1) {
			variables.add(v);
		}
	}

	public void instruct(Instruction instruction) {
		instructions.push(instruction);
	}

	private int varIndex(String name) {
		return variables.indexOf(ListUtil.find(variables, entry -> entry.name().equals(name)));
	}

	private int labelIndex(String name, int argCount) {
		return labels.indexOf(ListUtil.find(labels, entry -> entry.name.equals(name) && entry.argCount() == argCount));
	}

	public Label getLabel(String name, int args) {
		CodeBlock block = this;
		int i = labelIndex(name, args);
		if (i != -1) {
			return labels.get(i);
		} else if (i == -1) {
			while ((block = block.parent).getClass() != Script.class) {
				i = block.labelIndex(name, args);
				if (i != -1) {
					return block.labels.get(i);
				}
			}
		}
		return null;
	}

	public Variable getVariable(String string) {
		CodeBlock block = this;
		int i = varIndex(string);
		if (i == -1) {
			while ((block = block.parent).getClass() != Script.class) {
				i = block.varIndex(string);
				if (i != -1) {
					return block.variables.get(i);
				}
			}
		}
		return null;
	}

	// @Override
	// public String toString() {
	// return getClass().getSimpleName() + "-" + name;
	// }

	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		bldr.append("<" + name + ", parent= " + parent.name + ">");
		bldr.append("\n");
		for (Instruction i : instructions) {
			bldr.append(i);
			bldr.append("\n");
		}
		for (Label f : labels) {
			bldr.append("label");
			bldr.append(" ");
			bldr.append(f.name + ":" + f.numArgs);
			bldr.append(" ");
			bldr.append("{");
			bldr.append("\n");
			bldr.append(f);
			bldr.append("\n");
			bldr.append("}");
			bldr.append("\n");
		}
		bldr.append("</" + name + ">");
		return bldr.toString();
	}

	public List<Label> labels() {
		return labels;
	}
}