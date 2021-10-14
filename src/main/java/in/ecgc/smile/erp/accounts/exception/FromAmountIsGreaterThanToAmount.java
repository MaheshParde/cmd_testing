package in.ecgc.smile.erp.accounts.exception;
public class FromAmountIsGreaterThanToAmount extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private Object[] args;
	
	public FromAmountIsGreaterThanToAmount() {
		super();
	}
	
	public FromAmountIsGreaterThanToAmount(String message) {
		super(message);
	}
	
	public FromAmountIsGreaterThanToAmount(String message, Object[] args)
	{
		super(message);
		this.args = args;
	}

}
