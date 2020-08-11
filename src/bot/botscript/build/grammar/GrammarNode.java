package bot.botscript.build.grammar;

import java.util.ArrayList;

import bot.botscript.build.Token;

/**
 * 
 * @author rian
 *
 */
public class GrammarNode {

	private GrammarNode parent;
	private GrammarNode previous;
	private GrammarNode next;
	private ArrayList<Token> tokens;

	public GrammarNode() {
		this(null, null);
	}

	public GrammarNode(GrammarNode parent, Token token) {
		this.parent = parent;
		this.tokens = new ArrayList<Token>();
		this.tokens.add(token);
	}

	public GrammarNode next() {
		return next;
	}

	public GrammarNode previous() {
		return previous;
	}

	public GrammarNode parent() {
		return parent;
	}

	public Token singleToken() {
		return tokens.get(0);
	}

	public GrammarNode next(GrammarNode node) {
		this.next = node;
		return this;
	}

	public GrammarNode previous(GrammarNode node) {
		this.previous = node;
		return this;
	}
}