package in.ecgc.smile.erp.accounts.exception;

public class ImproperFtrDataProvidedException extends Exception {

	
	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public ImproperFtrDataProvidedException() {
	}
	public ImproperFtrDataProvidedException(String msg) {
		super(msg);
	}
	public ImproperFtrDataProvidedException(String msg,Object[] args) {
		super(msg);
		this.args = args;
	}
	public ImproperFtrDataProvidedException(Object[] args) {
		super();
		this.args = args;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}


}
