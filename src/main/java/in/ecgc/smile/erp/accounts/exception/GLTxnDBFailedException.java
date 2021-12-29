package in.ecgc.smile.erp.accounts.exception;

public class GLTxnDBFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public GLTxnDBFailedException() {
		super();
	}

	public GLTxnDBFailedException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public GLTxnDBFailedException(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
