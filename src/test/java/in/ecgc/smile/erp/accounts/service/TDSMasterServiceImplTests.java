package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.exception.DataAlreadyExist;
import in.ecgc.smile.erp.accounts.model.TDSMaster;
import in.ecgc.smile.erp.accounts.model.TcsMaster;
import in.ecgc.smile.erp.accounts.repository.TDSMasterDao;
import in.ecgc.smile.erp.accounts.repository.TcsMasterDao;

public class TDSMasterServiceImplTests {

	private MockMvc mockMvc;
	
	@Mock
	private TDSMaster tdsMaster;
	@Mock
	TDSMasterDao tdsMasterDao;
	@InjectMocks
	TDSMasterServiceImpl tdsMasterServiceImpl;
	@Mock
	List<TDSMaster> tdsMasters;
	
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc =MockMvcBuilders.standaloneSetup(tdsMasterServiceImpl).build();
	}
	@BeforeTest
	private void initTcsMaster() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			 fromDate = sdf.parse("2018-04-01");
			 toDate = sdf.parse("2019-04-01");
		} catch (Exception e) {
			System.out.println(e);
		}
		tdsMaster = new TDSMaster();
		tdsMaster.setAmount(1000.00);
		tdsMaster.setFiscalYr("2020-2021");
		tdsMaster.setFromAmount(2000.00);
		tdsMaster.setFromDt(fromDate);
		tdsMaster.setLimitEcgc(1000.00);
		tdsMaster.setSex('M');
		tdsMaster.setSurchargePer(11.0f);
		tdsMaster.setSurchargeAmt(4000.00);
		tdsMaster.setToDt(toDate);
		tdsMaster.setToAmount(5000.0);
		tdsMaster.setTaxPer(10f);
		
	}	
	@Test
	public void viewAllTds() {
		when(tdsMasterDao.viewAllTds()).thenReturn(tdsMasters);
		Assert.assertEquals(tdsMasterServiceImpl.viewAllTds(), tdsMasters);		
	}
	
	@Test
	public void find() {
		when(tdsMasterDao.checkExistingTdsDetail(tdsMaster.getFromAmount(), tdsMaster.getToAmount(),
				tdsMaster.getSex(), tdsMaster.getFiscalYr())).thenReturn(tdsMaster);
		Assert.assertEquals(tdsMasterServiceImpl.find(tdsMaster.getFromAmount(), tdsMaster.getToAmount(),
				tdsMaster.getSex(), tdsMaster.getFiscalYr()), tdsMaster);
	}
	
	@Test
	public void addTdsDetails() {
		when(tdsMasterDao.addTdsDetails(tdsMaster)).thenReturn(1);
		Assert.assertEquals(tdsMasterServiceImpl.addTdsDetails(tdsMaster), new Boolean(true));
		
		when(tdsMasterDao.addTdsDetails(tdsMaster)).thenReturn(0);
		Assert.assertEquals(tdsMasterServiceImpl.addTdsDetails(tdsMaster), new Boolean(false));
		
		
	}
}
