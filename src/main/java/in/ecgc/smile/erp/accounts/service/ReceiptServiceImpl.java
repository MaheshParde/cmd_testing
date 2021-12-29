package in.ecgc.smile.erp.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ecgc.smile.erp.accounts.exception.ReceiptNotFoundException;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.model.States;
import in.ecgc.smile.erp.accounts.repository.ReceiptDao;
@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired 
	ReceiptDao receiptDao; 
	@Autowired
	GlTxnService glTxnServive;

	@Override
	public Integer addReceipt(Receipt receipt) {
		try {
			receipt.setReceiptNumber(receiptDao.getReceiptNo(receipt.getLogicalLocCode(), receipt.getFiscalYear()));
			String invoiceNo=null;
			if(receipt.getSgstAmount()!= 0 || receipt.getIgstAmount()!=0 || receipt.getCgstAmount()!=0 || receipt.getUtgstAmount()!=0)	{
				 invoiceNo=receiptDao.getTaxInvoiceNo(receipt.getLogicalLocCode(), receipt.getFiscalYear());
				invoiceNo= "TI".concat(invoiceNo);					
				receipt.setInvoiceNo(invoiceNo);			
			}
			else {
				 invoiceNo=receiptDao.getBSInvoiceNo(receipt.getLogicalLocCode(), receipt.getFiscalYear());
				invoiceNo= "BS".concat(invoiceNo);	
				receipt.setInvoiceNo(invoiceNo);
			}
			int result = receiptDao.addReceipt(receipt);
			System.err.println(receipt.getReceiptNumber());

			if (result == 1)
			return receipt.getReceiptNumber();
			}catch (Exception e) {
				e.printStackTrace();
				//return -1;
			}
		return 0;
	}
		
	@Override
	public List<Receipt> listAllReceipts() {
		return receiptDao.listAllReceipts();
	}
	
	@Override
	public Receipt viewByLogicalLocCodeAndReceiptNo(String logicalLocCode, Integer receiptNumber) {
		System.err.println("inside viewByLogicalLocCodeAndReceiptNo service impl ");
		Receipt receipt= receiptDao.viewByLogicalLocCodeAndReceiptNo(logicalLocCode,receiptNumber);
		if(receipt== null) {
			throw new ReceiptNotFoundException("No Receipt details found with the given Receipt Id");
		}		
		return receiptDao.viewByLogicalLocCodeAndReceiptNo(logicalLocCode, receiptNumber);
	}
	
	@Override
	public Integer updateReceipt(String logicalLocCode, Integer receiptNumber, Receipt receiptUpdate) {

			Receipt receipt =  receiptDao.viewByLogicalLocCodeAndReceiptNo(logicalLocCode, receiptNumber);
			if (receipt == null) {
				throw new ReceiptNotFoundException("No Receipt details found with the given Receipt Id" ,
						new Integer[] {receiptNumber});
			}
			return receiptDao.updateReceipt(logicalLocCode, receiptNumber, receiptUpdate);
		}
	
		@Override
		public List<Receipt> viewByLogicalLocCode(String logicalLocCode) {
			return receiptDao.viewByLogicalLocationCode(logicalLocCode);
		}


		@Override
		public List<States> getAllStates() {
			 return receiptDao.getAllStates();
		}
		
		@Override
		public Integer updatePrintFlag(String logicalLocCode, Integer receiptNumber,String printFlag) {
			System.err.println("service"+receiptDao.updatePrintFlag(logicalLocCode, receiptNumber, printFlag));
			return receiptDao.updatePrintFlag(logicalLocCode, receiptNumber,printFlag);
		}	
		
		@Override
		public String getFlag(String logicalLocCode, Integer receiptNumber) {
				
				String result =receiptDao.getFlag(logicalLocCode, receiptNumber);
				System.err.println("be service"+result);
				if(result==null)
					return null;
				else
					return result;
			}
	

}
