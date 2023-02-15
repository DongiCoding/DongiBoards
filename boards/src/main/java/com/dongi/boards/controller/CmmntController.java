package com.dongi.boards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@DeleteMapping("/brd/dltCmmnt")
	public void dltCmmnt(@RequestParam("cmmntId") String cmmntId) {
		
		cmmntService.dltCmmnt(Integer.parseInt(cmmntId));
	}
	
}
