package bot.botscript.build;

/**
 *
 * @author Bot
 *
 */
public class BinaryOp<T> {

	public final T left;
	public final T right;

	public BinaryOp(T left, T right) {
		this.left = left;
		this.right = right;
	}
}