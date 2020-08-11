package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;
import bot.botscript.build.Token;
import bot.botscript.build.TokenType;

/**
 * 
 * @author rian
 *
 */
public class CommaParenthesisNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		NodeConstruct other = parser.getConstruct(ParenthesisNodeConstruct.class);
		if (!other.accept(parser)) {
			return false;
		}
		ParenthesisNode paren = (ParenthesisNode) other.construct(parser);
		int bracketCount = 0;
		TokenType prev = null;
		for (Token token : paren.getValue()) {
			if (token.type() == TokenType.LPAREN)
				bracketCount++;
			else if (token.type() == TokenType.RPAREN)
				bracketCount--;
			/* previous is only gonna be null once. So a(b, c); b won't be ignored.  */
			if (prev == TokenType.COMMA && bracketCount == 1)
				break;
			prev = token.type();
		}
		/* We already know it's complete since there's a parenthesis node */
		return prev == TokenType.COMMA && bracketCount == 1;
	}

	@Override
	public Node construct(SyntacticParser parser) {
		ParenthesisNode paren = (ParenthesisNode) parser.getConstruct(ParenthesisNodeConstruct.class).construct(parser);
		System.out.println("Paren tokens: " + java.util.Arrays.toString(paren.tokens.toArray()));
		CommaParenthesisNode commas = new CommaParenthesisNode(parser);
		int bracketCount = 1;
		TokenType prev = null;
		for (Token token : paren.getValue()) {
			if (token.type() == TokenType.LPAREN)
				bracketCount++;
			else if (token.type() == TokenType.RPAREN)
				bracketCount--;
			/*
			 * previous is only gonna be null once. So a(b, c); b won't be
			 * ignored.
			 */
			else if (prev == TokenType.COMMA || prev == null && bracketCount == 1)
				commas.child(parser.construct());
			prev = token.type();
		}
		return commas;
	}
}