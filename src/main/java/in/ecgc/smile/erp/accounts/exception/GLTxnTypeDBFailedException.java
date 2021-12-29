package in.ecgc.smile.erp.accounts.exception;

public class GLTxnTypeDBFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public GLTxnTypeDBFailedException() {
		super();
	}

	public GLTxnTypeDBFailedException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public GLTxnTypeDBFailedException(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
