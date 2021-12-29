package in.ecgc.smile.erp.accounts.exception;

public class ReadEntityGLFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public ReadEntityGLFailException() {
		super();
	}

	public ReadEntityGLFailException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public ReadEntityGLFailException(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
