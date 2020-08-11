package bot.botscript;

/**
 * 
 * @author rian
 *
 * @param <O>
 */
public class RepeatableBuffer<O> {

	private int pointer;
	private O[] objects;
	private O peekNext;
	private O previous;
	private O current;

	public RepeatableBuffer(O[] objects) {
		this.objects = objects;
		this.peekNext = objects[1];
		this.current = objects[0];
	}

	public final O current() {
		return current;
	}

	public final O peek() {
		return peekNext;
	}

	public final O next() {
		return next(1);
	}

	public final O previous() {
		return previous;
	}

	public final O previous(int count) {
		if ((pointer - count) < 0)
			throw new IndexOutOfBoundsException("Pointer - Count below 0");
		return objects[pointer - count];
	}

	public final O peek(int count) {
		if ((pointer + count) > objects.length)
			throw new IndexOutOfBoundsException("Pointer + Count exceeds " + objects.length);
		System.out.println("Peeking: " + (pointer + count) + ", " + count + ", " + pointer);
		return objects[pointer + count];
	}

	public final O rewind(int count) {
		if ((pointer - count) < 0)
			throw new IndexOutOfBoundsException("Pointer - Count below 0");
		pointer -= count;
		if (pointer > -1)
			previous = objects[pointer - 1];
		if (pointer < objects.length)
			peekNext = objects[pointer + 1];
		return current = objects[pointer];
	}

	public final O next(int count) {
		if ((pointer + count) > objects.length)
			throw new IndexOutOfBoundsException("Pointer + Count exceeds " + objects.length);
		previous = objects[pointer];
		pointer += count;
		if ((pointer + count) < objects.length)
			peekNext = objects[pointer + 1];
		return current = objects[pointer];
	}

	public static final RepeatableBuffer<Integer> of(Integer[] obj) {
		return new RepeatableBuffer<Integer>(obj);
	}

	public static final RepeatableBuffer<Character> of(Character[] obj) {
		return new RepeatableBuffer<Character>(obj);
	}

	public static final RepeatableBuffer<Character> of(String string) {
		Character[] chars = new Character[string.length()];
		for (int i = 0; i < chars.length; i++)
			chars[i] = string.charAt(i);
		return of(chars);
	}
}