package zoa.build.node;

import zoa.build.Node;
import zoa.build.SyntacticParser;

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
