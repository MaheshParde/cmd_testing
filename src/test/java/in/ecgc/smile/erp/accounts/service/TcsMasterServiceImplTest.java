package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.repository.TcsMasterDao;


public class TcsMasterServiceImplTest {
	
	private MockMvc mockMvc;
	@Mock
	TcsMasterDao tcsMasterDao;
	
	@Mock
	private TcsMaster tcsMaster;
	
	@InjectMocks
	TcsMasterServiceImpl tcsMasterServiceImpl;
	
	@Mock
	List<TcsMaster> tcsMasterList;
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tcsMasterServiceImpl).build();
	}
	
	@BeforeTest
	public void initTcsMaster() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			 fromDate = sdf.parse("2019-04-01");
			 toDate = sdf.parse("2019-04-01");
		} catch (Exception e) {
			System.out.println(e);
		}
		tcsMaster = new TcsMaster();
		tcsMaster.setAmount(1000.00);
		tcsMaster.setFinYear("2020-2021");
		tcsMaster.setFromAmount(500.0);
		tcsMaster.setFromDate(fromDate);
		tcsMaster.setLimitEcgc(1000.00);
		tcsMaster.setSex("M");
		tcsMaster.setSurchargePer(11.0f);
		tcsMaster.setSurchargeAmount(4000.00);
		tcsMaster.setToDate(toDate);
		tcsMaster.setToAmount(5000.0);
		tcsMaster.setTaxPercent(10f);
		
		tcsMasterList = new ArrayList<>();
		tcsMasterList.add(tcsMaster);
	}
	
	@Test 
	public void addTcsMasterServiceImplTest () {	
		
		when(tcsMasterDao.addTcsMaster(tcsMaster)).thenReturn(1);
		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster), new Boolean(true));
		
		when(tcsMasterDao.addTcsMaster(tcsMaster)).thenReturn(0);
		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster), new Boolean(false));
		
//		tcsMaster.setToAmount(5000.00);
//		tcsMaster.setFromAmount(5000.00);
//		when(tcsMasterDao.addTcsMaster(tcsMaster)).thenReturn(0);
//		when(tcsMasterDao.checkExistingData("2020-2021", tcsMaster.getFromAmount(),tcsMaster.getToAmount() ,
//				"M", tcsMaster.getFromDate(), tcsMaster.getToDate() )).thenReturn(tcsMaster); 
//		when(tcsMaster.getFromAmount() >= tcsMaster.getToAmount() ).thenReturn(true);
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster),new Boolean(true));

		
		
//		when(tcsMasterDao.addTcsMaster(tcsMaster)).thenReturn(1);
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster),new Boolean(true));
//
//		when(tcsMasterDao.addTcsMaster(tcsMaster)).thenReturn(0);
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster), new Boolean(false));
//		
//		when(tcsMasterDao.checkExistingData("2020-2021", 2000.00,5000.0 ,
//				"M", tcsMaster.getFromDate(), tcsMaster.getToDate() )).thenReturn(tcsMaster); 
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster),new Boolean(false));
//
//		
//		when(tcsMaster.getFromDate().after(tcsMaster.getToDate()) ).thenReturn(true);
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster),new Boolean(true));
//		
//		when(tcsMaster.getFromAmount() >= tcsMaster.getToAmount() ).thenReturn(true);
//		Assert.assertEquals(tcsMasterServiceImpl.addTcsMaster(tcsMaster),new Boolean(true));
//	
	}	
	
	@Test
	public void listAllTcsMsaterServiceImplTest() {
	
		when(tcsMasterDao.listAllTcs()).thenReturn(tcsMasterList);
		Assert.assertEquals(tcsMasterServiceImpl.listAllTcs(), tcsMasterList);
	}
	
}


