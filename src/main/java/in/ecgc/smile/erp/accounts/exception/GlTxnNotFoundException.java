package in.ecgc.smile.erp.accounts.exception;

public class GlTxnNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Object[] args; 
	
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public GlTxnNotFoundException(Object[] args) {
		super();
		this.args = args;
	}

	public GlTxnNotFoundException(String message) {
		super(message);
		
	}
	
}
