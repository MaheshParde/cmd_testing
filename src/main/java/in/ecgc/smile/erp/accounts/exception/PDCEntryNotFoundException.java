package in.ecgc.smile.erp.accounts.exception;

public class PDCEntryNotFoundException extends RuntimeException {

	private Object []args;

	public PDCEntryNotFoundException(Object[] args, String message) {
		super(message);
		this.args = args;
	}

	public PDCEntryNotFoundException(Object[] args) {
		this.args = args;
	}
	
	public PDCEntryNotFoundException(String message) {
		super(message);
	}
	
	
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	
	
}
