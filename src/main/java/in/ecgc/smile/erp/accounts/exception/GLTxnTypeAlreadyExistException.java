package in.ecgc.smile.erp.accounts.exception;

/**
 * Exception is raised by service layer{@link in.ecgc.smile.erp.accounts.service.GLTxnTypeService} 
 * when GL transaction type code is already present in the database and new attempt is made to insert same entry.
 *
 * @version 1.0 01-June-2020
 * @author Sanjali Kesarkar
 * 
 **/
public class GLTxnTypeAlreadyExistException extends RuntimeException{

	private Object[] args; 
	
	public GLTxnTypeAlreadyExistException() {
		super();
	}
	
	public GLTxnTypeAlreadyExistException(String message) {
		super(message);
	}
	
	public GLTxnTypeAlreadyExistException(String message, Object[] args) {
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
