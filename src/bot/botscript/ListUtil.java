package bot.botscript;

import java.util.List;

/**
 *
 * @author Bot
 *
 * @param <E>
 */
public final class ListUtil {

	public static interface EntryAcceptor<E> {

		public boolean accept(E entry);

	}

	@SuppressWarnings("unchecked")
	public static <E> E find(List<E> list, EntryAcceptor<E> acceptor) {
		for (Object e : list.toArray()) {
			if (acceptor.accept((E) e)) {
				return (E) e;
			}
		}
		return null;
	}

	private ListUtil() {

	}
}