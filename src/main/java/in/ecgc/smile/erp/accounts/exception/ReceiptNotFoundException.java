package in.ecgc.smile.erp.accounts.exception;

public class ReceiptNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public ReceiptNotFoundException() {

		super();
	}
	
	public ReceiptNotFoundException(String message) {
		super(message);
	}
	
	public ReceiptNotFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
