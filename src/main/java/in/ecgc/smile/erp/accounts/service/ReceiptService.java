package in.ecgc.smile.erp.accounts.service;

import java.util.List;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.model.States;

public interface ReceiptService {

	Integer addReceipt(Receipt receipt);	
	List<Receipt> listAllReceipts();
	Integer updateReceipt(String logicalLocCode, Integer receiptNumber , Receipt receiptUpdate); 
	List<Receipt>viewByLogicalLocCode(String logicalLocCode);
	Receipt viewByLogicalLocCodeAndReceiptNo(String logicalLocCode, Integer receiptNo);
	
	List<States> getAllStates ();
	Integer updatePrintFlag(String logicalLocCode, Integer receiptNumber, String printFlag);
	String getFlag(String logicalLocCode, Integer receiptNumber);
}
