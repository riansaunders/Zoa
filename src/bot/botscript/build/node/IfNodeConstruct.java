package bot.botscript.build.node;

import bot.botscript.build.NodeConstruct;
import bot.botscript.build.SyntacticParser;

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