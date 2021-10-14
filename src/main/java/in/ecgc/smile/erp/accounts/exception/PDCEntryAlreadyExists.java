package in.ecgc.smile.erp.accounts.exception;

public class PDCEntryAlreadyExists extends RuntimeException{

	private Object []args;

	public PDCEntryAlreadyExists(Object[] args, String message) {
		super(message);
		this.args = args;
	}

	public PDCEntryAlreadyExists(Object[] args) {
		this.args = args;
	}
	
	public PDCEntryAlreadyExists(String message) {
		super(message);
	}
	
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	
}
