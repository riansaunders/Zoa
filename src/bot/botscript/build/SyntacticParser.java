package bot.botscript.build;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.botmw.Watch;

import bot.botscript.CodeBlock;
import bot.botscript.Script;
import bot.botscript.build.node.AssignNodeConstruct;
import bot.botscript.build.node.CommaParenthesisNodeConstruct;
import bot.botscript.build.node.GetVariableNodeConstruct;
import bot.botscript.build.node.IfNodeConstruct;
import bot.botscript.build.node.InvokeNodeConstruct;
import bot.botscript.build.node.LeftBraceNodeConstruct;
import bot.botscript.build.node.NumberNodeConstruct;
import bot.botscript.build.node.ParenthesisNodeConstruct;
import bot.botscript.build.node.RightBraceNodeConstruct;
import bot.botscript.build.node.StringNodeConstruct;
import bot.botscript.lang.runtime.ScriptRuntime;

/**
 *
 * @author Bot
 *
 */
public class SyntacticParser implements Cloneable {

	private List<NodeConstruct> constructs;
	private ScriptRuntime runtime;
	private ListIterator<Node> nodes;
	private LinkedList<Node> nodeList;
	private Iterator<Token> tokenItr;
	private List<Token> tokens;
	private Script script;
	private CodeBlock block;
	/* Token controls */
	private Token prevToken = Token.EMPTY;
	private Token nextToken;
	private Token currentToken;
	/* Node controls */
	private Node prevNode;
	private Node nextNode;

	public SyntacticParser(ScriptRuntime runtime, List<Token> tokens) {
		block = script = new Script();
		this.runtime = runtime;
		this.constructs = new LinkedList<NodeConstruct>();
		this.tokens = new LinkedList<Token>(tokens);

		constructs.add(new LeftBraceNodeConstruct());
		constructs.add(new RightBraceNodeConstruct());
		constructs.add(new StringNodeConstruct());
		constructs.add(new AssignNodeConstruct());
		constructs.add(new IfNodeConstruct());
		constructs.add(new NumberNodeConstruct());
		constructs.add(new InvokeNodeConstruct());
		constructs.add(new GetVariableNodeConstruct());
		constructs.add(new CommaParenthesisNodeConstruct());
		constructs.add(new ParenthesisNodeConstruct());
	}

	public Script parse(ScriptRuntime env) {
		Watch compileKeep = new Watch();
		compileKeep.set();

		/* processing */
		NodeTreeBuildResult result = constructNodes(tokenItr = tokens.iterator());
		this.nodeList = result.nodes;
		this.nodes = (ListIterator<Node>) nodeList.iterator();
		System.out.println("Number of nodes: " + nodeList.size());
		while (nodes.hasNext()) {
			try {
				Node currentNode = nextNode();
				for (NodeVisitor visitor : NodeVisitor.impl) {
					try {
						if(visitor.visit(currentNode))
							break;
					} catch (Throwable t) {
						SourcePosition src = currentNode.pos();
						System.out.println(visitor);
						result.errors.add(new CompilationError(null, src, t));
					}
				}
				System.out.println("V");
				prevNode = currentNode;
			} catch (CompilationError error) {
				result.errors.add(error);
			}
		}

		System.out.println("Compilation took " + compileKeep.elapsedMillis() + "ms");
		System.out.println("Completed with " + result.errors.size() + " error(s)");
		if (!result.errors.isEmpty()) {
			for (CompilationError e : result.errors) {
				e.printStackTrace();
				// System.err.println(e.getCause() != null ?
				// e.getCause().getMessage() : e.getMessage());
			}
		}
		return script;
	}

	public Node construct() {
		Node node = null;
		for (NodeConstruct construct : this.constructs) {
			if (construct.accept(this.clone())) {
				try {
					node = construct.construct(this);
					break;
				} catch (Exception e) {
					/* node construct error */
					e.printStackTrace();
				}
			}
		}
		if (node != null) {
			if (node.pos() == null)
				node.pos(this.currentToken().pos());
			if (node.prev() == null)
				node.prev(this.prevNode);
		}
		return node;
	}

	public List<Node> construct(int numberOfTokens) {
		return constructNodes(this.tokenItr, numberOfTokens).nodes;
	}

	private NodeTreeBuildResult constructNodes(Iterator<Token> tokens, int limit) {
		LinkedList<CompilationError> errors = new LinkedList<CompilationError>();
		LinkedList<Node> nodes = new LinkedList<Node>();
		int startPosition = currentToken != null ? this.tokens.indexOf(currentToken) : 0;
		while (tokens.hasNext()) {
			Token token = tokens.next();
			currentToken = token;
			if (this.tokens.indexOf(currentToken) - startPosition == limit)
				break;
			nextToken = peekToken(1);
			try {
				Node node = construct();
				if (node == null) {
					throw new CompilationError("Unused token " + token, token.pos());
				}
				if (node != null) {
					System.out.println("SP " + node);
					nodes.add(node);
					prevNode = node;
				}
			} catch (Exception e) {
				SourcePosition src = token.pos();
				errors.add(new CompilationError(token.type(), src, e));
			}
			nextToken = Token.EMPTY;
			prevToken = token;
		}
		prevNode = null;
		return new NodeTreeBuildResult(errors, nodes);
	}

	private NodeTreeBuildResult constructNodes(Iterator<Token> tokens) {
		return constructNodes(tokens, -1);
	}

	public LinkedList<Token> fillTokens(int count) {
		int num = 0;
		LinkedList<Token> tokens = new LinkedList<Token>();
		while (tokenItr.hasNext()) {
			tokens.add(nextToken());
			if (++num == count)
				break;
		}
		return tokens;
	}

	public Token peekToken(int numAhead) {
		int now = tokens.indexOf(currentToken);
		if (now + numAhead <= tokens.size()) {
			return tokens.get(now + numAhead == tokens.size() ? tokens.size() - 1 : now + numAhead);
		}
		return Token.EMPTY;
	}

	private Token grabToken(int numAhead) {
		Token result = peekToken(numAhead);
		if (result == Token.EMPTY) {
			return result;
		}
		for (int i = 0; i < numAhead; i++) {
			tokenItr.next();
		}
		currentToken = result;
		nextToken = peekToken(1);
		return currentToken;
	}

	public Script script() {
		return script;
	}

	public void code(CodeBlock block) {
		this.block = block;
	}

	public CodeBlock code() {
		return block;
	}

	public Token prevToken() {
		return prevToken;
	}

	public ScriptRuntime runtime() {
		return runtime;
	}

	public Token peekToken() {
		return nextToken;
	}

	public Token currentToken() {
		return currentToken;
	}

	public Node peekNode() {
		return nextNode;
	}

	public Node prevNode() {
		return prevNode;
	}

	public Node findNodeBefore(Node node, Class<? extends Node> nodeClass) {
		boolean found = false;
		int index = nodeList.size() - 1;
		for (;;) {
			try {
				Node prev = nodeList.get(index);
				if (prev == node)
					found = true;
				if (nodeClass == prev.getClass() && found)
					return prev;
				if (--index < 0)
					return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Node nextNode() {
		if (!nodes.hasNext()) {
			return null;
		}
		Node currentNode = nodes.next();
		if (nodes.hasNext()) {
			nextNode = nodes.next();
			nodes.previous();
		} else {
			nextNode = null;
		}
		return currentNode;
	}

	public Token nextToken() {
		return grabToken(1);
	}

	public Token nextToken(int numAhead) {
		return grabToken(numAhead);
	}

	public Token nextToken(int numAhead, TokenType word) {
		Token peek = peekToken(numAhead);
		if (peek == Token.EMPTY) {
			return null;
		}
		if (peek.type() != word) {
			throw new Error("Expected " + word + " , got " + peek.type());
		}
		return grabToken(numAhead);
	}

	@Override
	public SyntacticParser clone() {
		try {
			return (SyntacticParser) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Crucial operation not supported.", e);
		}
	}

	public NodeConstruct getConstruct(Class<? extends NodeConstruct> class1) {
		for (NodeConstruct con : constructs)
			if (con.getClass() == class1)
				return con;
		return null;
	}

}