package in.ecgc.smile.erp.accounts.exception;

public class FailToInsertDataException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private Object[] args;
	public FailToInsertDataException() {
		super();
	}

	public FailToInsertDataException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public FailToInsertDataException(String message) {
		super(message);
	}
	
	public FailToInsertDataException(String message, Boolean result) {
		super(message);
	}

	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	

}
