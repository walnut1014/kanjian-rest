package name.walnut.sequence.exception;

import org.springframework.dao.DataAccessException;

public class SequenceException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5213988923741242107L;

	public SequenceException(String msg) {
		super(msg);
	}

}
