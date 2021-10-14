package in.ecgc.smile.erp.accounts.exception;

public class CalendarMonthlyOpeningClosingException extends Exception {

	private Object[] args;
	
	public CalendarMonthlyOpeningClosingException() {
		
	}
	public CalendarMonthlyOpeningClosingException(String msg) {
		super(msg);
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	
}
