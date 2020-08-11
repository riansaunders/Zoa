package bot.botscript.build;

import java.util.LinkedList;
import java.util.List;

import bot.botscript.build.node.NumberNode;

/**
 *
 * @author Bot
 *
 */
public class ArithmeticNode extends Node {

	private List<Arithmetic> values = new LinkedList<Arithmetic>();

	enum Operation {
		MULTIPLY, DIVIDE, ADD, SUBTRACT, ;

		public static Operation valueOf(TokenType type) {
			switch (type) {
			case PLUS:
				return ADD;
			case MINUS:
				return SUBTRACT;
			case ASTERISK:
				return MULTIPLY;
			case DIVIDE:
				return DIVIDE;
			default:
				return null;
			}
		}
	}

	public static class Arithmetic extends BinaryOp<Node> {
		public final Operation operation;

		public Arithmetic(Node left, Node right, Operation op) {
			super(left, right);
			operation = op;
		}
	}

	public ArithmeticNode(SyntacticParser syntacticParser) {
		super(syntacticParser);
//		/* Work out our current (obvious) arithmetic */
//		TokenType ct = syntacticParser.currentToken().type();
//		NumberNode left = (NumberNode) syntacticParser.prevNode();
//		/* Make it comply with what number node accepts */
//		syntacticParser.nextToken();
//		NumberNode right = new NumberNode(syntacticParser);
//		values.add(new Arithmetic(left, right, Operation.valueOf(ct)));
//		/* The next bits... */
//
//		System.out.println(left.getValue() + " " + ct.word + " " + right.getValue());
//		// System.out.println("Full expression " + expression);
	}

	@Override
	public final List<Arithmetic> getValue() {
		return values;
	}

	private static boolean isOp(TokenType ct) {
		return Operation.valueOf(ct) != null;
	}

	protected static boolean accept(TokenType type, SyntacticParser parser) {
		/* Parse 1 arithmetic expression, then parse them all in the future */
		return ArithmeticNode.isOp(type) && parser.prevNode().is(NumberNode.class)
				&& (parser.peekToken().type() == TokenType.DECIMAL || parser.peekToken().type() == TokenType.NUMBER);
	}
	protected static boolean accept(SyntacticParser parser) {
		/* Parse 1 arithmetic expression, then parse them all in the future */
		return ArithmeticNode.isOp(parser.currentToken().type()) && parser.prevNode().is(NumberNode.class)
				&& (parser.peekToken().type() == TokenType.DECIMAL || parser.peekToken().type() == TokenType.NUMBER);
	}
}