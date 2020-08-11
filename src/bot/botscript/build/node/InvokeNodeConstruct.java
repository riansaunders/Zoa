package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.TokenType;
import bot.botscript.build.node.InvokeNode.MethodCall;

/**
 * 
 * @author rian
 *
 */
public class InvokeNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return parser.currentToken().type() == TokenType.WORD && parser.peekToken().type() == TokenType.LPAREN;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		String methodName = parser.currentToken().val();
		InvokeNode invoke;
		try {
			parser.nextToken();
			int argCount = ParenthesisNode.paramCount(parser);
			
			invoke = new InvokeNode(parser, new MethodCall(methodName, argCount));

//			CommaParenthesisNode parenths = (CommaParenthesisNode) parser
//					.getConstruct(CommaParenthesisNodeConstruct.class);
			parser.construct(ParenthesisNode.getTokensSize(parser)).forEach((l) -> invoke.child(l));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e);
		}
		return invoke;
	}
}