package bot.botscript.build;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bot
 *
 */
public class LexicalAnalyzer {

	private int columnNo;
	private int lineNo = 1;
	private int currentCharIdx;
	private String code;

	public List<Token> evaluate(String... input) {
		final LinkedList<Token> list = new LinkedList<Token>();
		code = "";
		for (String s : input) {
			s = s.trim();
			if (s.startsWith("//") || s.isEmpty() || s.equals("\r") || s.equals("\r\n")
					|| s.equals("\n") || s.equals("\0")) {
				code += "\n";
			} else {
				code += s + "\n";
			}
		}
		boolean withinContainer = false;
		boolean ignore = false;
		for (int i = 0; i < code.length(); i++) {
			final char currentChar = code.charAt(currentCharIdx);
			boolean commitToken = true;
			if (currentChar == '\n') {
				++lineNo;
				columnNo = 0;
			} else {
				++columnNo;
				Token t = new Token();
				switch (currentChar) {

				case '\'':
				case '"':
					int endIdx = quoteEnd();
					t.setType(TokenType.STRING_LITERAL);
					t.setWord(code.substring(currentCharIdx + 1, endIdx));
					currentCharIdx = endIdx;
					break;
				case '+':
					if (completeStatement("=", true, false) != -1) {
						t.setType(TokenType.ADD);
					} else {
						t.setType(TokenType.PLUS);
					}
					break;
				case ',':
					t.setType(TokenType.COMMA);
					break;
				case '/':
					if (completeStatement("=", true, false) != -1) {
						t.setType(TokenType.DIVISION);
					} else if (completeStatement("*", true, false) != -1) {
						ignore = true;
					} else {
						t.setType(TokenType.DIVIDE);
					}
					break;
				case '-':
					if (completeStatement("=", true, false) != -1) {
						t.setType(TokenType.SUBTRACT);
					} else {
						t.setType(TokenType.MINUS);
					}
					break;
				case '@':
					t.setType(TokenType.AT);
					break;
				case '*':
					if (completeStatement("/", true, false) != -1) {
						ignore = false;
					} else {
						t.setType(TokenType.ASTERISK);
					}
					break;
				case '>':
					if (completeStatement("=", true) != -1) {
						t.setType(TokenType.GREATER_THAN_OR);
					} else {
						t.setType(TokenType.GREATER_THAN);
					}
					break;
				case '<':
					if (completeStatement("=", true) != -1) {
						t.setType(TokenType.LESS_THAN_OR);
					} else {
						t.setType(TokenType.LESS_THAN);
					}
					break;
				case '=':
					if (completeStatement("&", true) != -1) {
						t.setType(TokenType.EQUAL);
					} else {
						t.setType(TokenType.EQUAL_SIGN);
					}
					break;
				case '&':
					if (completeStatement("&", true) != -1) {
						t.setType(TokenType.COMPARATIVE_AND);
					} else {
						t.setType(TokenType.BITWISE_AND);
					}
					break;
				case '`':
					t.setType(TokenType.GRAVE_ACCENT);
					break;
				case '~':
					t.setType(TokenType.TILDE);
					break;
				case '(':
					t.setType(TokenType.LPAREN);
					break;
				case ')':
					t.setType(TokenType.RPAREN);
					break;
				case '[':
					t.setType(TokenType.LBRACK);
					withinContainer = true;
					break;
				case ']':
					t.setType(TokenType.RBRACK);
					withinContainer = false;
					break;
				case ';':
					t.setType(TokenType.SEMICOLON);
					break;
				case '{':
					t.setType(TokenType.LBRACE);
					break;
				case ':':
					t.setType(TokenType.COLON);
					break;
				case '}':
					t.setType(TokenType.RBRACE);
					break;
				default:
					if (!Character.isWhitespace(currentChar)) {
						boolean submitWordOrNumber = true;
						if (!withinContainer) {
							submitWordOrNumber = false;
							if (completeBlock("if")) {
								t.setType(TokenType.IF);
							} else if (completeBlock("return")) {
								t.setType(TokenType.RETURN);
							} else if (completeBlock("end")) {
								t.setType(TokenType.END);
							} else if (completeBlock("switch")) {
								t.setType(TokenType.SWITCH);
							} else {
								submitWordOrNumber = true;
							}
						}
						if (submitWordOrNumber) {
							if (currentChar >= '0' && currentChar <= '9') {
								endIdx = numberEnd();
								final String val = code.substring(currentCharIdx, endIdx);
								if (val.contains(".")) {
									t.setType(TokenType.DECIMAL);
								} else {
									t.setType(TokenType.NUMBER);
								}
								t.setWord(val);
								currentCharIdx = endIdx - 1;
							} else if (currentChar >= 'A' && currentChar <= 'Z'
									|| currentChar >= 'a' && currentChar <= 'z' || currentChar == '_') {
								endIdx = letterEnd();
								t.setType(TokenType.WORD);
								t.setWord(code.substring(currentCharIdx, endIdx));
								currentCharIdx = endIdx - 1;
							}
						}
					} else {
						commitToken = false;
					}
					break;
				}
				if (t.val() == null || t.val().isEmpty()) {
					t.setWord(String.valueOf(t.type().word));
				}
				final int colStart = columnNo;
				columnNo += !t.val().isEmpty() ? t.val().length() : 0;
				commitToken = t.type() != TokenType.INVALID;
				t.setWord(t.val().trim());
				if (commitToken && !ignore) {
					t.src(new SourcePosition(lineNo, colStart, columnNo));
					list.add(t);
				} else {
					t = null;
				}
			}
			if (++currentCharIdx >= code.length()) {
				break;
			}
		}
		currentCharIdx = 0;
		return list;
	}

	private int quoteEnd() {
		int idx = currentCharIdx;
		if (idx + 1 >= code.length()) {
			return idx;
		}
		final char quote = code.charAt(currentCharIdx);
		for (;;) {
			try {
				final char current = code.charAt(++idx);
				if (current == quote) {
					break;
				}
			} catch (final IndexOutOfBoundsException e) {
				throw new RuntimeException("Quote not closed on line " + lineNo);
			}
		}
		return idx;
	}

	private int numberEnd() {
		int idx = currentCharIdx;

		if (idx + 1 >= code.length()) {
			return idx;
		}
		boolean decimal = false;
		for (;;) {
			final char current = code.charAt(++idx);
			if (current == '.') {
				if (decimal) {
					throw new RuntimeException("Unexpected '.'");
				} else {
					decimal = true;
				}
			} else if (!Character.isDigit(current)) {
				if (code.charAt(idx - 1) == '.') {
					throw new RuntimeException("Incomplete decimal");
				}
				break;
			}
		}
		return idx;
	}

	private int letterEnd() {
		int idx = currentCharIdx;
		if (idx + 1 >= code.length()) {
			return idx;
		}
		for (;;) {
			final char current = code.charAt(++idx);
			if (!Character.isLetterOrDigit(current) && current != '_') {
				break;
			}
		}
		return idx;
	}

	private boolean completeBlock(String str) {
		// Roll back the character index . .
		currentCharIdx--;
		// Juggle the current character index.
		final int idx = completeStatement(str, false);
		// Fix what we did
		currentCharIdx++;
		if (idx == -1) {
			return false;
		}
		final char inFrontOf = code.charAt(idx);
		// || inFrontOf == '_'
		if (Character.isLetterOrDigit(inFrontOf) || inFrontOf == '_') {
			return false;
		}
		currentCharIdx = idx;
		return true;
	}

	private int completeStatement(String str, boolean shouldSeekIdx) {
		return completeStatement(str, shouldSeekIdx, true);
	}
	
	private int completeStatement(String str, boolean shouldSeekIdx, boolean ignoreWhitespace) {
		final int endIdx = currentCharIdx + str.length();
		if (endIdx > code.length()) {
			return -1;
		}
		int nextCharPtr = 0;
		for (int currentCharPtr = currentCharIdx + 1; currentCharPtr < code.length(); currentCharPtr++) {
			final char current = code.charAt(currentCharPtr);
			if (Character.isWhitespace(current) && ignoreWhitespace) {
				continue;
			}
			final char nextChar = str.charAt(nextCharPtr++);
			if (current != nextChar) {
				return -1;
			}
			if (nextCharPtr == str.length()) {
				break;
			}
		}
		return shouldSeekIdx ? currentCharIdx = endIdx + 1 : endIdx + 1;
	}
}