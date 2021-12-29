package in.ecgc.smile.erp.accounts.exception;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ECGC31
 *
 */
@ControllerAdvice
@PropertySource("classpath:errormap.properties")
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messageSource;
	
	
	/**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Within GlobalExceptionHandler#handleMethodArgumentNotValid");
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
	
	/**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails. Required for Method Level Validations
     *
     * @param ex the ConstraintViolationException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
    	log.info("Within GlobalExceptionHander#handleConstraintViolation");
    	String errorCode = env.getProperty(ExceptionConstantsMap.CONSTRAINT_VIOLATION_EXCEPTION);
    	String errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
    	ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getConstraintViolations().toString() );
    	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DatabaseOperationFailException.class)
    protected ResponseEntity<ErrorResponse> handleDatabaseOperationFailException(DatabaseOperationFailException ex, WebRequest request){
    	log.info("Within GlobalExceptionHander#handleDatabaseOperationFailException");
    	String errorCode = env.getProperty(ExceptionConstantsMap.DATABASE_OPERATION_FAILED_EXCEPTION,"acct.acct.defaultcode");
    	String errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
    	ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getLocalizedMessage());
    	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Handles  DataAccessException. Thrown when any exception occurs in DAO Layer which is not surrounded by try catch block
     *
     * @param ex the DataAccessException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex, WebRequest request){
    	log.info("Within GlobalExceptionHander#handleConstraintViolation");
    	String errorCode = env.getProperty(ExceptionConstantsMap.DATA_ACCESS_EXCEPTION);
    	String errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
    	ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getLocalizedMessage());
    	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	@ExceptionHandler(GLTxnTypeCodeNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleGLTxnTypeCodeNotFoundException(GLTxnTypeCodeNotFoundException ex,
			WebRequest request) throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_CODE_NOT_FOUND_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() );

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GLTxnTypeDBFailedException.class)
	public final ResponseEntity<ErrorResponse> handleGLTxnTypeDBFailedException(GLTxnTypeDBFailedException ex,
			WebRequest request) throws Exception {
		log.error("Within GlobalExceptionHandler#handleGLTxnTypeDBFailedException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_DB_FAILED_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() );

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(GLTxnDBFailedException.class)
	public final ResponseEntity<ErrorResponse> handleGLTxnDBFailedException(GLTxnDBFailedException ex,
			WebRequest request) throws Exception {
		log.error("Within GlobalExceptionHandler#handleGLTxnDBFailedException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_DB_FAILED_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() );

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	@ExceptionHandler(InsertEntityGLFailException.class)
	public final ResponseEntity<ErrorResponse> handleInsertEntityGLFailException(InsertEntityGLFailException ex, WebRequest request) {
		log.error("Within GlobalExceptionHandler#handleInsertEntityGLFailException");
		String errorCode = env.getProperty(ExceptionConstantsMap.INSERT_ENTITY_GL_FAILED_EXCEPTION);
		
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		
		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(),"", errorMessage, ExceptionUtils.getFullStackTrace(ex));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ReadEntityGLFailException.class)
	public final ResponseEntity<ErrorResponse> handleReadEntityGLFailException(ReadEntityGLFailException ex, WebRequest request) {
		log.error("Within GlobalExceptionHandler#handleReadEntityGLFailException");
		String errorCode = env.getProperty(ExceptionConstantsMap.READ_ENTITY_GL_FAILED_EXCEPTION);
		
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		
		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(),"", errorMessage, ExceptionUtils.getFullStackTrace(ex));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(GLTxnTypeAlreadyExistException.class)
	public final ResponseEntity<ErrorResponse> handleGLTxnTypeAlreadyExistException(GLTxnTypeAlreadyExistException ex,
			WebRequest request) throws Exception {
		log.error("Within GlobalExceptionHandler#handleGLTxnTypeAlreadyExistException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_CODE_ALREADY_EXIST_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ImproperFtrDataProvidedException.class)
	public final ResponseEntity<ErrorResponse> handleImproperFtrDataProvidedException(ImproperFtrDataProvidedException ex,
			WebRequest request) throws Exception {
		log.error("Within GlobalExceptionHandler#handleImproperFtrDataProvidedException");
		String errorCode = env.getProperty(ExceptionConstantsMap.IMPROPER_FTR_DATA_PROVIDED_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage() ); 

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FtrNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleFtrNotFoundException(FtrNotFoundException ex, WebRequest request)
			throws Exception {
		log.error("Within GlobalExceptionHandler#handleFtrNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.FTR_NOT_FOUND_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CalendarMonthlyOpeningClosingException.class)
	public final ResponseEntity<ErrorResponse> handleCalendarMonthlyOpeningClosingException(CalendarMonthlyOpeningClosingException ex,
			WebRequest request) throws Exception {
		log.error("Within GlobalExceptionHandler#handleCalendarMonthlyOpeningClosingException");
		String errorCode = env.getProperty(ExceptionConstantsMap.CAL_MONTHLY_OPENIN_CLOSING_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage()); 

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request){
		log.info("Within GlobalExceptionHandler#handleRecordNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.RECORD_NOT_FOUND_EXCEPTION);
	
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage());

		
		//HTTPStatus.NOT_FOUND populated Body, HTTPStatus.NO_CONTENT does not populate Response Body
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleGenericException(Exception ex,WebRequest request){
		log.info("Within GlobalExceptionHandler#handleGenericException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GENERIC_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, null , LocaleContextHolder.getLocale());
		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ex.getMessage()); 
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BankBranchNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelBankBranchNotFoundException(
			BankBranchNotFoundException ex, WebRequest request
			){
		//log.error("GlobalExceptionHandler#handelBankBranchNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.BANK_BRANCH_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(GLCodeNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelGLCodeNotFoundException(
			GLCodeNotFoundException ex, WebRequest request
			){
		//log.error("GlobalExceptionHandler#handelBankBranchNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_CODE_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
/////
	@ExceptionHandler(PaymentAdviceCreateException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceCreateException(
			PaymentAdviceCreateException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceCreateException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_CREATE_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PaymentAdviceEmptyListException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceEmptyListException(
			PaymentAdviceEmptyListException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceEmptyListException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_EMPTY_LIST_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PaymentAdviceRecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceRecordNotFoundException(
			PaymentAdviceRecordNotFoundException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceRecordNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_RECORD_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
	
	@ExceptionHandler(PaymentAdviceSeqNoGenException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceSeqNoGenException(
			PaymentAdviceSeqNoGenException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceSeqNoGenException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_SEQUENCE_NO_GENERATION_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(PaymentAdviceUpdateException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceUpdateException(
			PaymentAdviceUpdateException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceUpdateException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_UPDATE_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PaymentAdviceUpdateSeqNoException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceUpdateSeqNoException(
			PaymentAdviceUpdateSeqNoException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceUpdateSeqNoException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_UPDATE_SEQUENCE_NO_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PaymentAdviceValidationException.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceValidationException(
			PaymentAdviceValidationException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceValidationException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_VALIDATION_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CalendarRecordInsertException.class)
	public final ResponseEntity<ErrorResponse>handelCalendarRecordInsertException(
			CalendarRecordInsertException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelCalendarRecordInsertException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CALENDARRECORDINSERTEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PaymentAdviceRecordAlreadyExists.class)
	public final ResponseEntity<ErrorResponse>handelPaymentAdviceRecordAlreadyExists(
			PaymentAdviceRecordAlreadyExists ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPaymentAdviceValidationException");
		String errorCode = env.getProperty(ExceptionConstantsMap.PAYMENT_ADVICE_RECORD_ALREADY_EXISTS);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	//Calendar exceptions
	
	
	@ExceptionHandler(GlTxnNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelGlTxnNotFoundException(
			GlTxnNotFoundException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelGlTxnNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.GL_TXN_TYPE_CODE_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(MonthlyOpeningClosingException.class)
	public final ResponseEntity<ErrorResponse>handelMonthlyOpeningClosingException(
			MonthlyOpeningClosingException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelMonthlyOpeningClosingException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_MONTHLYOPENINGCLOSINGEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CalendarRecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelCalendarRecordNotFoundException(
			CalendarRecordNotFoundException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelCalendarRecordNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CALENDARRECORDNOTFOUNDEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CalendarRecorddeleteException.class)
	public final ResponseEntity<ErrorResponse>handelCalendarRecorddeleteException(
			CalendarRecorddeleteException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelCalendarRecorddeleteException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CALENDARRECORDDELETEEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CalendarRecordupdateException.class)
	public final ResponseEntity<ErrorResponse>handelCalendarRecordupdateException(
			CalendarRecordupdateException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelCalendarRecordupdateException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CALENDARRECORDUPDATEEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PreviousMonthopeningException.class)
	public final ResponseEntity<ErrorResponse>handelPreviousMonthopeningException(
			PreviousMonthopeningException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelPreviousMonthopeningException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_PREVIOUSMONTHOPENINGEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RegularOpeningException.class)
	public final ResponseEntity<ErrorResponse>handelRegularOpeningException(
			RegularOpeningException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelRegularOpeningException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_REGULAROPENINGEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RegularClosingException.class)
	public final ResponseEntity<ErrorResponse>handelRegularClosingException(
			RegularClosingException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelRegularClosingException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_REGULARCLOSINGEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConfiguredOpenException.class)
	public final ResponseEntity<ErrorResponse>handelConfiguredOpenException(
			ConfiguredOpenException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelConfiguredOpenException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CONFIGUREDOPENEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConfiguredClosingException.class)
	public final ResponseEntity<ErrorResponse>handelConfiguredClosingException(
			ConfiguredClosingException ex, WebRequest request
			){
		log.error("GlobalExceptionHandler#handelConfiguredClosingException");
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_CALENDAR_CONFIGUREDCLOSINGEXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ex.getMessage());

			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(ReceiptNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelReceiptNotFoundException(
			ReceiptNotFoundException ex, WebRequest request
			){
		//log.error("GlobalExceptionHandler#handelBankBranchNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.RECEIPT_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ScheduleCodeException.class)
	public final ResponseEntity<ErrorResponse>handelScheduleCodeException(
			ScheduleCodeException ex, WebRequest request
			){
		//log.error("GlobalExceptionHandler#handelBankBranchNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.SCHEDULE_CODE_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SubBifurcationLevelCodeNotFound.class)
	public final ResponseEntity<ErrorResponse>handelSubBifurcationLevelCodeNotFound(
			SubBifurcationLevelCodeNotFound ex, WebRequest request
			){
		String errorCode = env.getProperty(ExceptionConstantsMap.SUBBIFURCATION_LEVEL_CODE_NOT_FOUND);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SubBifurcationValueNotFoundException.class)
	public final ResponseEntity<ErrorResponse>handelSubBifurcationValueNotFoundException(
			SubBifurcationValueNotFoundException ex, WebRequest request
			){
		String errorCode = env.getProperty(ExceptionConstantsMap.SUB_BIFURCATION_VALUE_NOT_FOUND_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FailToInsertDataException.class)
	public final ResponseEntity<ErrorResponse>handelFailedToInserRecordException(
			FailToInsertDataException ex, WebRequest request){
		String errorCode = env.getProperty(ExceptionConstantsMap.ACCT_INSERT_RECORD_FAILED_EXCEPTION);

			String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

			ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
					ExceptionUtils.getFullStackTrace(ex));

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	//This Method handler should not be annotated with ExceptionHandler similar to handleMethodArgumentNotValid
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("GlobalExceptionHandler#handleHttpMessageNotReadable");
		//TODO Define Error Code and Message in errormap.properties 
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        ErrorResponse errorResponse = new ErrorResponse("acct-acct-001", Calendar.getInstance().getTime(), "", "Malformed JSON Request",
				ExceptionUtils.getFullStackTrace(ex));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
}
