package in.ecgc.smile.erp.accounts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.smile.erp.accounts.exception.GLCodeIncompleteDataException;
import in.ecgc.smile.erp.accounts.exception.GLCodeNotFoundException;
import in.ecgc.smile.erp.accounts.model.EntityGL;
import in.ecgc.smile.erp.accounts.service.EntityGLMasterService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;

public class GlMasterServiceControllerTest {

	private ObjectMapper mapper;

	private MockMvc mockMvc;

	String entityGLStr;

	@Mock
	EntityGLMasterService glService;

	@Mock
	EntityGL entityGL;

	@Mock
	List<EntityGL> entityGLList;

	@Mock
	List<EntityGL> emptyGLList;

	@InjectMocks
	EntityGlMasterController glMasterServiceController;

	@Mock
	ResponseModel<Integer> responseModel = new ResponseModel<Integer>();

	@Mock
	ResponseModel<List<EntityGL>> responseModelList = new ResponseModel<List<EntityGL>>();

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(glMasterServiceController).build();
		mapper = new ObjectMapper();
	}

	@BeforeTest
	public void initEntityGl() {

		entityGL = new EntityGL();

		entityGL.setEntityGlCd(1000);
		entityGL.setActive('Y');
		entityGL.setBalInd("na");
		entityGL.setCashaccount(982348123);
		entityGL.setGlIsGroup('N');
		entityGL.setGlName("BANK");
		entityGL.setMainglCd("1700");
		entityGL.setOldCd("1001");
		entityGL.setSubglCd("003");
		entityGL.setGlSubtype("002");
		entityGL.setGlType("Asset");
		entityGL.setGlName("PV");
		

		entityGLList = new ArrayList<EntityGL>();
		entityGLList.add(entityGL);

		emptyGLList = new ArrayList<EntityGL>();
	}

	@BeforeTest
	public void objToStr() throws JsonProcessingException {
		entityGLStr = mapper.writeValueAsString(entityGL);
	}

	@Test
	public void addGLCodeTest() throws Exception {

		when(glService.addGLCode(entityGL)).thenReturn(1);
		mockMvc.perform(post("/gl-master/add").content(entityGLStr).contentType(MediaType.APPLICATION_STREAM_JSON)
				.param("mainglCd", "3200")
				.param("subglCd", "002")
				.param("glName", "PV")
				.param("glType", "Asset")
				.param("glSubtype", "PV"))
		
				.andDo(print()).andReturn();

		when(glService.addGLCode(Mockito.any())).thenReturn(1);
		mockMvc.perform(post("/gl-master/add").content(entityGLStr).contentType(MediaType.APPLICATION_STREAM_JSON)
				.param("mainglCd", "3200")
				.param("subglCd", "002")
				.param("glName", "PV")
				.param("glType", "Asset")
				.param("glSubtype", "PV"))
				.andDo(print()).andExpect(status().isCreated()).andReturn();

		when(glService.addGLCode(Mockito.any())).thenThrow(GLCodeIncompleteDataException.class);
		mockMvc.perform(post("/gl-master/add").content(entityGLStr).contentType(MediaType.APPLICATION_STREAM_JSON)
				.param("mainglCd", "3200")
				.param("subglCd", "002")
				.param("glName", "PV")
				.param("glType", "Asset")
				.param("glSubtype", "PV"))
				.andDo(print()).andReturn();
	}

	@Test
	public void updateGLCodeTest() throws Exception {

		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenReturn(entityGL);
		when(glService.updateGLCode(Mockito.any(), Mockito.any())).thenReturn(null);

		mockMvc.perform(put("/gl-master/update/{mainGLCode}/{subGLCode}", "1700","001").content(entityGLStr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();

		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenReturn(entityGL);
		when(glService.updateGLCode(entityGL, new EntityGL())).thenReturn(new EntityGL());

		mockMvc.perform(get("/gl-master/update/{mainGLCode}/{subGLCode}", "1700","001").content(entityGLStr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();

		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenThrow(GLCodeNotFoundException.class);
		mockMvc.perform(put("/gl-master/update/{mainGLCode}/{subGLCode}", "1700","001").content(entityGLStr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();

	}

	@Test
	public void listAllGLCodesTest() throws Exception {
		when(glService.listAllGLCodes()).thenReturn(emptyGLList);
		mockMvc.perform(get("/gl-master/view-all").content(entityGLStr).contentType(MediaType.APPLICATION_STREAM_JSON))
				.andDo(print()).andReturn();

		when(glService.listAllGLCodes()).thenReturn(entityGLList);
		mockMvc.perform(get("/gl-master/view-all").content(entityGLStr).contentType(MediaType.APPLICATION_STREAM_JSON))
				.andDo(print()).andReturn();
	}

	@Test
	public void disableGLCodeTest() throws Exception {
		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenReturn(entityGL);
		when(glService.disableGLCode(Mockito.any())).thenReturn(1);
		mockMvc.perform(delete("/gl-master/delete/{mainGLCode}/{subGLCode}", "1700","001").content(entityGLStr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();

		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenReturn(entityGL);
		when(glService.disableGLCode(Mockito.any())).thenReturn(0);
		mockMvc.perform(delete("/gl-master/delete/{mainGLCode}/{subGLCode}", "1700","001").content(entityGLStr)
				.contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();

		/*
		 * when(glService.findGLByGLCode(Mockito.any(),
		 * Mockito.any())).thenThrow(GLCodeNotFoundException.class);
		 * mockMvc.perform(delete("/gl-master/delete/{mainGLCode}/{subGLCode}",
		 * "1700","001").content(entityGLStr)
		 * .contentType(MediaType.APPLICATION_STREAM_JSON)).andDo(print()).andReturn();
		 */
	}

	@Test
	public void findGLByEntityGLCodeTest() throws Exception {

		when(glService.findGLByGLCode(Mockito.any(), Mockito.any())).thenReturn(entityGL);
		mockMvc.perform(get("/gl-master/view/{mainGLCode}/{subGLCode}", "1700","001").contentType(MediaType.APPLICATION_STREAM_JSON))
				.andDo(print()).andReturn();
		/*
		 * when(glService.findGLByGLCode(Mockito.any(),
		 * Mockito.any())).thenThrow(GLCodeNotFoundException.class);
		 * mockMvc.perform(get("/gl-master/view/{mainGLCode}/{subGLCode}",
		 * "1700","001").content(entityGLStr)
		 * .contentType(MediaType.APPLICATION_STREAM_JSON)).andExpect(status().
		 * isBadRequest()).andDo(print()).andReturn();
		 */
	}

}
