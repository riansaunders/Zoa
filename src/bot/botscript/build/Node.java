package bot.botscript.build;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a combination of tokens.
 *
 * @author Bot
 *
 */
public abstract class Node {

	private ArrayList<Node> children;
	private Node previous;
	private SourcePosition pos;
	protected SyntacticParser syntacticParser;

	public Node(SyntacticParser syntacticParser) {
		this.syntacticParser = syntacticParser;
		children = new ArrayList<>();
	}

	public void child(Node child) {
		if (child == null)
			return;
		child.previous = this;
		children.add(child);
	}

	public Node child() {
		return children.size() < 1 ? null : children().get(0);
	}

	public List<Node> children() {
		return children;
	}

	protected void pos(SourcePosition src) {
		pos = src;
	}

	protected void prev(Node previous) {
		this.previous = previous;
	}

	public Node prev() {
		return previous;
	}

	public SourcePosition pos() {
		return pos;
	}

	public SyntacticParser parser() {
		return syntacticParser;
	}

	public abstract Object getValue();

	@Override
	public String toString() {
		return "[ " + this.getClass().getSimpleName() + (getValue() != null ? " value=" + getValue() : "")
				+ (children.size() > 0 ? (getValue() != null ? ", " : " ") + "children="
						+ java.util.Arrays.toString(children().toArray()) : "")
				+ " ]";
	}

	public boolean is(Class<? extends Node> nodeClazz) {
		if (nodeClazz == null) {
			return false;
		}
		return getClass() == nodeClazz || getClass().getSuperclass() == nodeClazz;
	}
}