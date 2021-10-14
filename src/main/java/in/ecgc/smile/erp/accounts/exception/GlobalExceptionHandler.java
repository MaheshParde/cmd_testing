package in.ecgc.smile.erp.accounts.exception;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:errormap.properties")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> fieldErrorMap = new HashMap<String, String>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			logger.info(fieldError);
			logger.info("field error = " + fieldError.getField());			
			logger.info("error message = " + fieldError.getDefaultMessage());
			fieldErrorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		String defaultMessage = "Validation Failed for " + ex.getParameter().getParameterType();
		String errorMessage;
		String errorCode = env
				.getProperty(ex.getParameter().getParameterType().getName() + ExceptionConstantsMap.VALIDATION_ERROR);

		if (errorCode != null) {
			errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
		} else {
			errorMessage = defaultMessage;
			errorCode = defaultMessage;
		}

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				fieldErrorMap.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GLTxnTypeCodeNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(GLTxnTypeCodeNotFoundException ex,
			WebRequest request) throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_CODE_NOT_FOUND_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() );

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(GLTxnTypeAlreadyExistException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(GLTxnTypeAlreadyExistException ex,
			WebRequest request) throws Exception {
		logger.info("in exception wala method");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_CODE_ALREADY_EXIST_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ImproperFtrDataProvidedException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(ImproperFtrDataProvidedException ex,
			WebRequest request) throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.IMPROPER_FTR_DATA_PROVIDED_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() ); 

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FtrNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(FtrNotFoundException ex, WebRequest request)
			throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.FTR_NOT_FOUND_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CalendarMonthlyOpeningClosingException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(CalendarMonthlyOpeningClosingException ex,
			WebRequest request) throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.CAL_MONTHLY_OPENIN_CLOSING_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage()); 

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
