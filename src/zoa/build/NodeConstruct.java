package zoa.build;

/**
 * 
 * @author rian
 *
 */
public interface NodeConstruct {

	public boolean accept(SyntacticParser parser);
	
	public Node construct(SyntacticParser parser);
	
}
