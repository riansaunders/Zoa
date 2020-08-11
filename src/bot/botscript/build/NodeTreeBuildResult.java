package bot.botscript.build;

import java.util.LinkedList;

/**
 * 
 * @author rian
 *
 */
public class NodeTreeBuildResult {

	public final LinkedList<CompilationError> errors;
	public final LinkedList<Node> nodes;

	public NodeTreeBuildResult(LinkedList<CompilationError> errors, LinkedList<Node> nodes) {
		this.errors = errors;
		this.nodes = nodes;
	}
}