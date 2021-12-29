package in.ecgc.smile.erp.accounts.exception;

public class FtrNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	public FtrNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public FtrNotFoundException(String msg) {
		super(msg);
	}
	public FtrNotFoundException(String msg,Object[] args) {
		super(msg);
		this.args = args;
	}
	public FtrNotFoundException(Object[] args) {
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
