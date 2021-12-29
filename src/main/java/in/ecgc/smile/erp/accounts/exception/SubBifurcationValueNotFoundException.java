package in.ecgc.smile.erp.accounts.exception;

public class SubBifurcationValueNotFoundException  extends RuntimeException{

	
	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public  SubBifurcationValueNotFoundException() {
		
		super();
	}
	
	public SubBifurcationValueNotFoundException(String message) {
		super(message);
	}
	
	public SubBifurcationValueNotFoundException(String message, Object[] args) {
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
