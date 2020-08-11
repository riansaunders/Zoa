package bot.botscript.build.grammar;

import java.util.regex.Matcher;

/**
 * 
 * @author rian
 *
 */
public class GrammarParser {

	private int currentIndex;
	private char current;
	private char next;
	private String input;

	private String complete(String at) {
		int idx = input.indexOf(at, currentIndex + 1);
		if (idx == -1)
			return null;
		String val = input.substring(currentIndex, idx + 1);
		setIndex(idx);
		return val;
	}

	private String finish() {
		String val = input.substring(currentIndex, input.length());
		next = '\0';
		currentIndex = input.length();
		return val;
	}

	private void next() {
		setIndex(currentIndex + 1);
	}

	private void setIndex(int index) {
		currentIndex = index;
		next = currentIndex < input.length() - 1 ? input.charAt(currentIndex + 1) : current;
	}

	private String parse0(String input) {
		String val = "";
		String wordBuffer = "";
		int index = 0;
		for (int i = index; i < input.length(); i++) {
			char cur = input.charAt(i);
			char up = i < input.length() - 1 ? input.charAt(i + 1) : '\0';
			if (Character.isLetter(cur)) {
				wordBuffer += cur;
				continue;
			}
			if (!wordBuffer.isEmpty()) {
				val += wordBuffer + " ";
				wordBuffer = " ";
			}

			switch (String.valueOf(cur)) {
			case "*":
				val += "Any ";
				break;
			case "?":
				val += "IfExists ";
				break;
			case "=":
				val += "IsEqual";
				break;
			case "\\i":
				break;
			case "!":
				val += "Except ";
				break;
			case "@":
				val += "GetGrammar ";
				break;
			case ">":
				val += "IfBefore ";
				break;
			case "<":
				val += "IfAfter ";
				break;
			case ",":
				val += "RevertToOrigin ";
				break;
			case "-":
				val += "Without ";
				break;
			case "+":
				val += "With";
				break;
			case ":":
				val += "Tag=";
				break;
			case "{":
				if (input.indexOf("}") == -1) {
					return "Incomplete {";
				}
				up = input.indexOf("}") < input.length() ? input.charAt(input.indexOf("}") + 1) : '\0';
				val += "Greedy" + (Character.isDigit(up) ? "(" + up + ") " : " ");
				break;
			}
		}
		if (!wordBuffer.isEmpty())
			val += wordBuffer;
		return val;
	}

	public Grammar parse(String input) {

		return null;
	}
}