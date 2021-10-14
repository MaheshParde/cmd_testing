package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.exception.ReceiptNotFoundException;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.repository.ReceiptDao;


public class ReceiptMasterServiceImplTests {

	private MockMvc mockMvc;
	
	@Mock
	ReceiptDao receiptDao;
	
	@Mock
	private Receipt receipt;
	
	@InjectMocks
	ReceiptServiceImpl receiptServiceImpl;
	
	@Mock
	List<Receipt> receipts;
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(receiptServiceImpl).build();
	}
	@BeforeTest
	public void initReceipt() {
		LocalDate receiptDate = LocalDate.parse("2018-04-01");
		LocalDate instrumentDate = LocalDate.parse("2018-04-01");		
	
		receipt = new Receipt();
		receipt.setLogicalLocCode("MUM");
		receipt.setReceivedFromCode("abc");
		receipt.setReceiptAmount(2000.00);
		receipt.setFiscalYear("2018");
		receipt.setReceiptNumber(100);
		receipt.setIwdNumber("abc100");
		receipt.setReceiptDate(receiptDate);
		receipt.setRemarks("final");
		receipt.setReceivedFromName("Super");
		receipt.setReceivedFromAddress("22 street road");
		receipt.setStampAmount(1000.00);
		receipt.setInstrumentType("NEFT");
		receipt.setInstrumentNumber("123456");
		receipt.setInstrumentDate(instrumentDate);
		receipt.setDrawnOn("2019-04-01");
		receipt.setGlTxnNumberOld(13452);
		receipt.setGlFlag("N");
		receipt.setOldReceiptNumber(1245);
		receipt.setPayInSlip("submitted");
		receipt.setMetaRemarks("abc");
		receipt.setMetaStatus("T");		
		
	}
	
	@Test
	public void addReceiptServiceImplTest() {
		when(receiptDao.addReceipt(receipt)).thenReturn(1);
		Assert.assertEquals(receiptServiceImpl.addReceipt(receipt),receipt.getReceiptNumber());
		
		when(receiptDao.addReceipt(receipt)).thenReturn(new Integer(0));
		Assert.assertEquals(receiptServiceImpl.addReceipt(receipt), new Integer(0));			
		}
	
	@Test(expectedExceptions = { ReceiptNotFoundException.class}) 
	public void viewReceiptByLogicalLocAndReceiptNoServiceImplTest() {
		when(receiptDao.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2021000002)).thenReturn(receipt);
		Assert.assertEquals(receiptServiceImpl.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2021000002), receipt);	
		
		when(receiptDao.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2021000002)).thenReturn(null);
		Assert.assertEquals(receiptServiceImpl.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2021000002),
				new ReceiptNotFoundException());	
	}

	@Test
	public void listAllReceiptServiceImplTest() {
		when(receiptDao.listAllReceipts()).thenReturn(receipts);
		Assert.assertEquals(receiptServiceImpl.listAllReceipts(), receipts);
	}
	
	@Test(expectedExceptions = { ReceiptNotFoundException.class})
	public void updateReceiptSericeImplTest() {
		when(receiptDao.updateReceipt("MUMBAILOG1", 2012000002, receipt)).thenReturn(1);
		when(receiptDao.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2021000002)).thenReturn(receipt);
		Assert.assertEquals(receiptServiceImpl.updateReceipt("MUMBAILOG1", 2012000002, receipt), new Integer(1));
		
		when(receiptDao.updateReceipt("MUMBAILOG1", 2012000002, receipt)).thenReturn(1);
		when(receiptDao.viewByLogicalLocCodeAndReceiptNo("MUMBAILOG1", 2012000002)).thenReturn(null);
		Assert.assertEquals(receiptServiceImpl.updateReceipt("MUMBAILOG1", 2012000002, receipt), new ReceiptNotFoundException());
	}
}
