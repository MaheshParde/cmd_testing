package in.ecgc.smile.erp.accounts.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecgc.smile.erp.accounts.model.Calendar;
import in.ecgc.smile.erp.accounts.model.DateOperation;
import in.ecgc.smile.erp.accounts.model.GlTxnDtl;
import in.ecgc.smile.erp.accounts.model.GlTxnHdr;
import in.ecgc.smile.erp.accounts.service.CalendarService;
import in.ecgc.smile.erp.accounts.service.GlTxnService;
import in.ecgc.smile.erp.accounts.util.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gl-txn")
@Api(value = "GL Transaction service")
@Slf4j
public class GlTxnController {

	@Autowired
	GlTxnService txnService;
	@Autowired 
	CalendarService calenderService;
	
	@GetMapping("/view-all/{logicalloc}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxn(@PathVariable("logicalloc") String logicalloc){
		log.info("Inside GlTxnController#getAllGltxn");
		return new ResponseEntity<List<GlTxnHdr>>(txnService.getAllGlTxnHdrs(logicalloc),HttpStatus.OK);
	}
	
	@GetMapping("/view-all/{logicalloc}/{gltxntype}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxnByTxnType(
								@PathVariable("logicalloc") String logicalloc,
								@PathVariable("gltxntype") String glTxntype){
		
		log.info("Inside GlTxnController#getAllGltxnByTxnType");
		return new ResponseEntity<List<GlTxnHdr>>(txnService.getAllGlTxnHdrs(logicalloc,glTxntype),HttpStatus.OK);
	}
	
	
	@GetMapping("/get-txn/{fromDt}/{toDt}")
	@ApiOperation(value = "View all GL codes between dates", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxnByFromDt(
			@PathVariable("fromDt") String fromDt,
			@PathVariable("toDt") String toDt){
		
		log.info("Inside GlTxnController#getAllGltxnByFromDt");
		
		return new ResponseEntity<List<GlTxnHdr>>(txnService.getAllGlTxnByFromDtToDt(LocalDate.parse(fromDt),LocalDate.parse(toDt)),HttpStatus.OK);
	}
	
	
	@GetMapping("/view/{gltxnno}/{logicalloc}/{gltxntype}")
	@ApiOperation(value = "View all GL codes", response = ResponseEntity.class)
	ResponseEntity<GlTxnHdr> getGltxn(
			@PathVariable("gltxnno") Integer glTxnNo,
			@PathVariable("logicalloc") String logicalLoc,
			@PathVariable("gltxntype") String glTxnType){
		
		log.info("Inside GlTxnController#getGltxn");
		
		return new ResponseEntity<GlTxnHdr>(txnService.getGlTxn(glTxnNo,logicalLoc,glTxnType),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Create GL Txn", response = ResponseEntity.class)
	public ResponseEntity<Integer> addGLTxn( @RequestBody GlTxnHdr glTxnHdr) {
		log.info("Inside GlTxnController#addGLTxn");
		return new ResponseEntity<>(txnService.addGlTxn(glTxnHdr),HttpStatus.CREATED);
	}
	
	//@ExceptionHandler(value = CalendarRecordNotFoundException.class)	
	/*	@PostMapping("/add")
		@ApiOperation(value = "Create GL Txn", response = ResponseEntity.class)
		public ResponseEntity<Integer> addGLTxn( @RequestBody GlTxnHdr glTxnHdr){
			DateOperation dt = new DateOperation(glTxnHdr.getTxnDt().getMonthValue());
			log.info("Inside GlTxnController#addGLTxn");

			Calendar calendar =  new Calendar();
			 calendar = calenderService.getByGlTypeLogicalLoc(glTxnHdr.getFiscalYr(), dt.currentMonth, glTxnHdr.getLogicalLocCd(),glTxnHdr.getGlTxnType());
			
			
			 if(calendar != null &&(calendar.getClosedStatus().equals('N') || calendar.getClosedStatus().equals('n'))) 
			 {
				return new ResponseEntity<>(txnService.addGlTxn(glTxnHdr),HttpStatus.CREATED);
			}else
				throw new CalendarRecordNotFoundException();
		//return new ResponseEntity<>(0,HttpStatus.NOT_FOUND);
		}
	
	*/
	
	
	@PostMapping("/update")
	@ApiOperation(value = "Update GL Txn", response = ResponseModel.class)
	public ResponseEntity<Integer> updateGLTxnDtl(@RequestBody GlTxnDtl glTxnDtl) {
		
		log.info("Inside GlTxnController#updateGLTxnDtl");
		
		return new ResponseEntity<>(txnService.updateGlTxnDtl(glTxnDtl),HttpStatus.CREATED);
	}
	
	@PostMapping("/reverse")
	@ApiOperation(value = "GL Txn Reversal", response = ResponseModel.class)
	public ResponseEntity<Integer> reverseTxn(@RequestBody GlTxnHdr glTxnhdr) {
		
		log.info("Inside GlTxnController#reverseTxn");
		
		return new ResponseEntity<>(txnService.reverseTransaction(glTxnhdr),HttpStatus.CREATED);
	}

	
	@GetMapping("/getbalance/{mainGlCd}/{subGlCd}")
	public ResponseEntity<BigDecimal> getbalance(
		@PathVariable("mainGlCd") String mainGlCd,
		@PathVariable("subGlCd") String subGlCd) {
		
		log.info("Inside GlTxnController#getbalance");
		
		return new ResponseEntity<>(txnService.getTotalAmt(mainGlCd, subGlCd), HttpStatus.OK);
		
	}

	@GetMapping("/getcrbalance/{mainGlCd}/{subGlCd}")
	public ResponseEntity<BigDecimal> getCrbalance(
		@PathVariable("mainGlCd") String mainGlCd,
		@PathVariable("subGlCd") String subGlCd) {
		
		log.info("Inside GlTxnController#getCrbalance");
		
		return new ResponseEntity<>(txnService.getTotalCreditAmt(mainGlCd, subGlCd), HttpStatus.OK);
	}
	
	@GetMapping("/get-txn/{fromDt}/{toDt}/{logicallocation}")
	@ApiOperation(value = "View all GL codes between dates by logicallocation ", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGltxnByFromDtLoc(
			@PathVariable("fromDt") String fromDt,
			@PathVariable("toDt") String toDt,
			@PathVariable("logicallocation") String logicallocation){
		
		log.info("Inside GlTxnController#getAllGltxnByFromDtLoc");
		List<GlTxnHdr> txnlist = txnService.getAllGltxnByFromDtLoc(LocalDate.parse(fromDt),LocalDate.parse(toDt),logicallocation);
		log.info("getAllGltxnByFromDtLoc ------------- {}",txnlist);
		return new ResponseEntity<List<GlTxnHdr>>(txnlist,HttpStatus.OK);
	}
	
	@GetMapping("/viewby/{glTxnNo}/{glTxnType}/{logicalLoc}")
	@ApiOperation(value = "View all GL codes by TxnNo,TxnType,Location", response = ResponseEntity.class)
	ResponseEntity<List<GlTxnHdr>> getAllGlTxnByTxnNoTxnTypeLoc(
			@PathVariable("glTxnNo") Integer glTxnNo,
			@PathVariable("glTxnType") String glTxnType,
			@PathVariable("logicalLoc") String logicalLoc)
			{
		
		log.info("Inside GlTxnController#getAllGlTxnByTxnNoTxnTypeLoc");
		
		return new ResponseEntity<>(txnService.getAllGlTxnByTxnNoTxnTypeLoc(glTxnNo, glTxnType, logicalLoc),HttpStatus.OK);
				
		
	} 
	
}
