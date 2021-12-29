package in.ecgc.smile.erp.accounts.exception;

public class ScheduleCodeException extends Exception {


private Object[] args; 
	
	public ScheduleCodeException() {
		super();
	}
	
	public ScheduleCodeException(String message) {
		super(message);
	}
	
	public ScheduleCodeException(String message, Object[] args) {
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
