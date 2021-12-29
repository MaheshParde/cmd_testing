package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.LOVMaster;
import in.ecgc.smile.erp.accounts.repository.LOVMasterDao;


public class LovMasterServiceImplTests {

	private MockMvc mockMvc;
	@Mock
	LOVMasterDao lovMasterDao;
	@Mock
	private LOVMaster lovMaster;
	@InjectMocks
	LOVMasterServiceImpl lovMasterServiceImpl;
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup().build();
	}
	
	@BeforeTest
	private void initLovMaster() {
		lovMaster  = new LOVMaster();
		lovMaster.setLovCd("ACCT");
		lovMaster.setLovDesc("Income");
		lovMaster.setLovSubCd("ACCOUNTS");
		lovMaster.setLovValue("INCM");
		
	}
	
	@Test 
	public void listAllLovMasterServiceImplTest() {
		List<LOVMaster> lovMasterList = new ArrayList<>();
		lovMasterList.add(lovMaster);
		when(lovMasterDao.viewallLOVMstEntries()).thenReturn(lovMasterList);
		Assert.assertEquals(lovMasterServiceImpl.viewallLOVMstEntries(), lovMasterList);
	}
	@Test
	public void addLovMasterServiceImplTest() {
		when(lovMasterDao.addLovMstEntry(lovMaster)).thenReturn(1);
		Assert.assertEquals(lovMasterServiceImpl.addLovMstEntry(lovMaster), 1);
	}
	
	@Test
	public void t1CodesTest() {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("Income", "INCM");
		when(lovMasterDao.getT1codes()).thenReturn(mapParam);
		Assert.assertEquals(lovMasterServiceImpl.t1Codes(),mapParam );
	}
	
	@Test
	public void t2CodesTest() {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("Income", "INCM");
		when(lovMasterDao.getT2codes()).thenReturn(mapParam);
		Assert.assertEquals(lovMasterServiceImpl.t2Codes(), mapParam);
	}
	
	@Test
	public void viewLovEntryTest() {
		when(lovMasterDao.viewLovEntry("ACCT","Accounts","ASST")).thenReturn(lovMaster);
		Assert.assertEquals(lovMasterServiceImpl.viewLovEntry("ACCT","Accounts","ASST"), lovMaster);
	}
	@Test
	public void modifyLovEntryTest() {
		when(lovMasterDao.modifyLovEntry(lovMaster)).thenReturn(1);
		Assert.assertEquals(lovMasterServiceImpl.modifyLovEntry(lovMaster), 1);
	}
	
	/*
	 * @Test public void disableLovEntryTest() {
	 * when(lovMasterDao.disableLovEntry("ACCT","ACCOUNTS","ASST")).thenReturn(null)
	 * ; Assert.assertEquals(lovMasterServiceImpl.disableLovEntry("ACCT","ACCOUNTS",
	 * "ASST"), null ); }
	 */
	
	@Test
	public void getRefNoTest() {
		when(lovMasterDao.getRefNo()).thenReturn(12345);
		Assert.assertEquals(lovMasterServiceImpl.getRefNo(), new Integer(12345));
	}
	
	@Test
	public void setPaymentAdviceNoTest() {
		when(lovMasterDao.setPaymentAdviceNo(12345,202100005)).thenReturn(1);
		Assert.assertEquals(lovMasterServiceImpl.setPaymentAdviceNo(12345,202100005), new Integer(1));
	}
	
	
}
