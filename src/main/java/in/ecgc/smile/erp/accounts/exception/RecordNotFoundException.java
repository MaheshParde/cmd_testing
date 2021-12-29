package in.ecgc.smile.erp.accounts.exception;

public class RecordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
