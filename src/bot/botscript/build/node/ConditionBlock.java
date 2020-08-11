package bot.botscript.build.node;

import bot.botscript.build.Node;
import bot.botscript.build.SyntacticParser;

/**
 * 
 * @author rian
 *
 */
public class ConditionBlock extends Node  {

	public ConditionBlock(SyntacticParser syntacticParser) {
		super(syntacticParser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValue() {
		return null;
	}

	public static boolean accept(SyntacticParser parser) {

		return false;
	}
}