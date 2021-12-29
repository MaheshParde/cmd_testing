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

import in.ecgc.smile.erp.accounts.exception.BankBranchNotFoundException;
import in.ecgc.smile.erp.accounts.exception.SubBifurcationValueNotFoundException;
import in.ecgc.smile.erp.accounts.model.BankBranch;
import in.ecgc.smile.erp.accounts.model.SubBifurcationValue;
import in.ecgc.smile.erp.accounts.repository.BankBranchDao;
import in.ecgc.smile.erp.accounts.repository.SubBifurcationValueDao;

public class SubBifurcationValueServiceImplTests {

private MockMvc mockMvc;
	
	@Mock
	SubBifurcationValueDao valueDao;
	
	@Mock
	private SubBifurcationValue subBifurcationValue;
	
	@InjectMocks
	SubBifurcationValueServiceImpl subBifurcationValueServiceImpl;
	
	@BeforeTest
	private void init() {
		MockitoAnnotations.initMocks(this);;
		mockMvc = MockMvcBuilders.standaloneSetup(subBifurcationValueServiceImpl).build();
		
	}
	@BeforeTest
	public void initBankBranch()
	{
		subBifurcationValue = new SubBifurcationValue();
		subBifurcationValue.setBifurcationLevelCode("AER");
		subBifurcationValue.setBifurcationValue("test");
		subBifurcationValue.setBifurcationValueCode("AER001");
		subBifurcationValue.setSubBifurcationLevel("Freehold - Building & Residential Property (ASST)");
		subBifurcationValue.setCreatedBy("101");
		subBifurcationValue.setLastUpdatedBy("101");
		subBifurcationValue.setMetaRemarks("V");
		subBifurcationValue.setMetaStatus("V");
		subBifurcationValue.setActive('Y');
		
	}

	@Test
	public void addSubBifurcationValue() {
		when(valueDao.addSubBifurcationsDtlData(subBifurcationValue)).thenReturn(true);
		Assert.assertEquals(subBifurcationValueServiceImpl.addSubBifurcationsDtlData(subBifurcationValue),true);
		when(valueDao.addSubBifurcationsDtlData(subBifurcationValue)).thenReturn(false);
		Assert.assertEquals(subBifurcationValueServiceImpl.addSubBifurcationsDtlData(subBifurcationValue),false);
		}
	

	@Test
	public void getSubBifurcationsDtlDataByIdTest() {
		when(valueDao.getSubBifurcationsDtlDataById("AER", "AER001")).thenReturn(subBifurcationValue);
		Assert.assertEquals(subBifurcationValueServiceImpl.getSubBifurcationsDtlDataById("AER", "AER001"), subBifurcationValue);	
	}
	
	@Test
	public void getBifurcationValueCode() {
		String valueCode =""; 
		when(valueDao.getBifurcationCode("AER")).thenReturn(valueCode);
		Assert.assertEquals(subBifurcationValueServiceImpl.getBifurcationCode("AER"),valueCode );
		
	}
	
	@Test
	public void getSubBifurcationsDtlListTest() {
		List<SubBifurcationValue> valueList = new ArrayList<>();
		valueList.add(subBifurcationValue);
		try {
			when(valueDao.getSubBifurcationsDtlList()).thenReturn(valueList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(subBifurcationValueServiceImpl.getSubBifurcationsDtlList(), valueList);
	}
	
//	@Test
//	public void updateSubBifurcationsDtlDataTest() {
//		when(valueDao.updateSubBifurcationsDtlData("AER", "AER001", subBifurcationValue)).thenReturn(true);
//		when(valueDao.getSubBifurcationsDtlDataById("AER", "AER001")). thenReturn(subBifurcationValue);
//		Assert.assertEquals(subBifurcationValueServiceImpl.updateSubBifurcationsDtlData("AER", "AER001", subBifurcationValue), true);
//		when(valueDao.updateSubBifurcationsDtlData("AER", "AER001", subBifurcationValue)).thenReturn(false);
//		when(valueDao.getSubBifurcationsDtlDataById("AER", "AER001")). thenReturn(subBifurcationValue);
//		Assert.assertEquals(subBifurcationValueServiceImpl.updateSubBifurcationsDtlData("AER", "AER001", subBifurcationValue), false);
//		
//	}
//	
	@Test
	public void disableSubBifurcationValueTest() {
		when(valueDao.disableSubBifurcationValue("AER", "AER001")).thenReturn(true);
		Assert.assertEquals(subBifurcationValueServiceImpl.disableSubBifurcationValue("AER","AER001"),true);
		
	}
	
}
