package in.ecgc.smile.erp.accounts.exception;


/**
 * Exception is raised by service layer{@link in.ecgc.smile.erp.accounts.service.EntityGLMasterService#addGLCode} 
 * when incomplete {@link in.ecgc.smile.erp.accounts.model.EntityGL} data is 
 * received during {@link in.ecgc.smile.erp.accounts.controller.EntityGlMasterController#addGLCode(in.ecgc.erp.accounts.model.EnityGL)} 
 * operation
 *
 * @version 1.0 21-April-2020
 * @author Sanjali Kesarkar
 * 
 **/
public class GLCodeIncompleteDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public GLCodeIncompleteDataException (String message) {
		super(message);
	}
}
