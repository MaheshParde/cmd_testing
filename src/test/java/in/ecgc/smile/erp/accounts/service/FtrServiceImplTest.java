/**
 * 
 */
package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.FTR;
import in.ecgc.smile.erp.accounts.model.FtrDtl;
import in.ecgc.smile.erp.accounts.repository.FTRDaoImpl;
import in.ecgc.smile.erp.accounts.util.FtrReportedToBankExcel;

/**
 * @author CMJ-SENG-LAPTOP9
 *
 */
public class FtrServiceImplTest extends AbstractTestNGSpringContextTests {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@Mock
	FTR ftr;

	@Mock
	FtrDtl dtl;
	
	@Mock
	private FTRDaoImpl ftrDao;
	
	
	
	
	@Mock
	FtrReportedToBankExcel ftrReportedToBank;

	@InjectMocks
	private FTRServiceImpl ftrService;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
	}
	
	@BeforeTest
	public void initFtr() {
		ftr = new FTR();
		ftr.setFtrReqNo(21);
		ftr.setFtrType("Fund Transfer");
		ftr.setLogicalLocCode("MUMBAILOG1");
		ftr.setFtrReqBranchCd("MUMBAI");
		ftr.setFtrReqBy(101);
		ftr.setFtrTrfAmt(60000.00);
		ftr.setFtrReqDeptCd("ADMIN");
		ftr.setFtrReqDt(LocalDate.now());
		ftr.setFtrApprBy(102);
		ftr.setFtrReqStatus("P");
		ftr.setFtrTrfDt(LocalDate.now());
		ftr.setPvStatus("P");		
		
		FtrDtl ftrdtl = new FtrDtl();
		ftrdtl.setFTRRequestNo(21);
		ftrdtl.setFTRRequestAmount((float)60000.00);
		ftrdtl.setFTRRequestReason("Test");
		ftrdtl.setFTRRequestType("ADM");
		ftrdtl.setFTRRequestSrNo(1);
		ftrdtl.setFtrFor("Other");
		List<FtrDtl> list = new ArrayList<FtrDtl>();
		list.add(ftrdtl);
		ftr.setFtrDtl(list);
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			
	}
	
	@Test
	public void getAllFtrREquestTest() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrDao.getAllFTRRequest()).thenReturn(ftrs);
		Assert.assertEquals(ftrService.getAllFTRRequest().size(), 1);
				
	}
	
	@Test
	public void addRequestTest() throws Exception{
		
		when(ftrDao.addRequest(ftr)).thenReturn(1);
		Assert.assertEquals(ftrService.addRequest(ftr),new Integer(0));
				
	}
	
	@Test
	public void getAllPendingFTRRequestTest() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		when(ftrDao.getAllPendingFTRRequest()).thenReturn(ftrs);
		Assert.assertEquals(ftrService.getAllPendingFTRRequest().size(), 1);
				
	}
	
	@Test
	public void getAllFtrREquestByLogicalLocTest() throws Exception{
		List<FTR> ftrs = new ArrayList<FTR>();
		ftrs.add(ftr);
		String logicalLoc = "SURAT1";
		when(ftrDao.getAllFTRRequest(Mockito.any())).thenReturn(ftrs);
		Assert.assertEquals(ftrService.getAllFTRRequest(logicalLoc), ftrs);
				
	}
	
	@Test
	public void getFTRRequestDtlTest() throws Exception{
		
		String ftrId = "21";
		when(ftrDao.findFtrDtl(Mockito.any())).thenReturn(ftr);
		//String ob = ftrService.getFTRRequestDTL(ftrId).toString();
		
		//FTR respRecievedObj = mapper.readValue(ob, FTR.class);	
		Assert.assertEquals(ftrService.getFTRRequestDTL(ftrId),ftr);
				
	}
	@Test
	public void decisionOnFTRRequestTest() throws Exception{
		Integer result=1;
		when(ftrDao.decisionOnFTRRequest(ftr)).thenReturn(1);
		Assert.assertEquals(ftrService.decisionOnFTRRequest(ftr),result);
				
	}	
	@Test
	public void updateFTRRequestTest() throws Exception{
		Integer result=0;  
		when(ftrDao.updateFtrhdr(ftr)).thenReturn(new Integer(0));
		Assert.assertEquals(ftrService.updateFTRRequest(ftr),result);
				
	}	
//	@Test
//	public void deleteFTRRequestTest() throws Exception{
//		Integer ftrNo = 21;
//		Integer result=1;
//		when(ftrDao.deteleFtrReq(ftrNo)).thenReturn(1);
//		Assert.assertEquals(ftrService.deleteFTRRequest(ftrNo),result);
//		
//		ftr.setFtrReqStatus("A");
//		FTR ftr =ftrDao.findFtrDtl(ftrNo.toString());
//		if(ftr.getFtrReqStatus().charAt(0) != 'P') {
//			result= -2;
//		}
//		when(ftrDao.deteleFtrReq(ftrNo)).thenReturn(-2);
//		Assert.assertEquals(ftrService.deleteFTRRequest(ftrNo),result);
//				
//	}	
//	
	@Test
	public void decisionOnMultipleFTRRequestTest() throws Exception{
		List<FTR> list = new ArrayList<>();
		list.add(ftr);
		Integer result=1;
		when(ftrDao.decisionOnFTRRequest(Mockito.any())).thenReturn(1);
		Assert.assertEquals(ftrService.decisionOnMultipleFTRRequest(list),result);
		
	}	
	@Test
	public void getAllApprovedFTRRequestTest() throws Exception{
		List<FTR> list = new ArrayList<>();
		list.add(ftr);
		when(ftrDao.getAllApprovedFTRRequest()).thenReturn(list);
		Assert.assertEquals(ftrService.getAllApprovedFTRRequest(),list);
		
	}	
//	@Test
//	public void generateExcelForFTRRequestTest() throws Exception{
//		String[] cds = {"21"};
//		when(ftrDao.findFtrDtl(Mockito.any())).thenReturn(ftr);
//		when(ftrDao.decisionOnFTRRequest(Mockito.any())).thenReturn(1);
//		when(FtrReportedToBankExcel.generateSheet(Mockito.any(),Mockito.any())).thenReturn(1);
//		Assert.assertEquals(ftrService.generateExcelForFTRRequest(cds,"BOB"),new Integer(1));
//		
//	}	
	@Test
	public void changeStatusToTransferTest() throws Exception{
		when(ftrDao.changeStatusToTransfer(Mockito.any())).thenReturn(1);
		Assert.assertEquals(ftrService.changeStatusToTransfer(ftr),new Integer(1));
		
	}	
	
	
	

}
