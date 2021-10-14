package in.ecgc.smile.erp.accounts.util;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ResponseModel to capture response after each rest operation
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
	private T data;
	private HttpStatus httpStatus;
	private String statusMessage;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "ResponseModel [data=" + data + ", httpStatus=" + httpStatus + ", statusMessage=" + statusMessage + "]";
	}	

}
