package in.ecgc.smile.erp.accounts.exception;

/**
 * Exception is raised by service layer{@link in.ecgc.smile.erp.accounts.service.EntityGLMasterService} 
 * when GL code is not found in find operation
 *
 * @version 1.0 28-April-2020
 * @author Sanjali Kesarkar
 * 
 **/

public class PaymentAdviceUpdateSeqNoException extends RuntimeException {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Object[] args; 
	
	public PaymentAdviceUpdateSeqNoException() {
		super();
	}
	
	public PaymentAdviceUpdateSeqNoException(String message) {
		super(message);
	}
	
	public PaymentAdviceUpdateSeqNoException(String message, Object[] args) {
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
