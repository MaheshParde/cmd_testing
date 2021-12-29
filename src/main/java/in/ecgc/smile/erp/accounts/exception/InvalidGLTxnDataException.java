package in.ecgc.smile.erp.accounts.exception;

public class InvalidGLTxnDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidGLTxnDataException(String msg) {
		super(msg);
	}
	public InvalidGLTxnDataException() {
		super("Invalid Date Present");
	}
}
