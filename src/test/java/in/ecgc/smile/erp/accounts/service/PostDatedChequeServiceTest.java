package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

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

import in.ecgc.smile.erp.accounts.model.PostDatedCheque;
import in.ecgc.smile.erp.accounts.repository.PDCDao;

public class PostDatedChequeServiceTest {

	private MockMvc mockMvc;
	
	@Mock
	PDCDao pdcDao;
	
	@Mock
	private PostDatedCheque pdc;
	
	@InjectMocks
	PDCServiceImpl pdcServiceImpl;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(pdcServiceImpl).build();
	}
	
	@BeforeTest
	public void initPostDatedCheque() {
		pdc = new PostDatedCheque();
		
		pdc.setDrawnOn("SBI");
		pdc.setInstrumentAmount(123456.00f);
		pdc.setInstrumentDate(new Date(2020-12-02));
		pdc.setInstrumentNo("123456");
		pdc.setInstrumentStatus('n');
		pdc.setInstrumentType("chq");
		pdc.setLogicalLocCode("PUNE123");
		pdc.setReceivedFromaddr("Pune");
		pdc.setReceivedFromCode("abcd");
		pdc.setReceivedFromName("ANN");
	}
	
	@Test
	public void createPDCEntryTest() {
		when(pdcDao.createPDCEntry(pdc)).thenReturn(1);
		Assert.assertEquals(pdcServiceImpl.createPDCEntry(pdc), new Boolean(true));
	}
	
	
	@Test
	public void listAllPDCTest() {
		List<PostDatedCheque> allPdc = new ArrayList<PostDatedCheque>();
		allPdc.add(pdc);
		when(pdcDao.listAllPDC()).thenReturn(allPdc);
		Assert.assertEquals(pdcServiceImpl.listAllPDC(), allPdc);
	}
	
	@Test
	public void viewByChequeNoTest() {
		when(pdcDao.viewByChequeNo(123456)).thenReturn(pdc);
		Assert.assertEquals(pdcServiceImpl.viewByChequeNo(123456), pdc);
	}
	
	@Test
	public void viewByStatusTest() {
		List<PostDatedCheque> allPdc = new ArrayList<PostDatedCheque>();
		allPdc.add(pdc);
		when(pdcDao.viewByStatus('n')).thenReturn(allPdc);
		Assert.assertEquals(pdcServiceImpl.viewByChequeNo(123456), pdc);
	}
}
