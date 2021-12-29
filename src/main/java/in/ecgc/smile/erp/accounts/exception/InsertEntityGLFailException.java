package in.ecgc.smile.erp.accounts.exception;

public class InsertEntityGLFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public InsertEntityGLFailException() {
		super();
	}

	public InsertEntityGLFailException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public InsertEntityGLFailException(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
