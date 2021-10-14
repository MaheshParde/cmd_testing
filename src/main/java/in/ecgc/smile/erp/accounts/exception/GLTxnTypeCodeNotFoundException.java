package in.ecgc.smile.erp.accounts.exception;

/**
 * Exception is raised by service layer{@link in.ecgc.smile.erp.accounts.service.GLTxnTypeService} 
 * when GL transaction type code is not found.
 *
 * @version 1.0 01-June-2020
 * @author Sanjali Kesarkar
 * 
 **/
public class GLTxnTypeCodeNotFoundException extends RuntimeException {
	
	private Object[] args; 
	
	public GLTxnTypeCodeNotFoundException() {
		super();
	}
	
	public GLTxnTypeCodeNotFoundException(String message) {
		super(message);
	}
	
	public GLTxnTypeCodeNotFoundException(String message, Object[] args) {
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
