package in.ecgc.smile.erp.accounts.service;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.controller.StampServiceController;
import in.ecgc.smile.erp.accounts.exception.StampIncompleteDataException;
import in.ecgc.smile.erp.accounts.model.StampParameterModel;
import in.ecgc.smile.erp.accounts.repository.StampDao;
import in.ecgc.smile.erp.accounts.repository.StampDaoImpl;

public class StampServiceImplTests {
	private MockMvc mockMvc;

	@Mock
	StampParameterModel stampModel;
	
	@Mock
	StampDao stampDao;
	
	@Mock
	StampServiceImpl stampService;
	
	@InjectMocks
	StampServiceController stampController;
	
	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(stampController).build();
		
	} 
	
	@BeforeTest
	public void initStampParameter() {
		stampModel = new StampParameterModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date effectiveDate = new Date();
		
		try {
			 effectiveDate = sdf.parse("2018-04-01");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		stampModel.setDescription("abc");
		stampModel.setFromAmount(2000.0);
		stampModel.setSrNo(1);
		stampModel.setStampAmount(1000.0);
		stampModel.setToAmount(3000.0);
		stampModel.setEffectiveDate(LocalDate.now());		
	}
	
	@Test
	public void addStampParameter() throws StampIncompleteDataException {
		when(stampDao.addStampParameter(stampModel)).thenReturn(1);
		Assert.assertEquals(stampService.addStampParameter(stampModel), new Integer(0));
	
		
	}
	
}
