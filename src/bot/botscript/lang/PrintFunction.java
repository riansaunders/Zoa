package bot.botscript.lang;

import java.util.Arrays;

import bot.botscript.InvocationException;
import bot.botscript.lang.runtime.SystemFunction;

/**
 * 
 * @author Bot
 *
 */
public class PrintFunction implements SystemFunction {

	@Override
	public Object invoke(Object... args) throws InvocationException {
		if (args.length > 1 || Arrays.equals(args, null))
			throw new InvocationException("Print requires 0..1 arguments");
		System.out.println(args[0]);
		return null;
	}

	@Override
	public String getName() {
		return "println";
	}

	@Override
	public Object[] getPossibleArgs() {
		return new Object[] { String.class };
	}
}