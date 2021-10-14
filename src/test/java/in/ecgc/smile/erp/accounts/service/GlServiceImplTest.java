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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.repository.EntityGLMasterDao;
import in.ecgc.smile.erp.accounts.util.ResponseModel;

public class GlServiceImplTest {

	private ObjectMapper mapper;
	
	private MockMvc  mockMvc;
	
	String entityGLStr;
	
	@Mock
	EntityGLMasterService glService;
	
	@Mock
	EntityGL entityGL;
	
	@Mock
	EntityGL entityGL2;

	@Mock
	List<EntityGL> entityGLList;
	
	@Mock
	List<EntityGL> emptyGLList;
	
	@InjectMocks
	EntityGLMasterServiceImpl glServiceImpl;
	
	@Mock
	EntityGLMasterDao glDao;
	
	@Mock
	ResponseModel<Integer> responseModel = new ResponseModel<Integer>();
	
	@Mock
	ResponseModel<List<EntityGL>> responseModelList = new ResponseModel<List<EntityGL>>();
	
	@BeforeTest
    public void init() {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(glServiceImpl).build();
        mapper = new ObjectMapper();
    }
	
	/*
	 * @BeforeTest public void initEntityGl() { entityGL = new EntityGL();
	 * 
	 * entityGL.setEntityGlCd(100101); entityGL.setActive(true);
	 * entityGL.setBalInd("na"); entityGL.setCashaccount(982348123);
	 * entityGL.setClosureCmts("na"); entityGL.setClosureDt(new Date());
	 * entityGL.setEcgcStatus('y'); entityGL.setGlIsGroup(false);
	 * entityGL.setGlName("BANK"); entityGL.setMainglCd(100);
	 * entityGL.setOldCd("1001"); entityGL.setSubglCd1(1); entityGL.setSubglCd2(2);
	 * entityGL.setSubglCd3(3); entityGL.setSubglCd4(4);
	 * entityGL.setGlSubtype("sbi"); entityGL.setCreatedBy(101);
	 * entityGL.setCreatedOn(new Date()); entityGL.setMetaRemarks("n");
	 * entityGL.setScheduleCd("c"); entityGL.setScheduleItemCd("C");
	 * 
	 * entityGLList = new ArrayList<EntityGL>(); entityGLList.add(entityGL);
	 * 
	 * emptyGLList = new ArrayList<EntityGL>();
	 * 
	 * entityGL2 = new EntityGL(); entityGL2.setGlIsGroup(true);
	 * 
	 * }
	 * 
	 * @BeforeTest public void objToStr() throws JsonProcessingException {
	 * entityGLStr = mapper.writeValueAsString(entityGL); }
	 * 
	 * @Test public void addGLCodeTest() throws Exception {
	 * 
	 * when(glDao.addGLCode(entityGL2)).thenReturn(0);
	 * Assert.assertEquals(glServiceImpl.addGLCode(entityGL2), new Integer(0)); }
	 * 
	 * @Test public void listAllGLCodesTest() throws Exception {
	 * when(glDao.listAllGLCodes()).thenReturn(entityGLList);
	 * Assert.assertEquals(glServiceImpl.listAllGLCodes(), entityGLList); }
	 * 
	 * @Test public void findGLByEntityGLCodeTest() throws Exception {
	 * when(glDao.findGLByEntityGLCode(100101)).thenReturn(entityGL);
	 * Assert.assertEquals(glServiceImpl.findGLByEntityGLCode(100101), entityGL); }
	 * 
	 * @Test public void updateGLCode() throws Exception {
	 * when(glDao.updateGLCode(entityGL2)).thenReturn(entityGL);
	 * Assert.assertEquals(glServiceImpl.updateGLCode(entityGL2, entityGL),
	 * entityGL); }
	 * 
	 * @Test public void disableGLCodeTest() throws Exception {
	 * when(glDao.disableGLCode(entityGL)).thenReturn(1);
	 * Assert.assertEquals(glServiceImpl.disableGLCode(entityGL), new Integer(1)); }
	 */
}
