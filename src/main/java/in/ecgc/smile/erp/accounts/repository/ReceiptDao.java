package in.ecgc.smile.erp.accounts.repository;

import java.util.List;

import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.model.States;

public interface ReceiptDao {


	Integer	addReceipt(Receipt receipt);
	List<Receipt> listAllReceipts();
	Integer getReceiptNo(String logicalLocCode,String fiscalYr);
	Integer updateReceipt(String logicalLocCode ,Integer receiptNumber,  Receipt receiptUpdate);
	List<Receipt> viewByLogicalLocationCode(String logicalLocCode);
	Receipt viewByLogicalLocCodeAndReceiptNo(String logicalLocCode, Integer receiptNumber);
	List<States> getAllStates();
	String getBSInvoiceNo(String logicalLocCode,String fiscalYr);
	String getTaxInvoiceNo(String logicalLocCode,String fiscalYr);
	String getFlag(String logicalLocCode, Integer receiptNumber);
	Integer updatePrintFlag(String logicalLocCode, Integer receiptNumber, String printFlag);
}