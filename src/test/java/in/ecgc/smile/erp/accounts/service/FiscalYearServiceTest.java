package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.model.FiscalYearModel;
import in.ecgc.smile.erp.accounts.repository.FiscalYearDaoImpl;

public class FiscalYearServiceTest {

	@Mock
	FiscalYearModel fiscalYear;
	
	@Mock
	FiscalYearDaoImpl dao;
	
	@InjectMocks
	FiscalYearServiceImpl service;
	
	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeTest
	void initFiscalYear() {
		fiscalYear = new FiscalYearModel();
		fiscalYear.setCurrFiscalYear("2020-21");
		fiscalYear.setCurrFiscalYearStartDt(LocalDate.of(2020,01,03));
		fiscalYear.setPrevFiscalYear("2019-20");
		fiscalYear.setPrevFiscalYearClosedDt(LocalDate.of(2020,01,03));
		fiscalYear.setMetaStatus('V');		
	}
	
	@Test
	public void findCurrentFiscalYearTest() {
		when(dao.findCurrentFiscalYear()).thenReturn(fiscalYear);
		Assert.assertEquals(service.findCurrentFiscalYear(),fiscalYear);
		
	}
	@Test
	public void getFiscalYearListTest() {
		List<String> list = new ArrayList<String>();
		list.add("2019-20");
		list.add("2020-21");
		when(dao.getFiscalYearList()).thenReturn(list);
		Assert.assertEquals(service.getFiscalYearList(),list);
		
	}
	
	
	
}
