 package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.Receipt;
import in.ecgc.smile.erp.accounts.service.ReceiptService;

public class ReceiptControllerTests {
	private ObjectMapper mapper; 
	private MockMvc mockMvc;
	@Mock
	private ReceiptService receiptService;
	@Mock
	private Receipt receipt;
	@InjectMocks
	ReceiptController receiptController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(receiptController).build();
		mapper= new ObjectMapper();
	}
	
	@BeforeTest
	public void initReceipt() {
		
		receipt = new Receipt();
		receipt.setLogicalLocCode("MUM");
		receipt.setReceivedFromCode("abc");
		receipt.setReceiptAmount(2000.00);
		receipt.setFiscalYear("2018-19");
		receipt.setReceiptNumber(100);
		receipt.setIwdNumber("abc100");
		//receipt.setReceiptDate(LocalDate.parse("2018-04-01"));
		receipt.setRemarks("final");
		receipt.setReceivedFromName("Super");
		receipt.setReceivedFromAddress("22 street road");
		receipt.setReceivedfromType("exporter");
		receipt.setStampAmount(1000.00);
		receipt.setInstrumentType("NEFT");
		receipt.setInstrumentNumber("123456");
		//receipt.setInstrumentDate(LocalDate.parse("2019-04-01"));
		receipt.setDrawnOn("2019-04-01");
		receipt.setGlTxnNumberOld(13452);
		receipt.setGlFlag("N");
		receipt.setOldReceiptNumber(1245);
		receipt.setPayInSlip("submitted");
		receipt.setMetaRemarks("abc");
		receipt.setMetaStatus("T");		
		receipt.setEntityCd("entity_cd");
		receipt.setProductDescription("product_description");
		receipt.setHsn(10.0);
		receipt.setUom(10.0);
		receipt.setQty(1.0);
		receipt.setRate(5.4);
		receipt.setAmount(1000.0);
		receipt.setDiscount(2.3);
		receipt.setTaxableDiscount(1.0);
		receipt.setCustomerGstNo("customer_gst_no");
		receipt.setEcgcGstNo("ecgc_gst_no");
		receipt.setInvoiceNo("invoice_no");
		receipt.setCgstAmount(100.0);
		receipt.setSgstAmount(100.0);
		receipt.setIgstAmount(100.0);
		receipt.setUtgstAmount(100.0);
		receipt.setCgstTaxPer(1.5);
		receipt.setSgstTaxPer(1.5);
		receipt.setIgstTaxPer(1.5);
		receipt.setUtgstTaxPer(1.5);
		receipt.setExpoState("expo_state");
		receipt.setBranchState("branch_state");
		receipt.setTaxType("tax_type");
		receipt.setOldInvoiceNo("old_invoice_no");
		receipt.setUpdateFlag("update_flg");
		receipt.setSez("sez");
	}

	@Test
	public void listAllReceiptsControllerTests()throws Exception{
		ArrayList<Receipt>receipts = new ArrayList<Receipt>();
		receipts.add(receipt);
		when(receiptService.listAllReceipts()).thenReturn(receipts);
		mockMvc.perform(MockMvcRequestBuilders.get("/receipt/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		when(receiptService.listAllReceipts()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/receipt/view-all")
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isNoContent());
		}
	@Test
	public void addReceiptControllerTest() throws Exception {
		String receipts = mapper.writeValueAsString(receipt);
		when(receiptService.addReceipt(receipt)).thenReturn(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/receipt/add")
				.content(receipts)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
		
		
	}

	@Test
	private void updateReceiptControllerTest()throws Exception{
		String receipts = mapper.writeValueAsString(receipt);
		when(receiptService.updateReceipt("MUM", 100, receipt)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.put("/receipt/update/{logicalLocCode}/{receiptNumber}","MUM",100)
				.content(receipts).contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
		
	@Test
	private void findReceiptByReceiptIdControllerTest()throws Exception{
		when(receiptService.viewByLogicalLocCodeAndReceiptNo("MUM", 100)).thenReturn(receipt);
		mockMvc.perform(MockMvcRequestBuilders.get("/receipt/view/{logicalLocCode}/{receiptNumber}","MUM", 100)
				.contentType(MediaType.APPLICATION_STREAM_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		//.andExpect(MockMvcResultMatchers.jsonPath(".receiptId").exists())
		
		.andExpect(MockMvcResultMatchers.jsonPath(".metaRemarks").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".glTxnNumberOld").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".metaStatus").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".oldReceiptNumber").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".fiscalYear").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".payInSlip").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".glFlag").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receiptAmount").exists())
		//.andExpect(MockMvcResultMatchers.jsonPath(".recievedfromType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".glTxnNumber").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".glTxnType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".logicalLocCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receiptNumber").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".receiptDate").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".iwdNumber").exists())
		//.andExpect(MockMvcResultMatchers.jsonPath(".recievedFromCode").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".remarks").exists())
		//.andExpect(MockMvcResultMatchers.jsonPath(".recievedFromName").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentType").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".stampAmount").exists())
		//.andExpect(MockMvcResultMatchers.jsonPath(".recievedFromAddress").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentDate").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".instrumentNumber").exists())
		.andExpect(MockMvcResultMatchers.jsonPath(".drawnOn").exists());
		}
}
