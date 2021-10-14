package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
	
	@Mock
	List<LOVMaster> lovMasterList;
	
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(lovMasterServiceImpl).build();
	}
	
	@BeforeTest
	private void initLovMaster() {
		lovMaster  = new LOVMaster();
		lovMaster.setLov_cd("PL");
		lovMaster.setLov_desc("previledge Leave");
		lovMaster.setLov_sub_cd("pl");
		lovMaster.setLov_value("PL");
		
		lovMasterList = new ArrayList<>();
		lovMasterList.add(lovMaster);
	}
	
	@Test 
	public void listAllLovMasterServiceImplTest() {
		
		when(lovMasterDao.viewallLOVMstEntries()).thenReturn(lovMasterList);
		Assert.assertEquals(lovMasterServiceImpl.viewallLOVMstEntries(), lovMasterList);
	}
	
	@Test
	public void findLovByLovcd() {
		when(lovMasterDao.getLovByLovcd("PL")).thenReturn(lovMasterList);
		Assert.assertEquals(lovMasterServiceImpl.findLovByLovcd("PL"), lovMasterList);
	}
	
	@Test
	public void addLovMasterServiceImplTest() {
		when(lovMasterDao.addLOVMstEntry(lovMaster)).thenReturn(1);
		Assert.assertEquals(lovMasterServiceImpl.addLOVMstEntry(lovMaster), true);
		
		when(lovMasterDao.addLOVMstEntry(lovMaster)).thenReturn(2);
		Assert.assertEquals(lovMasterServiceImpl.addLOVMstEntry(lovMaster), false);
	}
	
}
