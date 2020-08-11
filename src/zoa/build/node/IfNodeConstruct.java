package zoa.build.node;

import zoa.build.NodeConstruct;
import zoa.build.SyntacticParser;

/**
 * 
 * @author rian
 *
 */
public class IfNodeConstruct implements NodeConstruct {

	@Override
	public boolean accept(SyntacticParser parser) {
		return false;
	}

	@Override
	public IfNode construct(SyntacticParser parser) {
		return null;
	}
}
