package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.accounts.model.ChqDishonorEntry;
import in.ecgc.smile.erp.accounts.repository.ChqDishonorEntryDao;
import in.ecgc.smile.erp.accounts.service.ChqDishonorEntryService;
public class ChqDishonorEntryServiceImplTest{
	
	
	@InjectMocks
	private ChqDishonorEntryServiceImpl service;
	
	@Mock
	private List<ChqDishonorEntry> list;
	
	@Mock
	private ChqDishonorEntryDao chqDishonorEntryDao;
	
	@Mock
	private ChqDishonorEntry chqDishonorEntry;
	
	@BeforeTest
	public void beforeTest() {	
		MockitoAnnotations.initMocks(this);	MockMvcBuilders.standaloneSetup(service).build();}
	@BeforeTest
	public void initObjects() {
	 chqDishonorEntry = new ChqDishonorEntry();
	//chqDishonorEntry.setDishonorDt(LocalDate.parse("2021-12-23"));
	chqDishonorEntry.setDishonorReason("Test");
	chqDishonorEntry.setFiscalYr("2021-22");
	chqDishonorEntry.setGlTxnNo("2021000002");
	chqDishonorEntry.setInstrumentNo("123456789");
	chqDishonorEntry.setInstrumentType("DD");
	chqDishonorEntry.setLogicalLocCd("PUNELOG1");
	chqDishonorEntry.setOldRcptNo(202100002);
	chqDishonorEntry.setRcptNo(202100002);
	}
	
	@Test
	public void addChqDishonorEntryDataTest() throws Exception {
		when(chqDishonorEntryDao.addChqDishonorEntryData(chqDishonorEntry)).thenReturn(true);
		Assert.assertEquals(service.addChqDishonorEntryData(chqDishonorEntry),true);
	}
	@Test
	public void updateChqDishonorEntryDataTest() throws Exception {
		when(chqDishonorEntryDao.updateChqDishonorEntryData(chqDishonorEntry)).thenReturn(true);
		Assert.assertEquals(service.updateChqDishonorEntryData(chqDishonorEntry),true);
	}
	
	@Test
	public void  getChqDishonorEntryListTest() throws Exception {
		when(chqDishonorEntryDao.getChqDishonorEntryList()).thenReturn(list);
		Assert.assertEquals(service.getChqDishonorEntryList(),list);
	}
	
	@Test
	public void  getChqDishonorEntryDataByChequeNoTest() throws Exception {
		when(chqDishonorEntryDao.getChqDishonorEntryByChequeNo("10111567")).thenReturn(chqDishonorEntry);
		Assert.assertEquals(service.getChqDishonorEntryByChequeNo("10111567"),chqDishonorEntry);
	}
}
