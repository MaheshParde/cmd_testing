package in.ecgc.smile.erp.accounts.exception;
public class FromDateIsGreaterThanToDate extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] args;

	public FromDateIsGreaterThanToDate() {
		super();
	}
	
	public FromDateIsGreaterThanToDate(String message) {
		super(message);
	}
	
	public FromDateIsGreaterThanToDate(String message, Object[] args)
	{
		super(message);
		this.args = args;
	}
}
