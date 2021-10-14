package in.ecgc.smile.erp.accounts.exception;

public class DataAlreadyExist extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Object[] args;
	
	public DataAlreadyExist() {
		super();
	}
	
	public DataAlreadyExist(String message) {
		super(message);
	}
	
	public DataAlreadyExist(String message, Object[] args)
	{
		super(message);
		this.args = args;
	}

}
