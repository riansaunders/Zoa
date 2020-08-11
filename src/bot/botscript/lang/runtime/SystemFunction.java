package bot.botscript.lang.runtime;

import bot.botscript.InvocationException;

/**
 * 
 * @author Bot
 *
 */
public interface SystemFunction {

    public Object invoke(Object... args) throws InvocationException;

    public String getName();

    public Object[] getPossibleArgs();
    
}