package bot.botscript.lang;

import java.util.Arrays;

/**
 * 
 * @author Bot
 *
 */
public class RSArray {

    private String[] values;

    public RSArray(String[] values) {
	this.values = values;
    }

    @Override
    public boolean equals(Object other) {
	if ((other instanceof String[])) {
	    return Arrays.equals(values, (String[]) other);
	} else if (other instanceof RSArray) {
	    return Arrays.equals(values, ((RSArray) other).values);
	} else {
	    return false;
	}
    }
}