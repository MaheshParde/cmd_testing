package in.ecgc.smile.erp.accounts.exception;

public class DatabaseOperationFailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public DatabaseOperationFailException() {
		super();
	}
	
	public DatabaseOperationFailException(String message, Object[] args) {
		super(message);
		this.args = args;
	}
	
	public DatabaseOperationFailException(String message) {
		super(message);
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
		
}
