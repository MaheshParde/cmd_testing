package in.ecgc.smile.erp.accounts.exception;

import java.util.Date;

public class ErrorResponse {
	private String code;
	
	private Date timestamp;
	
	private String correlationId;
	
	private String message;
	
	private String detail;
	
	
	public ErrorResponse() {
	}
	

	public ErrorResponse(String code, Date timestamp, String correlationId, String message, String detail) {
		super();
		this.code = code;
		this.timestamp = timestamp;
		this.correlationId = correlationId;
		this.message = message;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}


	@Override
	public String toString() {
		return "ExceptionResponse [code=" + code + ", timestamp=" + timestamp + ", correlationId=" + correlationId
				+ ", message=" + message + ", detail=" + detail + "]";
	}
	

}
