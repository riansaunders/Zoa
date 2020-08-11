package zoa.build.node;

import java.util.LinkedList;
import java.util.List;

import zoa.build.Node;
import zoa.build.SyntacticParser;
import zoa.build.Token;
import zoa.build.TokenType;

/**
 * 
 * @author rian
 *
 */
public class ParenthesisNode extends Node {

	public final LinkedList<Token> tokens;

	public ParenthesisNode(SyntacticParser syntacticParser, LinkedList<Token> tokens) {
		super(syntacticParser);
		this.tokens = tokens;
	}

	public static int getTokensSize(SyntacticParser syntacticParser) {
		int count = 0;
		int openBrackets = 0;
		int idx = 0;
		Token token = syntacticParser.peekToken(idx);
		while (token != Token.EMPTY) {
			if (token.type() == TokenType.SEMICOLON) {
				break;
			} else if (token.type() == TokenType.LPAREN) {
				++openBrackets;
			} else if (token.type() == TokenType.RPAREN) {
				if(--openBrackets == 0) {
					break;
				}
			}
			++count;
			token = syntacticParser.peekToken(++idx);
		}
		return count;
	}

	public static int paramCount(SyntacticParser parser) {
		int countedArgs = 0;
		int openBrackets = 0;
		int idx = 0;
		Token token = parser.peekToken(idx);
		while (token != Token.EMPTY) {
			if (token.type() == TokenType.SEMICOLON) {
				break;
			} else if (token.type() == TokenType.LPAREN) {
				++openBrackets;
			} else if (token.type() == TokenType.RPAREN) {
				--openBrackets;
			}
			if (openBrackets == 0)
				break;
			if (openBrackets == 1 && token.type() != TokenType.COMMA && token.type() != TokenType.RPAREN
					&& token.type() != TokenType.LPAREN) {
				++countedArgs;
			}
			token = parser.peekToken(++idx);
		}
		/* Incomplete statement */
		if (openBrackets > 0) {
			return -1;
		}
		return countedArgs;
	}

	@Override
	public List<Token> getValue() {
		return tokens;
	}
}
