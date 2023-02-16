package com.dongi.boards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongi.boards.dto.CmmntDTO;
import com.dongi.boards.service.CmmntService;

@RestController
@RequestMapping("/brd")
public class CmmntController {
	
	@Autowired
	private CmmntService cmmntService;
	
	// 댓글 등록
	@PostMapping("/brd/{brdNm}/insrtCmmnt")
	public ResponseEntity<CmmntDTO> insrtCmmnt(@PathVariable int brdNm, 
			                                   @RequestBody CmmntDTO cmmntDTO) {
		CmmntDTO svdCmmntDTO = cmmntService.insrtCmmnt(brdNm, cmmntDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(svdCmmntDTO);
	}
	
	// 댓글 삭제
	@PostMapping("/brd/dltCmmnt")
	public ResponseEntity<CmmntDTO> dltCmmnt(@RequestBody CmmntDTO cmmntDTO) {
		
		CmmntDTO dltdCmmntDTO = cmmntService.dltCmmnt(cmmntDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(dltdCmmntDTO);
	}
	
	// 댓글 수정
	@PostMapping("/brd/edtCmmnt")
	public ResponseEntity<CmmntDTO> edtCmmnt(@RequestBody CmmntDTO cmmntDTO) {
		
		CmmntDTO edtdCmmntDTO = cmmntService.edtCmmnt(cmmntDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(edtdCmmntDTO);
	}
	
}
